package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.bituos.Utils;

/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>rfc - [your comment here]
 * <li>address - [your comment here]
 * <li>fullname - [your comment here]
 * <li>name - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class CompanyChangeForm extends ActionForm {

	private String rfc = null;
	private String fullname = null;
	private String address = null;
	private String name = null;
	private int id;
	private FormFile image = null;

	//smtp
	private String smtp_server;
	private String smtp_user_name;
	private String smtp_password;
	private String smtp_email;
	private String smtp_is_secure;
	private String smtp_port;
	
	//web site
	private String web_site;

	
	//additional
	private String additional1;
	private String additional2;
	private String additional3;
	private String additional4;
	private String additional5;
	private String additional6;
	private String additional7;
	private String additional8;
	private String additional9;
	private String additional10;
	
	//contract
	private String contractSigner;
	private String phones;
	private String streetContract;
	private String colonyContract;
	private String cityContract;
	private String stateContract;
	
	
	//reports Name
	private String nameRptQuote;
	private String nameRptSale;
	private String nameRptContract;
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rfc = null;
		fullname = null;
		address = null;
		name = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		/*
		if ( name != null )
		  {
			name = name.trim();
			if ( name.length() == 0 )
			  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
			else
			  if (name.indexOf(" ") > 0)
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required.no.space"));
		  }
		else
		  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		*/
		
	  if ( fullname != null )
		{
		  fullname = fullname.trim();
		  if ( fullname.length() == 0 )
			errors.add("fullname", new org.apache.struts.action.ActionError("error.field.required"));
		}
	  else
		errors.add("fullname", new org.apache.struts.action.ActionError("error.field.required"));

		if ( address != null )
		  {
			address = address.trim();
			if ( address.length() == 0 )
			  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
	
	  //smtp
	  if ( smtp_server != null )
	    {
		  smtp_server = smtp_server.trim();
		  if ( smtp_server.length() == 0 )
		    errors.add("smtp_server", new org.apache.struts.action.ActionError("error.field.required"));
		}
	  else
	    errors.add("smtp_server", new org.apache.struts.action.ActionError("error.field.required"));

	  if ( smtp_user_name != null )
	    {
	  	  smtp_user_name = smtp_user_name.trim();
		  if ( smtp_user_name.length() == 0 )
		    errors.add("smtp_user_name", new org.apache.struts.action.ActionError("error.field.required"));
		}
	  else
	    errors.add("smtp_user_name", new org.apache.struts.action.ActionError("error.field.required"));

	  if ( smtp_password != null )
	    {
	  	  smtp_password = smtp_password.trim();
		  if ( smtp_password.length() == 0 )
		    errors.add("smtp_password", new org.apache.struts.action.ActionError("error.field.required"));
		}
	  else
	    errors.add("smtp_password", new org.apache.struts.action.ActionError("error.field.required"));


	  if ( smtp_email != null )
	    {
	  	  smtp_email = smtp_email.trim();
		  if ( !Utils.isEmail( smtp_email ) )
			errors.add("smtp_email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		}
	  else
	    errors.add("smtp_email", new org.apache.struts.action.ActionError("error.field.required"));

	  if( smtp_is_secure == null  )
		  smtp_is_secure = "off";
	
	
		
	  if ( image == null )
		errors.add("image", new org.apache.struts.action.ActionError("error.field.required"));
	  else
		if ( getImage().getFileName().length() == 0)
		  errors.add("image", new org.apache.struts.action.ActionError("error.field.required"));


	  if ( nameRptQuote != null )
	    {
		  nameRptQuote = nameRptQuote.trim();
		  
		  if ( nameRptQuote.equals("") )
		    nameRptQuote = "rptQuote.rpt";
		}
	  else
	    nameRptQuote = "rptQuote.rpt";
  	  
	  
	  if ( nameRptSale != null )
	    {
		  nameRptSale = nameRptSale.trim();
		  
		  if ( nameRptSale.equals("") )
			  nameRptSale = "rptSale.rpt";
		}
	  else
		  nameRptSale = "rptSale.rpt";
	  
	  if ( nameRptContract != null )
	    {
		  nameRptContract = nameRptQuote.trim();
		  
		  if ( nameRptContract.equals("") )
			  nameRptContract = "rptContract.rpt";
		}
	  else
		  nameRptContract = "rptContract.rpt";
	   
	  
	  return errors;

	}

	
	
	
	
	
	
	public String getAdditional1() {
		return additional1;
	}

	public void setAdditional1(String additional1) {
		this.additional1 = additional1;
	}

	public String getAdditional2() {
		return additional2;
	}

	public void setAdditional2(String additional2) {
		this.additional2 = additional2;
	}

	public String getAdditional3() {
		return additional3;
	}

	public void setAdditional3(String additional3) {
		this.additional3 = additional3;
	}

	public String getAdditional4() {
		return additional4;
	}

	public void setAdditional4(String additional4) {
		this.additional4 = additional4;
	}

	public String getAdditional5() {
		return additional5;
	}

	public void setAdditional5(String additional5) {
		this.additional5 = additional5;
	}

	public String getAdditional6() {
		return additional6;
	}

	public void setAdditional6(String additional6) {
		this.additional6 = additional6;
	}

	public String getAdditional7() {
		return additional7;
	}

	public void setAdditional7(String additional7) {
		this.additional7 = additional7;
	}

	public String getAdditional8() {
		return additional8;
	}

	public void setAdditional8(String additional8) {
		this.additional8 = additional8;
	}

	public String getAdditional9() {
		return additional9;
	}

	public void setAdditional9(String additional9) {
		this.additional9 = additional9;
	}

	public String getAdditional10() {
		return additional10;
	}

	public void setAdditional10(String additional10) {
		this.additional10 = additional10;
	}

	public String getContractSigner() {
		return contractSigner;
	}

	public void setContractSigner(String contractSigner) {
		this.contractSigner = contractSigner;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getStreetContract() {
		return streetContract;
	}

	public void setStreetContract(String streetContract) {
		this.streetContract = streetContract;
	}

	public String getColonyContract() {
		return colonyContract;
	}

	public void setColonyContract(String colonyContract) {
		this.colonyContract = colonyContract;
	}

	public String getCityContract() {
		return cityContract;
	}

	public void setCityContract(String cityContract) {
		this.cityContract = cityContract;
	}

	public String getStateContract() {
		return stateContract;
	}

	public void setStateContract(String stateContract) {
		this.stateContract = stateContract;
	}

	public String getNameRptQuote() {
		return nameRptQuote;
	}

	public void setNameRptQuote(String nameRptQuote) {
		this.nameRptQuote = nameRptQuote;
	}

	public String getNameRptSale() {
		return nameRptSale;
	}

	public void setNameRptSale(String nameRptSale) {
		this.nameRptSale = nameRptSale;
	}

	public String getNameRptContract() {
		return nameRptContract;
	}

	public void setNameRptContract(String nameRptContract) {
		this.nameRptContract = nameRptContract;
	}

	public String getSmtp_server() {
		return smtp_server;
	}

	public void setSmtp_server(String smtp_server) {
		this.smtp_server = smtp_server;
	}

	public String getSmtp_user_name() {
		return smtp_user_name;
	}

	public void setSmtp_user_name(String smtp_user_name) {
		this.smtp_user_name = smtp_user_name;
	}

	public String getSmtp_password() {
		return smtp_password;
	}

	public void setSmtp_password(String smtp_password) {
		this.smtp_password = smtp_password;
	}

	public String getSmtp_email() {
		return smtp_email;
	}

	public void setSmtp_email(String smtp_email) {
		this.smtp_email = smtp_email;
	}

	public String getSmtp_is_secure() {
		return smtp_is_secure;
	}

	public void setSmtp_is_secure(String smtp_is_secure) {
		this.smtp_is_secure = smtp_is_secure;
	}
	
	
	

	public String getSmtp_port() {
		return smtp_port;
	}

	public void setSmtp_port(String smtp_port) {
		this.smtp_port = smtp_port;
	}

	public String getWeb_site() {
		return web_site;
	}

	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}

	/**
	 * Get rfc
	 * @return String
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * Set rfc
	 * @param <code>String</code>
	 */
	public void setRfc(String r) {
		this.rfc = r;
	}

	/**
	 * Get fullname
	 * @return String
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Set fullname
	 * @param <code>String</code>
	 */
	public void setFullname(String f) {
		this.fullname = f;
	}

	/**
	 * Get address
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address
	 * @param <code>String</code>
	 */
	public void setAddress(String a) {
		this.address = a;
	}

	/**
	 * Get name
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * @param <code>String</code>
	 */
	public void setName(String n) {
		this.name = n;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @return
	 */
	public FormFile getImage() {
		return image;
	}

	/**
	 * @param file
	 */
	public void setImage(FormFile file) {
		image = file;
	}

}
