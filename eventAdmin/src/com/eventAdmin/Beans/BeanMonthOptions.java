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

public class BeanMonthOptions{

	private int id;
	 
	
	 
	private int startDayOfWeek;
	private Boolean narrowWeekend;
	private int visibleWeeksCount;
	private int visibleScheduleCount;
	private String moreLayerSize;
	private String grid;
	private String scheduleFilter;
	private Boolean isAlways6Week;
	private Boolean workweek;
 
	List < Object > daynames = new ArrayList < Object > ();

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

	public int getVisibleWeeksCount() {
		return visibleWeeksCount;
	}

	public void setVisibleWeeksCount(int visibleWeeksCount) {
		this.visibleWeeksCount = visibleWeeksCount;
	}

	public int getVisibleScheduleCount() {
		return visibleScheduleCount;
	}

	public void setVisibleScheduleCount(int visibleScheduleCount) {
		this.visibleScheduleCount = visibleScheduleCount;
	}

	public String getMoreLayerSize() {
		return moreLayerSize;
	}

	public void setMoreLayerSize(String moreLayerSize) {
		this.moreLayerSize = moreLayerSize;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getScheduleFilter() {
		return scheduleFilter;
	}

	public void setScheduleFilter(String scheduleFilter) {
		this.scheduleFilter = scheduleFilter;
	}

 

	public Boolean getIsAlways6Week() {
		return isAlways6Week;
	}

	public void setIsAlways6Week(Boolean isAlways6Week) {
		this.isAlways6Week = isAlways6Week;
	}

	public Boolean getWorkweek() {
		return workweek;
	}

	public void setWorkweek(Boolean workweek) {
		this.workweek = workweek;
	}

	public List<Object> getDaynames() {
		return daynames;
	}

	public void setDaynames(List<Object> daynames) {
		this.daynames = daynames;
	}
 
 


}
