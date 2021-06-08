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

public class BeanCaseNotes{

	private int id;
	private BeanUser id_user;
	private BeanCase id_case;
	private Date date_reg;
	private String is_private;
	private String note;
	private String forwarded;
	private int days;
	
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
	* Obtiene el valor de la variable id_user
	 */
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	 * @param 
	* Asigna un objeto de tipo BeanUser a la variable id_user
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
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
	* Obtiene el valor de la variable
	 */
	public BeanCase getId_case() {
		return id_case;
	}

	/**
	* @return 
	* Obtiene el valor de la variable is_private
	 */
	public String getIs_private() {
		return is_private;
	}

	/**
	 * @param date
	* Asigna un valor de tipo date a la variable date_reg
	 */
	public void setDate_reg(Date date) {
		date_reg = date;
	}

	/**
	 * @param case1
	* Asigna un objeto de tipo BeanCase  a la variable id_case
	 */
	public void setId_case(BeanCase case1) {
		id_case = case1;
	}

	/**
	  * @param string 
	* Asigna un valor a la variable is_private
	 */
	public void setIs_private(String string) {
		is_private = string;
	}

	/**
	* @return 
	* Obtiene el valor de la variable note
	 */
	public String getNote() {
		return note;
	}

	/**
	  * @param string  
	* Asigna un valor a la variable note
	 */
	public void setNote(String string) {
		note = string;
	}

	

	/**
	 * @return
	 */
	public String getForwarded() {
		return forwarded;
	}

	

	/**
	 * @param string
	 */
	public void setForwarded(String string) {
		forwarded = string;
	}

	
	/**
	 * @return
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @param i
	 */
	public void setDays(int i) {
		days = i;
	}

}
