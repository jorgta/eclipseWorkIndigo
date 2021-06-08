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
import com.struts2.Beans.BeanCompany;
import com.struts2.Beans.BeanCounter;
import com.struts2.Beans.BeanCounterLog;
import com.struts2.Beans.BeanCounterLogDetail;
import com.struts2.Beans.BeanDevice;
import com.struts2.Beans.BeanCommand;
import com.struts2.Beans.BeanType_Counter_Detail;
import com.bituos.bituosMonitor.Beans.Counter;
import com.bituos.bituosMonitor.Beans.Device;
import com.bituos.bituosMonitor.Beans.CountersData;

import com.bituos.AESCrypt;;


public class CounterServer {
	
	/*
	public CounterServer()
	  {
	  }
	*/
	
	public String getName()
	  {
		return "counterServer";
	  }


	public void pingToEmail( String email )
	  {
		
	  }
   
	
	
	private int canAccess(String company, String location, String password) throws Exception
	  {
		/*
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession(); 
		*/

		Config config = new Config(  );
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int result = -1;
		
		if ( company == null )
			  company = "";

		if ( location == null )
			location = "";

		if ( password == null )
			password = "";
		
		company = company.trim().toUpperCase();
		location = location.trim().toUpperCase();
		
		try
		  {
			sessionFactory = config.getConfiguration(  ).buildSessionFactory();
			session = sessionFactory.openSession();
			
			String query =  " FROM BeanCompany u" +
							" WHERE u.name='" + company + "'" + 
							" AND u.location='" + location + "'" + 
							" AND u.password='" + password + "'"; 

			List list = session.createQuery( query ).list();
		
			if ( list.size() > 0 )
			  {
				result = ((BeanCompany) list.get(0)).getId();
			  }
			
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return result;
	  }

	private BeanDevice identifyDevice( Device device, int id_company ) throws Exception
	  {
		/*
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession(); 
		*/
		
		Config config = new Config(  );
		SessionFactory sessionFactory = null;
		Session    session = null;
		
		try
		  {
			sessionFactory = config.getConfiguration(  ).buildSessionFactory();
			session = sessionFactory.openSession();
			
			String query;
			List list;
			
			//buscar por mother board id
			if ( !device.getMbID().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company = " + id_company + 
					   " AND u.mbid = '" + device.getMbID() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			
			//buscar por cpu id
			if ( !device.getCpuID().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company=" + id_company + 
					   " AND u.cpuid = '" + device.getCpuID() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			/*
			//buscar por hard disk
			if ( !device.getMediaID1().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company=" + id_company + 
					   " AND u.mediaID1 = '" + device.getMediaID1() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			
			//buscar por hard disk
			if ( !device.getMediaID2().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company=" + id_company + 
					   " AND u.mediaID2 = '" + device.getMediaID1() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			
			//buscar por hard disk
			if ( !device.getMediaID3().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company=" + id_company + 
					   " AND u.mediaID3 = '" + device.getMediaID1() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			
			
			//buscar por hard disk
			if ( !device.getMediaID4().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company=" + id_company + 
					   " AND u.mediaID4 = '" + device.getMediaID1() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			
			//buscar por hard disk
			if ( !device.getMediaID5().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company=" + id_company + 
					   " AND u.mediaID5 = '" + device.getMediaID1() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			
			//buscar por hard disk
			if ( !device.getMediaID6().equals("") )
			  {
				query =" FROM BeanDevice u" +
					   " WHERE u.id_company=" + id_company + 
					   " AND u.mediaID6 = '" + device.getMediaID1() + "'"; 
		
				list = session.createQuery( query ).list();
				
				if ( list.size() > 0 )
				  {
					if ( list.size() > 1 )
					  //error grave
					  return null;
					else
					  return (BeanDevice) list.get(0);
				  }
			  }
			*/
			
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return null;
	  }
	
	
	private BeanCompany identifyCompany(String company) throws Exception
	  {
		/*
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		*/ 

		Config config = new Config(  );
		SessionFactory sessionFactory = null;
		Session    session = null;
		BeanCompany result = null;
		
		if ( company == null )
		  company = "";
		
		company = company.trim().toUpperCase();
		
		try
		  {
			sessionFactory = config.getConfiguration(  ).buildSessionFactory();
			session = sessionFactory.openSession();
			
			String query =  " FROM BeanCompany u" +
							" WHERE u.name='" + company + "'"; 

			List list = session.createQuery( query ).list();
		
			if ( list.size() == 1 )
			  {
				result = (BeanCompany) list.get(0);
			  }
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return result;
	  }
	
	
	
	public int getTimeToReport(String companyEncrypted, String locationEncrypted, String passwordEncrypted, Device device) throws Exception
	  {
		String company  = AESCrypt.decrypt( companyEncrypted );
		String location = AESCrypt.decrypt( locationEncrypted );
		String password = AESCrypt.decrypt( passwordEncrypted );

		Config config = new Config( );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int id_company = canAccess( company, location, password );
		int result = -1;

		try
		  {
			sessionFactory = config.getConfiguration( ).buildSessionFactory();
			session = sessionFactory.openSession();
			
			if ( id_company > 0 )
			  {
				BeanCompany beanCompany = (BeanCompany) session.load( BeanCompany.class, new Integer( id_company )); 
				
				result = beanCompany.getTimeToReport(); 
			  }		
			
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return result;
	  }
	
	
	public Counter[] getCounters(String companyEncrypted, String locationEncrypted, String passwordEncrypted, Device device ) throws Exception
	  {
		String company  = AESCrypt.decrypt( companyEncrypted );
		String location = AESCrypt.decrypt( locationEncrypted );
		String password = AESCrypt.decrypt( passwordEncrypted );

		Config config = new Config(  );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		Counter[] result = null;

		try
		  {
			sessionFactory = config.getConfiguration(  ).buildSessionFactory();
			session = sessionFactory.openSession();
			int id_company = canAccess( company, location, password );
			
			if ( id_company > 0 )
			  {
				BeanCompany beanCompany = (BeanCompany) session.load( BeanCompany.class, new Integer( id_company )); 
				
				BeanDevice beanDevice = identifyDevice( device, id_company );
				
				//TODO: definir error
				if ( beanDevice != null )
				  {
				    if ( beanDevice.getActive() == 0 )
					  return null;
				  }
				else
				  {
					beanDevice = new BeanDevice();

					beanDevice.setActive( 0 );
					beanDevice.setId_company( beanCompany );
					beanDevice.setMbid( device.getMbID() );
					beanDevice.setCpuid( device.getCpuID() );
					beanDevice.setHostname(device.getHostname());
					
					/*
					beanDevice.setMediaID1( device.getMediaID1() );
					beanDevice.setMediaID2( device.getMediaID2() );
					beanDevice.setMediaID3( device.getMediaID3() );
					beanDevice.setMediaID4( device.getMediaID4() );
					beanDevice.setMediaID5( device.getMediaID5() );
					beanDevice.setMediaID6( device.getMediaID6() );
					beanDevice.setMacID1( device.getMacID1() );
					beanDevice.setMacID2( device.getMacID2() );
					beanDevice.setMbDescription( device.getMbDescription() );
					beanDevice.setCpuDescription( device.getCpuDescription() );
					beanDevice.setMediaDescription1( device.getMediaDescription1() );
					beanDevice.setMediaDescription2( device.getMediaDescription2() );
					beanDevice.setMediaDescription3( device.getMediaDescription3() );
					beanDevice.setActive( "N" );
					beanDevice.setLocal_ip_address(device.getLocal_ip_address());
					*/
					
					tx = session.beginTransaction();
					session.save( beanDevice );
					tx.commit();
					
					return null;
				  }
				
				String query = " FROM BeanCounter u" +
						       " WHERE u.id_device.id = " + beanDevice.getId(); 
			
				List list = session.createQuery( query ).list();
				int index = 0;
				
				if(!list.isEmpty())
				{
				  result = new Counter[ list.size() ];
			
				  Iterator iter = list.iterator();
			
				  while ( iter.hasNext() )
				    {	
						BeanCounter beanCounter = (BeanCounter) iter.next();
						Counter counter = new Counter();
						
						counter.setId_counter( beanCounter.getId() );
						counter.setClassName( beanCounter.getId_type_counter_detail().getId_type_counter_master().getName_type_counter_master() );
						counter.setPropertyName( beanCounter.getId_type_counter_detail().getName_type_counter_detail() );
						counter.setTimeToRepot( beanCounter.getTimeToReport() );
						
						result[ index ]  = counter;
						
					  index++;				
				    }
				 }
			  }
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
			
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return result;	
	  }

	
	private ServiceConfiguration getConfiguration(String companyEncrypted, String locationEncrypted, String passwordEncrypted, Device device) throws Exception
	  {
		//Next: desencriptar info
		String company  = AESCrypt.decrypt( companyEncrypted );
		String location = AESCrypt.decrypt( locationEncrypted );
		String password = AESCrypt.decrypt( passwordEncrypted );

		ServiceConfiguration sc = new ServiceConfiguration( );
		
		sc.setError( -1 );
		sc.setErrorStr( "!" );
		
		Config config = new Config( );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int id_company = canAccess( company, location, password );

		try
		  {
			sessionFactory = config.getConfiguration( ).buildSessionFactory();
			session = sessionFactory.openSession();
			
			if ( id_company > 0 )
			  {
				BeanCompany beanCompany = (BeanCompany) session.load( BeanCompany.class, new Integer( id_company )); 
				int index = 0;
				
				BeanDevice beanDevice = identifyDevice(device, id_company);
				
				//NO existe el device
				//Hay que registrarlo como inactivo
				if ( beanDevice == null)
				  {
					beanDevice = new BeanDevice();

					beanDevice.setActive( 0 );
					beanDevice.setId_company( beanCompany );
					beanDevice.setMbid( device.getMbID() );
					beanDevice.setCpuid( device.getCpuID() );
					beanDevice.setHostname(device.getHostname());
					
					/*
					beanDevice.setMediaID1( device.getMediaID1() );
					beanDevice.setMediaID2( device.getMediaID2() );
					beanDevice.setMediaID3( device.getMediaID3() );
					beanDevice.setMediaID4( device.getMediaID4() );
					beanDevice.setMediaID5( device.getMediaID5() );
					beanDevice.setMediaID6( device.getMediaID6() );
					beanDevice.setMacID1( device.getMacID1() );
					beanDevice.setMacID2( device.getMacID2() );
					beanDevice.setMbDescription( device.getMbDescription() );
					beanDevice.setCpuDescription( device.getCpuDescription() );
					beanDevice.setMediaDescription1( device.getMediaDescription1() );
					beanDevice.setMediaDescription2( device.getMediaDescription2() );
					beanDevice.setMediaDescription3( device.getMediaDescription3() );
					beanDevice.setActive( "N" );
					beanDevice.setLocal_ip_address(device.getLocal_ip_address());
					*/
					
					tx = session.beginTransaction();
					session.save( beanDevice );
					tx.commit();
					
					sc.setError( 0 );
				    sc.setErrorStr( "Registrado como nuevo, por lo tanto inactivo" );
				    //return sc;
				  }
				else
				   //Devolver counters a monitorear
				  {
					sc.setTimeToReport( beanCompany.getTimeToReport() );
					
					// counters
					String query = " FROM BeanCounter u" +
								   " WHERE u.id_device.id = " + beanDevice.getId(); 
					
					List list = session.createQuery( query ).list();
					
					sc.redimCounter( list.size() );
					Iterator iter = list.iterator();
				
					while ( iter.hasNext() )
					  {
						// posible error pues el arreglo de los countes debe de estar vacio
						BeanCounter beanCounter = (BeanCounter) iter.next();
						Counter counter = new Counter();
						
						counter.setId_counter( beanCounter.getId() );
						counter.setClassName( beanCounter.getId_type_counter_detail().getId_type_counter_master().getName_type_counter_master() );
						counter.setPropertyName( beanCounter.getId_type_counter_detail().getName_type_counter_detail() );
						counter.setTimeToRepot( beanCounter.getTimeToReport() );
						
						//sc.putCounterAt( index, counter);
						sc.counterList[ index ]  = counter;
						index++;
					  }
				
					// commands
					//Next: enviar solo comandos para este dispositivo
					query =" FROM BeanCommand u" +
						   " WHERE u.id_company = " + beanCompany.getId(); 
					
					list = session.createQuery( query ).list();
					
					sc.redimCommand( list.size() );
					iter = list.iterator();
					index = 0;
					while ( iter.hasNext() )
					  {
						// posible error pues el arreglo de los countes debe de estar vacio
						BeanCommand beanCommand = (BeanCommand) iter.next();
						sc.putCommandAt( index, beanCommand);
						index++;
					  }

					sc.setError( 0 );
					sc.setErrorStr( "none"  );
				  }
			  }
			else
			  {
				sc.setError( -2 );
				sc.setErrorStr( "No se pudo encontrar la empresa o credenciales incorrectas."  );
			  }
			
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
			sc.setError( -99 );
			sc.setErrorStr( m.getMessage() );
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return sc;
	  }
	
	
	
	private BeanType_Counter_Detail identifyCounter( String className, String propertyName )
	  {
		/*
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession(); 
	 	*/
		
		Config config = new Config(  );
		SessionFactory sessionFactory = null;
		Session    session = null;
		BeanType_Counter_Detail result = null;
		
		try
		  {
			sessionFactory = config.getConfiguration(  ).buildSessionFactory();
			session = sessionFactory.openSession();
			
			String query =  " FROM BeanType_Counter_Detail u" +
							" WHERE u.name_type_counter_detail='" + propertyName + "'" + 
							" AND u.id_type_counter_master.name_type_counter_master='" + className + "'"; 

			List list = session.createQuery( query ).list();
			
			if ( list.size() == 1 )
			  {
				result = (BeanType_Counter_Detail) list.get(0);
			  }
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return result;
	  }
		
 	
	public int registerCounter( Device device, String companyEncrypted, String locationEncrypted, String passwordEncrypted, CountersData countersData, int is_str) throws Exception
	  {
		/*
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession(); 
		*/

		Config config = new Config(  );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		int result = 99;
		
		try
		  {
			sessionFactory = config.getConfiguration(  ).buildSessionFactory();
			session = sessionFactory.openSession();

			//Next: desencriptar info
			String company  = AESCrypt.decrypt( companyEncrypted );
			String location = AESCrypt.decrypt( locationEncrypted );
			String password = AESCrypt.decrypt( passwordEncrypted );
	
			int id_company = canAccess( company, location, password );
	
			if ( id_company < 0 )
			  return -1;
				
			BeanDevice beanDevice = identifyDevice( device, id_company );
			
			//Existe el device
			if ( beanDevice != null )
			  {
			    if ( beanDevice.getActive() != 0 )
			      //Hay q registrar datos
			      {
					BeanCounterLog beanCounterLog = new BeanCounterLog();
					BeanType_Counter_Detail beanTypeCounterDetail = identifyCounter( countersData.getClassName(), countersData.getPropertyName() );
					BeanCounter beanCounter = (BeanCounter) session.load( BeanCounter.class, new Integer( countersData.getId_counter() )); 
					BeanCounterLogDetail beanCounterLogDetail = null;
					String query;

					if (beanTypeCounterDetail == null )
						  return -2;
						
					if (beanCounter == null )
						  return -3;
						
					beanCounterLog.setId_counter( beanCounter );
					beanCounterLog.setIs_str( is_str );
					beanCounterLog.setDate_reg( Utils.Now() );
					
					tx = session.beginTransaction();
					
					session.save( beanCounterLog );

					for(int i=0; i<countersData.getDataCount(); i++)
					  {
						beanCounterLogDetail = new BeanCounterLogDetail();
						
						beanCounterLogDetail.setId_counter_log( beanCounterLog );
						if ( is_str != 0 )
						  beanCounterLogDetail.setValue_str( countersData.getData()[i] );
						else
						  beanCounterLogDetail.setValue_int( new Integer(countersData.getData()[i]).intValue() );
						
						session.save( beanCounterLogDetail );
					  }
					
					tx.commit();
					result = 0; 
			      }
			    else
			      //Inactivo, no se hace nada	
				  result = -97;
			  }
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
			
			return -99;
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return result;
	  }
	

}
