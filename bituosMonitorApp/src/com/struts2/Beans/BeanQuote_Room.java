/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.struts2.Beans;



import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


public class BeanQuote_Room  {

	private int id;
	private BeanQuote id_quote;
	private BeanRoom id_room;
	private String room_description;
	private String hotel_description;
	
		
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanRoom getId_room() {
		return id_room;
	}
	public void setId_room(BeanRoom id_room) {
		this.id_room = id_room;
	}
	public BeanQuote getId_quote() {
		return id_quote;
	}
	public void setId_quote(BeanQuote id_quote) {
		this.id_quote = id_quote;
	}
	public String getRoom_description() {
		return room_description;
	}
	public void setRoom_description(String room_description) {
		this.room_description = room_description;
	}
	public String getHotel_description() {
		return hotel_description;
	}
	public void setHotel_description(String hotel_description) {
		this.hotel_description = hotel_description;
	}
	
}
