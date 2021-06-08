/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;

/**
 * @author cirm
*/

public class BeanControlPanel{

	private int id;
	private String email;
	private String server;
	private String user_name;
	private String password;
	private String is_secure;
	private String email_personalization;
	private String path_server;
	private String path_www;
	private String hosting_server_name;
	
	

	public String getPath_server() {
		return path_server;
	}

	public void setPath_server(String path_server) {
		this.path_server = path_server;
	}

	public String getPath_www() {
		return path_www;
	}

	public void setPath_www(String path_www) {
		this.path_www = path_www;
	}

	public String getHosting_server_name() {
		return hosting_server_name;
	}

	public void setHosting_server_name(String hosting_server_name) {
		this.hosting_server_name = hosting_server_name;
	}

	/**
	 * Get id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set id
	 */
	public void setId(int integer) {
		id = integer;
	}


	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
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
	public String getEmail_personalization() {
		return email_personalization;
	}

	/**
	 * @param string
	 */
	public void setEmail_personalization(String string) {
		email_personalization = string;
	}

}
