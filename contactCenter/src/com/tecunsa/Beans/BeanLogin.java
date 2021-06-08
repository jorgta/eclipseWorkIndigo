/*
 * Created on February, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tecunsa.Beans;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */

public class BeanLogin{

	private int id;
	private String username;
	private String password;

	/**
	* @return 
	* Obtiene el valor de la variable password
	 */
	public String getPassword() {
		return password;
	}

	/**
	   * @param string  
	* Asigna un valor a la variable   password
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	* @return 
	* Obtiene el valor de la variable username
	 */
	public String getUsername() {
		return username;
	}

	/**
	  * @param string  
	* Asigna un valor a la variable   username
	 */
	public void setUsername(String string) {
		username = string;
	}

	/**
	 * @return 
	* Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	* Asigna el valor de i a  la variable id
	 */
	public void setId(int i) {
		id = i;
	}

}
