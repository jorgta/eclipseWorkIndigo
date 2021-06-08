/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.truckAdmin.Beans;

/**
 * @author cirm
*/

public class BeanTire{


	
	private int id;			
	private  String design;		
	private  int deep;			
	private  BeanTypeTireStatus id_type_tire_status;		
	private  String  serial_number;	
	private  BeanTypeMeasure id_type_measure;		
	private  String active;			
	private  BeanTypeUnitOfMeasure id_type_unit_of_measure;	
	private  int km_int;			
	private  int position;			
	private  BeanTypePlace id_type_place;			
		

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}


	

	public BeanTypeTireStatus getId_type_tire_status() {
		return id_type_tire_status;
	}

	public void setId_type_tire_status(BeanTypeTireStatus id_type_tire_status) {
		this.id_type_tire_status = id_type_tire_status;
	}


	

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}




	public BeanTypeMeasure getId_type_measure() {
		return id_type_measure;
	}

	public void setId_type_measure(BeanTypeMeasure id_type_measure) {
		this.id_type_measure = id_type_measure;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public BeanTypeUnitOfMeasure getId_type_unit_of_measure() {
		return id_type_unit_of_measure;
	}

	public void setId_type_unit_of_measure(BeanTypeUnitOfMeasure id_type_unit_of_measure) {
		this.id_type_unit_of_measure = id_type_unit_of_measure;
	}

	public int getKm_int() {
		return km_int;
	}

	public void setKm_int(int km_int) {
		this.km_int = km_int;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}



	public BeanTypePlace getId_type_place() {
		return id_type_place;
	}

	public void setId_type_place(BeanTypePlace id_type_place) {
		this.id_type_place = id_type_place;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}







}
