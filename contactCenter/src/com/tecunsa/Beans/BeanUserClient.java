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

public class BeanUserClient{

	private int id;
	private BeanUser id_user;
	private BeanClient id_client;

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
	 * @param user
	* Asigna un objeto de tipo BeanUser a  la variable id_user  
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
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

}
