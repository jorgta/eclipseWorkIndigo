<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="com.crystaldecisions.report.web.viewer.*"%>
<%@ page import="com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory"%>
<%@ page import="com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2"%>
<%@ page import="com.crystaldecisions.sdk.occa.report.reportsource.IReportSource"%>
<%@ page import = "com.crystaldecisions.sdk.occa.report.data.*" %>
<%@ page import = "com.crystaldecisions.report.web.viewer.*" %>
<%@ page import = "com.crystaldecisions.sdk.occa.report.*" %>
<%@ page import="com.crystaldecisions.sdk.occa.report.lib.*"%>
<%-- <%@ page import="com.crystaldecisions.sdk.occa.report.application.*"%> --%>
<%@ page import="com.crystaldecisions.sdk.occa.report.definition.*"%>
<%@ page import="com.crystaldecisions.sdk.occa.report.exportoptions.*"%>
<%@ page import="com.crystaldecisions.reports.sdk.ReportClientDocument" %>

<%@page import="com.crystaldecisions.sdk.occa.report.data.*" %>
<%@page import="com.crystaldecisions.reports.sdk.*" %>




<%@ page import="java.sql.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ taglib uri="/crystal-tags-reportviewer.tld" prefix="crviewer" %>


<html> 
<head> 
<title> 
Reporte 
</title>
</head>
<body bgcolor="#ffffff">



<%  
	

    HttpSession ses = request.getSession();
    String report = (String) ses.getAttribute( "crystal_rptname" );
    
    
    final String EXPORT_FILE = (String) ses.getAttribute( "EXPORT_FILE") +  ".pdf" ;
	//final String EXPORT_LOC = "C:\\WeatherExports\\";
 
     
    
    ReportClientDocument reportClientDoc = new ReportClientDocument();
    
    reportClientDoc.open(report, 0);
	Object reportSource = reportClientDoc.getReportSource();   
  
    CrystalReportViewer viewer = new CrystalReportViewer();
    viewer.setReportSource(reportSource);
    
	viewer.setName(report);
    viewer.setReportSource(reportSource);

    
    Integer parameterCount = (Integer) ses.getAttribute("parameterCount");
    Fields parameterFields = new Fields();
    int i = 0;
    
    if( parameterCount == null )
      parameterCount = new Integer( 0 );
    
    while ( i<parameterCount.intValue() )
      {
        i++;
        
        String parameterName = (String) ses.getAttribute( "parameterName" + i );
      
        
        if ( ((String) ses.getAttribute("parameterType" + i )).equals("String") ) 
          {
            String parameterString = (String) ses.getAttribute("parameter" + i);
            
            //add String parameter to crystal
                           
          }
        else if ( ((String) ses.getAttribute("parameterType" + i )).equals("Integer") ) 
          {
            Integer parameterInteger = (Integer) ses.getAttribute("parameter" + i);
            
           //add Integer parameter to crystal
           
	    	ParameterFieldDiscreteValue parameterField = new ParameterFieldDiscreteValue();
		    ParameterField pfieldNum = new ParameterField();
		    pfieldNum.setReportName("");
	    	Values numVals = new Values();
	
	    	pfieldNum.setName( parameterName ); //Discrete value
	
	    	parameterField.setValue(parameterInteger);
	    	numVals.add( parameterField );
	    	pfieldNum.setCurrentValues(numVals);
	    	parameterFields.add(pfieldNum);
	    	
	    	
	    	ParameterFieldController paramFieldController = reportClientDoc.getDataDefController().getParameterFieldController(); 	    	 
	    	paramFieldController.setCurrentValue("", parameterName, numVals);
           
          }
        else if ( ((String) ses.getAttribute("parameterType" + i )).equals("Date") ) 
          {
            java.util.Date parameterDate = (java.util.Date) ses.getAttribute("parameter" + i);
            
            //add Date parameter to crystal

	    	ParameterFieldDiscreteValue parameterField = new ParameterFieldDiscreteValue();
   //         ParameterField parameterField = newDateField( parameterName, parameterDate );
            
		    ParameterField pfieldNum = new ParameterField();
		    pfieldNum.setReportName("");
	    	Values numVals = new Values();
	
	    	pfieldNum.setName( parameterName ); //Discrete value
	
	    	parameterField.setValue(parameterDate); 
	    	numVals.add( parameterField );
	    	pfieldNum.setCurrentValues(numVals);
	    	parameterFields.add(pfieldNum);
           
          }
      }
    
    String type =(String)ses.getAttribute("type");
    if(type == null)
	{  
	    viewer.setParameterFields(parameterFields); 
	    
	    //end of parameters section
	
	    //viewer.setBestFitPage( true );
		viewer.setDisplayGroupTree(true);
	    viewer.setDisplayPage( true );
	    viewer.setDisplayToolbar( true );
		viewer.setEnableDrillDown(true); 
		viewer.setGroupTreeWidth(40);//ancho del tree
		 
		viewer.setHasExportButton(true);
		//viewer.setHasGotoPageButton(true);
		viewer.setHasLogo(false);
		//viewer.setHasPageNavigationButtons(true);
		viewer.setHasPrintButton(true);
	    viewer.setHasRefreshButton(true); 
	    viewer.setHasSearchButton(true); 
	    viewer.setHasToggleGroupTreeButton(true); 
	    //viewer.setHasViewList( true );
	    viewer.setHasZoomFactorList( true );
		viewer.setPrintMode(CrPrintMode.ACTIVEX);
		//viewer.setRenderAsHTML32(false);
		viewer.setSeparatePages(true);
		viewer.setShowAllPageIds(true);
		
	    viewer.setOwnPage(true); // con esto se activan los botones de imprimir y exportar
		viewer.setOwnForm(true);
	//	viewer.refresh();

		 
	}
 
 
 
    
    
    
    
    if(type != null)
    {    
    	ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream)reportClientDoc.getPrintOutputController().export(ReportExportFormat.PDF); 
        reportClientDoc.close(); 
    	if(type.equals("download"))
    	{
    		writeToDownload(byteArrayInputStream, response, EXPORT_FILE);	
    		//return;
    	}
    	else if(type.equals("pdf"))
    	{
    		writeToBrowserAndDownload(byteArrayInputStream, response,  EXPORT_FILE);
    		//return;
    	}
    	    
    }
    
    

    
	
	
	/*String EXPORT_OUTPUT = EXPORT_LOC + EXPORT_FILE;	
	writeDirectToFileSystem(byteArrayInputStream, EXPORT_OUTPUT);*/
    
	
    try
      {
    	if(type == null)
    	{
    		viewer.processHttpRequest(request, response,getServletConfig().getServletContext(), out);	
    	}
	      
    	
	  }
    catch( Throwable  m)
      {
	    m.printStackTrace();

	    //com.tecunsa.Error e = new com.tecunsa.Error("Error interno de UB (" + m.getMessage() + ")", request);
	    response.sendRedirect("/trip/notOK.jsp");
      }
    finally
      {
    	if(type == null)
    	{
    		viewer.dispose();
    	}
	    
      }
      
%> 

<%!
   /*
	* Utility method that demonstrates how to write an input stream to the server's local file system.  
	*/
	private void writeDirectToFileSystem(ByteArrayInputStream byteArrayInputStream, String exportFile) throws Exception {
	
		//Use the Java I/O libraries to write the exported content to the file system.
		byte byteArray[] = new byte[byteArrayInputStream.available()];

		File file = new File(exportFile);
		
		//Create a new file that will contain the exported result.
		if (!file.getParentFile().mkdirs())
                 throw new IOException("Unable to create " + file.getParentFile());
		
		
		
		FileOutputStream fileOutputStream = new FileOutputStream(file);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(byteArrayInputStream.available());
		int x = byteArrayInputStream.read(byteArray, 0, byteArrayInputStream.available());

		byteArrayOutputStream.write(byteArray, 0, x);
		byteArrayOutputStream.writeTo(fileOutputStream);

		//Close streams.
		byteArrayInputStream.close();
		byteArrayOutputStream.close();
		fileOutputStream.close();
		
		
         
   
		
	}
%>	

<%!
   /*
	* Utility method that demonstrates how to write an input stream to the server's local file system.  
	*/
	private void writeToBrowserAndDownload(ByteArrayInputStream byteArrayInputStream, HttpServletResponse response,  String exportFile) throws Exception {
	
	    File file = new File(exportFile);
		//Create a byte[] the same size as the exported ByteArrayInputStream.
		byte[] buffer = new byte[byteArrayInputStream.available()];
		int bytesRead = 0;
		
		//Set response headers to indicate mime type and inline file.
		response.reset();
		response.setHeader("Content-disposition", "inline;filename=" + exportFile);
		response.setContentType("application/pdf");

		
		//Stream the byte array to the client.
		while((bytesRead = byteArrayInputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);	
		}
		
		//Flush and close the output stream.
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
%>



<%!
   /*
	* Utility method that demonstrates how to write an input stream to the server's local file system.  
	*/
	private void writeToDownload(ByteArrayInputStream byteArrayInputStream, HttpServletResponse response,  String exportFile) throws Exception {
	
	     
		//Create a byte[] the same size as the exported ByteArrayInputStream.
		byte[] buffer = new byte[byteArrayInputStream.available()];
		int bytesRead = 0;
		
		//Set response headers to indicate mime type and inline file.
		response.reset();
		/*response.setHeader("Content-disposition", "inline;filename=" + exportFile);
		response.setContentType(mimetype);*/
		
		
		//response.setContentLength((int)file.length());
		        //response.setContentLength(-1);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition","attachment; filename=\"" + exportFile);
		response.setContentType("application/force-download");
		
		//Stream the byte array to the client.
		while((bytesRead = byteArrayInputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);	
		}
		
		//Flush and close the output stream.
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
%>	

</body> 
</html>
