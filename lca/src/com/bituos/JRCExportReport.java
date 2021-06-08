package com.bituos;
//Crystal Java Reporting Component (JRC) imports.
//import com.crystaldecisions.reports.sdk.*;
import com.crystaldecisions.sdk.occa.report.lib.*;
import com.crystaldecisions.sdk.occa.report.exportoptions.*;

//import com.crystaldecisions.report.web.viewer.*;
//import com.crystaldecisions.reports.reportengineinterface.*;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
//import com.crystaldecisions.sdk.occa.report.data.*;
//import com.crystaldecisions.sdk.occa.report.reportsource.*;
//import com.crystaldecisions.reports.sdk.*;
//import com.crystaldecisions.sdk.occa.report.lib.*;
//import com.crystaldecisions.sdk.occa.report.exportoptions.*;

//Java imports.
import java.io.*;

public class JRCExportReport {

      //static final String REPORT_NAME = "C:\\JRCExportReport.rpt";
      //static final String REPORT_NAME = "C:\\Report1.rpt";
      //static final String EXPORT_FILE = "C:\\myExportedReport.pdf";
      
      static final String REPORT_NAME = "/eclipseWork/lca/WebContent/rpt/Crystal/rptLog.rpt";
      static final String EXPORT_FILE = "/incoming/myExportedReport.pdf";
      
      
      public static void export() {

            try {
                  
                  System.out.println("Open report");
                  //Open report.                  
                  ReportClientDocument reportClientDoc = new ReportClientDocument();
                  System.out.println("After declaration");
                  
                  reportClientDoc.setReportAppServer("127.0.0.1"); 

                  reportClientDoc.open(REPORT_NAME, 0);
                  System.out.println("After open");
                  
                  //NOTE: If parameters or database login credentials are required, they need to be set before.
                  //calling the export() method of the PrintOutputController.
                  
                  //Export report and obtain an input stream that can be written to disk.
                  //See the Java Reporting Component Developer's Guide for more information on the supported export format enumerations
                  //possible with the JRC.
                  ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream)reportClientDoc.getPrintOutputController().export(ReportExportFormat.PDF);
                  
                  //Release report.
                  reportClientDoc.close();
                                    
                  //Use the Java I/O libraries to write the exported content to the file system.
                  byte byteArray[] = new byte[byteArrayInputStream.available()];

                  //Create a new file that will contain the exported result.
                  File file = new File(EXPORT_FILE);
                  FileOutputStream fileOutputStream = new FileOutputStream(file);

                  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(byteArrayInputStream.available());
                  int x = byteArrayInputStream.read(byteArray, 0, byteArrayInputStream.available());

                  byteArrayOutputStream.write(byteArray, 0, x);
                  byteArrayOutputStream.writeTo(fileOutputStream);

                  //Close streams.
                  byteArrayInputStream.close();
                  byteArrayOutputStream.close();
                  fileOutputStream.close();
                  
                  System.out.println("Successfully exported report to " + EXPORT_FILE);
                                                
            }
            catch(ReportSDKException ex) {
            
                  System.out.println("Inside catch");
                  ex.printStackTrace();
                  
            }
            catch(Exception ex) {
                  
                  ex.printStackTrace();
                                    
            }

      }

}

