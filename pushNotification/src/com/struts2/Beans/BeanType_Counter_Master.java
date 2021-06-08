
package com.struts2.Beans;

/**
 * @author cirm
*/

public class BeanType_Counter_Master{

	private int id;
	private String name_type_counter_master;
	private String description;
	private BeanTypeOS id_type_os;
	private int active;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_type_counter_master() {
		return name_type_counter_master;
	}
	public void setName_type_counter_master(String name_type_counter_master) {
		this.name_type_counter_master = name_type_counter_master;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BeanTypeOS getId_type_os() {
		return id_type_os;
	}
	public void setId_type_os(BeanTypeOS id_type_os) {
		this.id_type_os = id_type_os;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	


}
