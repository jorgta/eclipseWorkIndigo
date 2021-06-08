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

public class BeanWeekOptions{

	private int id;
	 
	
	 
	private int startDayOfWeek;
	private Boolean narrowWeekend; 
	private Boolean workweek;
	private Boolean showTimezoneCollapseButton;
	private Boolean timezonesCollapsed;
	private int hourStart;
	private int hourEnd;
	private List < Object > daynames = new ArrayList < Object > ();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStartDayOfWeek() {
		return startDayOfWeek;
	}
	public void setStartDayOfWeek(int startDayOfWeek) {
		this.startDayOfWeek = startDayOfWeek;
	}
	public Boolean getNarrowWeekend() {
		return narrowWeekend;
	}
	public void setNarrowWeekend(Boolean narrowWeekend) {
		this.narrowWeekend = narrowWeekend;
	}
	public Boolean getWorkweek() {
		return workweek;
	}
	public void setWorkweek(Boolean workweek) {
		this.workweek = workweek;
	}
	public Boolean getShowTimezoneCollapseButton() {
		return showTimezoneCollapseButton;
	}
	public void setShowTimezoneCollapseButton(Boolean showTimezoneCollapseButton) {
		this.showTimezoneCollapseButton = showTimezoneCollapseButton;
	}
	public Boolean getTimezonesCollapsed() {
		return timezonesCollapsed;
	}
	public void setTimezonesCollapsed(Boolean timezonesCollapsed) {
		this.timezonesCollapsed = timezonesCollapsed;
	}
	public int getHourStart() {
		return hourStart;
	}
	public void setHourStart(int hourStart) {
		this.hourStart = hourStart;
	}
	public int getHourEnd() {
		return hourEnd;
	}
	public void setHourEnd(int hourEnd) {
		this.hourEnd = hourEnd;
	}
	public List<Object> getDaynames() {
		return daynames;
	}
	public void setDaynames(List<Object> daynames) {
		this.daynames = daynames;
	}

 
	
	


}
