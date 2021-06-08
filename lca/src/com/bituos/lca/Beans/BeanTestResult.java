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
	private String test_name;
	private String test_parameter_name;
	private double value;
	private String value_str;
	private Date date_reg;
	private double min_value;
	private double max_value;
	private String min_value_str;
	private String max_value_str;
	private int is_numeric;
	private int must_compare;
	private int is_free_text;
	private String value_free_text;
	
	
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
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getMin_value() {
		return min_value;
	}
	public void setMin_value(double min_value) {
		this.min_value = min_value;
	}
	public double getMax_value() {
		return max_value;
	}
	public void setMax_value(double max_value) {
		this.max_value = max_value;
	}

	public String getMin_value_str() {
		return min_value_str;
	}
	public void setMin_value_str(String min_value_str) {
		this.min_value_str = min_value_str;
	}
	public String getMax_value_str() {
		return max_value_str;
	}
	public void setMax_value_str(String max_value_str) {
		this.max_value_str = max_value_str;
	}
	public int getMust_compare() {
		return must_compare;
	}
	public void setMust_compare(int must_compare) {
		this.must_compare = must_compare;
	}
	public int getIs_free_text() {
		return is_free_text;
	}
	public void setIs_free_text(int is_free_text) {
		this.is_free_text = is_free_text;
	}
	public String getValue_free_text() {
		return value_free_text;
	}
	public void setValue_free_text(String value_free_text) {
		this.value_free_text = value_free_text;
	}





}
