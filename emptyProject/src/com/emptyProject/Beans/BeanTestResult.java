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
import java.util.Date;


public class BeanTestResult{

	private int id;
	private BeanTestOrder id_order;
	private BeanTestParameter id_test_parameter;
	private double value;
	private Date date_reg;
	private int is_numeric;
	private String value_str;
	private String test_name;
	private String test_parameter_name;
	private String min_value;
	private String max_value;
	 
	 
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanTestOrder getId_order() {
		return id_order;
	}
	public void setId_order(BeanTestOrder id_order) {
		this.id_order = id_order;
	}
	public BeanTestParameter getId_test_parameter() {
		return id_test_parameter;
	}
	public void setId_test_parameter(BeanTestParameter id_test_parameter) {
		this.id_test_parameter = id_test_parameter;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public int getIs_numeric() {
		return is_numeric;
	}
	public void setIs_numeric(int is_numeric) {
		this.is_numeric = is_numeric;
	}
	public String getValue_str() {
		return value_str;
	}
	public void setValue_str(String value_str) {
		this.value_str = value_str;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getTest_parameter_name() {
		return test_parameter_name;
	}
	public void setTest_parameter_name(String test_parameter_name) {
		this.test_parameter_name = test_parameter_name;
	}
	
	
	
	
	public String getMin_value() {
		return min_value;
	}
	public void setMin_value(String min_value) {
		this.min_value = min_value;
	}
	public String getMax_value() {
		return max_value;
	}
	public void setMax_value(String max_value) {
		this.max_value = max_value;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	





}
