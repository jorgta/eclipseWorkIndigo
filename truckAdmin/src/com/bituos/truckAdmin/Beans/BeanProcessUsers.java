/*
 * Created on June 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.truckAdmin.Beans;

/**
 * @author cirm
*/

public class BeanProcessUsers{

	private int id;
	private BeanProcess id_process;
	private BeanUser id_user;


	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public BeanProcess getId_process() {
		return id_process;
	}

	/**
	 * @return
	 */
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param process
	 */
	public void setId_process(BeanProcess process) {
		id_process = process;
	}

	/**
	 * @param user
	 */
	public void setId_user(BeanUser user) {
		id_user = user;
	}

}
