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

public class BeanRaw{

	private int id;
	private String memo;
	private Boolean hasToOrCc;
	private Boolean hasRecurrenceRule;
	private String location;
	private String className;
	private BeanCreator creator;
	private Boolean active;
 
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
 
 
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public BeanCreator getCreator() {
		return creator;
	}
	public Boolean getHasToOrCc() {
		return hasToOrCc;
	}
	public void setHasToOrCc(Boolean hasToOrCc) {
		this.hasToOrCc = hasToOrCc;
	}
 
 
	public Boolean getHasRecurrenceRule() {
		return hasRecurrenceRule;
	}
	public void setHasRecurrenceRule(Boolean hasRecurrenceRule) {
		this.hasRecurrenceRule = hasRecurrenceRule;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public void setCreator(BeanCreator creator) {
		this.creator = creator;
	}
 
 
	
	 
	
	/*
	"raw":{"memo":"Vur sed revtuar irgu vu zotize ir rudomvit uwu hafihi jodi gauso liduwha velodom vatwo zuece ir aje.","hasToOrCc":false,"hasRecurrenceRule":false,"location":null,"class":"public",
		  "creator":{"name":"Lulu Greer","avatar":"//www.gravatar.com/avatar/f4f3c8603546553104da08fa9506f824","company":"Comcast Corp.","email":"hiwbupoj@iwa.yt","phone":"(633) 627-6758"}
	},

	 */

	
	

}
