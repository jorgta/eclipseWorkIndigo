package com.bituos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Transaction;

import com.eventAdmin.Beans.*;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

public class CalendarSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s ="";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
 
		String proccess = request.getParameter("proccess");
		 
		String id = request.getParameter("id");
		String dir = request.getParameter("dir");
		String orderField = request.getParameter("orderField");
		String bean = request.getParameter("bean");
		String listName = request.getParameter("listName");
		String fields = request.getParameter("fields");
		String searchValue  = request.getParameter("searchValue");
		String searchParam = request.getParameter("searchParam");
		String searchExtraParam = request.getParameter("searchExtraParam");
		String searchExtraValue  = request.getParameter("searchExtraValue");
		String searchLIKEParam = request.getParameter("searchLIKEParam");
		String searchLIKEValue  = request.getParameter("searchLIKEValue");
		
		String active = request.getParameter("active");
		String query =  "";		
		String queryFilter = "" ;
		List listObjects = null;
		
		
		HttpSession ses = request.getSession();
		net.sf.hibernate.Session sessionHibernate= null;
		com.bituos.Config configuration = new com.bituos.Config( request );
		net.sf.hibernate.SessionFactory sessionFactory;
		Transaction tx = null;
		try {
			
			 
			if ( proccess.equals("testing") )
			{
				sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
				sessionHibernate = sessionFactory.openSession();
	
				
				
				String resltJsonString ="";
				 
				query =  " FROM " + bean + " u" +
						 " WHERE u." + searchParam + "=" + searchValue;
				
				
				listObjects = sessionHibernate.createQuery( query ).list();
				
				resltJsonString = new Gson().toJson(listObjects);
				
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				System.out.println(resltJsonString);

				out.print(resltJsonString);
				out.flush();
				out.close();
				
			}
 
			BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			if(!beanUser.equals(null))
			{
				if ( proccess.equals("getinfo") )
				  {
	
					sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
					sessionHibernate = sessionFactory.openSession();
					
					
					if (listName != null)
					{
						ses.removeAttribute(listName );
					}
					else
					{
						listName = "listObjets";
					}
					
					
					
					if(searchParam != null)
					{
					  queryFilter = " WHERE u." + searchParam + "='" + searchValue + "' " ;
					  //sort = searchParam;
					  
					}
					
					
					if(searchExtraParam != null)
					{
					  queryFilter += " AND u." + searchExtraParam + " LIKE '%" + searchExtraValue + "'" ;
					  //sort = searchParam;
					  
					}
					
					if(searchLIKEValue != null)
					{
						queryFilter += " LIKE '%" + searchLIKEValue + "'" ;
					  //sort = searchParam;
					  
					}
					
					
		
					if(dir != null)
					{
						queryFilter += " " + dir ;
					  //sort = searchParam;
					}
					else
					{
						dir = "asc";
					}
					
					
					if(active != null)
					{
						if (searchParam != null)
						{
							queryFilter += " AND u.active = '"+ active +"'";
						}
						else
						{
							queryFilter += " WHERE u.active = '"+ active +"'";
						}
					  //sort = searchParam;
					}
					
					if(orderField != null)
					{
						queryFilter += " ORDER BY u." + orderField;
					  //sort = searchParam;
					}
					
					if(bean != null)
					{
						query =  " FROM " + bean + " u" +
						queryFilter;
						
						listObjects = sessionHibernate.createQuery( query ).list();
						ses.setAttribute(listName, listObjects );
						
						
						 
					
						sessionHibernate.close();
						sessionFactory.close();
						
						
						String resltJsonString = new Gson().toJson(listObjects);
						
						PrintWriter out = response.getWriter();
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						System.out.println(resltJsonString);
		
						out.print(resltJsonString);
						out.flush();
						out.close();
					}
			      }
				else if ( proccess.equals("scheduleinfoFull") )
				  {
					sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
					sessionHibernate = sessionFactory.openSession();
					
				 
					String resltJsonString ="";
	 
					query =  " FROM " + bean + " u" +
							 " WHERE u.calendarId.id=" + id;
					
					
					listObjects = sessionHibernate.createQuery( query ).list();
					ses.setAttribute(listName, listObjects );
					
					
					
					
					
					
					ArrayList<Object>  schedulejasonList= new  ArrayList<Object>();
					
		 
					
					Iterator<BeanScheduleInfo> beanScheduleInfoIterator = listObjects.iterator();
					while (beanScheduleInfoIterator.hasNext()) {
						Schedule schedulejason = new Schedule();
						
						BeanScheduleInfo beanScheduleInfo = beanScheduleInfoIterator.next();
						
						
						schedulejason.setId(beanScheduleInfo.getGuid());
						schedulejason.setGuid(beanScheduleInfo.getGuid());
			 
						schedulejason.setCalendarId(String.valueOf( beanScheduleInfo.getCalendarId().getId() ) );
						schedulejason.setTitle(beanScheduleInfo.getTitle());
					    schedulejason.setBody(beanScheduleInfo.getBody());
					    schedulejason.setIsAllday(beanScheduleInfo.getIsAllday());
					    schedulejason.setStart(beanScheduleInfo.getStart());
					    schedulejason.setEnd(beanScheduleInfo.getEnd());
					    schedulejason.setCategory(beanScheduleInfo.getCategory());
						schedulejason.setDueDateClass(beanScheduleInfo.getDueDateClass());
						schedulejason.setColor(beanScheduleInfo.getColor());
						schedulejason.setBgColor(beanScheduleInfo.getBgColor());
						schedulejason.setDragBgColor(beanScheduleInfo.getDragBgColor());
						schedulejason.setBorderColor(beanScheduleInfo.getBorderColor());
						schedulejason.setCustomStyle(beanScheduleInfo.getCustomStyle());
						schedulejason.setIsFocused(beanScheduleInfo.getIsFocused());
						schedulejason.setIsPending(beanScheduleInfo.getIsPending());
						schedulejason.setIsVisible(beanScheduleInfo.getIsVisible());
						schedulejason.setIsReadOnly(beanScheduleInfo.getIsReadOnly());
						
						schedulejason.setGoingDuration(beanScheduleInfo.getGoingDuration());
						schedulejason.setComingDuration(beanScheduleInfo.getComingDuration());
						
						schedulejason.setRecurrenceRule(beanScheduleInfo.getRecurrenceRule());
						
						Raw raw = new Raw();
						raw.setMemo(beanScheduleInfo.getRaw().getMemo());
						raw.setHasToOrCc(beanScheduleInfo.getRaw().getHasToOrCc());
						raw.setHasRecurrenceRule(beanScheduleInfo.getRaw().getHasRecurrenceRule());
						raw.setLocation(beanScheduleInfo.getRaw().getLocation());
						raw.setClassName(beanScheduleInfo.getRaw().getClassName());
						//raw.setActive(true);
						
						
						
			
						Creator creator= new Creator();
						creator.setName(beanScheduleInfo.getRaw().getCreator().getName());
						creator.setAvatar(beanScheduleInfo.getRaw().getCreator().getAvatar());
						creator.setCompany(beanScheduleInfo.getRaw().getCreator().getCompany());
						creator.setEmail(beanScheduleInfo.getRaw().getCreator().getEmail());
						creator.setPhone(beanScheduleInfo.getRaw().getCreator().getPhone());
						//creator.setActive(beanScheduleInfo.getRaw().getCreator().getActive());
						
						raw.setCreator(creator);
						schedulejason.setRaw(raw);
						
						
						schedulejason.setIsPrivate(beanScheduleInfo.getIsPrivate());
						schedulejason.setLocation(beanScheduleInfo.getLocation());
						
						
						
						query =  " FROM BeansScheduleinfoAttendees u"+
								 " where u.scheduleinfo.id =" + beanScheduleInfo.getId() ;
						
						ArrayList<Object>  attendeesList= new  ArrayList<Object>();
						
						ArrayList<BeansScheduleinfoAttendees> beansScheduleinfoAttendeesList = (ArrayList<BeansScheduleinfoAttendees>) sessionHibernate.createQuery( query ).list();
						
						Iterator<BeansScheduleinfoAttendees> beansScheduleinfoAttendeesIterator = beansScheduleinfoAttendeesList.iterator();
						while (beansScheduleinfoAttendeesIterator.hasNext()) {
							
							BeansScheduleinfoAttendees beansScheduleinfoAttendees = beansScheduleinfoAttendeesIterator.next();
							attendeesList.add(beansScheduleinfoAttendees.getAttendees().getName());
						}
						
						schedulejason.setAttendees(attendeesList);
						
						schedulejasonList.add(schedulejason);
						
		 
						
						
					}
					
					resltJsonString = new Gson().toJson(schedulejasonList);
					
					sessionHibernate.close();
					sessionFactory.close();
					
					
					
					
					PrintWriter out = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					System.out.println(resltJsonString);
	
					out.print(resltJsonString);
					out.flush();
					out.close();
					
					
					
				  }
				else if ( proccess.equals("saveScheduleInfoToDatabase") )
				  {
		 
					String schedule = request.getParameter("schedule");
					
					
	 
				 
					//bean= "MappingDynamicJSON";
					ObjectMapper objectMapper = new ObjectMapper();
					//objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
					objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
					objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
					objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
					//MappingDynamicJSON product; 
					byte[] bytes;
					UUID uuid;
					Schedule schedulejason;
					
					try {
							sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
							sessionHibernate = sessionFactory.openSession();
						
							tx = sessionHibernate.beginTransaction();
							BeanScheduleInfo beanScheduleInfo = new BeanScheduleInfo();
							schedulejason = objectMapper.readValue(schedule, Schedule.class);
							
							
							
							bytes = schedulejason.getCalendarId().toString().getBytes("UTF-8");
							uuid = UUID.nameUUIDFromBytes(bytes);
				 
							
							
							beanScheduleInfo.setGuid(uuid.toString());
							 
							
							BeanCalendarProps beanCategoryCalendar = (BeanCalendarProps) sessionHibernate.load( BeanCalendarProps.class, new Integer( schedulejason.getCalendarId() ) );
							beanScheduleInfo.setCalendarId(beanCategoryCalendar);
							beanScheduleInfo.setTitle(schedulejason.getTitle());
						    beanScheduleInfo.setBody(schedulejason.getBody());
						    beanScheduleInfo.setIsAllday(schedulejason.getIsAllday());
						    beanScheduleInfo.setStart(schedulejason.getStart());
						    beanScheduleInfo.setEnd(schedulejason.getEnd());
						    beanScheduleInfo.setCategory(schedulejason.getCategory());
							beanScheduleInfo.setDueDateClass(schedulejason.getDueDateClass());
							beanScheduleInfo.setColor(schedulejason.getColor());
							beanScheduleInfo.setBgColor(schedulejason.getBgColor());
							beanScheduleInfo.setDragBgColor(schedulejason.getDragBgColor());
							beanScheduleInfo.setBorderColor(schedulejason.getBorderColor());
							beanScheduleInfo.setCustomStyle(schedulejason.getCustomStyle());
							beanScheduleInfo.setIsFocused(schedulejason.getIsFocused());
							beanScheduleInfo.setIsPending(schedulejason.getIsPending());
							beanScheduleInfo.setIsVisible(schedulejason.getIsVisible());
							beanScheduleInfo.setIsReadOnly(schedulejason.getIsReadOnly());
							
							beanScheduleInfo.setGoingDuration(schedulejason.getGoingDuration());
							beanScheduleInfo.setComingDuration(schedulejason.getComingDuration());
							
							beanScheduleInfo.setRecurrenceRule(schedulejason.getRecurrenceRule());
							
							
							BeanRaw raw = new BeanRaw();
							raw.setMemo(schedulejason.getRaw().getMemo());
							raw.setHasToOrCc(schedulejason.getRaw().getHasToOrCc());
							raw.setHasRecurrenceRule(schedulejason.getRaw().getHasRecurrenceRule());
							raw.setLocation(schedulejason.getRaw().getLocation());
							raw.setClassName(schedulejason.getRaw().getClassName());
							raw.setActive(true);
							//raw.save();
							
							
							 
							query =  " FROM BeanCreatorUser u" +
							 		 " WHERE u.userid.id=" + beanUser.getId();
					
					
							listObjects = sessionHibernate.createQuery( query ).list();
							BeanCreator creator= null;
							BeanCreatorUser beanCreatorUser = null;
							if(listObjects.isEmpty())
							{
								creator= new BeanCreator();
								
								/*creator.setName(schedulejason.getRaw().getCreator().getName());
								creator.setAvatar(schedulejason.getRaw().getCreator().getAvatar());
								creator.setCompany(schedulejason.getRaw().getCreator().getCompany());
								creator.setEmail(schedulejason.getRaw().getCreator().getEmail());
								creator.setPhone(schedulejason.getRaw().getCreator().getPhone());*/
								
								creator.setName(beanUser.getName());
								//creator.setAvatar(schedulejason.getRaw().getCreator().getAvatar());
								creator.setCompany(beanUser.getId_company().getName());
								creator.setEmail(beanUser.getEmail());
								//creator.setPhone(schedulejason.getRaw().getCreator().getPhone());
								
								
								creator.setActive(true);
								sessionHibernate.save( creator );
								tx.commit();
								
								beanCreatorUser = new BeanCreatorUser();
								beanCreatorUser.setCreatorid(creator);
								beanCreatorUser.setUserid(beanUser);
								sessionHibernate.save( beanCreatorUser );
								tx.commit();
							}
							else
							{
								beanCreatorUser =(BeanCreatorUser)listObjects.get(0);
								creator= beanCreatorUser.getCreatorid();
							}
							
							
							
							
							
							
							raw.setCreator(creator);
							
							sessionHibernate.save( raw );
							tx.commit();
	 
							beanScheduleInfo.setRaw(raw);
							
						    
							
							beanScheduleInfo.setIsPrivate(schedulejason.getIsPrivate());
							beanScheduleInfo.setLocation(schedulejason.getLocation());
							
							 
							 
							ArrayList<Object>  attendees= schedulejason.getAttendees();
							ArrayList<Object>  beansScheduleinfoAttendeesList= new  ArrayList<Object>();
							
							Iterator<Object> attendeesIterator = attendees.iterator();
							while (attendeesIterator.hasNext()) {
								String name1 = (String)attendeesIterator.next();
								BeanAttendees beanAttendees = new BeanAttendees();
								beanAttendees.setName(name1);
								//beanAttendees.setActive(true);
								sessionHibernate.save( beanAttendees );
								//beanAttendees.save();
								
								BeansScheduleinfoAttendees beansScheduleinfoAttendees = new BeansScheduleinfoAttendees();
								beansScheduleinfoAttendees.setAttendees(beanAttendees);
								beansScheduleinfoAttendees.setScheduleinfo(beanScheduleInfo);
								
								//beanScheduleInfo.setBeansScheduleinfoAttendees(beansScheduleinfoAttendees);
								beansScheduleinfoAttendeesList.add(beansScheduleinfoAttendees);
								
							}
						 
	  
							sessionHibernate.save( beanScheduleInfo );
							
							
							
							Iterator<Object> beansScheduleinfoAttendeesIterator = beansScheduleinfoAttendeesList.iterator();
							while (beansScheduleinfoAttendeesIterator.hasNext()) {
								sessionHibernate.save( (BeansScheduleinfoAttendees)beansScheduleinfoAttendeesIterator.next() );
							}
							
			
							tx.commit();
							
							
							sessionHibernate.close();
							sessionFactory.close();
							
							
							PrintWriter out = response.getWriter();
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							System.out.println(uuid.toString());
							
							String resltJsonString = new Gson().toJson(beanScheduleInfo);
							
	
							out.print(resltJsonString);
							out.flush();
							out.close();
							
							
							  /*
							 product = objectMapper.readValue(schedule, MappingDynamicJSON.class);
							Field[] field;
							try {
								String pack="com.eventAdmin.Beans"; 
							 
								String beanString = pack +"."  + bean;
								Class<?> objClass = Class.forName(beanString);
								field = objClass.getDeclaredFields();
								 
								Constructor<?> ctor = objClass.getConstructor();
								Object beanOject = ctor.newInstance();
								String pg=beanOject.getClass().getPackage().getName();
								
					          
								for (int i = 0; i < field.length; i++) {
									boolean accessible = field[i].isAccessible();
									field[i].setAccessible(true);
									Class<?> beanObj = field[i].getType();
									boolean isprimitive = beanObj.isPrimitive();
									
									Object beanOject2=null; 
					 
									String fieldname = field[i].getName();
									Object fieldvalue = product.getFields().get(field[i].getName());
									
									
									if (fieldname.equals("raw"))
									{
									  String s= "";
					 
									}
									
									 
									boolean toint = false;
									boolean isInstantiable; 
									if(!isprimitive)
									{
										Constructor<?> ctor2;
										try {
											ctor2 = beanObj.getConstructor();
											beanOject2 = ctor2.newInstance();
											pg=beanOject2.getClass().getPackage().getName();
											isInstantiable = true;
										} catch (Exception e) {
											isInstantiable = false; 
										}
										
										
										 
										if(pack.equals(pg ))
										{
											toint = true;
											if(isInstantiable)
											{
											  beanOject2 = beanObj.cast( sessionHibernate.load( field[i].getType(), new Integer( fieldvalue.toString() ) ));
											}
										}
									}
		  
									 
									if (!field[i].getName().equals("id"))
									{
										
										if (!field[i].getName().equals("guid"))
										{
										 
											if(toint)
											{
												field[i].set(beanOject, beanOject2);
											}
											else{
												field[i].set(beanOject, fieldvalue);
											}
											
											 
										}
										else
										{
											 
											
											field[i].set(beanOject, uuid.toString());
										}
									}
									 
						            field[i].setAccessible(accessible);
									
								} 
								
								
								
								
								
								
							
						} catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						}catch (NoSuchMethodException e) {
						
							e.printStackTrace();
						} catch (SecurityException e) {
							
							e.printStackTrace();
						} catch (InstantiationException e) {
							
							e.printStackTrace();
						} catch (IllegalAccessException e) {
						
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							
							e.printStackTrace();
						}
						*/
						
						
					} catch (JsonParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JsonMappingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
	
					//BeanScheduleInfo data = new Gson().fromJson(schedule, BeanScheduleInfo.class);
					 
					 
					
					System.out.println(schedule);
				  }
			}

			
	
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
	}
}
