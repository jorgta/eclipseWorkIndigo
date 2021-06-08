/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and

Comments
 */
package com.bituos.truckAdmin.Beans;

/**
 * @author cirm
*/

import java.util.Date;

public class BeanLog{

	private int id;
	private BeanLog_Type id_log_type;
	private String ip;
	private BeanUser id_user;
	private String event;
	private Date date_reg;


	/**
	 * @return
	 */
	public String getEvent() {
		return event;
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
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param string
	 */
	public void setEvent(String string) {
		event = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	
	/**
	 * @param user
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
	}

	/**
	 * @param string
	 */
	public void setIp(String string) {
		ip = string;
	}

	/**
	 * @return
	 */
	public BeanLog_Type getId_log_type() {
		return id_log_type;
	}

	/**
	 * @param type
	 */
	public void setId_log_type(BeanLog_Type type) {
		id_log_type = type;
	}


	/**
	 * @return
	 */
	public Date getDate_reg() {
		return date_reg;
	}

	/**
	 * @param date
	 */
	public void setDate_reg(Date date) {
		date_reg = date;
	}

}
