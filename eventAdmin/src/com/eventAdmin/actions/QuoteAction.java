package com.eventAdmin.actions;

import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import com.bituos.*;
import com.eventAdmin.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.eventAdmin.forms.QuoteForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class QuoteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		QuoteForm quoteForm = (QuoteForm) form;

		HttpSession httpSession = request.getSession();

		httpSession.removeAttribute("done");

		//Usuario que se logeo y que esta en sesi?n
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else 
		  {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try 
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String error = new String();
				String query = "nothing";
				int id_sale = 0;
				
				if( quoteForm.getProcess().equals("quoteToSale") )
				  {
					boolean showError = false;

					query = " FROM BeanQuote u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
					  		" AND u.id =" +  quoteForm.getId_quote();
					
					List list = session.createQuery( query ).list();

					if ( list.size() == 0 )
					    {
							com.bituos.Error e = new com.bituos.Error("No existe cotizacion con ese numero.", request);
							showError = true;
					    }
					
				
					BeanQuote beanQuote = null;
					if ( !showError)
					  {
						
						beanQuote = (BeanQuote) list.get(0);
						
						//verificar disponibilidad
						query = " FROM BeanSale u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
						  		" AND u.date_event ='" + Utils.DateToStrInv( beanQuote.getDate_event() ) + "'" +
							    " AND u.id_saloon = " + beanQuote.getId_saloon().getId() +
								" AND ( (" + beanQuote.getId_hour().getId() + " BETWEEN u.id_hour AND u.id_hour + u.hourQuantity)" +
								"       OR" +
								"       (" + (beanQuote.getId_hour().getId() + beanQuote.getHourQuantity() ) + " BETWEEN u.id_hour AND u.id_hour + u.hourQuantity )" +
								"     )";
						
						list = session.createQuery( query ).list();
						
						if ( list.size() > 0 )
						    {
								com.bituos.Error e = new com.bituos.Error("El evento especificado se solapa con uno existente, numero de evento: " + ((BeanSale) list.get(0)).getId_intern(), request);
								showError = true;
						    }
					  }
					
					if ( !showError)
					  {
						int id_intern = 1;

						query = " SELECT MAX(u.id_intern)" +
								" FROM BeanSale u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId();
				
						list = session.createQuery( query ).list();
		
						try
						  {
							id_intern = ((Integer) list.get(0)).intValue() + 1;
						  }
						catch (Exception m)
						  {
							id_intern = 1;
						  }
						
						
						
						
						BeanSale beanSale = new BeanSale();
						BeanClient beanClient = beanQuote.getId_client();
						BeanCompany beanCompany = beanQuote.getId_company();
						
						beanSale.setDate_reg( beanQuote.getDate_event() );
						beanSale.setId_client( beanQuote.getId_client() );
						beanSale.setClient_name( beanClient.getName() );
						beanSale.setClient_address( beanClient.getAddress() );
						beanSale.setPeople_count( beanQuote.getPeople_count() );
						beanSale.setId_list( beanQuote.getId_list() );
						beanSale.setList_description( beanQuote.getList_description() );
						beanSale.setList_min( beanQuote.getList_min() );
						beanSale.setList_max( beanQuote.getList_max() );
						beanSale.setId_list_option( beanQuote.getId_list_option() );
						beanSale.setTotal( beanQuote.getTotal() );
						beanSale.setId_company( beanQuote.getId_company() );
						beanSale.setId_user( beanUser );
						beanSale.setId_list_option_menu( beanQuote.getId_list_option_menu() );
						beanSale.setOption_description( beanQuote.getOption_description() );
						beanSale.setMenu_description( beanQuote.getMenu_description() );
						beanSale.setId_saloon( beanQuote.getId_saloon() );
						beanSale.setSaloon_description( beanQuote.getSaloon_description() );
						beanSale.setHourQuantity( beanQuote.getHourQuantity() );
						beanSale.setId_hour( beanQuote.getId_hour() );
						beanSale.setDate_event( beanQuote.getDate_event() );
						beanSale.setAnotherEmail( beanQuote.getAnotherEmail() );
						beanSale.setMenu_price( beanQuote.getMenu_price() );
						beanSale.setClient_phone( beanClient.getPhone() );
						beanSale.setClient_company( beanClient.getCompany() );
						beanSale.setClient_email( beanClient.getEmail() );
						
						beanSale.setCompanyName(beanCompany.getName());
						beanSale.setContractSigner(beanCompany.getContractSigner());
						beanSale.setStreetContract(beanCompany.getStreetContract());
						beanSale.setColonyContract(beanCompany.getColonyContract());
						beanSale.setCityContract(beanCompany.getCityContract());
						beanSale.setStateContract(beanCompany.getStateContract());
						beanSale.setCompanyPhones(beanCompany.getPhones());
						
				
						
						if ( Utils.isEmail( quoteForm.getAnotherEmail() ))
						  beanSale.setAnotherEmail1( quoteForm.getAnotherEmail() );
						else
						  beanSale.setAnotherEmail1( "" );
						
						beanSale.setId_intern( id_intern );

					  	tx = session.beginTransaction();
						session.save( beanSale );
						
						id_sale = beanSale.getId();
						
						
						
						//crear schedule para el calendario
						Date d = beanSale.getDate_event();
						//String dt = Utils.DateToStrV3(d) + "T";//"2019-11-12T19:30:00.000Z";
						
						
						//SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
						BeanTypeHour starthours = beanSale.getId_hour();
						int starthour = Integer.valueOf(starthours.getHour());
						int durationhours = beanSale.getHourQuantity();
						
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
					    
					    
					    BeanCalendarProps beanCategoryCalendar = (BeanCalendarProps) session.load( BeanCalendarProps.class, new Integer( 1 ) );
					    
						byte[]  bytes = String.valueOf( beanCategoryCalendar.getId()).getBytes("UTF-8");
						UUID uuid = UUID.nameUUIDFromBytes(bytes);
					    
					    BeanScheduleInfo beanScheduleInfo = new BeanScheduleInfo();					     
					    beanScheduleInfo.setGuid(uuid.toString());			    
					    beanScheduleInfo.setCalendarId(beanCategoryCalendar);// = null;
					    
				 
					    beanScheduleInfo.setTitle(beanSale.getList_description());//  = null;
					    beanScheduleInfo.setBody(beanSale.getMenu_description());//  = null;
					    beanScheduleInfo.setIsAllday(false);//  = false;
					    beanScheduleInfo.setStart(isoFormat.format(datetimestart));
					    beanScheduleInfo.setEnd(isoFormat.format(datetimeend));
					    beanScheduleInfo.setCategory("time");//  'milestone', 'task', allday', 'time';
					    beanScheduleInfo.setDueDateClass("");// cualquier valor de cadena est? bien y es obligatorio si la categor?a es 'task';

					    beanScheduleInfo.setColor("#ffffff");//  = null;
					    beanScheduleInfo.setBgColor("#9e5fff");//  = null;
					    beanScheduleInfo.setDragBgColor("#9e5fff");//  = null;
					    beanScheduleInfo.setBorderColor("#9e5fff");//  = null;
					    beanScheduleInfo.setCustomStyle("");//  = '';

					    beanScheduleInfo.setIsFocused(false);// = false;
					    beanScheduleInfo.setIsPending(false);// = false;
					    beanScheduleInfo.setIsVisible(true);// = true;
					    beanScheduleInfo.setIsReadOnly(true);// = false;
					    beanScheduleInfo.setGoingDuration(0);// = 0;
					    beanScheduleInfo.setComingDuration(0);// = 0;
					    beanScheduleInfo.setRecurrenceRule("");// = '';
					    beanScheduleInfo.setIsPrivate(false);
					    beanScheduleInfo.setLocation(beanSale.getId_saloon().getDescription());
					    
					    BeanRaw raw = new BeanRaw();
						//raw.setMemo(schedulejason.getRaw().getMemo());
						raw.setHasToOrCc(false);
						raw.setHasRecurrenceRule(false);
						raw.setLocation("");
						raw.setClassName("public");
						raw.setActive(true);
						
						
						query =  " FROM BeanCreatorUser u" +
				 		 		 " WHERE u.userid.id=" + beanUser.getId();
		
		
						List listObjects = session.createQuery( query ).list();
						
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
							session.save( creator );
							tx.commit();
							
							beanCreatorUser = new BeanCreatorUser();
							beanCreatorUser.setCreatorid(creator);
							beanCreatorUser.setUserid(beanUser);
							session.save( beanCreatorUser );
							tx.commit();
						}
						else
						{
							beanCreatorUser =(BeanCreatorUser)listObjects.get(0);
							creator= beanCreatorUser.getCreatorid();
						}
						
						raw.setCreator(creator);						
						session.save( raw );
					    beanScheduleInfo.setRaw(raw);    
	
					    
					    ArrayList<Object>  attendees = new  ArrayList<Object>();
					    BeanAttendees beanAttendees = new BeanAttendees();
					    beanAttendees.setName(beanSale.getClient_name());
					    attendees.add(beanAttendees);
					    
					    
					    ArrayList<Object>  beansScheduleinfoAttendeesList= new  ArrayList<Object>();
					    
					    
					    Iterator<Object> attendeesIterator = attendees.iterator();
						while (attendeesIterator.hasNext()) {
							BeanAttendees beanAttendeesItem = (BeanAttendees)attendeesIterator.next();
							session.save( beanAttendeesItem );
							
							BeansScheduleinfoAttendees beansScheduleinfoAttendees = new BeansScheduleinfoAttendees();
							beansScheduleinfoAttendees.setAttendees(beanAttendeesItem);
							beansScheduleinfoAttendees.setScheduleinfo(beanScheduleInfo);
							beansScheduleinfoAttendeesList.add(beansScheduleinfoAttendees);
						}
						
					 
						
						
						session.save( beanScheduleInfo );
						 
						
						Iterator<Object> beansScheduleinfoAttendeesIterator = beansScheduleinfoAttendeesList.iterator();
						while (beansScheduleinfoAttendeesIterator.hasNext()) {
							BeansScheduleinfoAttendees beansScheduleinfoAttendeesItem =(BeansScheduleinfoAttendees)beansScheduleinfoAttendeesIterator.next();
							session.save(beansScheduleinfoAttendeesItem );
						}
						
					 
					    
					    
						
						//Productos
						query = " FROM BeanQuoteProduct u" +
								" WHERE u.id_quote = " + beanQuote.getId();
						
						list = session.createQuery( query ).list();
						
						if ( list.size() > 0 )
						  {
							Iterator iter = list.iterator();
							
							while ( iter.hasNext() )
							  {
								BeanQuoteProduct beanQuoteProduct = (BeanQuoteProduct) iter.next();
								BeanSaleProduct beanSaleProduct = new BeanSaleProduct();
								
								beanSaleProduct.setId_sale( beanSale );
								beanSaleProduct.setId_product( beanQuoteProduct.getId_product() );
								beanSaleProduct.setProduct_description( beanQuoteProduct.getProduct_description() );
								beanSaleProduct.setQty( beanQuoteProduct.getQty() );
								beanSaleProduct.setPerPerson( beanQuoteProduct.getPerPerson() );
								beanSaleProduct.setPersonsQty( beanQuoteProduct.getPersonsQty() );
								beanSaleProduct.setProduct_price( beanQuoteProduct.getProduct_price() );
								
								session.save( beanSaleProduct );
							  }
						  }

						
						//Flowers
						query = " FROM BeanQuoteFlower u" +
								" WHERE u.id_quote = " + beanQuote.getId();
						
						list = session.createQuery( query ).list();
						
						if ( list.size() > 0 )
						  {
							Iterator iter = list.iterator();
							
							while ( iter.hasNext() )
							  {
								BeanQuoteFlower beanQuoteFlower = (BeanQuoteFlower) iter.next();
								BeanSaleFlower beanSaleFlower = new BeanSaleFlower();
								
								beanSaleFlower.setId_sale( beanSale );
								beanSaleFlower.setFlower_price( beanQuoteFlower.getFlower_price() );
								beanSaleFlower.setId_flower( beanQuoteFlower.getId_flower() );
								beanSaleFlower.setFlower_perPerson( beanQuoteFlower.getFlower_perPerson() );
								beanSaleFlower.setFlower_description( beanQuoteFlower.getFlower_description() );
								beanSaleFlower.setPersonsQty( beanQuoteFlower.getPersonsQty() );

								session.save( beanSaleFlower );
							  }
						  }
		
						
						//Music
						query = " FROM BeanQuoteMusic u" +
								" WHERE u.id_quote = " + beanQuote.getId();
						
						list = session.createQuery( query ).list();
						
						if ( list.size() > 0 )
						  {
							Iterator iter = list.iterator();
							
							while ( iter.hasNext() )
							  {
								BeanQuoteMusic beanQuoteMusic = (BeanQuoteMusic) iter.next();
								BeanSaleMusic beanSaleMusic = new BeanSaleMusic();
								
								beanSaleMusic.setId_sale( beanSale );
								beanSaleMusic.setId_music( beanQuoteMusic.getId_music() );
								beanSaleMusic.setMusic_description( beanQuoteMusic.getMusic_description() );
								beanSaleMusic.setMusic_price( beanQuoteMusic.getMusic_price() );
								beanSaleMusic.setMusic_perPerson( beanQuoteMusic.getMusic_perPerson() );
								beanSaleMusic.setPersonsQty( beanQuoteMusic.getPersonsQty() );

								session.save( beanSaleMusic );
							  }
						  }
		
						
						tx.commit();

					  
						//envio de email
						Aes aes = new Aes();
						String encID = Aes.encrypt( new Integer( beanSale.getId()).toString() ); 
						BeanControlPanel beanControlPanel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer(1));
						
						try
						  {
							if ( Utils.isEmail(beanClient.getEmail() ))
								new com.bituos.Mail( beanClient.getEmail()
										, "Hola\n\n" + 
										  "La venta solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
										  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
										  beanUser.getId_company().getFull_name()
										, "Venta " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
										, request);
							
							if ( Utils.isEmail( beanSale.getAnotherEmail() ))
								new com.bituos.Mail( quoteForm.getAnotherEmail()
										, "Hola\n\n" + 
										  "La venta solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
										  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
										  beanUser.getId_company().getFull_name()
										, "Venta " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
										, request);

							if ( Utils.isEmail( quoteForm.getAnotherEmail() ))
								new com.bituos.Mail( quoteForm.getAnotherEmail()
										, "Hola\n\n" + 
										  "La venta solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
										  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
										  beanUser.getId_company().getFull_name()
										, "Venta " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
										, request);
						  }
						catch (Exception m)
						  {
							
						  }
						
						//httpSession.setAttribute( "crystal_rptname", new String("rptSale.rpt") ) ;
						httpSession.setAttribute( "crystal_rptname", beanUser.getId_company().getNameRptSale() ) ;
						httpSession.setAttribute( "parameterCount", new Integer(1) ) ;

						//id quote
						httpSession.setAttribute( "parameter1", new Integer( id_sale ) );
						httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
						httpSession.setAttribute( "parameterName1", new String("id_sale") ) ;
						
					    forward = mapping.findForward("printGlobal");
					  }
					
					if(showError)
					  forward = mapping.findForward("error");
				  }
				else if( quoteForm.getProcess().equals("selectQuoteToSale") )
				  {
					boolean showError = false;

					query = " FROM BeanQuote u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
					  		" AND u.id =" +  quoteForm.getId_quote();
					
					List list = session.createQuery( query ).list();

					if ( list.size() == 0 )
					    {
							com.bituos.Error e = new com.bituos.Error("No existe cotizacion con ese numero.", request);
							showError = true;
					    }
					
				
					BeanQuote beanQuote = null;
					if ( !showError)
					  {
						
						beanQuote = (BeanQuote) list.get(0);
						httpSession.setAttribute( "selectedQuote", beanQuote ) ;
					  }
					
					forward = mapping.findForward("loop");
				   
				  }
				else
				  {
					httpSession.setAttribute("done", "javascript:history.back();");
					com.bituos.Error e = new com.bituos.Error("Opcion no programada", request);
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
			  } 
			catch (Exception m) 
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");
				com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally	
			  {
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			  }
		  }

		// Termina con
		return (forward);

	}
}

