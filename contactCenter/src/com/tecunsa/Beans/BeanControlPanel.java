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

public class BeanControlPanel{

	private int id;
	private String server;
	private String email;
	private String user_name;
	private String password;
	private String is_secure;
	private String path_server;
	private String path_www;


	/**
	* @return 
	* Obtiene el valor de la variable email
	 */
	public String getEmail() {
		return email;
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
	* Obtiene el valor de la variable server
	 */
	public String getServer() {
		return server;
	}

	/**
	  * @param string  
	* Asigna un valor a la variable email
	 */
	public void setEmail(String string) {
		email = string;
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
	* Asigna un valor a la variable server
	 */
	public void setServer(String string) {
		server = string;
	}

	/**
	 * @return
	 */
	public String getIs_secure() {
		return is_secure;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param string
	 */
	public void setIs_secure(String string) {
		is_secure = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @return
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param string
	 */
	public void setUser_name(String string) {
		user_name = string;
	}

	/**
	 * @return
	 */
	public String getPath_server() {
		return path_server;
	}

	/**
	 * @return
	 */
	public String getPath_www() {
		return path_www;
	}

	/**
	 * @param string
	 */
	public void setPath_server(String string) {
		path_server = string;
	}

	/**
	 * @param string
	 */
	public void setPath_www(String string) {
		path_www = string;
	}

}
