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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BeanScheduleInfo{

	private int id;
	private String guid;
	private BeanCalendarProps calendarId;// = null;
    private String title;//  = null;
    private String body;//  = null;
    private Boolean isAllday;//  = false;
    private String start;//  = null;
    private String end;//  = null;
    private String category;//  = '';
    private String dueDateClass;//  = '';

    private String color;//  = null;
    private String bgColor;//  = null;
    private String dragBgColor;//  = null;
    private String borderColor;//  = null;
    private String customStyle;//  = '';

    private Boolean isFocused;// = false;
    private Boolean isPending;// = false;
    private Boolean isVisible;// = true;
    private Boolean isReadOnly;// = false;
    private float goingDuration;// = 0;
    private float comingDuration;// = 0;
    private String recurrenceRule;// = '';
	private Boolean isPrivate;
	private String location;
 
    private BeanRaw raw;
    //private BeansScheduleinfoAttendees beansScheduleinfoAttendees;
    
    List < Object > attendees = new ArrayList < Object > ();
    
     

  
    public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	 
	
	
	public BeanCalendarProps getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(BeanCalendarProps calendarId) {
		this.calendarId = calendarId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
 
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDueDateClass() {
		return dueDateClass;
	}
	public void setDueDateClass(String dueDateClass) {
		this.dueDateClass = dueDateClass;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public String getDragBgColor() {
		return dragBgColor;
	}
	public void setDragBgColor(String dragBgColor) {
		this.dragBgColor = dragBgColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getCustomStyle() {
		return customStyle;
	}
	public void setCustomStyle(String customStyle) {
		this.customStyle = customStyle;
	}
	 
 
	 
	public float getGoingDuration() {
		return goingDuration;
	}
	public void setGoingDuration(float goingDuration) {
		this.goingDuration = goingDuration;
	}
	public float getComingDuration() {
		return comingDuration;
	}
	public void setComingDuration(float comingDuration) {
		this.comingDuration = comingDuration;
	}
	public void setComingDuration(int comingDuration) {
		this.comingDuration = comingDuration;
	}
 
	public String getRecurrenceRule() {
		return recurrenceRule;
	}
	public void setRecurrenceRule(String recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}
	public Boolean getIsAllday() {
		return isAllday;
	}
	public void setIsAllday(Boolean isAllday) {
		this.isAllday = isAllday;
	}
	public Boolean getIsFocused() {
		return isFocused;
	}
	public void setIsFocused(Boolean isFocused) {
		this.isFocused = isFocused;
	}
	public Boolean getIsPending() {
		return isPending;
	}
	public void setIsPending(Boolean isPending) {
		this.isPending = isPending;
	}
	public Boolean getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}
	public Boolean getIsReadOnly() {
		return isReadOnly;
	}
	public void setIsReadOnly(Boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	public BeanRaw getRaw() {
		return raw;
	}
	public void setRaw(BeanRaw raw) {
		this.raw = raw;
	}
	public List<Object> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<Object> attendees) {
		this.attendees = attendees;
	}
	 
	
	/*
	public BeansScheduleinfoAttendees getBeansScheduleinfoAttendees() {
		return beansScheduleinfoAttendees;
	}
	public void setBeansScheduleinfoAttendees(
			BeansScheduleinfoAttendees beansScheduleinfoAttendees) {
		this.beansScheduleinfoAttendees = beansScheduleinfoAttendees;
	}*/
 
	
	


}
