package com.bituos.bituosMonitor;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//Hibernate
import org.apache.struts2.ServletActionContext;
import org.hibernate.*;
import org.hibernate.cfg.*;

/*
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
*/

import com.bituos.*;
import com.bituos.bituosMonitor.Beans.*;
import com.struts2.Beans.BeanCompany;
import com.struts2.Beans.BeanCounter;
import com.struts2.Beans.BeanCounterLog;
import com.struts2.Beans.BeanCounterLogDetail;
import com.struts2.Beans.BeanDevice;
import com.struts2.Beans.BeanCommand;
import com.struts2.Beans.BeanType_Counter_Detail;

import com.bituos.bituosMonitor.Beans.Device;
import com.bituos.bituosMonitor.Beans.CountersData;

import com.bituos.AESCrypt;

public class Notification {
	
	public String getName()
	  {
		return "counterServer";
	  }

}
