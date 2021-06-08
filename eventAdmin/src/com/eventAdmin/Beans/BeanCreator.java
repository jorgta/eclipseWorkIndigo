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

public class BeanCreator{

	private int id;
	private String name;
	private String avatar;
	private String company;
	private String email;
	private String phone;
	private Boolean active;// = false;
	
 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	/*
	"creator":
	{
	"name":"Lulu Greer",
	"avatar":"//www.gravatar.com/avatar/f4f3c8603546553104da08fa9506f824",
	"company":"Comcast Corp.",
	"email":"hiwbupoj@iwa.yt",
	"phone":"(633) 627-6758"}
	*/
	
	
	

}
