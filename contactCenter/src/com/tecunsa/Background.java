package com.tecunsa;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */


import com.tecunsa.Beans.*;
import javax.servlet.http.*;

//Hibernate 2.1

import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;
import java.util.*;


public class Background {

  public Background( HttpServletRequest req )
    {
		try
		  {
			HttpSession httpSession = req.getSession();

			Config config = new Config( req );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try 
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( req ).buildSessionFactory();
				session 	= sessionFactory.openSession();

				/*
				// actualizar realFileName
				String query =  " FROM BeanFiles u" +
								" WHERE u.realFileName is null OR u.realFileName =''";

				List list = session.createQuery( query ).list();
				Iterator iter = list.iterator();

				while ( iter.hasNext() )
					{
						BeanFiles beanFile = (BeanFiles) iter.next();
						String aux = beanFile.getFullFileName();
						int index = aux.indexOf("/");
						 
						while( index > -1 )
						  {
						  	aux = aux.substring( index+1, aux.length() );
							index = aux.indexOf("/");
						  }
						
						beanFile.setRealFileName( aux );

						tx = session.beginTransaction();
						session.save( beanFile);
						tx.commit();

					}
				*/
				
				/*
				// actualizar date_delivery
				String query2 =  " FROM BeanCase u";

				List list2 = session.createQuery( query2 ).list();
				Iterator iter2 = list2.iterator();

				while ( iter2.hasNext() )
					{
						BeanCase beanCase = (BeanCase) iter2.next();
						
						query2 = " FROM BeanCaseNotes cn" +
						         " WHERE cn.id_case.id=" + beanCase.getId() +
						         " ORDER BY cn.date_reg";
						        
						List list3 = session.createQuery(  query2 ).list();
						Iterator iter3 = list3.iterator();
						int days = beanCase.getId_type_case().getDays();
						
						while ( iter3.hasNext() )
						  {
						  	BeanCaseNotes  cn = (BeanCaseNotes) iter3.next(); 
						  	
						  	days += cn.getDays();
						  }

						
						Date date_sum = Utils.Date_plus( beanCase.getDate_reg(), days);
						Date date_last_forward = Utils.Date_plus( beanCase.getDate_last_change(), beanCase.getId_type_case().getDays() );
						
						if ( beanCase.getId_type_case_status().getId() == 1 )
							if ( date_last_forward.before(date_sum)  )  
							  beanCase.setDate_delivery( Utils.Date_plus( beanCase.getDate_reg(), beanCase.getId_type_case().getDays()) );
							else
							  beanCase.setDate_delivery( Utils.Date_plus( beanCase.getDate_reg(), days) );
						else
						   beanCase.setDate_delivery( Utils.Date_plus( beanCase.getDate_reg(), days) );
						
						
						tx = session.beginTransaction();
						session.save( beanCase );
						tx.commit();

					}
				*/




				//query para ver directament
//				select *, date_last_change, u.date_last_change + tc.days
//				FROM Cases u, type_case tc
//				where u.date_last_change + tc.days < '20080815'
//				and u.id_type_case = tc.id
//				AND u.lazy='N'
//				AND u.id_type_case_status = 1
//				ORDER BY u.date_last_change + tc.days

				//query para ver directament, con el cambio de delivery
//				select *, date_reg, date_last_change, date_delivery
//				FROM Cases u, type_case tc
//				where u.date_delivery  < '20080831'
//				and u.id_type_case = tc.id
//				AND u.id_type_case_status = 1
//				ORDER BY u.date_delivery

				
				String query =  " FROM BeanCase u" +
								//" WHERE u.date_last_change + u.id_type_case.days < '" + Utils.DateToStrInvMySQL( Utils.Today() ) + "'" +
								" WHERE u.date_delivery < '" + Utils.DateToStrInvMySQL( Utils.Today() ) + "'" +
								" AND u.lazy='N'" +
								" AND u.id_type_case_status.id = 1";

				List list = session.createQuery( query ).list();
				Iterator iter = list.iterator();

				while ( iter.hasNext() )
					{
						BeanCase beanCase = (BeanCase) iter.next();
						
						beanCase.setLazy("S");

						tx = session.beginTransaction();
						session.save( beanCase);
						tx.commit();

						query =  " FROM BeanUserUser u" +
								 " WHERE u.id_user.id = " + beanCase.getId_user().getId();

						List list1 = session.createQuery( query ).list();

						if ( list1.size() > 0 )
						  {
							BeanUserUser beanUserUser = (BeanUserUser) list1.get(0);

								//Envia Email diciendolo al nuevo user que tiene un caso
							new com.tecunsa.Mail(  beanUserUser.getId_supervisor().getEmail(),
												"Hola:\n\n" +
												"A Ud se le esta avisando, por esta via, que hay un caso atrasado de un subordinado suyo" + "\n\n" +
												"Datos del caso:\n" +
												"Numero de caso: " + beanCase.getId() + "\n" +
												"Nombre del cliente:" + beanCase.getId_client().getName() + "\n" +
												"Nombre del usuario que esta atrasado:" + beanCase.getId_user().getName() + "\n" +
												"Descripcion corta: " + beanCase.getShort_description() + "\n" +
												"Descripcion larga: " + beanCase.getDescription() + "\n\n\n" +
												"Sistema Contact Center."
												,
												"Contact Center: caso atrasado: " +  beanCase.getId(),
												req);
						  }
					}
			  }
			catch (Exception e) 
			  {
				   e.printStackTrace();

				   com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", req);
			   }
		    finally	
			   {
				   if ( session != null )
					   session.close();

				   if ( sessionFactory != null )
					   sessionFactory.close();
			   }


		  }
		catch( Throwable  m )
		  {
			m.printStackTrace();
		  }
    }



}
