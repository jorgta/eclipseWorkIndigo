/**
 * Created on Apr 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.bituos.gjv.Beans;

/**
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BeanControlPanel{

	private int id;
	private String email;
	private String email_mine;
	private String email_ct;
	private String server;   
	private String user_name;
	private String password;
	private String is_secure;

	private double tc;
	private double tax;
	private String Banco;
	private String cuenta;

	private String tmx_order_email; 

	private String path_server;
	private String path_www;
	private int days_order_lapsing;

	private double gain;

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
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
	 * @param i
	 */
	public void setId(int i) {
		id = i;
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
	public double getTax() {
		return tax;
	}



	/**
	 * @param d
	 */
	public void setTax(double d) {
		tax = d;
	}

	/**
	 * @return
	 */
	public String getBanco() {
		return Banco;
	}

	/**
	 * @return
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param string
	 */
	public void setBanco(String string) {
		Banco = string;
	}

	/**
	 * @param string
	 */
	public void setCuenta(String string) {
		cuenta = string;
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
	 * @return
	 */
	public String getUser_name() {
		return user_name;
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
	 * @param string
	 */
	public void setUser_name(String string) {
		user_name = string;
	}

	/**
	 * @return
	 */
	public String getTmx_order_email() {
		return tmx_order_email;
	}

	/**
	 * @param string
	 */
	public void setTmx_order_email(String string) {
		tmx_order_email = string;
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

	/**
	 * @return
	 */
	public int getDays_order_lapsing() {
		return days_order_lapsing;
	}

	/**
	 * @param i
	 */
	public void setDays_order_lapsing(int i) {
		days_order_lapsing = i;
	}

	/**
	 * @return
	 */
	public double getTc() {
		return tc;
	}

	/**
	 * @param d
	 */
	public void setTc(double d) {
		tc = d;
	}



	/**
	 * @return
	 */
	public double getGain() {
		return gain;
	}

	/**
	 * @param d
	 */
	public void setGain(double d) {
		gain = d;
	}

	/**
	 * @return
	 */
	public String getEmail_mine() {
		return email_mine;
	}

	/**
	 * @param string
	 */
	public void setEmail_mine(String string) {
		email_mine = string;
	}

	/**
	 * @return
	 */
	public String getEmail_ct() {
		return email_ct;
	}

	/**
	 * @param string
	 */
	public void setEmail_ct(String string) {
		email_ct = string;
	}

}
