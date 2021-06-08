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

public class BeanProcessUsers{

	private int id;
	private BeanProcess id_process;
	private BeanUser id_user;


	/**
	 * @return 
	 * Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	* @return 
	* Obtiene el valor de la variable id_process
	 */
	public BeanProcess getId_process() {
		return id_process;
	}

	/**
	* @return 
	* Obtiene el valor de la variable id_user
	 */
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	 * @param i
	* Asigna el valor de i a  la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param process
	* Asigna un objeto de tipo  BeanProcess a la variable id_process
	 */
	public void setId_process(BeanProcess process) {
		id_process = process;
	}

	/**
	 * @param user
	* Asigna un objeto de tipo  BeanUser a la variable id_user
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
	}

}
