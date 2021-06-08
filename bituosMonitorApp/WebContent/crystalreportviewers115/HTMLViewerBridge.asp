<!--
  File Version Start - Do not remove this if you are modifying the file
  Build: 11.0.0
  File Version End

	(c) 2004 Business Objects, Inc.  All rights reserved.
	This code or any derivative work thereof may not be distributed without the express written
	permission of Business Objects.
-->
<%@ Language=JScript codepage=65001 %>
<%
// *********************************************************************
// SERVER-SIDE STRING VARIABLES FOR LOCALIZING
// *********************************************************************
var L_SESSION_EXPIRED = "Unable to retrieve Object.";
var L_INVALID_RPTSRC = "ReportSource invalid.";
var L_INVALID_ARGUMENT ="Invalid query. Need a report id or ReportSource variable."
// *********************************************************************
%>

<%
Session.CodePage = 65001;
Response.ContentType = "text/html; charset=utf-8";
Response.Write("<meta http-equiv=content-type content=\"text/html; charset=utf-8\">");

function PreventCaching()
{
	// prevent browser from caching the page
	var cache_date = new Date();
	cache_date.setFullYear( cache_date.getFullYear() - 1 );
	Response.ExpiresAbsolute = cache_date.getVarDate();
}

function GetSession(name)
{
	return Session.Contents(name);
}

function SetSession(name, value)
{
	Session.Contents(name) = value;
}

function RemoveSession(name)
{
	Session.Contents.Remove(name);
}

function GetApplication(name)
{
	return Application.Value(name);
}

function SetApplication(name, value)
{
	Application.Value(name) = value;
}

function GetQueryString(name)
{
	var s =  Request.QueryString.Item(name);
	if (s.Count > 0) return s;
	return null;
}

function EnsureImageCleanerIsRunning()
{
  if ( GetSession("imageCleanerRunning"))
    return;

  Application.Lock();
  if (!GetApplication("theCrystalImageCleaner") || typeof(GetApplication("theCrystalImageCleaner")) != "object")
  {
    var cleaner = Server.CreateObject("CrystalReports.CrystalImageCleaner");
    cleaner.Start(120000, 120000); // 2 min, 2 min
    SetApplication("theCrystalImageCleaner", cleaner);
  }
  Application.Unlock();

  // Store a flag in session so I don't have to check the application object every time.
  SetSession ("imageCleanerRunning", 1);  // Must be anything but an empty string
}
//-------start----------
PreventCaching();

try
{
	// Get parameters, build uri query string
	var rptsrckey;  //reportsource is stored here
	var rptid;	  //report id for managed case or reportsource session variable for unmanaged case
	
	var init =  GetQueryString( "init" ) ;
	if(init != null)
	{
		var initStr = String(init).toLowerCase();
		var index = initStr.indexOf("java");
		if (index < 0)
			index = initStr.indexOf("actx")
		if (index >= 0)
		{
			//if not html viewer, go back to reportsourcebridge entry.
			var query = String (Request.ServerVariables.Item("QUERY_STRING"));
			Response.Redirect ("./viewrpt.asp?" + query);	
		}		
	}
	  
	var id =  GetQueryString( "id" ) ;
	var uriQueryString;
	var rptSrc;
	if (id == null)
	{ 
		var rptsrckey =  GetQueryString( "rptsrc" ) ;  // Use the cached report source 
		if (rptsrckey == null)
		{   
			//reportsource stored by user is invalid
			Response.Write( L_INVALID_ARGUMENT + "<BR>" );
			Response.End;	
		}
		rptid = String(rptsrckey); 
		rptSrc = GetSession(rptid);
		if ( typeof(rptSrc) != "object")
		{   
			// can not find the reportsource user stored session just return error
			Response.Write( L_INVALID_RPTSRC + "<BR>" );
			Response.End;	
		}
		uriQueryString = "rptsrc=" + Server.URLEncode(rptid); 
	} else
	{
		rptid = String(id);
		rptsrckey = "rsbridge_rptsrc_" + rptid;
		rptSrc = GetSession(rptsrckey); 
		if ( typeof(rptSrc) != "object")
		{
			//can not find reportsource, maybe timeout, maybe user enter a new report id
			var query = String (Request.ServerVariables.Item("QUERY_STRING"));
			Response.Redirect ("./viewrpt.asp?" + query);
		}		
		uriQueryString = "id=" + rptid; 
	}     			
  
  //everyting is fine, show the report  
	EnsureImageCleanerIsRunning();
	var viewer1 = Server.CreateObject( "CrystalReports.CrystalReportViewer" );

	// set selection formula
	var sfkey = "rsbridge_vtsf_" + rptid;   
	var sf = GetSession(sfkey);
	if ( typeof(sf) == "string") 
	{
		viewer1.ViewTimeSelectionFormula = String(sf);
		RemoveSession(sfkey);
	}

	var promptonrefresh =  GetQueryString( "promptonrefresh" );
	if (promptonrefresh != null)
	{
		var bPrompt = String(promptonrefresh);
		if (bPrompt == "0")
		{
			viewer1.ReuseParameterValuesOnRefresh = true;
			uriQueryString += "&promptonrefresh=0";
		}
	}
	
	viewer1.ReportSource = rptSrc;
	viewer1.IsOwnForm = true;
	viewer1.IsOwnPage = true;
	viewer1.IsDisplayGroupTree = true;
	viewer1.PageToTreeRatio = 5.0;
	viewer1.IsDisplayToolbar = true;
	viewer1.IsDisplayPage = true;
	viewer1.HasRefreshButton = true;
	viewer1.URI = String(Request.ServerVariables.Item("SCRIPT_NAME")) + "?" + uriQueryString;

	var printControl =  GetQueryString( "advprint" ) ;
	var printMode = 1;  // ActiveX print control
	if (printControl != null)
	{
		printControl = String(printControl).toLowerCase();
		if (printControl == "acro")
		{
			printMode = 0;		// Acrobat Reader print control
		}
	}		
	   	
	viewer1.PrintMode = printMode;

	var key = "rsbridge_connInfos_" + rptid;   
	var connInfos = GetSession(key);
	if ( typeof(connInfos) == "object") 
	{
		viewer1.DatabaseLogOnInfos = connInfos;
		RemoveSession(key);
	}
		
	key = "rsbridge_prompts_" + rptid;
	var prompts = GetSession(key);
	if ( typeof(prompts) == "object")
	{
		viewer1.ParameterFields = prompts;
		RemoveSession(key);
	} 
}
catch(e)
{
  Response.Write( L_SESSION_EXPIRED + "<BR>" );
  Response.Write( e.description );
  Response.End();
}

try
{
	viewer1.ProcessHttpRequest( Request, Response, Session );
}
catch (e)
{
	// do not show the error message since the viewer will show a formatted message	
}
%>

