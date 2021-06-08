/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.truckAdmin.Beans;

import java.util.Date;

/**
 * @author cirm
*/

public class BeanCompany{

	private int id;
	private String name;
	private String rfc;
	private String address;
	private String active;
	private String full_name;
	private String logo_file_name;
	
	//smtp
	private String smtp_server;
	private String smtp_user_name;
	private String smtp_password;
	private String smtp_email;
	private String smtp_is_secure;
	private String smtp_port;
	
	
	private String web_site;
	
	//aditionals
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

	/**
	 * @return
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param string
	 */
	public void setActive(String string) {
		active = string;
	}

	/**
	 * @param string
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setRfc(String string) {
		rfc = string;
	}

	/**
	 * @return
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * @param string
	 */
	public void setFull_name(String string) {
		full_name = string;
	}

	/**
	 * @return
	 */
	public String getLogo_file_name() {
		return logo_file_name;
	}

	/**
	 * @param string
	 */
	public void setLogo_file_name(String string) {
		logo_file_name = string;
	}

	public String getAdditional1() {
		return additional1;
	}

	public void setAdditional1(String additional1) {
		this.additional1 = additional1;
	}

	public String getAdditional10() {
		return additional10;
	}

	public void setAdditional10(String additional10) {
		this.additional10 = additional10;
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


	public String getCityContract() {
		return cityContract;
	}

	public void setCityContract(String cityContract) {
		this.cityContract = cityContract;
	}

	public String getColonyContract() {
		return colonyContract;
	}

	public void setColonyContract(String colonyContract) {
		this.colonyContract = colonyContract;
	}

	public String getStateContract() {
		return stateContract;
	}

	public void setStateContract(String stateContract) {
		this.stateContract = stateContract;
	}

	public String getStreetContract() {
		return streetContract;
	}

	public void setStreetContract(String streetContract) {
		this.streetContract = streetContract;
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

	public String getWeb_site() {
		return web_site;
	}

	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}
	
	
	

}
