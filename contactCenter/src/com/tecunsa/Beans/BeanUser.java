/*
 * Created on February, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments 
 */
package com.tecunsa.Beans;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class BeanUser{

	private int id;
	private String name;
	private String full_name;
	private String password;
	private String active;
	private String address;
	private String rfc;
	private String email;
	private String telephone;

	/**
	 * @return 
	 * Obtiene el valor de la variable active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @return 
	 * Obtiene el valor de la variable address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return 
	 * Obtiene el valor de la variable email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return 
	 * Obtiene el valor de la variable full_name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * @return 
	 * Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return 
	 * Obtiene el valor de la variable name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return * Obtiene el valor de la variable password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return 
	 * Obtiene el valor de la variable rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/** 
	 * @param string 
	 * Asigna un valor a  la variable active 
	 */
	public void setActive(String string) {
		active = string;
	}

	/**
	 * @param string 
	 * Asigna un valor a  la variable address 
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	 * @param string 
	 * Asigna un valor a  la variable email 
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string 
	 * Asigna un valor a  la variable full_name 
	 */
	public void setFull_name(String string) {
		full_name = string;
	}

	/**
	 * @param i
           * Asigna el valor de i a  la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param string
	 * Asigna un valor a  la variable name 
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string 
	 * Asigna un valor a  la variable password 
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string 
	 * Asigna un valor a  la variable rfc 
	 */
	public void setRfc(String string) {
		rfc = string;
	}

	/**
	 * @return 
	 * Obtiene el valor de la variable return 
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param string 
	 * Asigna un valor a  la variable telephone 
	 */
	public void setTelephone(String string) {
		telephone = string;
	}

}
