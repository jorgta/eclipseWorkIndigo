/*
 * Created on June 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;

/**
 * @author cirm
*/

public class BeanProcess{

	private int id;
	private String description;
	private String url;
	private String img_name;
	private String action;
	private BeanTypeProcess id_type_process;
	private String target;

	/**
	 * Get description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description
	 */
	public void setDescription(String string) {
		description = string;
	}
	
	

	/**
	 * @return
	 */
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
	public String getImg_name() {
		return img_name;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param string
	 */
	public void setImg_name(String string) {
		img_name = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	

	/**
	 * @return
	 */
	public BeanTypeProcess getId_type_process() {
		return id_type_process;
	}

	/**
	 * @param process
	 */
	public void setId_type_process(BeanTypeProcess process) {
		id_type_process = process;
	}

	/**
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param string
	 */
	public void setTarget(String string) {
		target = string;
	}

}
