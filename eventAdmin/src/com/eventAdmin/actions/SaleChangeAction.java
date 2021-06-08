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

import com.eventAdmin.forms.SaleChangeForm;
import com.eventAdmin.forms.UserChangeForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class SaleChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		SaleChangeForm saleChangeForm = (SaleChangeForm) form;
		//SaleChangeForm saleChangeForm1 = new SaleChangeForm();

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


				

				String error = new String();

				String query = "nothing";
				if(saleChangeForm.getProcess().equals("selectedSale"))
				  {
					  query = " FROM BeanSale u" +
		        	          " WHERE u.id_company = " + beanUser.getId_company().getId() +
		        	          " AND u.id = " + saleChangeForm.getId_sale();

					  List list = session.createQuery( query ).list();
					
					  if ( list.size() > 0 )
					    {
						    
						  httpSession.removeAttribute("qnCarListProduct");
						  httpSession.removeAttribute("beanSaleProductList");
						  
						  
						  BeanSale beanSale = (BeanSale) session.load( BeanSale.class, new Integer(saleChangeForm.getId_sale()));
						 
						  	//Busca al cliente por medio de la venta
							query = " FROM BeanClient u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
								    " AND u.id =" + beanSale.getId_client().getId() +
									" AND u.active = 'Y'" +
							  		" ORDER BY u.name";

							list = session.createQuery( query ).list();

							httpSession.removeAttribute("clientNumber");
							httpSession.removeAttribute("clientName");
							httpSession.removeAttribute("total");

							if( list.size()!=0 )
							  {
								httpSession.setAttribute("clientNumber",  new Integer(((BeanClient) list.get(0)).getId_intern()).toString() );
								httpSession.setAttribute("realClientNumber",  new Integer(((BeanClient) list.get(0)).getId()).toString() );
								httpSession.setAttribute("clientName", ((BeanClient) list.get(0)).getName() );
								httpSession.setAttribute("visibleUserData","yes");
								
								saleChangeForm.setDate_event(Utils.DateToStr( beanSale.getDate_event()));
								saleChangeForm.setId_hour(beanSale.getId_hour().getHour());
								saleChangeForm.setHourQuantity(String.valueOf( beanSale.getHourQuantity()) );
								saleChangeForm.setPersonsCount( String.valueOf(beanSale.getPeople_count()));
								saleChangeForm.setId_list( String.valueOf( beanSale.getId_list()) );
								//saleChangeForm.setId_option( String.valueOf(  beanSale.getId_list_option().getId()) );
								
								/*saleChangeForm.set
								*/
								//beanSale.getId_list().toString()
								
								int id_list = beanSale.getId_list().getId();
							    LocalUtils.putListInSession( request, 0 );
								int id_option = beanSale.getId_list_option().getId();
								LocalUtils.putOptionInSession( request, id_list, 0 );
								int id_menu = beanSale.getId_list_option_menu().getId();
								LocalUtils.putMenuInSession( request, id_option, 0 );
								
								
								query = " FROM BeanSaleFlower u" +
										" WHERE u.id_sale = " + beanSale.getId() +										
								  		" ORDER BY u.flower_description";

								list = session.createQuery( query ).list();							
								if( list.size()!=0 )
									httpSession.setAttribute("beanSaleFlower", (BeanSaleFlower)list.get(0));
								  
								
								
								query = " FROM BeanFlower u" +
										" WHERE u.id_company = " + beanUser.getId_company().getId() +
										" AND u.active = 'Y'" +
								  		" ORDER BY u.description";

								list = session.createQuery( query ).list();

								httpSession.setAttribute("listFlowers",  list );
								
								
								query = " FROM BeanSaleMusic u" +
										" WHERE u.id_sale = " + beanSale.getId() +										
								  		" ORDER BY u.music_description";

								list = session.createQuery( query ).list();
							
								if( list.size()!=0 )
									httpSession.setAttribute("beanSaleMusic", (BeanSaleMusic)list.get(0));
								
								
								

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
					
								httpSession.setAttribute("listSaloon", list2 );
								if ( list2.size() > 0 )
								  httpSession.setAttribute("sale_currentSaloon", (BeanSaloon) list2.get(0) );
					
								query = " FROM BeanTypeHour u" +
										" ORDER BY u.id";
			
								List list3 = session.createQuery( query ).list();
					
								httpSession.setAttribute("listHours", list3 );
				
								
								query = " FROM BeanSaleProduct u" +
										" WHERE u.id_sale = " + beanSale.getId() +										
								  		" ORDER BY u.product_description";

								list = session.createQuery( query ).list();							
								if( list.size()!=0 )
								{
									httpSession.setAttribute("beanSaleProductList", list);								
								   // saleChangeForm.setProductQuantity(String.valueOf( ((BeanSaleProduct)list.get(0)).getQty() ));
								
									/*List qnCarListProduct = (List) httpSession.getAttribute("qnCarListProduct");
									httpSession.removeAttribute("qnCarListProduct");*/
									
									BeanProduct beanProduct;// 
									List qnCarListProduct = new ArrayList<BeanProduct>();
									
									
									//Iterator iter = qnCarListProduct.iterator();
									Iterator iter = list.iterator();
									
									while ( iter.hasNext() )
									  {
										
										BeanSaleProduct beanSaleProduct = (BeanSaleProduct) iter.next();
										SaleProduct saleProduct = new SaleProduct();
									
										saleProduct.setId_product(beanSaleProduct.getId_product().getId());
										saleProduct.setDescription( beanSaleProduct.getId_product().getDescription() );
										saleProduct.setPrice( beanSaleProduct.getId_product().getPrice() );
										saleProduct.setQty(beanSaleProduct.getQty());
										saleProduct.setPerPerson( beanSaleProduct.getId_product().getPerPerson() );
										
										qnCarListProduct.add( saleProduct );
									  }
									
									//httpSession.removeAttribute( "qnCarListProduct" );
									httpSession.setAttribute("qnCarListProduct", qnCarListProduct);  
									
									
									
									httpSession.removeAttribute("total");							
									double total = 0;
					
											
									total = beanSale.getTotal();
									httpSession.setAttribute("total",  new Double( total ).toString() );
								
								}
					
								
								
								query = " FROM BeanProduct u" +
										" WHERE u.id_company = " + beanUser.getId_company().getId() +
										" AND u.active = 'Y'" +
								  		" ORDER BY u.description";
			
								List list4 = session.createQuery( query ).list();

								httpSession.setAttribute("listProduct", list4 );
								
								List list5 = session.createQuery( query ).list();
								
								httpSession.setAttribute("selectedSale", "yes");
								httpSession.setAttribute("beanSale", beanSale );
								httpSession.setAttribute("saleChangeForm", saleChangeForm );
								
								forward = mapping.findForward("loop");
							  }
							else
							  {
								httpSession.setAttribute("visibleUserData","no");
								httpSession.setAttribute("visibleCase","no");

								httpSession.setAttribute("done", "/eventAdmin/sale/saleChange.jsp");
								com.bituos.Error e = new com.bituos.Error("El cliente no existe", request);
								//forward declarado en struts-config.xml
								forward = mapping.findForward("error");
							  }
						  
						  httpSession.setAttribute("id_sale", saleChangeForm.getId_sale() );						  
						 
						  httpSession.setAttribute("currentSale", beanSale );	 
						  
						 				  
						  
						 
						  forward = mapping.findForward("loop");
					    }			  
					  else
					   {
						 com.bituos.Error e = new com.bituos.Error("Error consulte al soporte tecnico.", request);
						 httpSession.setAttribute("done", "javascript:window.close();" );						  
						 forward = mapping.findForward("error");
					   }
				  }
				else if(saleChangeForm.getProcess().equals("selectList"))
				  {
					  int id_list = LocalUtils.putListInSession( request, new Integer( saleChangeForm.getId_list() ).intValue() );
					  int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
					  int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );
					  
					  //conservar opciones seleccionadas de saloon, flores y musica
					  BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(saleChangeForm.getId_saloon()));
					  httpSession.setAttribute("sale_currentSaloon", beanSaloon );
					  
					  BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(saleChangeForm.getId_flower()));
					  httpSession.setAttribute("sale_currentFlower", beanFlower );
					  
					  BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(saleChangeForm.getId_music()));
					  httpSession.setAttribute("sale_currentMusic", beanMusic );
					  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica
					  
					  httpSession.setAttribute("selectedSale", "no");

					  forward = mapping.findForward("loop");
				  }
				else if(saleChangeForm.getProcess().equals("selectOption"))
				  {
					  int id_list = LocalUtils.getCurrentList( request );
					  int id_option = LocalUtils.putOptionInSession( request, id_list, new Integer(saleChangeForm.getId_option()).intValue() );
					  int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );


					  BeanListOptions beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(saleChangeForm.getId_option()));
//					  int personsCount = new Integer( saleChangeForm.getPersonsCount() ).intValue();
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
						  BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(saleChangeForm.getId_saloon()));
						  httpSession.setAttribute("sale_currentSaloon", beanSaloon );
						  
						  BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(saleChangeForm.getId_flower()));
						  httpSession.setAttribute("sale_currentFlower", beanFlower );
						  
						  BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(saleChangeForm.getId_music()));
						  httpSession.setAttribute("sale_currentMusic", beanMusic );
						  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica
						  httpSession.setAttribute("selectedSale", "no");
						  forward = mapping.findForward("loop");
					    }
					  
				  }
				else if( saleChangeForm.getProcess().equals("updateTotal") || saleChangeForm.getProcess().equals("register") )
				  {
					int personsCount = new Integer( saleChangeForm.getPersonsCount() ).intValue();
					BeanClient beanClient = (BeanClient) session.load( BeanClient.class, new Integer( (String) httpSession.getAttribute("realClientNumber") ));
					BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(saleChangeForm.getId_flower()));
					BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(saleChangeForm.getId_music()));
					BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(saleChangeForm.getId_saloon()));
					BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(saleChangeForm.getId_list()));
					BeanListOptions beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(saleChangeForm.getId_option()));
					BeanListOptionMenu beanListOptionMenu = (BeanListOptionMenu) session.load( BeanListOptionMenu.class, new Integer(saleChangeForm.getId_menu()));
					BeanTypeHour beanTypeHour = (BeanTypeHour) session.load( BeanTypeHour.class, new Integer(saleChangeForm.getId_hour()));
					
					int musicCount = 1;
					int flowerCount = 1;
					double price = 0;
					double total = 0;
					int dow = 0;
					Date date_event = Utils.StrToDate1( saleChangeForm.getDate_event() ); 
					int id_hour = new Integer (saleChangeForm.getId_hour() ).intValue();
					int hourQuantity = new Integer (saleChangeForm.getHourQuantity() ).intValue();
					boolean showError = false;
					double totalProducts = 0;
					int id_sale = 0;
					
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
							SaleProduct sp = (SaleProduct) iter.next();

							if ( sp.getPerPerson().equals("Y") )
							  totalProducts += personsCount * sp.getQty() * sp.getPrice();
							else
							  totalProducts += sp.getQty() * sp.getPrice();
						  }
					  }
					
					total = flowerCount*beanFlower.getPrice() + musicCount*beanMusic.getPrice() + personsCount*price + totalProducts; 
					
					
					
					BeanSale beanSaleOnSession = (BeanSale) httpSession.getAttribute( "beanSale" );
					BeanSale beanSale = (BeanSale) session.load( BeanSale.class, new Integer( beanSaleOnSession.getId() ) );
					//total = total + totalProducts;
					
					
					
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
						   
						    if(((BeanSale) list.get(0)).getId() != beanSale.getId())
						    {						
						      com.bituos.Error e = new com.bituos.Error("El evento especificado se solapa con otro existente, numero de evento: " + ((BeanSale) list.get(0)).getId(), request);
							  showError = true;
						    }
					    }
					
					  //conservar opciones seleccionadas de saloon, flores y musica
					  BeanSaloon beanSaloon1 = (BeanSaloon) session.load( BeanSaloon.class, new Integer(saleChangeForm.getId_saloon()));
					  httpSession.setAttribute("sale_currentSaloon", beanSaloon1 );
					  
					  BeanFlower beanFlower1 = (BeanFlower) session.load( BeanFlower.class, new Integer(saleChangeForm.getId_flower()));
					  httpSession.setAttribute("sale_currentFlower", beanFlower1 );
					  
					  BeanMusic beanMusic1 = (BeanMusic) session.load( BeanMusic.class, new Integer(saleChangeForm.getId_music()));
					  httpSession.setAttribute("sale_currentMusic", beanMusic1 );
					  
					  
					  int id_list =Integer.parseInt( saleChangeForm.getId_list() );//beanSale.getId_list().getId();
				     // LocalUtils.putListInSession( request, id_list );
					  int id_option =Integer.parseInt( saleChangeForm.getId_option());   // beanSale.getId_list_option().getId();
					 // LocalUtils.putOptionInSession( request, id_list, id_option );
					  int id_menu =Integer.parseInt(saleChangeForm.getId_menu() );// beanSale.getId_list_option_menu().getId();
					 // LocalUtils.putMenuInSession( request, id_option, id_menu );
					  
					  httpSession.setAttribute("currentId_list", String.valueOf( id_list ) );
					  httpSession.setAttribute("currentId_option",String.valueOf( id_option) );
					  httpSession.setAttribute("currentId_menu", String.valueOf(id_menu) );
					  
					  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica
					  
					  
					  httpSession.setAttribute("selectedSale", "no");
					  
					if ( !showError )
					  {
						if ( saleChangeForm.getProcess().equals("register") )
						  {
							
							beanSale.setDate_reg( Utils.Today() );
							beanSale.setDate_event( date_event  );
							
							beanSale.setId_client( beanClient );
							beanSale.setClient_name( beanClient.getName() );
							beanSale.setClient_address( beanClient.getAddress() );
							
							beanSale.setPeople_count( personsCount );
							
							beanSale.setId_list( beanList );
							beanSale.setList_description( beanList.getDescription() );
							beanSale.setList_min( beanListOptions.getMin() );
							beanSale.setList_max( beanListOptions.getMax() );
	
							beanSale.setId_list_option( beanListOptions );
							beanSale.setTotal( total );
							beanSale.setId_company( beanUser.getId_company() );
							beanSale.setId_user( beanUser );
							beanSale.setId_list_option_menu( beanListOptionMenu );
							beanSale.setOption_description( "" );  //next: quizas no tenga sentido este field
							beanSale.setMenu_description( beanListOptionMenu.getName() );
							beanSale.setId_saloon( beanSaloon );
							beanSale.setSaloon_description( beanSaloon.getDescription() );
							
							if ( Utils.isEmail( saleChangeForm.getAnotherEmail() ))
								beanSale.setAnotherEmail( saleChangeForm.getAnotherEmail() );
							else
								beanSale.setAnotherEmail( "" );
							
							beanSale.setMenu_price( price );
							beanSale.setClient_phone( beanClient.getPhone() );
							beanSale.setClient_company( beanClient.getCompany() );
							beanSale.setClient_email( beanClient.getEmail() );
							
							beanSale.setId_hour( beanTypeHour );
							beanSale.setHourQuantity( hourQuantity );
							
						  	tx = session.beginTransaction();
							//session.save( beanSale );
							session.update(beanSale);
							id_sale = beanSale.getId();
							  
							 
//							total anterior que estaba en la bd + el total de los nuevos productos
							
							List beanSaleProductList = (List) httpSession.getAttribute("beanSaleProductList");
							
							if ( beanSaleProductList != null )
							  {
								
								Iterator iter = beanSaleProductList.iterator();
								
								while ( iter.hasNext() )
								  {
									BeanSaleProduct sp = (BeanSaleProduct) iter.next();
									
									session.delete(sp);
								  }
							  }
							
							
							if ( qnCarListProduct != null )
								  {
									Iterator iter = qnCarListProduct.iterator();
									
									while ( iter.hasNext() )
									  {
										SaleProduct sp = (SaleProduct) iter.next();										
										BeanSaleProduct beanSaleProduct = new BeanSaleProduct();
										
										BeanProduct beanProduct = (BeanProduct) session.load( BeanProduct.class, new Integer(sp.getId_product()));
										beanSaleProduct.setId_sale(beanSale);
										beanSaleProduct.setId_product( beanProduct );
										beanSaleProduct.setProduct_description( beanProduct.getDescription() );
										beanSaleProduct.setQty( sp.getQty() );
										beanSaleProduct.setPerPerson( beanProduct.getPerPerson() );
										beanSaleProduct.setPersonsQty( personsCount );
										beanSaleProduct.setProduct_price( sp.getPrice() );

										session.save( beanSaleProduct );
										
									  }
								  }
							  
				
							query = " FROM BeanSaleFlower u" +
							        " WHERE u.id_sale = " + beanSale.getId();
							 
							
							list = session.createQuery( query ).list();
							BeanSaleFlower beanSaleFlower = new BeanSaleFlower();
							if ( list.size() > 0 )
							  {
								//BeanSaleFlower beanSaleFlower = (BeanSaleFlower) session.load( BeanSaleFlower.class, new Integer(beanSale.getId()));
								beanSaleFlower = (BeanSaleFlower)list.get(0);
							  }
							
							query = " FROM BeanSaleMusic u" +
					        		" WHERE u.id_sale = " + beanSale.getId();
					 
					
							list = session.createQuery( query ).list();
							BeanSaleMusic beanSaleMusic = new BeanSaleMusic();
							if ( list.size() > 0 )
							  {
								//BeanSaleMusic beanSaleMusic = (BeanSaleMusic) session.load( BeanSaleMusic.class, new Integer(beanSale.getId()));
								beanSaleMusic = (BeanSaleMusic)list.get(0);
							  }
									
							
							
							session.delete(beanSaleFlower);
							BeanSaleFlower beanSaleFlower1 = new BeanSaleFlower();
						
							beanSaleFlower1.setId_sale( beanSale );
							beanSaleFlower1.setFlower_price( beanFlower.getPrice() );
							beanSaleFlower1.setFlower_perPerson( beanFlower.getPerPerson() );
							beanSaleFlower1.setId_flower( beanFlower );
							beanSaleFlower1.setFlower_description( beanFlower.getDescription() );
							beanSaleFlower1.setPersonsQty( personsCount );
							session.save( beanSaleFlower1 );
								
						
							
							
							session.delete(beanSaleMusic);
							BeanSaleMusic beanSaleMusic1 = new BeanSaleMusic();	
							beanSaleMusic1.setId_sale( beanSale );
							beanSaleMusic1.setId_music( beanMusic );
							beanSaleMusic1.setMusic_description( beanMusic.getDescription() );
							beanSaleMusic1.setMusic_price( beanMusic.getPrice() );
							beanSaleMusic1.setMusic_perPerson( beanMusic.getPerPerson() );
							beanSaleMusic1.setPersonsQty( personsCount );
							session.save( beanSaleMusic1 );
								
							  
							
							
							
							tx.commit();
							
							//envio de email
							Aes aes = new Aes();
							String encID = Aes.encrypt( new Integer(beanSale.getId()).toString() ); 
							BeanControlPanel beanControlPanel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer(1));
							
							try
							  {
								if ( Utils.isEmail(beanClient.getEmail() ))
									new com.bituos.Mail( beanClient.getEmail()
											, "Hola\n\n" + 
											  "La cotizacion solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
											  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
											  beanUser.getId_company().getFull_name()
											, "Cotizacion " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
											, request);
								
								if ( Utils.isEmail( saleChangeForm.getAnotherEmail() ))
									new com.bituos.Mail( saleChangeForm.getAnotherEmail()
											, "Hola\n\n" + 
											  "La cotizacion solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
											  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
											  beanUser.getId_company().getFull_name()
											, "Cotizacion " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
											, request);
							  }
							catch (Exception m)
							  {
								
							  }
							
							
							httpSession.setAttribute( "crystal_rptname", beanUser.getId_company().getNameRptSale() ) ;
							httpSession.setAttribute( "parameterCount", new Integer(1) ) ;

							//id sale
							httpSession.setAttribute( "parameter1", new Integer( id_sale ) );
							httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
							httpSession.setAttribute( "parameterName1", new String("id_sale") ) ;
							
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
				else if(saleChangeForm.getProcess().equals("calendar"))
				  {
					query = " FROM BeanSale u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId();/* +
						    " AND u.id_saloon = " + saleChangeForm.getId_saloon();*/

					List list = session.createQuery( query ).list();

					httpSession.setAttribute("listCalendar", list );
					
					forward = mapping.findForward("showCalendar");
				  }
				else if(saleChangeForm.getProcess().equals("addProduct"))
				  {
					String id_str = saleChangeForm.getId_product();
					BeanProduct beanProduct = (BeanProduct) session.load( BeanProduct.class, new Integer(id_str));
					List qnCarListProduct = (List) httpSession.getAttribute("qnCarListProduct");
					
					if ( qnCarListProduct == null )
					  qnCarListProduct = new ArrayList<BeanProduct>();
					
					int id = beanProduct.getId();
					int newQty = (new Integer( saleChangeForm.getProductQuantity() )).intValue(); 
					
					if (newQty < 0)
					{
						if (qnCarListProduct.size() == 0)
						  {
							httpSession.setAttribute("done", "javascript:history.back();");
							com.bituos.Error e = new com.bituos.Error("No hay productos a eliminar", request);
							forward = mapping.findForward("error");
							return forward;
						  }
						else
						 {				
							
							Iterator iter = qnCarListProduct.iterator();	
							boolean found = false;
							while ( iter.hasNext() )
							  {
								SaleProduct saleProductTemp = (SaleProduct) iter.next();
								if (saleProductTemp.getId_product() == beanProduct.getId())
								{
									found = true;
								}
							  }
							
							if (!found)
							  {
								httpSession.setAttribute("done", "javascript:history.back();");
								com.bituos.Error e = new com.bituos.Error("No hay productos a eliminar de ese tipo", request);
								forward = mapping.findForward("error");
								return forward;
							  }
								
						 }
					}
							
					
					SaleProduct saleProduct = new SaleProduct();
					 
					saleProduct.setId_product( beanProduct.getId() );
					saleProduct.setDescription( beanProduct.getDescription() );
					saleProduct.setPrice( beanProduct.getPrice() );
					saleProduct.setQty( newQty );
					saleProduct.setPerPerson( beanProduct.getPerPerson() );
					
					int index = saleProduct.indexOf( qnCarListProduct );
					
					if ( index < 0 )
					  {
						qnCarListProduct.add( saleProduct );
					  }
					else
					  {
						SaleProduct saleProduct1 = new SaleProduct();
						
						
						saleProduct1.setQty( saleProduct1.getQty() + saleProduct.getQty() );
						
						if ( saleProduct1.getQty() <= 0 )
							qnCarListProduct.remove(index);  
					  }
					
					if ( qnCarListProduct.size()> 0 )
					  httpSession.setAttribute("qnCarListProduct", qnCarListProduct );
					else
					  httpSession.removeAttribute( "qnCarListProduct" );
					

					  //conservar opciones seleccionadas de saloon, flores y musica
					  BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(saleChangeForm.getId_saloon()));
					  httpSession.setAttribute("sale_currentSaloon", beanSaloon );
					  
					  BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(saleChangeForm.getId_flower()));
					  httpSession.setAttribute("sale_currentFlower", beanFlower );
					  
					  BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(saleChangeForm.getId_music()));
					  httpSession.setAttribute("sale_currentMusic", beanMusic );
					  //fin de codigo para conservar opciones seleccionadas de saloon, flores y musica
					
					forward = mapping.findForward("loop");
				  }
				else if( saleChangeForm.getProcess().equals("registerZZZZZZ") )
				  {
					BeanClient client = (BeanClient) httpSession.getAttribute("client");
				  	boolean ok = false;
				  	
					BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(saleChangeForm.getId_list()));

					//Se pasan los valores a el objeto bean de tipo BeanCase
					/*bean.setId_list( beanList );
					bean.setDate_reg( Utils.Today() );
					bean.setId_user( beanUser );
					bean.setId_company( beanUser.getId_company() );*/

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
					//httpSession.setAttribute("msg", "Su numero de caso es " + bean.getId());
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

