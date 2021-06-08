/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.emptyProject.Beans;

/**
 * @author cirm
*/

public class BeanSettingsRow{

	private int id;
	private BeanSettings id_settings;
	private BeanProcess id_process;
	private int rows_to_show;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanProcess getId_process() {
		return id_process;
	}
	public void setId_process(BeanProcess id_process) {
		this.id_process = id_process;
	}
	public BeanSettings getId_settings() {
		return id_settings;
	}
	public void setId_settings(BeanSettings id_settings) {
		this.id_settings = id_settings;
	}
	public int getRows_to_show() {
		return rows_to_show;
	}
	public void setRows_to_show(int rows_to_show) {
		this.rows_to_show = rows_to_show;
	}
	

	

}
