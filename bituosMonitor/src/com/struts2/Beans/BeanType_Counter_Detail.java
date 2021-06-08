
package com.struts2.Beans;

/**
 * @author cirm
*/

public class BeanType_Counter_Detail{

	private int id;
	private BeanType_Counter_Master id_type_counter_master;
	private String name_type_counter_detail;
	private String description;
	private int active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanType_Counter_Master getId_type_counter_master() {
		return id_type_counter_master;
	}
	public void setId_type_counter_master(
			BeanType_Counter_Master id_type_counter_master) {
		this.id_type_counter_master = id_type_counter_master;
	}
	public String getName_type_counter_detail() {
		return name_type_counter_detail;
	}
	public void setName_type_counter_detail(String name_type_counter_detail) {
		this.name_type_counter_detail = name_type_counter_detail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	


}
