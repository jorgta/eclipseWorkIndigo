/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and

Comments
 */
package com.eventAdmin.Beans;

/**
 * @author cirm
*/

import java.util.Date;

public class BeanEmail{

	private int id;
	private String name;
	private String email;



	public int getId() {
		return id;
	}




	

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}


	



	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

}
