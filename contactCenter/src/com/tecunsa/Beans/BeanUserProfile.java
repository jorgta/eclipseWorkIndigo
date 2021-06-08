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

public class BeanUserProfile{

	private int id;
	private BeanUser id_user;
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
	* Obtiene el valor de la variable id_profile
	 */
	public BeanProfile getId_profile() {
		return id_profile;
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
	 * @param profile
	* Asigna un bijeto de tipo BeanProfile a  la variable id_profile
	 */
	public void setId_profile(BeanProfile profile) {
		id_profile = profile;
	}

	/**
	 * @param user
	* Asigna un bijeto de tipo BeanUser a  la variable id_user
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
	}

}
