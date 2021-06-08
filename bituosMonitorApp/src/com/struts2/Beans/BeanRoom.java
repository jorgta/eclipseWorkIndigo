/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.struts2.Beans;



import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


public class BeanRoom  {

	private int id;
	private BeanHotel id_hotel;
	private String description;
	private BeanType_Status_Room id_type_status_room;
	private Date date_reg;
	private int active;
	private String image1;
	private double price1;
	private double price2;
	private double price3;
	private double price4;
	private double priceBaby;
	private double priceBasic;
	private double priceChild;
	private double priceJr;
	private Date date_begin;
	private Date date_end;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanHotel getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(BeanHotel id_hotel) {
		this.id_hotel = id_hotel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public BeanType_Status_Room getId_type_status_room() {
		return id_type_status_room;
	}
	public void setId_type_status_room(BeanType_Status_Room id_type_status_room) {
		this.id_type_status_room = id_type_status_room;
	}
	public double getPrice1() {
		return price1;
	}
	public void setPrice1(double price1) {
		this.price1 = price1;
	}
	public double getPrice2() {
		return price2;
	}
	public void setPrice2(double price2) {
		this.price2 = price2;
	}
	public double getPrice3() {
		return price3;
	}
	public void setPrice3(double price3) {
		this.price3 = price3;
	}
	public double getPrice4() {
		return price4;
	}
	public void setPrice4(double price4) {
		this.price4 = price4;
	}
	public double getPriceBaby() {
		return priceBaby;
	}
	public void setPriceBaby(double priceBaby) {
		this.priceBaby = priceBaby;
	}
	public double getPriceBasic() {
		return priceBasic;
	}
	public void setPriceBasic(double priceBasic) {
		this.priceBasic = priceBasic;
	}
	public double getPriceChild() {
		return priceChild;
	}
	public void setPriceChild(double priceChild) {
		this.priceChild = priceChild;
	}
	public double getPriceJr() {
		return priceJr;
	}
	public void setPriceJr(double priceJr) {
		this.priceJr = priceJr;
	}
	public Date getDate_begin() {
		return date_begin;
	}
	public void setDate_begin(Date date_begin) {
		this.date_begin = date_begin;
	}
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	
	
	
	

}
