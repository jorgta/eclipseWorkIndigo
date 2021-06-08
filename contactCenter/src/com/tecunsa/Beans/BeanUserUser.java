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

public class BeanUserUser{

	private int id;
	private BeanUser id_user;
	private BeanUser id_supervisor;

	/**
	 * @return 
	* Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return 
	* Obtiene el valor de la variable id_supervisor
	 */
	public BeanUser getId_supervisor() {
		return id_supervisor;
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
	* Asigna un objeto de tipo BeanUser a  la variable id_supervisor  
	 */
	public void setId_supervisor(BeanUser user) {
		id_supervisor = user;
	}

	/**
	 * @return 
	* Obtiene el valor de la variable id_user
	 */
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	 * @param user
	* Asigna un objeto de tipo BeanUser a  la variable id_user  
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
	}

}
