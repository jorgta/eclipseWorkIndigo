package com.eventAdmin.Beans;

import java.util.ArrayList;

 
public class Schedule {
	 private String id = null;
	 private String guid = null;
	 private String calendarId;
	 private String title;
	 private String body;
	 private boolean isAllday;
	 private String start;
	 private String end;
	 private String category;
	 private String dueDateClass;
	 private String color;
	 private String bgColor;
	 private String dragBgColor;
	 private String borderColor;
	 private String customStyle;
	 private boolean isFocused;
	 private boolean isPending;
	 private boolean isVisible;
	 private boolean isReadOnly;
	 private float goingDuration;
	 private float comingDuration;
	 private String recurrenceRule;
	 private Raw raw;
	 private boolean isPrivate;
	 private String location;
	 ArrayList < Object > attendees = new ArrayList < Object > ();


	 // Getter Methods 

	 public String getId() {
	  return id;
	 }

	 public String getGuid() {
	  return guid;
	 }

	 public String getCalendarId() {
	  return calendarId;
	 }

	 public String getTitle() {
	  return title;
	 }

	 public String getBody() {
	  return body;
	 }

	 public boolean getIsAllday() {
	  return isAllday;
	 }

	 public String getStart() {
	  return start;
	 }

	 public String getEnd() {
	  return end;
	 }

	 public String getCategory() {
	  return category;
	 }

	 public String getDueDateClass() {
	  return dueDateClass;
	 }

	 public String getColor() {
	  return color;
	 }

	 public String getBgColor() {
	  return bgColor;
	 }

	 public String getDragBgColor() {
	  return dragBgColor;
	 }

	 public String getBorderColor() {
	  return borderColor;
	 }

	 public String getCustomStyle() {
	  return customStyle;
	 }

	 public boolean getIsFocused() {
	  return isFocused;
	 }

	 public boolean getIsPending() {
	  return isPending;
	 }

	 public boolean getIsVisible() {
	  return isVisible;
	 }

	 public boolean getIsReadOnly() {
	  return isReadOnly;
	 }

	 public float getGoingDuration() {
	  return goingDuration;
	 }

	 public float getComingDuration() {
	  return comingDuration;
	 }

 
	 

	 public String getRecurrenceRule() {
		return recurrenceRule;
	}

	public void setRecurrenceRule(String recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}

	public boolean getIsPrivate() {
	  return isPrivate;
	 }

	 public String getLocation() {
	  return location;
	 }

	 // Setter Methods 

	 public void setId(String id) {
	  this.id = id;
	 }

	 public void setGuid(String guid) {
	  this.guid = guid;
	 }

	 public void setCalendarId(String calendarId) {
	  this.calendarId = calendarId;
	 }

	 public void setTitle(String title) {
	  this.title = title;
	 }

	 public void setBody(String body) {
	  this.body = body;
	 }

	 public void setIsAllday(boolean isAllday) {
	  this.isAllday = isAllday;
	 }

	 public void setStart(String start) {
	  this.start = start;
	 }

	 public void setEnd(String end) {
	  this.end = end;
	 }

	 public void setCategory(String category) {
	  this.category = category;
	 }

	 public void setDueDateClass(String dueDateClass) {
	  this.dueDateClass = dueDateClass;
	 }

	 public void setColor(String color) {
	  this.color = color;
	 }

	 public void setBgColor(String bgColor) {
	  this.bgColor = bgColor;
	 }

	 public void setDragBgColor(String dragBgColor) {
	  this.dragBgColor = dragBgColor;
	 }

	 public void setBorderColor(String borderColor) {
	  this.borderColor = borderColor;
	 }

	 public void setCustomStyle(String customStyle) {
	  this.customStyle = customStyle;
	 }

	 public void setIsFocused(boolean isFocused) {
	  this.isFocused = isFocused;
	 }

	 public void setIsPending(boolean isPending) {
	  this.isPending = isPending;
	 }

	 public void setIsVisible(boolean isVisible) {
	  this.isVisible = isVisible;
	 }

	 public void setIsReadOnly(boolean isReadOnly) {
	  this.isReadOnly = isReadOnly;
	 }

	 public void setGoingDuration(float goingDuration) {
	  this.goingDuration = goingDuration;
	 }

	 public void setComingDuration(float comingDuration) {
	  this.comingDuration = comingDuration;
	 }

	 

	 public void setIsPrivate(boolean isPrivate) {
	  this.isPrivate = isPrivate;
	 }

	 public void setLocation(String location) {
	  this.location = location;
	 }

	 

	public Raw getRaw() {
		return raw;
	}

	public void setRaw(Raw raw) {
		this.raw = raw;
	}

	public ArrayList<Object> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<Object> attendees) {
		this.attendees = attendees;
	}

	public void setAllday(boolean isAllday) {
		this.isAllday = isAllday;
	}

	public void setFocused(boolean isFocused) {
		this.isFocused = isFocused;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	 
	 
	 
	 
	}

