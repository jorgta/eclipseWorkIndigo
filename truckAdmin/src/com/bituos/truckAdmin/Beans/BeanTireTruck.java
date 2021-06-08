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

public class BeanTireTruck{

	private int id;
	private BeanTire id_tire;
	private BeanTruck id_truck;
    private BeanTypePosition id_type_position;
    private BeanTruckTireConfigurationDetail position;
    private String active;
    
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
	public BeanTruck getId_truck() {
		return id_truck;
	}
	public void setId_truck(BeanTruck id_truck) {
		this.id_truck = id_truck;
	}
	public BeanTypePosition getId_type_position() {
		return id_type_position;
	}
	public void setId_type_position(BeanTypePosition id_type_position) {
		this.id_type_position = id_type_position;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public BeanTruckTireConfigurationDetail getPosition() {
		return position;
	}
	public void setPosition(BeanTruckTireConfigurationDetail position) {
		this.position = position;
	}

	

    
    

}
