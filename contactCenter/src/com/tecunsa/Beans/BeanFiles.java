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

public class BeanFiles{

	private int id;
	private String description;	
	private String fileName;	
	private String fullFileName;	
	private Date date_reg;
	private BeanUser id_user;
	private BeanCase id_case;
	private String realFileName;	


	/**
	 * @return
	 */
	public Date getDate_reg() {
		return date_reg;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public BeanUser getId_user() {
		return id_user;
	}

	/**
	 * @param date
	 */
	public void setDate_reg(Date date) {
		date_reg = date;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param string
	 */
	public void setFileName(String string) {
		fileName = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
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
	public BeanCase getId_case() {
		return id_case;
	}

	/**
	 * @param case1
	 */
	public void setId_case(BeanCase case1) {
		id_case = case1;
	}

	/**
	 * @return
	 */
	public String getFullFileName() {
		return fullFileName;
	}

	/**
	 * @param string
	 */
	public void setFullFileName(String string) {
		fullFileName = string;
	}

	/**
	 * @return
	 */
	public String getRealFileName() {
		return realFileName;
	}

	/**
	 * @param string
	 */
	public void setRealFileName(String string) {
		realFileName = string;
	}

}
