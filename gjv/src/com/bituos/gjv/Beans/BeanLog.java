/*
 * Created on February, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and

Comments
 */
package com.bituos.gjv.Beans;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */

import java.util.Date;

public class BeanLog{

	private int id;
	private BeanLogType id_log_type;
	private String ip;
	private BeanUser id_user;
	private String event;
	private Date date_reg;


	/**
	* @return
	* Obtiene el valor de la variable event
	 */
	public String getEvent() {
		return event;
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
	* Obtiene el valor de la variable id_user
	 */
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	* @return
	* Obtiene el valor de la variable ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	  * @param string
	* Asigna un valor a la variable event
	 */
	public void setEvent(String string) {
		event = string;
	}

	/**
	 * @param i
	* Asigna el valor de i a  la variable id
	 */
	public void setId(int i) {
		id = i;
	}


	/**
	 * @param user
	* Asigna un objeto de tipo BeanUser a la variable id_user
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
	}

	/**
	  * @param string
	* Asigna un valor a la variable ip
	 */
	public void setIp(String string) {
		ip = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable id_log_type
	 */
	public BeanLogType getId_log_type() {
		return id_log_type;
	}

	/**
	 * @param type
	* Asigna un Asigna un objeto de tipo BeanLogType a la variable id_log_type
	 */
	public void setId_log_type(BeanLogType type) {
		id_log_type = type;
	}


	/**
	* @return
	* Obtiene el valor de la variable
	 */
	public Date getDate_reg() {
		return date_reg;
	}

	/**
	 * @param date
	* Asigna un Asigna un valor de tipo Date  a la variable date_reg
	 */
	public void setDate_reg(Date date) {
		date_reg = date;
	}

}
