/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.lca.Beans;

/**
 * @author cirm
*/

public class BeanParameterLimits{

	private int id;
	private String description;
	private BeanTestParameter id_test_parameter;
	private double limit_from_num;
	private double limit_to_num;
	private String limit_from_str;
	private String limit_to_str;
	private int show_in_report;
	
	
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BeanTestParameter getId_test_parameter() {
		return id_test_parameter;
	}
	public void setId_test_parameter(BeanTestParameter id_test_parameter) {
		this.id_test_parameter = id_test_parameter;
	}
	
	public double getLimit_to_num() {
		return limit_to_num;
	}
	public void setLimit_to_num(double limit_to_num) {
		this.limit_to_num = limit_to_num;
	}
	public String getLimit_from_str() {
		return limit_from_str;
	}
	public void setLimit_from_str(String limit_from_str) {
		this.limit_from_str = limit_from_str;
	}
	public String getLimit_to_str() {
		return limit_to_str;
	}
	public void setLimit_to_str(String limit_to_str) {
		this.limit_to_str = limit_to_str;
	}
	public int getShow_in_report() {
		return show_in_report;
	}
	public void setShow_in_report(int show_in_report) {
		this.show_in_report = show_in_report;
	}


	public double getLimit_from_num() {
		return limit_from_num;
	}


	public void setLimit_from_num(double limit_from_num) {
		this.limit_from_num = limit_from_num;
	}


	
	
	

}
