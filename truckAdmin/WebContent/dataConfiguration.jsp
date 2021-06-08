
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%
    HttpSession ses = request.getSession();
	String bean = request.getParameter("bean");
	String list = request.getParameter("list");
	String field = request.getParameter("field");
	String searchValue  = request.getParameter("value");
	String module = request.getParameter("module");	
	String active = request.getParameter("active");
	String idtruck = request.getParameter("idtruck");
	

	
	net.sf.hibernate.Session sessionHibernate= null;
	com.bituos.Config configuration = new com.bituos.Config( request );
	net.sf.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
	sessionHibernate = sessionFactory.openSession();
	BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
	BeanCompany beanComany = (BeanCompany) ses.getAttribute( "BeanCompany" );
	
	
	
	String query =  "";
	
	String queryFilter = "" ;
	String queryFilterActive1 = "" ;
	String queryFilterActive2 = "" ;

	ses.removeAttribute(list );
	List listObjects = null;
	Iterator iter;
	BeanTruck beanTruck =(BeanTruck) sessionHibernate.load( BeanTruck.class, new Integer(  idtruck ) );
	
	query =   " FROM BeanTruckTireConfigurationDetail u" +
      		  " WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId();


	listObjects = sessionHibernate.createQuery(query).list();
	int maxCols = 0;
	int tireAxis=0;
	int tireFrontAxis=0;
	int tireBottomAxis=0;
	List axisList1 = null;
	List<BeanTruckTireConfigurationDetail> axisList2 = new ArrayList<BeanTruckTireConfigurationDetail>(); 
	int[] tireByAxis = new int[beanTruck.getId_truck_configuration().getAxis_count()];	
	String[] typeAxis = new String[beanTruck.getId_truck_configuration().getAxis_count()];
	
	if(!listObjects.isEmpty())
	{
		ses.removeAttribute("beanTruckTireConfigurationDetailList");
		ses.setAttribute("beanTruckTireConfigurationDetailList", list );
	
	
		BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail= null;
		
		for(int i= 1; i <= beanTruck.getId_truck_configuration().getAxis_count(); i++ ) 
		{
		
		
		   query =   " FROM BeanTruckTireConfigurationDetail u" +
			  		 " WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId() +
			         " AND u.axis_number = " + i +
			         " GROUP BY u.is_front_axis";
		
		   beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)sessionHibernate.createQuery(query).list().get(0);
		
		
		
		   typeAxis[i - 1]= beanTruckTireConfigurationDetail.getIs_front_axis();
		
		
		   query =   " FROM BeanTruckTireConfigurationDetail u" +
			   		 " WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId() +
			         " AND u.axis_number = " + i;// +
			      
		
		   axisList1 = sessionHibernate.createQuery(query).list();
		
		
		   if(!axisList1.isEmpty())	
		   {
		      maxCols =axisList1.size();
		      tireByAxis[i-1]=axisList1.size();
		      iter = axisList1.iterator();						    
		      while(iter.hasNext())
			  {						     
		        beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)iter.next();
		  						      
		        axisList2.add(beanTruckTireConfigurationDetail);
		   
			  } 
		   } 
		   else
			maxCols = 0;
		
		  if(tireAxis > maxCols)
		  { 
			  maxCols = tireAxis; 
		  } 
		
		}
		 
		String htmlTableWidth =String.valueOf((maxCols * 22) + 75) ;
		ses.setAttribute("htmlTableWidth", htmlTableWidth );
		
		BuildTruckHtml buildTruckHtml = new  BuildTruckHtml(beanTruck.getId_truck_configuration().getAxis_count(), maxCols); 
		buildTruckHtml.setBeanTruckTireConfigurationDetailList(axisList2);
		buildTruckHtml.setTireByAxis(tireByAxis);
		buildTruckHtml.setTypeAxis(typeAxis);
		buildTruckHtml.setTireExtras(null);
		
		
		String truckHtml = buildTruckHtml.getHTMLV1();
		out.write(truckHtml);
		/*ses.removeAttribute("truckHtml");
		ses.setAttribute("truckHtmlOnlyView", truckHtml );*/
		
		

	
	}


	%> 
	

			