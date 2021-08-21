package com.bituos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class UploadMedia {

	
		public String sayHelloWorld(String name)
	    {
	        return "Hello world from 1: "+ name;
	    }
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
