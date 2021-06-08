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


import java.util.Date;

public class BeanTireMovements{

	
	private int id; 
	private BeanTire  id_tire; 
	private Date date_reg; 
	private Double deep; 
	private Double measurentment; 
	private BeanTypeMeasure id_type_measure;  
	private BeanTypePlace id_type_place; 
	private int km_int; 
	private int km_truck; 
	private String design; 
	private Double price; 
    private String  buy_of_reference;
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanTire getId_tire() {
		return id_tire;
	}
	public void setId_tire(BeanTire id_tire) {
		this.id_tire = id_tire;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public Double getDeep() {
		return deep;
	}
	public void setDeep(Double deep) {
		this.deep = deep;
	}
	public Double getMeasurentment() {
		return measurentment;
	}
	public void setMeasurentment(Double measurentment) {
		this.measurentment = measurentment;
	}
	public BeanTypeMeasure getId_type_measure() {
		return id_type_measure;
	}
	public void setId_type_measure(BeanTypeMeasure id_type_measure) {
		this.id_type_measure = id_type_measure;
	}


	
	public BeanTypePlace getId_type_place() {
		return id_type_place;
	}
	public void setId_type_place(BeanTypePlace id_type_place) {
		this.id_type_place = id_type_place;
	}
	public int getKm_int() {
		return km_int;
	}
	public void setKm_int(int km_int) {
		this.km_int = km_int;
	}
	public int getKm_truck() {
		return km_truck;
	}
	public void setKm_truck(int km_truck) {
		this.km_truck = km_truck;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}


	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getBuy_of_reference() {
		return buy_of_reference;
	}
	public void setBuy_of_reference(String buy_of_reference) {
		this.buy_of_reference = buy_of_reference;
	} 

	





}
