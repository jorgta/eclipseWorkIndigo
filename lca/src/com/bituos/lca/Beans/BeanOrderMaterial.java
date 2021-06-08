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

public class BeanOrderMaterial{


	private int id;
	private BeanTestOrder id_order;
	private BeanMaterial id_material;
	private int qty;
	private String material_description;
	private int material_has_label;
	private int is_patient;
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
	public BeanMaterial getId_material() {
		return id_material;
	}
	public void setId_material(BeanMaterial id_material) {
		this.id_material = id_material;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getMaterial_description() {
		return material_description;
	}
	public void setMaterial_description(String material_description) {
		this.material_description = material_description;
	}
	public int getMaterial_has_label() {
		return material_has_label;
	}
	public void setMaterial_has_label(int material_has_label) {
		this.material_has_label = material_has_label;
	}
	public int getIs_patient() {
		return is_patient;
	}
	public void setIs_patient(int is_patient) {
		this.is_patient = is_patient;
	}
	
	

	
	
	
	

}
