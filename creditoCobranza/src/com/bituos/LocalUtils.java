/*
 * Created on Feb 09, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.bituos;

/**
 * @author David Sanchez Aroche (dsa@bituos.com)
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.*;
import java.text.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.struts2.Beans.*;

import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;

public class LocalUtils {

	
	
	public static int getCurrentList( HttpServletRequest request ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		BeanList beanList = (BeanList) httpSession.getAttribute( "listEdit_currentList" );
		
		if ( beanList == null )
		  return 0;
		else
		  return beanList.getId();
	  }

	public static int getCurrentOption( HttpServletRequest request ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		BeanListOptions beanListOptions = (BeanListOptions) httpSession.getAttribute( "listEdit_currentOption" );
		
		if ( beanListOptions == null )
		  return 0;
		else
		  return beanListOptions.getId();
	  }

	

	public static int putListInSession( HttpServletRequest request, int id_list ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		httpSession.removeAttribute( "listEdit_currentList" );							    
		httpSession.removeAttribute( "listEdit_currentOption" );							    
		httpSession.removeAttribute( "listEdit_currentMenu" );			

		httpSession.removeAttribute( "listList" );
		httpSession.removeAttribute( "listOptionsList" );
		httpSession.removeAttribute( "listOptionMenuList" );
		
		try 
		  {
			//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

			String query = " FROM BeanList u" +
						   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
						   " AND u.active = 'Y' " +  
						   " ORDER BY u.description";
				
			List list = session.createQuery( query ).list();
		  			
			httpSession.setAttribute("listList", list );

			if ( list.size() != 0 )
			  {
				BeanList beanList = null;
				
				if ( id_list == 0)
				  beanList = (BeanList) list.get(0);
				else
				  beanList = (BeanList) session.load( BeanList.class, new Integer(id_list) );
				
				httpSession.setAttribute("listEdit_currentList", beanList );
				
				Return = beanList.getId();
			  }
		  }
		catch (Exception m) 
		  {
			m.printStackTrace();
		  }
		finally	
		  {
			if ( session != null )
				session.close();

			if ( sessionFactory != null )
				sessionFactory.close();
		  }
		return Return;
	  }
	
	
	public static int putOptionInSession( HttpServletRequest request, int id_list, int id_option ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;
		
		httpSession.removeAttribute( "listEdit_currentOption" );							    
		httpSession.removeAttribute( "listEdit_currentMenu" );			

		httpSession.removeAttribute( "listOptionsList" );
		httpSession.removeAttribute( "listOptionMenuList" );

		try 
		  {
			//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

			if (id_list==0)
			  {
				String query =" FROM BeanListOptions u";
		
				List list = session.createQuery( query ).list();
				
				list.clear();
			
				httpSession.setAttribute("listOptionsList", list );

				return 0;
			  }
			
			String query =" FROM BeanListOptions u" +
						  " WHERE u.id_list = " + id_list +  
						  " ORDER BY u.min";
				
			List list = session.createQuery( query ).list();
		  			
			httpSession.setAttribute("listOptionsList", list );

			if ( list.size() != 0 )
			  {
				BeanListOptions beanListOptions = null;
				
				if ( id_option == 0)
					beanListOptions = (BeanListOptions) list.get(0);
				else
					beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(id_option) );
				
				httpSession.setAttribute("listEdit_currentOption", beanListOptions );
				
				Return = beanListOptions.getId();
			  }
		  }
		catch (Exception m) 
		  {
			m.printStackTrace();
		  }
		finally	
		  {
			if ( session != null )
				session.close();

			if ( sessionFactory != null )
				sessionFactory.close();
		  }
		return Return;
	  }
	

	
	public static int putMenuInSession( HttpServletRequest request, int id_option, int id_menu ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		httpSession.removeAttribute( "listEdit_currentMenu" );			
		httpSession.removeAttribute( "listOptionMenuList" );


		try 
		  {
			//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
			
			if (id_option==0)
			  {
				String query =" FROM BeanListOptionMenu u";
		
				List list = session.createQuery( query ).list();
				
				list.clear();
			
				httpSession.setAttribute("listOptionMenuList", list );

				return 0;
			  }

			String query =" FROM BeanListOptionMenu u" +
						  " WHERE u.id_list_option = " + id_option +  
						  " ORDER BY u.name";
				
			List list = session.createQuery( query ).list();
		  			
			httpSession.setAttribute("listOptionMenuList", list );

			if ( list.size() != 0 )
			  {
				BeanListOptionMenu beanListOptionMenu = null;
				
				if ( id_menu == 0)
					beanListOptionMenu = (BeanListOptionMenu) list.get(0);
				else
					beanListOptionMenu = (BeanListOptionMenu) session.load( BeanListOptionMenu.class, new Integer(id_menu) );
				
				httpSession.setAttribute("listEdit_currentMenu", beanListOptionMenu );
				
				Return = beanListOptionMenu.getId();
			  }
		  }
		catch (Exception m) 
		  {
			m.printStackTrace();
		  }
		finally	
		  {
			if ( session != null )
				session.close();

			if ( sessionFactory != null )
				sessionFactory.close();
		  }
		return Return;
	  }
	
	

	public static int putFlowerInSession( HttpServletRequest request, int id_flower) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		httpSession.removeAttribute( "listFlowerMusicEdit_currentFlower" );							    

		httpSession.removeAttribute( "listFlower" );
		
		try 
		  {
			//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

			String query = " FROM BeanFlower u" +
						   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
						   " AND u.active = 'Y' " +  
						   " ORDER BY u.description";
				
			List list = session.createQuery( query ).list();
		  			
			httpSession.setAttribute("listFlower", list );

			if ( list.size() != 0 )
			  {
				BeanFlower beanFlower = null;
				
				if ( id_flower == 0)
				  beanFlower = (BeanFlower) list.get(0);
				else
				  beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(id_flower) );
				
				httpSession.setAttribute("listFlowerMusicEdit_currentFlower", beanFlower );
				
				Return = beanFlower.getId();
			  }
		  }
		catch (Exception m) 
		  {
			m.printStackTrace();
		  }
		finally	
		  {
			if ( session != null )
				session.close();

			if ( sessionFactory != null )
				sessionFactory.close();
		  }
		return Return;
	  }
	
	
	public static int putMusicInSession( HttpServletRequest request, int id_music ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		httpSession.removeAttribute( "listFlowerMusicEdit_currentMusic" );							    

		httpSession.removeAttribute( "listMusic" );
		
		try 
		  {
			//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

			String query = " FROM BeanMusic u" +
						   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
						   " AND u.active = 'Y' " +  
						   " ORDER BY u.description";
				
			List list = session.createQuery( query ).list();
		  			
			httpSession.setAttribute("listMusic", list );

			if ( list.size() != 0 )
			  {
				BeanMusic beanMusic = null;
				
				if ( id_music == 0)
					beanMusic = (BeanMusic) list.get(0);
				else
					beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(id_music) );
				
				httpSession.setAttribute("listFlowerMusicEdit_currentMusic", beanMusic );
				
				Return = beanMusic.getId();
			  }
		  }
		catch (Exception m) 
		  {
			m.printStackTrace();
		  }
		finally	
		  {
			if ( session != null )
				session.close();

			if ( sessionFactory != null )
				sessionFactory.close();
		  }
		return Return;
	  }
	
	
	public static int getCurrentFlower( HttpServletRequest request ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		BeanFlower beanFlower = (BeanFlower) httpSession.getAttribute( "listFlowerMusicEdit_currentFlower" );
		
		if ( beanFlower == null )
		  return 0;
		else
		  return beanFlower.getId();
	  }
	
	public static int getCurrentMusic( HttpServletRequest request ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		BeanMusic beanMusic = (BeanMusic) httpSession.getAttribute( "listFlowerMusicEdit_currentMusic" );
		
		if ( beanMusic == null )
		  return 0;
		else
		  return beanMusic.getId();
	  }

	
	public static int putSaloonInSession( HttpServletRequest request, int id_saloon ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		httpSession.removeAttribute( "listSaloonEdit_currentSaloon" );							    

		httpSession.removeAttribute( "listSaloon" );
		
		try 
		  {
			//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

			String query = " FROM BeanSaloon u" +
						   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
						   " AND u.active = 'Y' " +  
						   " ORDER BY u.description";
				
			List list = session.createQuery( query ).list();
		  			
			httpSession.setAttribute("listSaloon", list );

			if ( list.size() != 0 )
			  {
				BeanSaloon beanSaloon = null;
				
				if ( id_saloon == 0)
					beanSaloon = (BeanSaloon) list.get(0);
				else
					beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(id_saloon) );
				
				httpSession.setAttribute("listSaloonEdit_currentSaloon", beanSaloon );
				
				Return = beanSaloon.getId();
			  }
		  }
		catch (Exception m) 
		  {
			m.printStackTrace();
		  }
		finally	
		  {
			if ( session != null )
				session.close();

			if ( sessionFactory != null )
				sessionFactory.close();
		  }
		return Return;
	  }
	
	
	
	
	public static int putProductInSession( HttpServletRequest request, int id_saloon ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		httpSession.removeAttribute( "listProductEdit_currentProduct" );							    

		httpSession.removeAttribute( "listProductEdit_productList" );
		
		try 
		  {
			//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

			String query = " FROM BeanProduct u" +
						   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
						   " AND u.active = 'Y' " +  
						   " ORDER BY u.description";
				
			List list = session.createQuery( query ).list();
		  			
			httpSession.setAttribute("listProductEdit_productList", list );

			if ( list.size() != 0 )
			  {
				BeanProduct beanProduct = null;
				
				if ( id_saloon == 0)
					beanProduct = (BeanProduct) list.get(0);
				else
					beanProduct = (BeanProduct) session.load( BeanProduct.class, new Integer(id_saloon) );
				
				httpSession.setAttribute("listProductEdit_currentProduct", beanProduct );
				
				Return = beanProduct.getId();
			  }
		  }
		catch (Exception m) 
		  {
			m.printStackTrace();
		  }
		finally	
		  {
			if ( session != null )
				session.close();

			if ( sessionFactory != null )
				sessionFactory.close();
		  }
		return Return;
	  }
	
	
	public static int getCurrentProduct( HttpServletRequest request ) throws Exception 
	  {
		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int Return = 0;

		BeanMusic beanMusic = (BeanMusic) httpSession.getAttribute( "listProductEdit_currentProduct" );
		
		if ( beanMusic == null )
		  return 0;
		else
		  return beanMusic.getId();
	  }

	
	
}



