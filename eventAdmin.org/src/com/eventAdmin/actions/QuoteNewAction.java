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

import java.util.*;
import java.io.*;

import com.bituos.*;
import com.eventAdmin.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.eventAdmin.forms.QuoteNewForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class QuoteNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		QuoteNewForm quoteNewForm = (QuoteNewForm) form;

		HttpSession httpSession = request.getSession();

		httpSession.removeAttribute("done");

		//Usuario que se logeo y que esta en sesión
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


				//Objeto de tipo  BeanCase
				BeanQuote bean = new BeanQuote();

				String error = new String();

				/*caseNewForm.getProcess().equals()
				    obtiene  el valor de la variable process y compara con una cadena
				*/

				//si el process = "validate"
				
				String query = "nothing";
				
				if(quoteNewForm.getProcess().equals("validateClient"))
				  {
					//Busca al cliente
					query = " FROM BeanClient u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
						    " AND u.id_intern =" + quoteNewForm.getId_client() +
							" AND u.active = 'Y'" +
					  		" ORDER BY u.name";

					List list = session.createQuery( query ).list();

					httpSession.removeAttribute("clientNumber");
					httpSession.removeAttribute("clientName");
					httpSession.removeAttribute("total");

					if( list.size()!=0 )
					  {
						httpSession.setAttribute("clientNumber",  new Integer(((BeanClient) list.get(0)).getId_intern()).toString() );
						httpSession.setAttribute("realClientNumber",  new Integer(((BeanClient) list.get(0)).getId()).toString() );
						httpSession.setAttribute("clientName", ((BeanClient) list.get(0)).getName() );
						httpSession.setAttribute("visibleUserData","yes");
						
						int id_list = LocalUtils.putListInSession( request, 0 );
						int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
						int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );
						
						query = " FROM BeanFlower u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active = 'Y'" +
						  		" ORDER BY u.description";

						list = session.createQuery( query ).list();

						httpSession.setAttribute("listFlowers",  list );

						query = " FROM BeanMusic u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active = 'Y'" +
						  		" ORDER BY u.description";
		
						List list1 = session.createQuery( query ).list();
		
						httpSession.setAttribute("listMusic", list1 );
		
						query = " FROM BeanSaloon u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active = 'Y'" +
						  		" ORDER BY u.description";
			
						List list2 = session.createQuery( query ).list();
			
						httpSession.setAttribute("quoteNew_listSaloon", list2 );
						if ( list2.size() > 0 )
						  httpSession.setAttribute("quoteNew_currentSaloon", (BeanSaloon) list2.get(0) );
			
						query = " FROM BeanTypeHour u" +
								" ORDER BY u.id";
	
						List list3 = session.createQuery( query ).list();
			
						httpSession.setAttribute("listHours", list3 );
		
						query = " FROM BeanProduct u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active = 'Y'" +
						  		" ORDER BY u.description";
	
						List list4 = session.createQuery( query ).list();

						httpSession.setAttribute("listProduct", list4 );
						
						List list5 = session.createQuery( query ).list();
						
						forward = mapping.findForward("loop");
					  }
					else
					  {
						httpSession.setAttribute("visibleUserData","no");
						httpSession.setAttribute("visibleCase","no");

						httpSession.setAttribute("done", "/eventAdmin/quote/quoteNew.jsp");
						com.bituos.Error e = new com.bituos.Error("El cliente no existe", request);
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }
				  }

				else if(quoteNewForm.getProcess().equals("selectList"))
				  {
					  int id_list = LocalUtils.putListInSession( request, new Integer( quoteNewForm.getId_list() ).intValue() );
					  int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
					  int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );
					  
					  //conservar opciones seleccionadas de saloon, flores y musica
					  BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(quoteNewForm.getId_saloon()));
					  httpSession.setAttribute("quoteNew_currentSaloon", beanSaloon );
					  
					  BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(quoteNewForm.getId_flower()));
					  httpSession.setAttribute("quoteNew_currentFlower", beanFlower );
					  
					  BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(quoteNewForm.getId_music()));
					  httpSession.setAttribute("quoteNew_currentMusic", beanMusic );
					  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica

					  forward = mapping.findForward("loop");
				  }
				else if(quoteNewForm.getProcess().equals("selectOption"))
				  {
					  int id_list = LocalUtils.getCurrentList( request );
					  int id_option = LocalUtils.putOptionInSession( request, id_list, new Integer(quoteNewForm.getId_option()).intValue() );
					  int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );


					  BeanListOptions beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(quoteNewForm.getId_option()));
//					  int personsCount = new Integer( quoteNewForm.getPersonsCount() ).intValue();
//					  
//					  if ( (personsCount < beanListOptions.getMin() )
//						   ||
//						   (personsCount > beanListOptions.getMax() )
//						 )
//					    {
//							com.bituos.Error e = new com.bituos.Error("Debe seleccionar una opcion correcta de acuerdo al numero de personas", request);
//
//							forward = mapping.findForward("error");
//					    }
//					  else
					    {
						  //conservar opciones seleccionadas de saloon, flores y musica
						  BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(quoteNewForm.getId_saloon()));
						  httpSession.setAttribute("quoteNew_currentSaloon", beanSaloon );
						  
						  BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(quoteNewForm.getId_flower()));
						  httpSession.setAttribute("quoteNew_currentFlower", beanFlower );
						  
						  BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(quoteNewForm.getId_music()));
						  httpSession.setAttribute("quoteNew_currentMusic", beanMusic );
						  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica

						  forward = mapping.findForward("loop");
					    }
					  
				  }
				else if( quoteNewForm.getProcess().equals("updateTotal") || quoteNewForm.getProcess().equals("register") )
				  {
					int personsCount = new Integer( quoteNewForm.getPersonsCount() ).intValue();
					BeanClient beanClient = (BeanClient) session.load( BeanClient.class, new Integer( (String) httpSession.getAttribute("realClientNumber") ));
					BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(quoteNewForm.getId_flower()));
					BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(quoteNewForm.getId_music()));
					BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(quoteNewForm.getId_saloon()));
					BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(quoteNewForm.getId_list()));
					BeanListOptions beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(quoteNewForm.getId_option()));
					BeanListOptionMenu beanListOptionMenu = (BeanListOptionMenu) session.load( BeanListOptionMenu.class, new Integer(quoteNewForm.getId_menu()));
					BeanTypeHour beanTypeHour = (BeanTypeHour) session.load( BeanTypeHour.class, new Integer(quoteNewForm.getId_hour()));
					
					int musicCount = 1;
					int flowerCount = 1;
					double price = 0;
					double total = 0;
					int dow = 0;
					Date date_event = Utils.StrToDate1( quoteNewForm.getDate_event() ); 
					int id_hour = new Integer (quoteNewForm.getId_hour() ).intValue();
					int hourQuantity = new Integer (quoteNewForm.getHourQuantity() ).intValue();
					boolean showError = false;
					double totalProducts = 0;
					int id_quote = 0;
					
					if ( personsCount < beanSaloon.getMin_occupancy() )
					    {
							com.bituos.Error e = new com.bituos.Error("La cantidad de personas es menor al minimo de la capacidad del salon(" + beanSaloon.getMin_occupancy() + ")", request);
							showError = true;
					    }

					if ( personsCount > beanSaloon.getMax_occupancy() )
					    {
							com.bituos.Error e = new com.bituos.Error("La cantidad de personas es mayor a la capacidad del salon(" + beanSaloon.getMax_occupancy() + ")", request);
							showError = true;
					    }

					if ( personsCount > beanListOptions.getMax() )
					    {
							com.bituos.Error e = new com.bituos.Error("Debe seleccionar la opcion de Menu adecuada para el numero de personas(" + beanListOptions.getMax() + ")", request);
							showError = true;
					    }

					if ( personsCount < beanListOptions.getMin() )
					    {
							com.bituos.Error e = new com.bituos.Error("Debe seleccionar la opcion de Menu adecuada para el numero de personas(" + beanListOptions.getMin() + ")", request);
							showError = true;
					    }

					if ( beanFlower.getPerPerson().equals("Y"))
					  flowerCount = personsCount;
					
					if ( beanMusic.getPerPerson().equals("Y"))
					  musicCount = personsCount;
					
					dow = Utils.dayOfWeek1( date_event );
					
					if ( dow == 1 )
					  price = beanListOptionMenu.getPrice7();
					else if ( dow == 2 )
						  price = beanListOptionMenu.getPrice1();
					else if ( dow == 3 )
						  price = beanListOptionMenu.getPrice2();
					else if ( dow == 4 )
						  price = beanListOptionMenu.getPrice3();
					else if ( dow == 5 )
						  price = beanListOptionMenu.getPrice4();
					else if ( dow == 6 )
						  price = beanListOptionMenu.getPrice5();
					else 
						  price = beanListOptionMenu.getPrice6();
					
					
					List qnCarListProduct = (List) httpSession.getAttribute("qnCarListProduct");
					
					if ( qnCarListProduct != null )
					  {
						Iterator iter = qnCarListProduct.iterator();
						
						while ( iter.hasNext() )
						  {
							QuoteProduct qp = (QuoteProduct) iter.next();

							if ( qp.getPerPerson().equals("Y") )
							  totalProducts += personsCount * qp.getQty() * qp.getPrice();
							else
							  totalProducts += qp.getQty() * qp.getPrice();
						  }
					  }
					
					total = flowerCount*beanFlower.getPrice() + musicCount*beanMusic.getPrice() + personsCount*price + totalProducts; 
					
					httpSession.setAttribute("total",  new Double( total ).toString() );
					
					//verificar disponibilidad
					query = " FROM BeanSale u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
					  		" AND u.date_event ='" + Utils.DateToStrInv( date_event ) + "'" +
						    " AND u.id_saloon = " + beanSaloon.getId() +
							" AND ( (" + id_hour + " BETWEEN u.id_hour AND u.id_hour + u.hourQuantity)" +
							"       OR" +
							"       (" + (id_hour + hourQuantity) + " BETWEEN u.id_hour AND u.id_hour + u.hourQuantity )" +
							"     )";
					
					List list = session.createQuery( query ).list();
					
					if ( list.size() > 0 )
					    {
							com.bituos.Error e = new com.bituos.Error("El evento especificado se solapa con uno existente, numero de evento: " + ((BeanSale) list.get(0)).getId(), request);
							showError = true;
					    }
					
					  //conservar opciones seleccionadas de saloon, flores y musica
					  BeanSaloon beanSaloon1 = (BeanSaloon) session.load( BeanSaloon.class, new Integer(quoteNewForm.getId_saloon()));
					  httpSession.setAttribute("quoteNew_currentSaloon", beanSaloon1 );
					  
					  BeanFlower beanFlower1 = (BeanFlower) session.load( BeanFlower.class, new Integer(quoteNewForm.getId_flower()));
					  httpSession.setAttribute("quoteNew_currentFlower", beanFlower1 );
					  
					  BeanMusic beanMusic1 = (BeanMusic) session.load( BeanMusic.class, new Integer(quoteNewForm.getId_music()));
					  httpSession.setAttribute("quoteNew_currentMusic", beanMusic1 );
					  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica
					  
					if ( !showError )
					  {
						if ( quoteNewForm.getProcess().equals("register") )
						  {
							BeanQuote beanQuote = new BeanQuote();
							int id_intern = 1;
							
							query = " FROM BeanQuote u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId();

							list = session.createQuery( query ).list();

							if (list.size() > 0 )
							  {
								query = " SELECT MAX(u.id_intern)" +
										" FROM BeanQuote u" +
										" WHERE u.id_company = " + beanUser.getId_company().getId();
						
								list = session.createQuery( query ).list();
		
								id_intern = ((Integer) list.get(0)).intValue() + 1;
							  }
							
							bean.setId_intern( id_intern );
							bean.setDate_reg( Utils.Today() );
							bean.setDate_event( date_event  );
							
							bean.setId_client( beanClient );
							bean.setClient_name( beanClient.getName() );
							bean.setClient_address( beanClient.getAddress() );
							
							bean.setPeople_count( personsCount );
							
							bean.setId_list( beanList );
							bean.setList_description( beanList.getDescription() );
							bean.setList_min( beanListOptions.getMin() );
							bean.setList_max( beanListOptions.getMax() );
	
							bean.setId_list_option( beanListOptions );
							bean.setTotal( total );
							bean.setId_company( beanUser.getId_company() );
							bean.setId_user( beanUser );
							bean.setId_list_option_menu( beanListOptionMenu );
							bean.setOption_description( "" );  //next: quizas no tenga sentido este field
							bean.setMenu_description( beanListOptionMenu.getName() );
							bean.setId_saloon( beanSaloon );
							bean.setSaloon_description( beanSaloon.getDescription() );
							
							if ( Utils.isEmail( quoteNewForm.getAnotherEmail() ))
							  bean.setAnotherEmail( quoteNewForm.getAnotherEmail() );
							else
								bean.setAnotherEmail( "" );
							
							bean.setMenu_price( price );
							bean.setClient_phone( beanClient.getPhone() );
							bean.setClient_company( beanClient.getCompany() );
							bean.setClient_email( beanClient.getEmail() );
							
							bean.setId_hour( beanTypeHour );
							bean.setHourQuantity( hourQuantity );
							bean.setNotes( quoteNewForm.getNotes() );
							
						  	tx = session.beginTransaction();
							session.save( bean );
							
							id_quote = bean.getId();
							  
							  if ( qnCarListProduct != null )
								  {
									Iterator iter = qnCarListProduct.iterator();
									
									while ( iter.hasNext() )
									  {
										QuoteProduct qp = (QuoteProduct) iter.next();
										BeanQuoteProduct beanQuoteProduct = new BeanQuoteProduct();
										BeanProduct beanProduct = (BeanProduct) session.load( BeanProduct.class, new Integer(qp.getId_product()));
										
										beanQuoteProduct.setId_quote( bean );
										beanQuoteProduct.setId_product( beanProduct );
										beanQuoteProduct.setProduct_description( beanProduct.getDescription() );
										beanQuoteProduct.setQty( qp.getQty() );
										beanQuoteProduct.setPerPerson( beanProduct.getPerPerson() );
										beanQuoteProduct.setPersonsQty( personsCount );
										beanQuoteProduct.setProduct_price( qp.getPrice() );

										session.save( beanQuoteProduct );
									  }
								  }
							
							  
							BeanQuoteFlower beanQuoteFlower = new BeanQuoteFlower();
							BeanQuoteMusic beanQuoteMusic = new BeanQuoteMusic();
							
							beanQuoteFlower.setId_quote( bean );
							beanQuoteFlower.setFlower_price( beanFlower.getPrice() );
							beanQuoteFlower.setFlower_perPerson( beanFlower.getPerPerson() );
							beanQuoteFlower.setId_flower( beanFlower );
							beanQuoteFlower.setFlower_description( beanFlower.getDescription() );
							beanQuoteFlower.setPersonsQty( personsCount );
							  
							session.save( beanQuoteFlower );
							
							
							beanQuoteMusic.setId_quote( bean );
							beanQuoteMusic.setId_music( beanMusic );
							beanQuoteMusic.setMusic_description( beanMusic.getDescription() );
							beanQuoteMusic.setMusic_price( beanMusic.getPrice() );
							beanQuoteMusic.setMusic_perPerson( beanMusic.getPerPerson() );
							beanQuoteMusic.setPersonsQty( personsCount );
							  
							session.save( beanQuoteMusic );
							
							tx.commit();
							
							//envio de email
							Aes aes = new Aes();
							String encID = Aes.encrypt( new Integer(bean.getId()).toString() ); 
							BeanControlPanel beanControlPanel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer(1));
							
							try
							  {
								if ( Utils.isEmail(beanClient.getEmail() ))
									new com.bituos.Mail( beanClient.getEmail()
											, "Hola\n\n" + 
											  "La cotizacion solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
											  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showQuote&nip=" + encID + "\n\n" +
											  beanUser.getId_company().getFull_name()
											, "Cotizacion " + bean.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
											, request);
								
								if ( Utils.isEmail( quoteNewForm.getAnotherEmail() ))
									new com.bituos.Mail( quoteNewForm.getAnotherEmail()
											, "Hola\n\n" + 
											  "La cotizacion solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
											  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showQuote&nip=" + encID + "\n\n" +
											  beanUser.getId_company().getFull_name()
											, "Cotizacion " + bean.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
											, request);
							  }
							catch (Exception m)
							  {
								
							  }
							
							//httpSession.setAttribute( "crystal_rptname", new String("rptQuote.rpt") ) ;
							httpSession.setAttribute( "crystal_rptname", beanUser.getId_company().getNameRptQuote() ) ;
							httpSession.setAttribute( "parameterCount", new Integer(1) ) ;

							//id quote
							httpSession.setAttribute( "parameter1", new Integer( id_quote ) );
							httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
							httpSession.setAttribute( "parameterName1", new String("id_quote") ) ;
							
						    forward = mapping.findForward("printGlobal");
						  }
						else
						  {
							forward = mapping.findForward("loop");
						  }
					  }
					else
						forward = mapping.findForward("error");
				  }
				else if(quoteNewForm.getProcess().equals("calendar"))
				  {
					/*query = " FROM BeanQuote u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
						    " AND u.id_saloon = " + quoteNewForm.getId_saloon();

					List list = session.createQuery( query ).list();

					httpSession.setAttribute("listCalendar", list );*/
					
					query = " FROM BeanSale u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId();/* +
						    " AND u.id_saloon = " + quoteNewForm.getId_saloon();*/

					List list = session.createQuery( query ).list();
		
					httpSession.setAttribute("listCalendar", list );
					
					forward = mapping.findForward("showCalendar");
				  }
				else if(quoteNewForm.getProcess().equals("addProduct"))
				  {
					String id_str = quoteNewForm.getId_product();
					BeanProduct beanProduct = (BeanProduct) session.load( BeanProduct.class, new Integer(id_str));
					List qnCarListProduct = (List) httpSession.getAttribute("qnCarListProduct");
					
					if ( qnCarListProduct == null )
					  qnCarListProduct = new ArrayList<BeanProduct>();
					
					int id = beanProduct.getId();
					int newQty = (new Integer( quoteNewForm.getProductQuantity() )).intValue(); 
					QuoteProduct quoteProduct = new QuoteProduct();
					 
					quoteProduct.setId_product( beanProduct.getId() );
					quoteProduct.setDescription( beanProduct.getDescription() );
					quoteProduct.setPrice( beanProduct.getPrice() );
					quoteProduct.setQty( newQty );
					quoteProduct.setPerPerson( beanProduct.getPerPerson() );
					
					int index = quoteProduct.indexOf( qnCarListProduct );
					
					if ( index < 0 )
					  {
						qnCarListProduct.add( quoteProduct );
					  }
					else
					  {
						QuoteProduct quoteProduct1 = (QuoteProduct) qnCarListProduct.get(index);
						
						quoteProduct1.setQty( quoteProduct1.getQty() + quoteProduct.getQty() );
						
						if ( quoteProduct1.getQty() <= 0 )
							qnCarListProduct.remove(index);  
					  }
					
					if ( qnCarListProduct.size()> 0 )
					  httpSession.setAttribute("qnCarListProduct", qnCarListProduct );
					else
					  httpSession.removeAttribute( "qnCarListProduct" );
					

					  //conservar opciones seleccionadas de saloon, flores y musica
					  BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(quoteNewForm.getId_saloon()));
					  httpSession.setAttribute("quoteNew_currentSaloon", beanSaloon );
					  
					  BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(quoteNewForm.getId_flower()));
					  httpSession.setAttribute("quoteNew_currentFlower", beanFlower );
					  
					  BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(quoteNewForm.getId_music()));
					  httpSession.setAttribute("quoteNew_currentMusic", beanMusic );
					  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica
					
					forward = mapping.findForward("loop");
				  }
				else if( quoteNewForm.getProcess().equals("registerZZZZZZ") )
				  {
					BeanClient client = (BeanClient) httpSession.getAttribute("client");
				  	boolean ok = false;
				  	
					BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(quoteNewForm.getId_list()));

					//Se pasan los valores a el objeto bean de tipo BeanCase
					bean.setId_list( beanList );
					bean.setDate_reg( Utils.Today() );
					bean.setId_user( beanUser );
					bean.setId_company( beanUser.getId_company() );

					//Guarda la informacion el la BD
					tx = session.beginTransaction();

					
					//envia email
					
//					new com.bituos.Mail(  beanUser.getEmail(),
//										"Hola:\n\n" +
//										"Ud tiene un nuevo caso asignado" + "\n\n" +
//										"Datos del caso:\n" + 
//										"Numero de caso: " + bean.getId() + "\n" +
//										"Nombre del cliente:" + bean.getId_client().getName() + "\n" +
//										"Nombre del usuario que captura:" + beanUser.getName()+ "\n" +
//										"Descripcion corta: " + bean.getShort_description() + "\n" +  
//										"Descripcion larga: " + bean.getDescription() + "\n\n\n" +
//										"Sistema Contact Center."
//										,
//										"Contact Center: caso nuevo: " +  bean.getId(),
//										request);
									
					httpSession.setAttribute("done", "pre.do?process=caseNew" );
					httpSession.setAttribute("msg", "Su numero de caso es " + bean.getId());
					//forward declarado en struts-config.xml
					forward = mapping.findForward("okGlobal");

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

