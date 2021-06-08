package com.eventAdmin.Beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Raw {
	 private String memo;
	 private boolean hasToOrCc;
	 private boolean hasRecurrenceRule;
	 private String location = null;
	 private String className;
	 private Creator creator;


	 // Getter Methods 

	 public String getMemo() {
	  return memo;
	 }

	 public boolean getHasToOrCc() {
	  return hasToOrCc;
	 }

	 public boolean getHasRecurrenceRule() {
	  return hasRecurrenceRule;
	 }

	 public String getLocation() {
	  return location;
	 }

	 @JsonProperty("class")
	 public String getClassName() {
	  return className;
	 }

	 

	 // Setter Methods 

	 public void setMemo(String memo) {
	  this.memo = memo;
	 }

	 public void setHasToOrCc(boolean hasToOrCc) {
	  this.hasToOrCc = hasToOrCc;
	 }

	 public void setHasRecurrenceRule(boolean hasRecurrenceRule) {
	  this.hasRecurrenceRule = hasRecurrenceRule;
	 }

	 public void setLocation(String location) {
	  this.location = location;
	 }

	 @JsonProperty("class")
	 public void setClassName(String classValue) {
	  this.className = classValue;
	 }

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	 


	}
