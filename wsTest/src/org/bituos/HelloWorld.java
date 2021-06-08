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

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;




//import com.bituos.Config;
import org.bituos.Config;
import com.bituos.SMS.Beans.*;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
/*import com.struts2.Beans.*;
import com.bituos.*;*/

import org.hibernate.classic.Session;


public class HelloWorld {

    public String sayHelloWorld(String name)
    {
        return "Hello world from 1: "+ name;
    }
    
    public String sayHelloWorld2(String name)
    {
        return "Hello world from 2: "+ name;
    }
    

    
    public String[] getSMSList( String name) throws Exception
    {
		//Config config = new Config( );
    	String[] bs = new String[2];
		bs[0] = "ghf";
		bs[1] = "gh56f";
		return bs;
	}
    
    
    public BeanSmsTest[] getObjectList(  ) throws Exception
    {
		//Config config = new Config( );
    	BeanSmsTest[] bs = new BeanSmsTest[2];
    	BeanSmsTest o1 = new BeanSmsTest();
    	o1.setId(1);
    	o1.setMsg("fgdf");
    	o1.setNumber("56565656");
    	
    	
    	BeanSmsTest o2 = new BeanSmsTest();
    	o1.setId(2);
    	o1.setMsg("uiouio");
    	o1.setNumber("34564646456");
		 
    	bs[0] = o1;
    	bs[1] = o2;
		 
		return bs;
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