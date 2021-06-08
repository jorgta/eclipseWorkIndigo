package com.bituos;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLWriterDOM {
	
	private String id;
	private String name;
	private String location;
	private String password;
	private String path;
	private String filename;
	
	public void XMLWriter()
	{
	    DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder icBuilder;
	    try 
	    {
		    icBuilder = icFactory.newDocumentBuilder();
		    Document doc = icBuilder.newDocument();
		    Element mainRootElement = doc.createElementNS("", "Companies");
		    doc.appendChild(mainRootElement);
		
		    // append child elements to root element
		    mainRootElement.appendChild(getCompany(doc, getId(), getName(), getLocation(), getPassword()));
		    //mainRootElement.appendChild(getCompany(doc, "2", "eBay", "Shopping", "2000"));
		    //mainRootElement.appendChild(getCompany(doc, "3", "Google", "Search", "3000"));
		
		    // output DOM XML to console 
		    Transformer transformer = TransformerFactory.newInstance().newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
		    DOMSource source = new DOMSource(doc);
		    StreamResult console = new StreamResult(System.out);
		    transformer.transform(source, console);
		    String full_path = getPath()+"/" + getFilename()+"Config.xml";
		    StreamResult result = new StreamResult(new File(full_path));
		    transformer.transform(source, result);
		
		    System.out.println("\nXML DOM Created Successfully..");
	
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    
    
    private static Node getCompany(Document doc, String id, String name, String location, String password) {
        Element company = doc.createElement("Company");
        company.setAttribute("id", id);
        company.appendChild(getCompanyElements(doc, company, "Name", name));
        company.appendChild(getCompanyElements(doc, company, "Location", location));
        company.appendChild(getCompanyElements(doc, company, "Password", password));
        return company;
    }
 
    // utility method to create text node
    private static Node getCompanyElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


 


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}
    
    
    


}