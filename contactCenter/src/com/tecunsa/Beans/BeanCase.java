/*
 * Created on February, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.tecunsa.Beans;

import java.util.Date;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */

public class BeanCase{

	private int id;
	private BeanUser id_user_capture;
	private BeanUser id_supervisor;
	private BeanClient id_client;
	private BeanTypeCase id_type_case;
	private String short_description;	
	private String description;	
	private BeanTypeCaseStatus id_type_case_status;
	private Date date_reg;
	private Date date_last_change;
	private Date date_close;
	private BeanUser id_user;
	private String successful;
	private String lazy;
	private Date date_delivery;

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




	/**
	* @return
	* Obtiene el valor de la variable date_last_change
	 */
	public Date getDate_last_change() {
		return date_last_change;
	}

	/**
	* @return 
	* Obtiene el valor de la variable date_reg
	 */
	public Date getDate_reg() {
		return date_reg;
	}

	/**
	* @return 
	* Obtiene el valor de la variable description
	 */
	public String getDescription() {
		return description;
	}

	/**
	* @return 
	* Obtiene el valor de la variable id_supervisor
	 */
	public BeanUser getId_supervisor() {
		return id_supervisor;
	}

	/**
	* @return 
	* Obtiene el valor de la variable id_type_case
	 */
	public BeanTypeCase getId_type_case() {
		return id_type_case;
	}

	/**
	* @return 
	* Obtiene el valor de la variable id_user_capture
	 */
	public BeanUser getId_user_capture() {
		return id_user_capture;
	}

	/**
	* @return
	* Obtiene el valor de la variable short_description
	 */
	public String getShort_description() {
		return short_description;
	}

	/**
	 * @param date
	* Asigna el valor de tipo Date  a  la variable date_last_change
	 */
	public void setDate_last_change(Date date) {
		date_last_change = date;
	}

	/**
	 * @param date
	* Asigna el valor de tipo Date  a  la variable date_reg
	 */
	public void setDate_reg(Date date) {
		date_reg = date;
	}

	/**
	  * @param string  
	* Asigna un valor a la variable description
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param user
	* Asigna el valor de tipo BeanUser  a  la variable id_supervisor
	 */
	public void setId_supervisor(BeanUser user) {
		id_supervisor = user;
	}

	/**
	 * @param case1
	* Asigna el valor de tipo BeanTypeCase  a  la variable id_type_case
	 */
	public void setId_type_case(BeanTypeCase case1) {
		id_type_case = case1;
	}

	/**
	 * @param user
	* Asigna el valor de tipo BeanUser  a  la variable id_user_capture
	 */
	public void setId_user_capture(BeanUser user) {
		id_user_capture = user;
	}

	/** 
	  * @param string  
	* Asigna un valor a la variable short_description
	 */
	public void setShort_description(String string) {
		short_description = string;
	}


	

	/**
	* @return 
	* Obtiene el valor de la variable date_close
	 */
	public Date getDate_close() {
		return date_close;
	}

	/**
	 * @param date
	* Asigna el valor de tipo Date  a  la variable date_close
	 */
	public void setDate_close(Date date) {
		date_close = date;
	}

	/**
	* @return 
	* Obtiene el valor de la variable id_type_case_status
	 */
	public BeanTypeCaseStatus getId_type_case_status() {
		return id_type_case_status;
	}

	/**
	 * @param status
	* Asigna el valor de tipo BeanTypeCaseStatus  a  la variable id_type_case_status
	 */
	public void setId_type_case_status(BeanTypeCaseStatus status) {
		id_type_case_status = status;
	}

	

	/**
	 * @return
	 */
	public BeanClient getId_client() {
		return id_client;
	}

	/**
	 * @param client
	 */
	public void setId_client(BeanClient client) {
		id_client = client;
	}

	/**
	 * @return
	 */
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	 * @param user
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
	}

	/**
	 * @return
	 */
	public String getSuccessful() {
		return successful;
	}

	/**
	 * @param string
	 */
	public void setSuccessful(String string) {
		successful = string;
	}

	/**
	 * @return
	 */
	public String getLazy() {
		return lazy;
	}

	/**
	 * @param string
	 */
	public void setLazy(String string) {
		lazy = string;
	}

	/**
	 * @return
	 */
	public Date getDate_delivery() {
		return date_delivery;
	}

	/**
	 * @param date
	 */
	public void setDate_delivery(Date date) {
		date_delivery = date;
	}

}
