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

public class BeanProcessProfile{

	private int id;
	private BeanProcess id_process;
	private BeanProfile id_profile;


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
	* @return * Obtiene el valor de la variable id_profile
	 */
	public BeanProfile getId_profile() {
		return id_profile;
	}

	/**
	 * @param profile
	* Asigna un objeto de tipo  BeanProfile a la variable id_profile
	 */
	public void setId_profile(BeanProfile profile) {
		id_profile = profile;
	}

}
