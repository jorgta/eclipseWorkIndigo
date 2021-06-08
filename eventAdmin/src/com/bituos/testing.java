package com.bituos;

import com.eventAdmin.Beans.BeanScheduleInfo;
import com.eventAdmin.Beans.BeanTypeHour;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
 

public class testing {

 
	
	
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, ParseException {

		String sDate1="2019-12-04";
		 
		//String dt = Utils.DateToStrV3(d) + "T";//"2019-11-12T19:30:00.000Z";
		Date d=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		 
		int starthour = Integer.valueOf(1);
		int durationhours = 3;
		
		isoFormat.setTimeZone(TimeZone.getTimeZone("MST"));
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(d); // sets calendar time/date
	    cal.add(Calendar.HOUR, starthour); // adds  hours
	    Date datetimestart= cal.getTime();

	    cal.setTime(datetimestart); // sets calendar time/date
	    cal.add(Calendar.HOUR, durationhours); // adds  hours
	    Date datetimeend= cal.getTime();
	    
	    System.out.println(isoFormat.format(datetimestart));
	    System.out.println(isoFormat.format(datetimeend));
		
 
		//String resltJsonString = new Gson().toJson(beanScheduleInfo);
		
		//System.out.println(resltJsonString);

	}

}
