/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.gjv.Beans;

import java.util.Date;
import com.bituos.gjv.Beans.*;
/**
 * @author dsa@bituos.com
*/

public class  BeanNotesGroup{

	private int id;
	private String note;
	private Date fecha;
	private BeanContactRHGroup idContactRH;
	
	
	public BeanContactRHGroup getIdContactRH() {
		return idContactRH;
	}
	public void setIdContactRH(BeanContactRHGroup idContactRH) {
		this.idContactRH = idContactRH;
	}


	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	
	
}
