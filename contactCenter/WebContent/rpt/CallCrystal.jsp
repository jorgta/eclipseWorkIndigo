<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="com.crystaldecisions.report.web.viewer.*"%>
<%@ page import="com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory"%>
<%@ page import="com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2"%>
<%@ page import="com.crystaldecisions.sdk.occa.report.reportsource.IReportSource"%>
<%@ page import = "com.crystaldecisions.sdk.occa.report.data.*" %>
<%@ page import = "com.crystaldecisions.report.web.viewer.*" %>
<%@ page import = "com.crystaldecisions.sdk.occa.report.*" %>

<%@ page import="java.sql.Date"%>
<%@ page import="java.util.*"%>


<HTML> 
<HEAD> 
<TITLE> 
Reporte 
</TITLE>
</HEAD>
<BODY bgcolor="#ffffff">
<%  

    HttpSession ses = request.getSession();
    String report = (String) ses.getAttribute( "crystal_rptname" );
    
    
    //test
    //report = "rptInvoice.rpt";
    //report = "rptSheetBudget.rpt";
    //report = "rptInvoices.rpt";
     
    IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();
    IReportSource reportSource = (IReportSource) rptSrcFactory.createReportSource(report, request.getLocale());
    CrystalReportViewer viewer = new CrystalReportViewer();
    
	viewer.setName("Reporte");
    viewer.setReportSource(reportSource);

	//test
//    
//		//invoice
//		ses.setAttribute( "parameter1", new Integer(4) ) ;
//		ses.setAttribute( "parameterType1", new String("Integer") ) ;
//		ses.setAttribute( "parameterName1", new String("id_invoice") ) ;
//		ses.setAttribute( "parameterCount", new Integer(1) ) ;
//
//		// budget
//		ses.setAttribute( "parameter1", new Integer(6) ) ;
//		ses.setAttribute( "parameterType1", new String("Integer") ) ;
//		ses.setAttribute( "parameterName1", new String("id_budget") ) ;
//		ses.setAttribute( "parameterCount", new Integer(1) ) ;
//
//		// invoices
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(2000, 1, 1);
//	
//		ses.setAttribute( "parameter1", calendar.getTime() ) ;
//		ses.setAttribute( "parameterType1", new String("Date") ) ;
//		ses.setAttribute( "parameterName1", new String("date_begin") ) ;
//    
//		calendar = Calendar.getInstance();
//		calendar.set(2008, 1, 1);
//		ses.setAttribute( "parameter2", calendar.getTime() ) ;
//		ses.setAttribute( "parameterType2", new String("Date") ) ;
//		ses.setAttribute( "parameterName2", new String("date_end") ) ;
//    
//		ses.setAttribute( "parameterCount", new Integer(2) ) ;





    //parameters section
    
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
	    	ParameterFieldDiscreteValue parameterField = new ParameterFieldDiscreteValue();
		    ParameterField pfieldNum = new ParameterField();
		    pfieldNum.setReportName("");
	    	Values numVals = new Values();
	
	    	pfieldNum.setName( parameterName ); //Discrete value
	
	    	parameterField.setValue(parameterString);
	    	numVals.add( parameterField );
	    	pfieldNum.setCurrentValues(numVals);
	    	parameterFields.add(pfieldNum);
                           
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
      
    viewer.setParameterFields(parameterFields); 
    
    //end of parameters section

    //viewer.setBestFitPage( true );
	viewer.setDisplayGroupTree(true);
    viewer.setDisplayPage( true );
    viewer.setDisplayToolbar( true );
	viewer.setEnableDrillDown(true); 
	viewer.setGroupTreeWidth(120);
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
    
	//viewer.setReuseParameterValuesOnRefresh(true);
	
	
    try
      {
	    viewer.processHttpRequest(request, response,getServletConfig().getServletContext(), out);
	  }
    catch( Throwable  m)
      {
	    m.printStackTrace();

	    //com.tecunsa.Error e = new com.tecunsa.Error("Error interno de UB (" + m.getMessage() + ")", request);
	    response.sendRedirect("/cexpress/notOK.jsp");
      }
    finally
      {
	    viewer.dispose();
      }
      
%> 
</BODY> 
</HTML>
