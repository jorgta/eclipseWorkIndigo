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

public class BeanOptions{

	private int id;
 
	private String defaultView;
	private String taskView;
	private String scheduleView;
	private String theme;
	private BeanTemplate template;
	private BeanWeekOptions week;
	private BeanMonthOptions month;
	private Boolean useCreationPopup;
	private Boolean useDetailPopup;
	//private String timezones;
	private Boolean disableDblClick;
	private Boolean disableClick;
	private Boolean isReadOnly;
	private Boolean usageStatistics;
	
	
	List < Object > calendars = new ArrayList < Object > ();
	List < Object > timezones = new ArrayList < Object > ();
 
	
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDefaultView() {
		return defaultView;
	}
	public void setDefaultView(String defaultView) {
		this.defaultView = defaultView;
	}
	public String getTaskView() {
		return taskView;
	}
	public void setTaskView(String taskView) {
		this.taskView = taskView;
	}
	public String getScheduleView() {
		return scheduleView;
	}
	public void setScheduleView(String scheduleView) {
		this.scheduleView = scheduleView;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
 
	
	
	public BeanTemplate getTemplate() {
		return template;
	}
	public void setTemplate(BeanTemplate template) {
		this.template = template;
	}
	public BeanWeekOptions getWeek() {
		return week;
	}
	public void setWeek(BeanWeekOptions week) {
		this.week = week;
	}
	public BeanMonthOptions getMonth() {
		return month;
	}
	public void setMonth(BeanMonthOptions month) {
		this.month = month;
	}
	public Boolean getUseCreationPopup() {
		return useCreationPopup;
	}
	public void setUseCreationPopup(Boolean useCreationPopup) {
		this.useCreationPopup = useCreationPopup;
	}
	public Boolean getUseDetailPopup() {
		return useDetailPopup;
	}
	public void setUseDetailPopup(Boolean useDetailPopup) {
		this.useDetailPopup = useDetailPopup;
	}
	public Boolean getDisableDblClick() {
		return disableDblClick;
	}
	public void setDisableDblClick(Boolean disableDblClick) {
		this.disableDblClick = disableDblClick;
	}
	public Boolean getDisableClick() {
		return disableClick;
	}
	public void setDisableClick(Boolean disableClick) {
		this.disableClick = disableClick;
	}
	public Boolean getIsReadOnly() {
		return isReadOnly;
	}
	public void setIsReadOnly(Boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	public Boolean getUsageStatistics() {
		return usageStatistics;
	}
	public void setUsageStatistics(Boolean usageStatistics) {
		this.usageStatistics = usageStatistics;
	}
	public List<Object> getCalendars() {
		return calendars;
	}
	public void setCalendars(List<Object> calendars) {
		this.calendars = calendars;
	}
	public List<Object> getTimezones() {
		return timezones;
	}
	public void setTimezones(List<Object> timezones) {
		this.timezones = timezones;
	}

 
 
	


}
