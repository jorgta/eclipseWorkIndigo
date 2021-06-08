/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.lca.Beans;

/**
 * @author cirm
*/

public class BeanControlPanel{

	private int id;
	private String email_email;
	private String email_username;
	private String email_password;
	private String email_smtp_server;
	private String email_smtp_port;
	private String email_is_secure;
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



	public String getEmail_email() {
		return email_email;
	}

	public void setEmail_email(String email_email) {
		this.email_email = email_email;
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

	public String getEmail_username() {
		return email_username;
	}

	public void setEmail_username(String email_username) {
		this.email_username = email_username;
	}

	public String getEmail_password() {
		return email_password;
	}

	public void setEmail_password(String email_password) {
		this.email_password = email_password;
	}

	public String getEmail_smtp_server() {
		return email_smtp_server;
	}

	public void setEmail_smtp_server(String email_smtp_server) {
		this.email_smtp_server = email_smtp_server;
	}



	public String getEmail_smtp_port() {
		return email_smtp_port;
	}

	public void setEmail_smtp_port(String email_smtp_port) {
		this.email_smtp_port = email_smtp_port;
	}

	public String getEmail_is_secure() {
		return email_is_secure;
	}

	public void setEmail_is_secure(String email_is_secure) {
		this.email_is_secure = email_is_secure;
	}

	
	
	
	
	
}
