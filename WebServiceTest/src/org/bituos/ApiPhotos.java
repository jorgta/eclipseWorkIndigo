package org.bituos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;




import java.util.*;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceException;

 


public class ApiPhotos {


    public String saveFile(String ruta, String filename, byte[] infoBytes) throws Exception
    {

    	String filePath = "C:/eclipseWorkIndigo/files/WebContent/uploadAndroid/" + infoBytes +".jpg";
        String result = "";
       
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(infoBytes);
            outputStream.close();
             
           // System.out.println("Received file: " + filePath);
            result = "Received file: " + filePath;
             
      
        return  result;

      }
}