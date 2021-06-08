package com.bituos.truckAdmin.Beans;



import com.bituos.truckAdmin.Beans.*;




import java.util.*;

import net.sf.hibernate.Session;


public class BuildTruckHtml {
	private List<BeanTruckTireConfigurationDetail> beanTruckTireConfigurationDetailList; 
	private int axisCount;
	private int maxCols;
	int[] tireByAxis;
	String[] typeAxis;
	List<BeanTireTruck> tireExtras;
	Session    session = null;
	
	public BuildTruckHtml(int axisCount,
			       		  int maxCols) throws Exception {
	{
		
		this.axisCount =axisCount;
		this.maxCols =maxCols;
		
		
	} }
	
	public String getHTMLV1()
	{
		
		String html = "";		
		String axisFront="<td onclick='test(this);' style='margin:0px' ><img src='./truckimg/ejedelantero.png' width='75' height='60'/></td>";		
		String axisBottom = "<td><img src='./truckimg/ejetrasero.png' width='75' height='63'/></td>";
		String tLeft ="<td width='22' style='background-image:url(./truckimg/tfl.png);background-repeat:no-repeat;background-size:22px 60px;'></td>";
		String tRigth ="<td width='22' style='background-image:url(./truckimg/tfr.png);background-repeat:no-repeat;background-size:22px 60px;'></td>";

		String hmtlChasisBody="";
		
						 
			
			
			
	    String trOpen="<tr>";				
		String tdOpen="<td width='22'>";
		String tdClose="</td>";					
		String trClose="</tr>";
		
		  
	    String htmlTableWidth =String.valueOf((maxCols * 22) + 75) ;
	    String htmlTableStart="<table align='center' style='margin-top: 10px;' width='"+ htmlTableWidth +"' border='0' cellspacing='0'  cellpadding='0'>";
	    String htmlTableEnd="</table>";
	  
	    hmtlChasisBody= "<td><img src='./truckimg/chasis.png' width='75' height='186' /></td>";
	  
	    String htmlChasisBottom  = "<td><img src='./truckimg/chasisback.png' width='75' height='41'  alt=''/></td>";
	  
	    String tdTemp="";
	    String trTemp="";
	    String tdMidleChasisFront =  "<td width='75'><img  src='./truckimg/chasisfront.png' width='75' height='37'  alt=''/></td>";
	
	
	    
	    String htmlConfiguration=""; 

	    
		String axisHTMLFront="";
		String axisHTMLBottom="";
		String axisTdLeft="";
        String axisTdRigth="";
	    
	   
		
		String truckFront="";
		String truckBottom="";
		String truckBody="";
		
		String[] axis = new String[axisCount];
		
		
		BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail= null;
		Iterator<BeanTruckTireConfigurationDetail> iter = beanTruckTireConfigurationDetailList.iterator();	
	
		int tdEmptyLeftAndRigth=0;
		
		tdTemp = "";
		int tireCount = 0;
		int tireIndexLeft= 0;
		int tireIndexList= 0;
		int tireIndexRigth= 0;
		int tireTotalCount = 1;
		
		
		truckFront =  trOpen; 
		    
		for(int j=1; j <= (maxCols + 1); j++)
		  {
			 if( ((maxCols / 2) + 1)== j)
			 {
			   tdTemp = tdTemp + tdMidleChasisFront; 
			  
			   
			 }
			 else
			   tdTemp = tdTemp + tdOpen + tdClose;
		  }
		truckFront = truckFront + tdTemp + trClose;   
				
		tdTemp = "" ;
		tireCount = 0;
		int id = 0;
		for(int j=0; j < axisCount; j++ )
		{
			
			
			tireIndexLeft = (tireByAxis[j])/ 2;
			//tireIndexRigth = (tireByAxis[j])/ 2;
			tireIndexRigth= 0;
			
			/*while(iter.hasNext())
	    	{*/		
				//beanTruckTireConfigurationDetail= (BeanTruckTireConfigurationDetail)iter.next();
				
				tdEmptyLeftAndRigth =  ((maxCols + 1) - (tireByAxis[j] + 1))/ 2;
				
				tireCount = 0;
				for(int i=1; i <= (maxCols + 1); i++ )
				{
					if (tireCount < tireByAxis[j])		
					{
					  beanTruckTireConfigurationDetail= beanTruckTireConfigurationDetailList.get(tireIndexList);
					  id =beanTruckTireConfigurationDetail.getId();	
					}
					
					if(i  == 1)
					   trTemp =  trOpen; 
					   
					
					 if( ((maxCols / 2) + 1)== i )
					 {
						 if(typeAxis[j].equals("Y"))
						  tdTemp = tdTemp +  axisFront  ; 
						 else
						  tdTemp = tdTemp +  axisBottom  ; 
					 
					  					   
					 }
					
					
	
					if(beanTruckTireConfigurationDetail.getIs_front_axis().equals("Y"))
					{
							
						if(beanTruckTireConfigurationDetail.getAxis_side().equals("L")) 
					    {
							if( i <  ((maxCols / 2) + 1) )
							{
							  if((i) <= tdEmptyLeftAndRigth)
								axisTdLeft= axisTdLeft + tdOpen + tdClose;
							
							
							  if((i) > tdEmptyLeftAndRigth)
							  {
						        
								String value ="";
								//String tag = String.valueOf(j + 1) + String.valueOf(tireIndexLeft);
								String tag = String.valueOf(tireTotalCount++);
								
								
								
								String param1 ="selectPosition";
								String param2 ="";
								
								String onclick="";		  
						        axisTdLeft= axisTdLeft + "<td id='tire" + value +"' width='22' "+ onclick +" style='background-image:url(./truckimg/tfl.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
						                                 "<center><font size='3' color='white'>" +
						                                 tag +  					                                   
						                                 "</font></center>"+
						        						 "</td>"; 
						        tireCount++;
						        tireIndexList++;
						        tireIndexLeft--;
							  }
							}
						  
					    }
				
						
					   if(beanTruckTireConfigurationDetail.getAxis_side().equals("R")) 
					    {
						   if( i >  ((maxCols / 2) + 1) )
						   {
							  
							   
							   if(i <=  ( ((maxCols) + 1) - tdEmptyLeftAndRigth ))	
							   {
							
								   
								 tireIndexRigth++;  
								
								 String value ="";
								 //String tag = String.valueOf(j + 1) + String.valueOf(tireIndexRigth);
								 String tag = String.valueOf(tireTotalCount++);
								 String param1 ="selectPosition";
								 String param2 ="";
								
								 String onclick="";
							    		  
								 axisTdRigth= axisTdRigth + "<td id='tire" + value +"' width='22' "+ onclick +" style='background-image:url(./truckimg/tfr.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
						                                    "<center><font size='3' color='white'>" +
						                                    tag +  					                                   
						                                   "</font></center>"+
						        						   "</td>"; 
								 tireCount++;
								 tireIndexList++;
								
							   }
	
							   if(i >  ( ((maxCols ) + 1) - tdEmptyLeftAndRigth ))
								  axisTdRigth= axisTdRigth + tdOpen + tdClose;
						   }
							
					    }	
						
						 
	
						 
					}else if(beanTruckTireConfigurationDetail.getIs_front_axis().equals("N"))
					{
						if(beanTruckTireConfigurationDetail.getAxis_side().equals("L")) 
					    {
							if( i <  ((maxCols / 2) + 1) )
							{
								if((i) <= tdEmptyLeftAndRigth)
									axisTdLeft= axisTdLeft + tdOpen + tdClose;
								
								
								if((i) > tdEmptyLeftAndRigth)
								{
							        
									 String value ="";
									 //String tag = String.valueOf(j + 1) + String.valueOf(tireIndexLeft);
									 String tag = String.valueOf(tireTotalCount++);
									 String param1 ="selectPosition";
									 String param2 ="";
									 
									 String onclick="";		  
							        axisTdLeft= axisTdLeft + "<td id='tire" + value +"' width='22' "+ onclick +" style='background-image:url(./truckimg/tfl.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
							                                 "<center><font size='3' color='white'>" +
							                                 tag + 				                                   
							                                 "</font></center>"+
							        						 "</td>"; 
							        tireCount++;
							        tireIndexList++;
							        tireIndexLeft--;
								}
							}
						  
					    }
				
						
					   if(beanTruckTireConfigurationDetail.getAxis_side().equals("R")) 
					    {
						   
						   if( i >  ((maxCols / 2) + 1) )
						   {
							   
							   
							   if(i <=  ( ((maxCols) + 1) - tdEmptyLeftAndRigth ))	
							   {
								 
								   
								 tireIndexRigth++;   
								
								 String value ="";
								// String tag = String.valueOf(j + 1) + String.valueOf(tireIndexRigth);
								 String tag = String.valueOf(tireTotalCount++);
								 String param1 ="selectPosition";
								 String param2 ="";
								
								 String onclick="";
								 
								 axisTdRigth= axisTdRigth + "<td id='tire" + value +"' width='22' "+ onclick +" style='background-image:url(./truckimg/tfr.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
						                                    "<center><font size='3' color='white'>" +
						                                    tag + 					                                   
						                                   "</font></center>"+
						        						   "</td>"; 
								 tireCount++;
								 tireIndexList++;
								 
							
								 
							   }
	
							   if(i >  ( ((maxCols ) + 1) - tdEmptyLeftAndRigth ))
								  axisTdRigth= axisTdRigth + tdOpen + tdClose;
						   }
						   	
							
					    }	
						
						
						
					}
						
						
					
	 	
					
				}
				
		
	          	 htmlConfiguration = trTemp +  axisTdLeft + tdTemp + axisTdRigth + trClose;
	          	 axis[j] = htmlConfiguration;
	          	 trTemp = "";
	          	 axisTdLeft = "";
	          	 tdTemp ="";
	          	 axisTdRigth="";
		          

		}
			
		

	    
	    tdTemp  = "";	    
	    truckBody =trOpen;
	    for(int j=1; j <= (maxCols + 1); j++)
		  {
			 if( ((maxCols / 2) + 1)== j)
			 {
			   tdTemp = tdTemp + hmtlChasisBody; 
			   
			 }
			 else
			   tdTemp = tdTemp + tdOpen + tdClose;
		  }
	    truckBody = truckBody + tdTemp + trClose;
      
	    
	    tdTemp  = "";
	    truckBottom =trOpen;
        for(int j=1; j <= (maxCols + 1); j++)
		  {
			 if( ((maxCols / 2) + 1)== j)
			 {
			   tdTemp = tdTemp + htmlChasisBottom; 
			   
			 }
			 else
			   tdTemp = tdTemp + tdOpen + tdClose;
		  }
        truckBottom = truckBottom + tdTemp + trClose;

        
        
        	 
        for(int x= 0; x< axisCount; x++)
        {
        	if(typeAxis[x].equals("Y"))
                axisHTMLFront = axisHTMLFront + axis[x];
        	
        	if(typeAxis[x].equals("N"))
        		axisHTMLBottom = axisHTMLBottom + axis[x];
         
        }
        
        
        if(typeAxis[0].equals("Y"))
            html = html + truckFront + axisHTMLFront + truckBody + axisHTMLBottom + truckBottom;
          
        if(typeAxis[0].equals("N"))
        	html = html +  truckBody + axisHTMLBottom + truckBottom;
        
        
        

        
       
        
        
        
        
	   String divOpen  = "";	   
	   	    
	   String divClose = "";
	   
	   
	   
	    
	  return  divOpen +  htmlTableStart + html +  htmlTableEnd +  divClose;


		
	}
	
	
	public String getHTMLV2( ) {
		

		
		String html = "";		
		String axisFront="<td onclick='test(this);' style='margin:0px' ><img src='./truckimg/ejedelantero.png' width='75' height='60'/></td>";		
		String axisBottom = "<td><img src='./truckimg/ejetrasero.png' width='75' height='63'/></td>";
		String tLeft ="<td width='22' style='background-image:url(./truckimg/tfl.png);background-repeat:no-repeat;background-size:22px 60px;'></td>";
		String tRigth ="<td width='22' style='background-image:url(./truckimg/tfr.png);background-repeat:no-repeat;background-size:22px 60px;'></td>";

		String hmtlChasisBody="";
		
						 
			
			
			
	    String trOpen="<tr>";				
		String tdOpen="<td width='22'>";
		String tdClose="</td>";					
		String trClose="</tr>";
		
		
		  
	    String htmlTableWidth =String.valueOf((maxCols * 22) + 75) ;
	    String htmlTableStart="<table width='"+ htmlTableWidth +"' style='background-color:#E0E6F8; border-collapse:separate; border: solid #ccc 2px; border-radius: 25px;'   cellspacing='0'  cellpadding='0'>";
	    String htmlTableEnd="</table>";
	  
	    hmtlChasisBody= "<td><img src='./truckimg/chasis.png' width='75' height='186' /></td>";
	  
	    String htmlChasisBottom  = "<td><img src='./truckimg/chasisback.png' width='75' height='41'  alt=''/></td>";
	  
	    String tdTemp="";
	    String trTemp="";
	    String tdMidleChasisFront =  "<td width='75'><img  src='./truckimg/chasisfront.png' width='75' height='37'  alt=''/></td>";
	
	
	    
	    String htmlConfiguration=""; 

	    
		String axisHTMLFront="";
		String axisHTMLBottom="";
		String axisTdLeft="";
        String axisTdRigth="";
	    
	   
		
		String truckFront="";
		String truckBottom="";
		String truckBody="";
		
		String[] axis = new String[axisCount];
		
		
		BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail= null;
		Iterator<BeanTruckTireConfigurationDetail> iter = beanTruckTireConfigurationDetailList.iterator();	
	
		int tdEmptyLeftAndRigth=0;
		
		tdTemp = "";
		int tireCount = 0;
		int tireTotalCount = 1;
		int tireIndexLeft= 0;
		int tireIndexList= 0;
		int tireIndexRigth= 0;
		
		
		truckFront =  trOpen; 
		    
		for(int j=1; j <= (maxCols + 1); j++)
		  {
			 if( ((maxCols / 2) + 1)== j)
			 {
			   tdTemp = tdTemp + tdMidleChasisFront; 
			  
			   
			 }
			 else
			   tdTemp = tdTemp + tdOpen + tdClose;
		  }
		truckFront = truckFront + tdTemp + trClose;   
				
		tdTemp = "" ;
		tireCount = 0;
		BeanTireTruck beanTireTruck =new BeanTireTruck();
		String id_tire = "";
		int id = 0;
		for(int j=0; j < axisCount; j++ )
		{
			
			
			tireIndexLeft = (tireByAxis[j])/ 2;
			//tireIndexRigth = (tireByAxis[j])/ 2;
			tireIndexRigth= 0;
			
			/*while(iter.hasNext())
	    	{*/		
				//beanTruckTireConfigurationDetail= (BeanTruckTireConfigurationDetail)iter.next();
				
				tdEmptyLeftAndRigth =  ((maxCols + 1) - (tireByAxis[j] + 1))/ 2;
				
				tireCount = 0;
				for(int i=1; i <= (maxCols + 1); i++ )
				{
					if (tireCount < tireByAxis[j])		
					{
					  beanTruckTireConfigurationDetail= beanTruckTireConfigurationDetailList.get(tireIndexList);
					  id =beanTruckTireConfigurationDetail.getId();	
					 
					  
					  //BeanTypePosition beanTypePosition = (BeanTypePosition) .load( BeanTypePosition.class, new Integer( 2 ) );//En el Vehículo  extra
						
					  try {
						 /* BeanTireTruck beanTireTruck = (BeanTireTruck) getSession().load( BeanTireTruck.class, new Integer( 2 ) );//En el Vehículo  extra
						  */
						    
						  if(session!=null)
						  { 
						    String 	query = " FROM BeanTireTruck t" +
					                        " WHERE t.position.id =" + id +					                        
					                        " AND t.active ='Y'"+
					                        " ORDER BY t.id DESC LIMIT 1";
						
							List list = session.createQuery(query).list();
							id_tire = "";
							if( !list.isEmpty())
							{
							    beanTireTruck = (BeanTireTruck)list.get(0);
							    id_tire = String.valueOf( beanTireTruck.getId_tire().getId());
								
							}
							
						  }
						
					  } catch (Exception e) {

			                e.printStackTrace();
			    
						}
					}
					
					if(i  == 1)
					   trTemp =  trOpen; 
					   
					
					 if( ((maxCols / 2) + 1)== i )
					 {
						 if(typeAxis[j].equals("Y"))
						  tdTemp = tdTemp +  axisFront  ; 
						 else
						  tdTemp = tdTemp +  axisBottom  ; 
					 
					  					   
					 }
					
					
	
					if(beanTruckTireConfigurationDetail.getIs_front_axis().equals("Y"))
					{
							
						if(beanTruckTireConfigurationDetail.getAxis_side().equals("L")) 
					    {
							if( i <  ((maxCols / 2) + 1) )
							{
							  if((i) <= tdEmptyLeftAndRigth)
								axisTdLeft= axisTdLeft + tdOpen + tdClose;
							
							
							  if((i) > tdEmptyLeftAndRigth)
							  {
						       
								 
								String value =String.valueOf(id);
								//String tag = String.valueOf(j + 1) + String.valueOf(tireIndexLeft); //cambio echo el 13/03/2015 a peticion de jorge fuentes
								String tag = String.valueOf(tireTotalCount++); //cambio echo el 13/03/2015 a peticion de jorge fuentes
								String param1 ="selectPosition";
								String param2 ="";
								String onclick="onclick=\"javascript:sp__doPostBack('" + param1 + "', '" + value + "','" + param2 + "')\";";
							    		  
						        axisTdLeft= axisTdLeft + "<td id='tire" + value +"' ondrop='drop(event)' ondragover='allowDrop(event)' width='22' "+ onclick +" style='background-image:url(./truckimg/tfl.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
						        						 "<div style='border: 0px solid #a1a1a1; height: 60px;'  draggable='false' ondragstart='drag(event)' data-truck-tire-cong-detail-id='" +value+"'  data-id-tire='" +id_tire+"' id='draggabletire" +value+"' >" +
						        						 "<center><font size='4' color='white'>" +
						                                  tag +				                                   
						                                 "</font></center>"+
						                                 "</div>"+
						        						 "</td>"; 
						        tireCount++;
						        tireIndexList++;
						        tireIndexLeft--;
							  }
							}
						  
					    }
				
						
					   if(beanTruckTireConfigurationDetail.getAxis_side().equals("R")) 
					    {
						   if( i >  ((maxCols / 2) + 1) )
						   {
							  
							   
							   if(i <=  ( ((maxCols) + 1) - tdEmptyLeftAndRigth ))	
							   {
								// axisTdRigth= axisTdRigth + tRigth;
								   
								 tireIndexRigth++;  
								 String value =String.valueOf(id);
								 //String tag = String.valueOf(j + 1) + String.valueOf(tireIndexRigth);
								 String tag = String.valueOf(tireTotalCount++);
								 String param1 ="selectPosition";
								 String param2 ="";
								 String onclick="onclick=\"javascript:sp__doPostBack('" + param1 + "', '" + value + "','" + param2 + "')\";";
							    		  
								 axisTdRigth= axisTdRigth + "<td id='tire" + value +"' ondrop='drop(event)' ondragover='allowDrop(event)' width='22' "+ onclick +" style='background-image:url(./truckimg/tfr.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
								 							"<div style='border: 0px solid #a1a1a1; height: 60px;'    draggable='false' ondragstart='drag(event)' data-truck-tire-cong-detail-id='" +value+"' data-id-tire='" +id_tire+"'  id='draggabletire" +value+"' >" +
								 							"<center><font size='4' color='white'>" +
						                                    tag +  					                                   
						                                    "</font></center>"+
						                                    "</div>"+
						        						    "</td>"; 
								 tireCount++;
								 tireIndexList++;
								
							   }
	
							   if(i >  ( ((maxCols ) + 1) - tdEmptyLeftAndRigth ))
								  axisTdRigth= axisTdRigth + tdOpen + tdClose;
						   }
							
					    }	
						
						 
	
						 
					}else if(beanTruckTireConfigurationDetail.getIs_front_axis().equals("N"))
					{
						if(beanTruckTireConfigurationDetail.getAxis_side().equals("L")) 
					    {
							if( i <  ((maxCols / 2) + 1) )
							{
								if((i) <= tdEmptyLeftAndRigth)
									axisTdLeft= axisTdLeft + tdOpen + tdClose;
								
								
								if((i) > tdEmptyLeftAndRigth)
								{
							        //axisTdLeft= axisTdLeft + tLeft;
									 String value =String.valueOf(id);
									 //String tag = String.valueOf(j + 1) + String.valueOf(tireIndexLeft);
									 String tag = String.valueOf(tireTotalCount++);
									 String param1 ="selectPosition";
									 String param2 ="";
									 String onclick="onclick=\"javascript:sp__doPostBack('" + param1 + "', '" + value + "','" + param2 + "')\";";
								    		  
							        axisTdLeft= axisTdLeft + "<td id='tire" + value +"' ondrop='drop(event)' ondragover='allowDrop(event)' width='22' "+ onclick +" style='background-image:url(./truckimg/tfl.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
							        						"<div style='border: 0px solid #a1a1a1; height: 60px;'    draggable='false' ondragstart='drag(event)' data-truck-tire-cong-detail-id='" +value+"' data-id-tire='" +id_tire+"'   id='draggabletire" +value+"' >" +
							        						"<center><font size='4' color='white'>" +
							                                 tag + 				                                   
							                                 "</font></center>"+
							                                 "</div>"+
							        						 "</td>"; 
							        tireCount++;
							        tireIndexList++;
							        tireIndexLeft--;
								}
							}
						  
					    }
				
						
					   if(beanTruckTireConfigurationDetail.getAxis_side().equals("R")) 
					    {
						   
						   if( i >  ((maxCols / 2) + 1) )
						   {
							   
							   
							   if(i <=  ( ((maxCols) + 1) - tdEmptyLeftAndRigth ))	
							   {
								 //axisTdRigth= axisTdRigth + tRigth;
								   
								 tireIndexRigth++;   
								 String value =String.valueOf(id);
								// String tag = String.valueOf(j + 1) + String.valueOf(tireIndexRigth);
								 String tag = String.valueOf(tireTotalCount++);
								 String param1 ="selectPosition";
								 String param2 ="";
								 String onclick="onclick=\"javascript:sp__doPostBack('" + param1 + "', '" + value + "','" + param2 + "')\";";
								// onclick="onclick='selectTire("+value+")';";
								 
								 axisTdRigth= axisTdRigth + "<td id='tire" + value +"' ondrop='drop(event)' ondragover='allowDrop(event)' width='22' "+ onclick +" style='background-image:url(./truckimg/tfr.png);background-repeat:no-repeat;background-size:22px 60px;'>"+
								 						    "<div style='border: 0px solid #a1a1a1; height: 60px;'    draggable='false' ondragstart='drag(event)' data-truck-tire-cong-detail-id='" +value+"' data-id-tire='" +id_tire+"'  id='draggabletire" +value+"' >" +
								 							"<center><font size='4' color='white'>" +
						                                    tag + 					                                   
						                                   "</font></center>"+
						                                   "</div>"+
						        						   "</td>"; 
								 tireCount++;
								 tireIndexList++;
								 
							
								 
							   }
	
							   if(i >  ( ((maxCols ) + 1) - tdEmptyLeftAndRigth ))
								  axisTdRigth= axisTdRigth + tdOpen + tdClose;
						   }
						   	
							
					    }	
						
						
						
					}
						
						
					/*if (tireCount == tireByAxis[j])				 
						break;	*/		
	 	
					
				}
				
		
	          	 htmlConfiguration = trTemp +  axisTdLeft + tdTemp + axisTdRigth + trClose;
	          	 axis[j] = htmlConfiguration;
	          	 trTemp = "";
	          	 axisTdLeft = "";
	          	 tdTemp ="";
	          	 axisTdRigth="";
		          

		}
			
		

	    
	    tdTemp  = "";	    
	    truckBody =trOpen;
	    for(int j=1; j <= (maxCols + 1); j++)
		  {
			 if( ((maxCols / 2) + 1)== j)
			 {
			   tdTemp = tdTemp + hmtlChasisBody; 
			   
			 }
			 else
			   tdTemp = tdTemp + tdOpen + tdClose;
		  }
	    truckBody = truckBody + tdTemp + trClose;
      
	    
	    tdTemp  = "";
	    truckBottom =trOpen;
        for(int j=1; j <= (maxCols + 1); j++)
		  {
			 if( ((maxCols / 2) + 1)== j)
			 {
			   tdTemp = tdTemp + htmlChasisBottom; 
			   
			 }
			 else
			   tdTemp = tdTemp + tdOpen + tdClose;
		  }
        truckBottom = truckBottom + tdTemp + trClose;

        
        
        	 
        for(int x= 0; x< axisCount; x++)
        {
        	if(typeAxis[x].equals("Y"))
                axisHTMLFront = axisHTMLFront + axis[x];
        	
        	if(typeAxis[x].equals("N"))
        		axisHTMLBottom = axisHTMLBottom + axis[x];
         
        }
        
        
         
        
        
       //String tableExtras ="<table border='0'><tr><td>Extras</td></tr><tr><td width='60' height='60' style='background-image:url(./truckimg/extra.png);background-repeat:no-repeat;background-size:60px 60px;'><font size='3' color='white'>uno</font></td></tr><tr><td width='60' height='60' style='background-image:url(./truckimg/extra.png);background-repeat:no-repeat;background-size:60px 60px;'><font size='3' color='white'>dos</font></td></tr></table>";
 	   
        String tableExtras ="";
        String tableExtraRow = " ";
        String tableExtrasOpen = "";
        String tableExtrasClose = "";
        String divOpen2  = ""; 	   
 	    String divClose2 = "";
 	    

       	 
 	  
          tableExtrasOpen = "<td><table height='300' width='70' border='0'><tr><td height='22'>Extras</td></tr>";
          tableExtrasClose ="</table></td>";
          divOpen2  = "<div style='background-color:#096' id='divExtra' ondrop='drop(event)' ondragover='allowDrop(event)'>"; 
          
   	      divClose2 = "</div>";
   	      

   	     
   	      
   	     String tableExtrasOpenTr = "<tr>";       
         String tableExtrasCloseTd = "</td>";        
         String tableExtrasCloseTr ="</tr>";
       
         
         // boolean empty = tireExtras.isEmpty();
         if(tireExtras != null && !tireExtras.isEmpty())
         {
           Iterator extraIter = tireExtras.iterator();
           String id_tireExtra = "";
		   while ( extraIter.hasNext() )
		   {   
			 BeanTireTruck beanTireTruckTemp = (BeanTireTruck)extraIter.next();
			 id_tireExtra =String.valueOf( beanTireTruckTemp.getId_tire().getId()) ;
			 String value =String.valueOf(beanTireTruckTemp.getPosition().getId());
			 String param1 ="selectExtraTire";
			 String param2 ="";
			 String onclick="onclick=\"javascript:sp__doPostBack('" + param1 + "', '" + value + "','" + param2 + "')\";";
			 
			 tableExtraRow =  "<div style='border: 0px solid #e5e500; height: 60px; background-image:url(./truckimg/extra.png); background-repeat:no-repeat; background-size:60px 60px;' draggable='true' ondragstart='drag(event)' data-truck-tire-cong-detail-id='" +value+"' data-id-tire='" +id_tireExtra+"'  id='extraTire" +value+"' >" +	
				                    "<center><font size='4' color='white'>" +
				                     "" + 					                                   
	                               "</font></center>"+
			                    divClose2;
			  
		   }
		  
		  
		  
		 // tableExtras = tableExtrasOpen +  "<tr><td>" + divOpen2 +  tableExtraRow + divClose2 + "</td></tr>" + tableExtrasClose;
          }
         tableExtras = tableExtrasOpen +  "<tr><td>" + divOpen2 +  tableExtraRow + divClose2 + "</td></tr>" + tableExtrasClose;
        

         
        
   	   String tableMainStart =  "<table border='0'><tr>";  
   	   String tableMainEnd =  "</tr></table>";
   	   
   	   BeanTruck beanTruck = new BeanTruck();
	   	try {	 
			  if(session!=null)
			  { 
			    String 	query = " FROM BeanTruck t" +
		                        " WHERE t.id_truck_configuration.id =" + beanTruckTireConfigurationDetail.getId_truck_tire_configuration().getId() +					                        
		                        " ORDER BY t.id DESC LIMIT 1";
			
				List list = session.createQuery(query).list();
				id_tire = "";
				if( !list.isEmpty())
				{
					beanTruck = (BeanTruck)list.get(0);
				     
					
				}
				
			  }
			
		  } catch (Exception e) {
	
	           e.printStackTrace();
	
		  }
	   
		  
   	
       String tableMainHeadTROpen =  "<table border='0'><tr>";  
       String tableMainHeadTD=  "<td><div style='background-color:#FE9A2E; border: solid #D64078 3px;  border-radius: 25px;  height:20px;'; id='divAlmacen' ondrop='drop(event)' ondragover='allowDrop(event)'>" + beanTruck.getDescription() + "</div></td>";  
	   String tableMainHeadTRClose =  "</tr></table>";
  	   String Header=tableMainHeadTROpen + tableMainHeadTD + tableMainHeadTRClose;
  	   
	   String tableTrashOpen = "<td><table height='300' width='70' border='0'><tr><td height='22'>Basura</td></tr>";
	   String tableTrashClose ="</table></td>";
	   divOpen2  = "<div style='background-color:#F00' id='divTrash' ondrop='drop(event)' ondragover='allowDrop(event)'>";   	   
	   divClose2 = "</div>";
	   
	   String tableTrash = tableTrashOpen +  "<tr><td>" + divOpen2 + divClose2 + "</td></tr>" + tableTrashClose;
  	   
	   
	   String tableStockOpen = "<td><table height='300' width='70' border='0'><tr><td height='22'>Almacen</td></tr>";
	   String tableStockClose ="</table></td>";
	   divOpen2  = "<div style='background-color:#ffe802' id='divAlmacen' ondrop='drop(event)' ondragover='allowDrop(event)'>";   	   
	   divClose2 = "</div>";
	   
	   String tableStock = tableStockOpen +  "<tr><td>" + divOpen2 + divClose2 + "</td></tr>" + tableStockClose;
  	
	   
	   tableMainStart = tableMainStart + tableStock + tableTrash + tableExtras  ;
        
        if(typeAxis[0].equals("Y"))
            html =   html +  truckFront + axisHTMLFront + truckBody + axisHTMLBottom + truckBottom + "</td>";
          
        if(typeAxis[0].equals("N"))
        	html =   html +   truckBody + axisHTMLBottom + truckBottom + "</td>" ;
      
        
    		

        
	   String divOpen  = "<div id='truckdiv'  class='box1' >";	   
	   String divButton  = "<input type='button' value='Cerrar' onclick='btnClose();'>";	    
	   String divClose = "</div>";
	   
	   
	   
	    
		return  divOpen + divButton + Header + tableMainStart + "<td>" + htmlTableStart + html + htmlTableEnd + "</td>" + tableMainEnd +   divClose;
		//return  divOpen + divButton + htmlTableStart + html + htmlTableEnd +  divClose;
		
	}

	public List<BeanTruckTireConfigurationDetail> getBeanTruckTireConfigurationDetailList() {
		return beanTruckTireConfigurationDetailList;
	}

	public void setBeanTruckTireConfigurationDetailList(
			List<BeanTruckTireConfigurationDetail> beanTruckTireConfigurationDetailList) {
		this.beanTruckTireConfigurationDetailList = beanTruckTireConfigurationDetailList;
	}

	public int[] getTireByAxis() {
		return tireByAxis;
	}

	public void setTireByAxis(int[] tireByAxis) {
		this.tireByAxis = tireByAxis;
	}

	public String[] getTypeAxis() {
		return typeAxis;
	}

	public void setTypeAxis(String[] typeAxis) {
		this.typeAxis = typeAxis;
	}

	public List<BeanTireTruck> getTireExtras() {
		return tireExtras;
	}

	public void setTireExtras(List<BeanTireTruck> tireExtras) {
		this.tireExtras = tireExtras;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

 
	
	
	
  
}
