/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and

Comments
 */
package com.eventAdmin.Beans;

/**
 * @author cirm
*/

import java.util.Date;

public class BeanCreatorUser{

	private int id;
    private BeanUser userid;
    public BeanUser getUserid() {
		return userid;
	}
	public void setUserid(BeanUser userid) {
		this.userid = userid;
	}
	public BeanCreator getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(BeanCreator creatorid) {
		this.creatorid = creatorid;
	}
	private BeanCreator creatorid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
 
	
 
	
	
	

}
