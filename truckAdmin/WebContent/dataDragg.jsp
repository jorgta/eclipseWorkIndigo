
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*,net.sf.hibernate.*"%>

<%
    HttpSession ses = request.getSession();

	String id_tire = request.getParameter("id_tire");
	String truck_tire_cong_detail_id_origin = request.getParameter("truck_tire_cong_detail_id_origin");
	String targetPosition = request.getParameter("targetPosition");
	String targetPlace  = request.getParameter("targetPlace");
	
	String deep  = request.getParameter("deep");
	String km_truck  = request.getParameter("km_truck");
	String km_int  = request.getParameter("km_int");
	String active  = request.getParameter("active");
    // deep;
    // km_truck;
    // km_int; 
    net.sf.hibernate.SessionFactory sessionFactory = null;
    net.sf.hibernate.Session sessionHibernate = null;
	Transaction tx = null;
	
    try {
	
			
			com.bituos.Config configuration = new com.bituos.Config( request );
			sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
			sessionHibernate = sessionFactory.openSession();
			
			//Transaction tx = null;
			/*BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			BeanCompany beanComany = (BeanCompany) ses.getAttribute( "BeanCompany" );*/
			
			
			if(targetPlace.equals("3"))//veiculo
			  {
				String query =  " FROM BeanTireTruck tt" +
								" WHERE tt.id_tire.id=" + id_tire +
								" AND tt.position.id=" + truck_tire_cong_detail_id_origin +
								" AND tt.active ='" + active + "'" +
								" ORDER BY tt.id"  +
								" DESC LIMIT 1";
				
				
				//ses.removeAttribute(list );
				List listObjects = null;	
				listObjects = sessionHibernate.createQuery( query ).list();
				
				if(listObjects != null || !listObjects.isEmpty())
				  {
					BeanTireTruck beanTireTruck= (BeanTireTruck)listObjects.get(0);
					
					tx = sessionHibernate.beginTransaction();
					
					beanTireTruck.setActive("N");	
					
					BeanTireTruck beanTireTruckNew= new BeanTireTruck();
					
					
					beanTireTruckNew.setActive("Y");
					beanTireTruckNew.setId_tire(beanTireTruck.getId_tire());
					beanTireTruckNew.setId_truck(beanTireTruck.getId_truck());
					
					BeanTireMovements beanTireMovements = new BeanTireMovements();
					
					
						
					BeanTypePosition beanTypePosition = (BeanTypePosition) sessionHibernate.load( BeanTypePosition.class, new Integer( 1 ) );  //en el vehiculo
					beanTireTruckNew.setId_type_position(beanTypePosition);
					
					BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail) sessionHibernate.load( BeanTruckTireConfigurationDetail.class, new Integer( targetPosition ) );
					beanTireTruckNew.setPosition(beanTruckTireConfigurationDetail);
					
					BeanTypePlace beanTypePlace = (BeanTypePlace) sessionHibernate.load( BeanTypePlace.class, new Integer( targetPlace ) );
					
					
						
					if(beanTruckTireConfigurationDetail == null)
					{
			%>
					  Error Interno: Ocurrio un error consulte a soporte tecnico.
			<%
					}
			
					beanTireMovements.setId_tire(beanTireTruck.getId_tire());
					Date ahora = new Date();
					beanTireMovements.setDate_reg(ahora);
					beanTireMovements.setDeep(Double.valueOf( deep  ));
					
					
					beanTireMovements.setId_type_measure(beanTireTruck.getId_tire().getId_type_measure());
					
					beanTireMovements.setId_type_place(beanTypePlace);
					
					
					query = " FROM BeanTireMovements t" +
				            " WHERE t.id_tire =" + beanTireTruck.getId_tire().getId() +				
				            " ORDER by t.id DESC LIMIT 1";
		
					List list = sessionHibernate.createQuery(query).list();
					BeanTireMovements beanTireMovementsPrevious= null;
						
					if(!list.isEmpty())
					{
						beanTireMovementsPrevious = (BeanTireMovements) list.get(0);						
					
						if(beanTireMovementsPrevious.getKm_truck() <= Integer.valueOf( km_truck ) )
						{
						    
						    beanTireMovements.setKm_truck(Integer.valueOf(km_truck)); 
						}
						else
						{
			%>
			                El kilometraje del vehículo no puede ser menor que el registrado anteriormente.
			<% 				
							sessionHibernate.close();
							return;
						}
				    }
						
						
						
					if(beanTireMovementsPrevious.getKm_int() <= Integer.valueOf( km_int ) )	
					{
					    beanTireMovements.setKm_int(Integer.valueOf( km_int  ) );
					}
					else
					{			
				
			%>
					  El kilometraje del neumático no puede ser menor que el registrado anteriormente.
			<%
					  sessionHibernate.close();
					  return;
					}
						
						//BeanTireMovements beanTireMovements = (beanTireMovements) session.load( BeanTruckTireConfigurationDetail.class, new Integer( 0 ) );//Posicion Ninguna
						
	
						
						beanTireMovements.setMeasurentment(Double.valueOf(beanTireMovementsPrevious.getMeasurentment()));
						beanTireMovements.setDesign(beanTireTruck.getId_tire().getDesign());
						beanTireMovements.setBuy_of_reference(beanTireMovementsPrevious.getBuy_of_reference());
						beanTireMovements.setPrice(Double.valueOf(beanTireMovementsPrevious.getPrice()));
						
						
						sessionHibernate.update(beanTireTruck);
						sessionHibernate.save(beanTireTruckNew);
						sessionHibernate.save(beanTireMovements);
						
						tx.commit();
						
						
					  
			 
					
				  }
				else
				  {
					
				  }
			  }
			else if(targetPlace.equals("1"))//almacen
			 {
		
				String query =  " FROM BeanTireTruck tt" +
								" WHERE tt.id_tire.id=" + id_tire +
								" AND tt.position.id=" + truck_tire_cong_detail_id_origin +
								" AND tt.active ='Y'";
				
				
				//ses.removeAttribute(list );
				List listObjects = null;	
				listObjects = sessionHibernate.createQuery( query ).list();
				
				if(listObjects != null || !listObjects.isEmpty())
				  {
					BeanTireTruck beanTireTruck= (BeanTireTruck)listObjects.get(0);			
		 
					
					tx = sessionHibernate.beginTransaction();			
					beanTireTruck.setActive("N");	
					
					BeanTireMovements beanTireMovements = new BeanTireMovements();
					beanTireMovements.setId_tire(beanTireTruck.getId_tire());
					Date ahora = new Date();
					beanTireMovements.setDate_reg(ahora);
					beanTireMovements.setDeep(Double.valueOf( deep  ));
					
					//BeanTypePosition beanTypePosition = (BeanTypePosition) sessionHibernate.load( BeanTypePosition.class, new Integer( 1 ) );
					
					
					//BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail) sessionHibernate.load( BeanTruckTireConfigurationDetail.class, new Integer( targetPosition ) );
					
					
					BeanTypePlace beanTypePlace = (BeanTypePlace) sessionHibernate.load( BeanTypePlace.class, new Integer( targetPlace ) );
					
					beanTireMovements.setId_type_measure(beanTireTruck.getId_tire().getId_type_measure());
					
					beanTireMovements.setId_type_place(beanTypePlace);
					
					
					query = " FROM BeanTireMovements t" +
				            " WHERE t.id_tire =" + beanTireTruck.getId_tire().getId() +				
				            " ORDER by t.id DESC LIMIT 1";
		
					List list = sessionHibernate.createQuery(query).list();
					BeanTireMovements beanTireMovementsPrevious= null;
						
					if(!list.isEmpty())
					{
						beanTireMovementsPrevious = (BeanTireMovements) list.get(0);						
					
						if(beanTireMovementsPrevious.getKm_truck() <= Integer.valueOf( km_truck ) )
						{
						    
						    beanTireMovements.setKm_truck(Integer.valueOf(km_truck)); 
						}
						else
						{
			%>
			                El kilometraje del vehículo no puede ser menor que el registrado anteriormente.
			<% 				
							sessionHibernate.close();
							return;
						}
				    }
						
						
						
					if(beanTireMovementsPrevious.getKm_int() <= Integer.valueOf( km_int ) )	
					{
					    beanTireMovements.setKm_int(Integer.valueOf( km_int  ) );
					}
					else
					{			
				
			%>
					  El kilometraje del neumático no puede ser menor que el registrado anteriormente.
			<%
					  sessionHibernate.close();
					  return;
					}
						
						//BeanTireMovements beanTireMovements = (beanTireMovements) session.load( BeanTruckTireConfigurationDetail.class, new Integer( 0 ) );//Posicion Ninguna
		
					beanTireMovements.setMeasurentment(Double.valueOf(beanTireMovementsPrevious.getMeasurentment()));
					beanTireMovements.setDesign(beanTireTruck.getId_tire().getDesign());
					beanTireMovements.setBuy_of_reference(beanTireMovementsPrevious.getBuy_of_reference());
					beanTireMovements.setPrice(Double.valueOf(beanTireMovementsPrevious.getPrice()));
					
					
					sessionHibernate.update(beanTireTruck);
					sessionHibernate.save(beanTireMovements);
					
					tx.commit();
					
					
					
				 }
					
			 }else if(targetPlace.equals("6"))//trash
			 {
				 String query = " FROM BeanTireTruck tt" +
								" WHERE tt.id_tire.id=" + id_tire +
								" AND tt.position.id=" + truck_tire_cong_detail_id_origin +
								" AND tt.active ='Y'";
		
		
				//ses.removeAttribute(list );
				 List listObjects = null;	
				 listObjects = sessionHibernate.createQuery( query ).list();
				
				 if(listObjects != null || !listObjects.isEmpty())
				 {
					BeanTireTruck beanTireTruck= (BeanTireTruck)listObjects.get(0);			
					
					
					tx = sessionHibernate.beginTransaction();			
					beanTireTruck.setActive("N");	
					
					BeanTireMovements beanTireMovements = new BeanTireMovements();
					beanTireMovements.setId_tire(beanTireTruck.getId_tire());
					Date ahora = new Date();
					beanTireMovements.setDate_reg(ahora);
					beanTireMovements.setDeep(Double.valueOf( deep  ));
		
					
					BeanTypePlace beanTypePlace = (BeanTypePlace) sessionHibernate.load( BeanTypePlace.class, new Integer( targetPlace ) );
					
					beanTireMovements.setId_type_measure(beanTireTruck.getId_tire().getId_type_measure());
					
					beanTireMovements.setId_type_place(beanTypePlace);
					
					
					query = " FROM BeanTireMovements t" +
					        " WHERE t.id_tire =" + beanTireTruck.getId_tire().getId() +				
					        " ORDER by t.id DESC LIMIT 1";
					
					List list = sessionHibernate.createQuery(query).list();
					BeanTireMovements beanTireMovementsPrevious= null;
						
					if(!list.isEmpty())
					{
						beanTireMovementsPrevious = (BeanTireMovements) list.get(0);						
					
						if(beanTireMovementsPrevious.getKm_truck() <= Integer.valueOf( km_truck ) )
						{
						    
						    beanTireMovements.setKm_truck(Integer.valueOf(km_truck)); 
						}
						else
						{
					%>
					     El kilometraje del vehículo no puede ser menor que el registrado anteriormente.
					<% 				
							sessionHibernate.close();
							return;
						}
					}
						
						
						
					if(beanTireMovementsPrevious.getKm_int() <= Integer.valueOf( km_int ) )	
					{
					  beanTireMovements.setKm_int(Integer.valueOf( km_int  ) );
					}
					else
					{			
					
					%>
					 El kilometraje del neumático no puede ser menor que el registrado anteriormente.
					<%
					sessionHibernate.close();
					return;
					}
						
						//BeanTireMovements beanTireMovements = (beanTireMovements) session.load( BeanTruckTireConfigurationDetail.class, new Integer( 0 ) );//Posicion Ninguna
					
					beanTireMovements.setMeasurentment(Double.valueOf(beanTireMovementsPrevious.getMeasurentment()));
					beanTireMovements.setDesign(beanTireTruck.getId_tire().getDesign());
					beanTireMovements.setBuy_of_reference(beanTireMovementsPrevious.getBuy_of_reference());
					beanTireMovements.setPrice(Double.valueOf(beanTireMovementsPrevious.getPrice()));
					
					
					sessionHibernate.update(beanTireTruck);
					
					sessionHibernate.save(beanTireMovements);
					
					tx.commit();
				
				
				
				} 
			 }else if(targetPlace.equals("4"))//extra
			 {
				 String query = " FROM BeanTireTruck tt" +
								" WHERE tt.id_tire.id=" + id_tire +
								" AND tt.position.id=" + truck_tire_cong_detail_id_origin +
								" AND tt.active ='Y'";
				
				
				//ses.removeAttribute(list );
				List listObjects = null;	
				listObjects = sessionHibernate.createQuery( query ).list();
				
				if(listObjects != null || !listObjects.isEmpty())
				{
					BeanTireTruck beanTireTruck= (BeanTireTruck)listObjects.get(0);	
					
					tx = sessionHibernate.beginTransaction();					
					beanTireTruck.setActive("N");					
					BeanTireTruck beanTireTruckNew= new BeanTireTruck();
					
					
					beanTireTruckNew.setActive("Y");
					beanTireTruckNew.setId_tire(beanTireTruck.getId_tire());
					beanTireTruckNew.setId_truck(beanTireTruck.getId_truck());
					
					BeanTireMovements beanTireMovements = new BeanTireMovements();
					beanTireMovements.setId_tire(beanTireTruck.getId_tire());
					Date ahora = new Date();
					beanTireMovements.setDate_reg(ahora);
					beanTireMovements.setDeep(Double.valueOf( deep  ));
					
						
					BeanTypePosition beanTypePosition = (BeanTypePosition) sessionHibernate.load( BeanTypePosition.class, new Integer( 2 ) );  //en el vehiculo extra
					beanTireTruckNew.setId_type_position(beanTypePosition);
					
					BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail) sessionHibernate.load( BeanTruckTireConfigurationDetail.class, new Integer( targetPosition ) );
					//BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail) session.load( BeanTruckTireConfigurationDetail.class, new Integer( 0 ) );//0 Posicion Ninguna
					beanTireTruckNew.setPosition(beanTruckTireConfigurationDetail);
					
					BeanTypePlace beanTypePlace = (BeanTypePlace) sessionHibernate.load( BeanTypePlace.class, new Integer( targetPlace ) );
					
					beanTireMovements.setId_type_measure(beanTireTruck.getId_tire().getId_type_measure());					
					beanTireMovements.setId_type_place(beanTypePlace);
					
					
					query = " FROM BeanTireMovements t" +
							" WHERE t.id_tire =" + beanTireTruck.getId_tire().getId() +				
							" ORDER by t.id DESC LIMIT 1";
					
					List list = sessionHibernate.createQuery(query).list();
					BeanTireMovements beanTireMovementsPrevious= null;
						
					if(!list.isEmpty())
					{
						beanTireMovementsPrevious = (BeanTireMovements) list.get(0);						
					
						if(beanTireMovementsPrevious.getKm_truck() <= Integer.valueOf( km_truck ) )
						{
						    
						   beanTireMovements.setKm_truck(Integer.valueOf(km_truck)); 
						}
						else
						{
		%>
					       El kilometraje del vehículo no puede ser menor que el registrado anteriormente.
		<% 				
						   sessionHibernate.close();
					       return;
						}
					}
						
						
						
					if(beanTireMovementsPrevious.getKm_int() <= Integer.valueOf( km_int ) )	
					{
					  beanTireMovements.setKm_int(Integer.valueOf( km_int  ) );
					}
					else
					{			
					
		%>
					  El kilometraje del neumático no puede ser menor que el registrado anteriormente.
		<%
					  sessionHibernate.close();
					  return;
					}
						
						//BeanTireMovements beanTireMovements = (beanTireMovements) session.load( BeanTruckTireConfigurationDetail.class, new Integer( 0 ) );//Posicion Ninguna
					
					beanTireMovements.setMeasurentment(Double.valueOf(beanTireMovementsPrevious.getMeasurentment()));
					beanTireMovements.setDesign(beanTireTruck.getId_tire().getDesign());
					beanTireMovements.setBuy_of_reference(beanTireMovementsPrevious.getBuy_of_reference());
					beanTireMovements.setPrice(Double.valueOf(beanTireMovementsPrevious.getPrice()));
					 
					
					
					sessionHibernate.update(beanTireTruck);
					sessionHibernate.save(beanTireTruckNew);
					sessionHibernate.save(beanTireMovements);
					
					tx.commit();
					
					
					
				}  
			 }
	
	
		} catch (Exception e) {
		
		    e.printStackTrace();
		    
			// Report the error using the appropriate name and ID.
			/*com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
			errors.add("name", new ActionError("id"));*/
		
		}
		finally	{
			if ( sessionHibernate != null )
				sessionHibernate.close(); 
			  
			if ( sessionFactory != null )
				 sessionFactory.close();
		}
%>
				 
