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

public class  BeanInOutGroup{

	private int id;
	private Date starting_date;
	private Date ending_date;
	private BeanContactRHGroup idContactRHGroup;
	
	
	public BeanContactRHGroup getIdContactRHGroup() {
		return idContactRHGroup;
	}
	public void setIdContactRHGroup(BeanContactRHGroup idContactRHGroup) {
		this.idContactRHGroup = idContactRHGroup;
	}
	public Date getEnding_date() {
		return ending_date;
	}
	public void setEnding_date(Date ending_date) {
		this.ending_date = ending_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStarting_date() {
		return starting_date;
	}
	public void setStarting_date(Date starting_date) {
		this.starting_date = starting_date;
	}


	
	
}
