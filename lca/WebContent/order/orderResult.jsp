<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">-->



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.lca.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>


	
    <TITLE> 
    </TITLE>
    
    <link href="./grid/index_data/style.css" type="text/css" rel="stylesheet">
    <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
<!-- DataGrid CSS definitions - START -->
<link href="./grid/index_data/print.css" type="text/css" rel="stylesheet" media="print">
<link rel="stylesheet" type="text/css" href="./grid/index_data/style_002.css">
<!--[if IE]><link rel="stylesheet" type="text/css" href="modules/datagrid/styles/x-blue/style_IE.css" /><![endif]-->
<!-- DataGrid CSS definitions - END -->


    <script type="text/javascript" src="./grid/index_data/jquery.js"></script>	
    <SCRIPT type="text/javascript" src="/truckAdmin/js/ajaxfile.js"></SCRIPT>
<style>
    .db_menu_wrap {left: 0px;top: 0px;right: 0px;z-index:9999;width:100%;height:50px;background: -moz-linear-gradient(top, #b8e1fc 0%, #a9d2f3 10%, #90bae4 25%, #90bcea 37%, #90bff0 50%, #6ba8e5 51%, #a2daf5 83%, #bdf3fd 100%); /* firefox */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#b8e1fc), color-stop(10%,#a9d2f3), color-stop(25%,#90bae4), color-stop(37%,#90bcea), color-stop(50%,#90bff0), color-stop(51%,#6ba8e5), color-stop(83%,#a2daf5), color-stop(100%,#bdf3fd)); /* webkit */ -webkit-box-shadow: 0px 2px 5px #000000;-moz-box-shadow: 0px 2px 5px #000000;box-shadow: 0px 2px 5px #000000;position: fixed;margin-top:-35px;}
    .db_menu_links {height: 50px; float: left; margin-left:10px; }
    .db_menu_links ul {margin: 0px;padding: 0px;list-style-type: none;}
    .db_menu_links ul li {display: inline;background-image:none;}
    .db_menu_links ul li a {float:left;line-height:50px;padding-right:20px;padding-left:20px;color: #FFF;font-size: 18px;text-decoration: none;text-shadow: -1px -1px 1px #333;display: block;border-right-width: 1px;border-right-style: solid;border-right-color: #FFF;}
    .db_menu_links ul li a:hover {color: #000;text-shadow: -1px -1px 1px #EEE;background: #ffffff; /* old browsers */ background: -moz-linear-gradient(top, #ffffff 0%, #f1f1f1 50%, #e1e1e1 51%, #f6f6f6 100%); /* firefox */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(50%,#f1f1f1), color-stop(51%,#e1e1e1), color-stop(100%,#f6f6f6)); /* webkit */ }
    .db_menu_buttons { float:right; line-height: 50px; height: 50px; margin-top: -100px; margin-right: 30px; color:#fff; font-size: 16px;text-shadow: -1px -1px 1px #333;}
    .db_menu_buttons SELECT { padding:4px; font-size: 14px; }
    .db_normal { margin-top: -100px;}
    .db_first { border-left-width: 1px; border-left-style: solid; border-left-color: #FFF; -webkit-box-shadow: 0px 2px 5px #000000;-moz-box-shadow: 0px 2px 5px #000000;box-shadow: 0px 2px 5px #000000; margin-top:23px; border-radius: 0 0 12px 12px; height:45px; background-color:#6ba8e5; background: -moz-linear-gradient(top, #b8e1fc 0%, #a9d2f3 10%, #90bae4 25%, #90bcea 37%, #90bff0 50%, #6ba8e5 51%, #a2daf5 83%, #bdf3fd 100%); /* firefox */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#b8e1fc), color-stop(10%,#a9d2f3), color-stop(25%,#90bae4), color-stop(37%,#90bcea), color-stop(50%,#90bff0), color-stop(51%,#6ba8e5), color-stop(83%,#a2daf5), color-stop(100%,#bdf3fd)); }
    </style>
    
  <style>
  
   .textBox
   {
     height:30px; 
	 width:300px
   }
   
   


  </style>
  
  

    
    
</HEAD>

<BODY style=" padding:50px; background-image: url('spitxtr.jpg')">

	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 62;
			    
			    List list = (List) ses.getAttribute("processList");
			    Iterator iter = list.iterator();
			    BeanProcessUsers beanProcessUser;
			    int found = 0;
			    
			    while( iter.hasNext() )
			      {
			        beanProcessUser = (BeanProcessUsers) iter.next(); 
			        
			        if ( beanProcessUser.getId_process().getId() == TAG )
			          {
			            found = 1;
			            break;
			          }
			      } 
		        if ( found == 0 )
		          response.sendRedirect( "../login.jsp");
		        else
		          found = 0;
		        
		        BeanTestOrderTest currentBeanTestOrderTest = (BeanTestOrderTest) ses.getAttribute( "orderResult_currentBeanTestOrderTest" );
				
				String currentTestOrderTest_String = "-1";
				
				if ( currentBeanTestOrderTest != null )
				  currentTestOrderTest_String = new Integer( currentBeanTestOrderTest.getId() ).toString();
				
				BeanTestResult currentBeanTestResult = (BeanTestResult) ses.getAttribute( "orderResult_currentBeanTestResult" );
			    
				String currentTestResult_String = "-1";
				
				if ( currentBeanTestResult != null )
				  currentTestResult_String = new Integer( currentBeanTestResult.getId() ).toString();
				
				BeanTestResult beanTestResult = (BeanTestResult) ses.getAttribute( "orderResult_currentBeanTestResult" );
				if ( beanTestResult == null )	
					beanTestResult =  new BeanTestResult();
				
				
				BeanTestResult[] orderResult_beanTestResults = (BeanTestResult[]) ses.getAttribute( "orderResult_beanTestResults" );	
				
				
				Integer beanTestResultsSize = (Integer) ses.getAttribute( "beanTestResultsSize" );
				if ( beanTestResultsSize != null )
					beanTestResultsSize = (Integer) ses.getAttribute( "beanTestResultsSize" );
				else
					beanTestResultsSize=0;

			  %>
    
           <SCRIPT>	
            var isCorrect = true;
            var backTab = false;
			function checkLimit(element,index){
			    
				//alert(document.getElementById("label"+index).id);
				
			    min = parseFloat(document.getElementsByName("item[" +index+  "].min_value")[0].value);
			   
			    max = parseFloat(document.getElementsByName("item[" +index+  "].max_value")[0].value);
			    value = parseFloat(element.value);

			    var label = document.getElementById("label"+index);
			 //  alert(max);
			 //  alert(min);
			    //label.innerHTML='fgdf';
			    //alert( label.innerHTML);
			    
			    if (!isNaN(value))
			    {	
				    if((max == 999999))
				      {	
				    	   
				    	if(!(value >= min)) 
				    		{
				    		  element.style.backgroundColor = "RED";
						      // alert("El valor capturado debe ser mayor a: \n                        " + min + " \n          confirme o corrija el valor");
						       label.innerHTML ='El valor capturado debe ser mayor a:' + min +                        ' \n          confirme o corrija el valor';
						       isCorrect = false;
						      // return;
				    		}
				    	  else				    			
				    	   {
				    		  element.style.backgroundColor = "";
				    		  label.innerHTML ='';
				    		  isCorrect = true;
				    	   }
				    	  
				      }
				    else if((min == -999999))
				      {	
				    	
				    	  if(!(value <= max)) 
				    		{
				    		  element.style.backgroundColor = "RED";
						      //alert("El valor capturado debe ser menor a: \n                        " + max +" \n          confirme o corrija el valor");
						      label.innerHTML ='El valor capturado debe ser menor a: \n                        ' + max +' \n          confirme o corrija el valor';
						      isCorrect = false;
						      //return;
				    		}
				    	  else				    			
				    	   {
				    		  element.style.backgroundColor = "";
				    		  label.innerHTML ='';
				    		  isCorrect = true;
				    	   }
				      }
				    else if((value >= min) && (value <= max))
				      {	
				    	 element.style.backgroundColor = "";
				    	 label.innerHTML ='';
				    	 isCorrect = true;
			    	  } 
				    else
				     {			    	
				    	
				    	element.style.backgroundColor = "RED";
				    	//alert("El valor capturado debe estar entre: \n                        " + min +" y " + max +" \n          confirme o corrija el valor");
				    	label.innerHTML ='El valor capturado debe estar entre: \n                        ' + min +' y ' + max +' \n          confirme o corrija el valor';
				    	isCorrect = false;
				    	
				    	//return;
			    		
				     }
			    }
			    else
			    {
			    	 element.style.backgroundColor = "RED";
			    	  //alert("El valor capturado no debe ser vacio"	);
			    	 //label.innerHTML ="El valor capturado no debe ser vacio";
			    	 label.innerHTML ='El valor capturado debe estar entre: \n                        ' + min +' y ' + max +' \n          confirme o corrija el valor';
					    
			    	 isCorrect = false;
			    	 backTab = true;
			    	 //alert(backTab);
			    	 //return;
			    }
			  
			    //alert(isCorrect);
			    
			    
			}  
			
			 
			function enterToTab(pEvent,index) {

				element = window.event.srcElement;
			        
				if (window.event.keyCode == 13) //&& window.event.srcElement.type != 'input') 
		        {

		           if(element.tagName == 'INPUT')
	        		{
		        	   
	        		  if(element.type == 'text')
	        		  {		
	        			element = window.event.srcElement;		           
	  		            var tbix= element.tabIndex + 1;	  		
	  		            
	  		            next = document.getElementsByName("item["+tbix+"].value")[0];
	  		            
	  		            
	  		            if(next == undefined)
  		            	{
  		            	  next = document.getElementsByName("item["+tbix+"].value_str")[0];
  		            	}
	  		           
	  		           
	  		          if(next != undefined)
		            	{
	  		        	  next.focus();	  		      	    
		  		      	  pEvent.preventDefault();
		  		      	  window.event.returnValue = false;
		  		          return false; 
		            	}
	  		          else
	  		        	{  	        	  
	  		        	   var node_list = document.getElementsByTagName('input');
 
							for (var i = 0; i < node_list.length; i++) {
							    var node = node_list[i];
							 
							    if (node.getAttribute('type') == 'submit') {
							        node.focus();	 
							    }
							} 
							
	  		        	  pEvent.preventDefault();
		  		      	  window.event.returnValue = false;
		  		          return false; 
	  		        	}
	  		            
	  		      	   
	        		  }
	        	   }
	        		  
		        	 
		        	
                   /*
		        	if(element.tagName == 'INPUT')
		        		{
		        		
		        		  if(element.type == 'text')
		        		  {		        			  
			        		window.event.keyCode = 9;	
			        		
		        		  }else		        			  
		        		  {   
		        			  if(backTab)
		       				  {
		       					window.event.keyCode = 9;
		       					backTab= false; 	
		       					element.checked = false; 
		       				  }else
		       				  {
		       					if(isCorrect == false)
			        			 {
			        				 
			        				 element.checked = true; 			        			 
				        			 isCorrect= true; 		
				        			 
				        			 window.event.keyCode = 9;
	 
			        			 }else
			        			 { 
			        				 window.event.keyCode = 9;
			        			 }
		       				  }

		        		  }
		        		  
		        		 
		        		   
		        		}*/
		        
		        	
		            //window.event.keyCode = 9;	            
		        	  
		        	//alert(window.event.keyCode);
		        	//alert(window.event.returnValue);
		        }
		        
		        
			   
			}
			
		</SCRIPT>

			
		    <logic:notPresent name="orderResult_beanOrder" scope="session">
	            <html:form action="/orderResult" enctype="multipart/form-data" >
				<html:hidden property="process" value="selectOrder"/>
	
	               <table ALIGN="CENTER" border="0" style="width: 50%;">
						<TR bgcolor="lightblue">
							<TD colspan="4" align="center" >					 
							 <div class="x-blue_dg_caption"> <bean:message key="label.order" />   </div>					 
							</TD>
						</TR>
						<TR>
							<TD><bean:message key="label.order" /></TD>
							<TD><html:text property="id_order"></html:text></TD>
				        	<TD> <FONT COLOR="RED"> <html:errors property="id_order"/></FONT> 
							<TD>
								<html:submit><bean:message key="label.confirm" /></html:submit>										
							</TD>
							<TD bgcolor="">					       
						  </TD> 					  
						    <TD bgcolor="">					    				       
						  </TD> 
						</TR>
					</table>
					
				</html:form>
  	        </logic:notPresent>
			
		    <logic:present name="orderResult_beanOrder" scope="session">
	
               <table ALIGN="CENTER" border="0" style="width: 50%;">
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.order" />   </div>					 
						</TD>
					</TR>
					<TR>
						<TD><bean:message key="label.order" /></TD>
						<TD><bean:write name="orderResult_beanOrder" property="id" /></TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
				</table>



	            <html:form action="/orderResult" enctype="multipart/form-data" >
				<html:hidden property="process" value="captureValue"/>
    
	                  <table ALIGN="CENTER" border="0" style="width: 50%;">
						<TR bgcolor="lightblue">
							<TD colspan="5" align="center" >					 
							 <div class="x-blue_dg_caption"> <bean:message key="label.results.capture" />   </div>					 
							</TD>
						</TR>
						

                  <% 
                  boolean test_name = false;   
                  String testName = "";
                  int index = 0;
                  %>
                <logic:iterate id="item" name="orderResultForm" property="lstItems">
                 <bean:define id="p" name="item" property="test_name"> </bean:define>
                 <html:hidden name="item" property="id_order"  indexed="true"  />
                 <html:hidden name="item" property="id_test"   indexed="true"  />   
                 <html:hidden name="item" property="is_numeric"  indexed="true"  />  
                 <html:hidden name="item" property="must_compare"   indexed="true"  /> 
                 <html:hidden name="item" property="test_name"  indexed="true"  />        
                 <html:hidden name="item" property="is_free_text"  indexed="true"  />  
                  

                  <%
                  
                  if(!testName.equals( ((String) p)) )
                  {
                	  testName = ((String) p);
		          %>          		 
	               
					<TR bgcolor="lightblue">
						<TD colspan="5" align="center" >		
						   			 
						 <bean:write name="item" property="test_name"/>	 </div>					 
						</TD>
					</TR>
					
					   <tr>
		             	   <TD><strong><bean:message key="label.parameters" /></strong> </TD>
						 
						   <TD align="left" width=""><strong>Valor</strong></TD>
						 
						   <TD align="left"><strong>Confirmar</strong></TD>
						   <TD bgcolor="">					       
						  </TD> 
						  
						    <TD bgcolor="">					    				       
						  </TD> 
		             	
		             	</tr>
				   <%
                  }
				   %>
                 
                   <TR>
				      <TD bgcolor="" rowspan="" align="top" >
				           <html:hidden name="item" property="id"  indexed="true"  />  
				           <html:hidden name="item" property="test_parameter_name"  indexed="true"  />  
				           <bean:write name="item" property="test_parameter_name"/>	
					  </TD>
					  
					  
					   
						 				
						  <logic:equal name="item" property="is_numeric" value="1"> 
						  <logic:equal name="item" property="must_compare" value="1">
						  <TD>	
						       
						   	 <html:hidden name="item" property="min_value"   indexed="true"  />
						   	 <html:hidden name="item" property="max_value"   indexed="true"  />  
						   	 <%String onblur="checkLimit(this," + String.valueOf( index ) + ");"; String enterToTab="enterToTab(event," + String.valueOf( index ) + ");";%>
						   	 
						   	 <html:text name="item" property="value" style="width:98%" indexed="true" onblur="<%=onblur%>" onkeydown="<%=enterToTab%>"  tabindex="<%=String.valueOf( index )%>"> </html:text>	
						   	 <%String value= "item[" + String.valueOf( index ) + "].value";%>						  
							   <FONT COLOR="RED"> <html:errors property="<%=value%>"/> </FONT>	
							   
						  </TD>		 
						   <TD bgcolor="">
						      <html:checkbox name="item" property="confirm" indexed="true"></html:checkbox>					      
						  </TD>
						  <TD bgcolor="">
						      
						      <%String labelid= "label" + String.valueOf( index ) + "";%>
							<label id="<%=labelid%>" ></label>
							
							
							
						  </TD>
						  </logic:equal>
						  
						  <logic:equal name="item" property="must_compare" value="0"> 
						   <TD>									
							 <% String enterToTab="enterToTab(event," + String.valueOf( index ) + ");";%>
						   	 				   	 
						   	 <html:text name="item" property="value" style="width:98%" indexed="true" onkeydown="<%=enterToTab%>" tabindex="<%=String.valueOf( index )%>"> </html:text>	
						   	 <%String value= "item[" + String.valueOf( index ) + "].value";%>						  
							 <FONT COLOR="RED"> <html:errors property="<%=value%>"/> </FONT>	 
	  
						  </TD>		 
						   <TD bgcolor="">				   
						  </TD>
							    
						 </logic:equal>
						  
						  <TD bgcolor="">
						  </TD> 				  
						 </logic:equal>	
					 
					 
					 
					 
							
					<logic:equal name="item" property="is_numeric" value="0"> 
				    <TD>
				    <% String enterToTab="enterToTab(event," + String.valueOf( index ) + ");";%>
				    <logic:equal name="item" property="is_free_text" value="0"> 
					   	 <html:hidden name="item" property="min_value_str"   indexed="true"  />
					   	 <html:hidden name="item" property="max_value_str"   indexed="true"  />  
					   	 <html:text name="item" property="value_str" style="width:98%" indexed="true" tabindex="<%=String.valueOf( index )%>" onkeydown="<%=enterToTab%>"> </html:text>	
					   	 <%String value_str= "item[" + String.valueOf( index ) + "].value_str";%>						  
						   <FONT COLOR="RED"> <html:errors property="<%=value_str%>"/> </FONT>	
					 </logic:equal>	   	
						
						<logic:equal name="item" property="is_free_text" value="1"> 					   
						 <html:textarea name="item" property="free_text" rows="4" cols="40" indexed="true"> </html:textarea>
						 <%String free_text= "item[" + String.valueOf( index ) + "].free_text";%>						  
						   <FONT COLOR="RED"> <html:errors property="<%=free_text%>"/> </FONT>	
						 </logic:equal>	   
						 
				   	 </TD>		
				   	 <TD bgcolor="">					     
					  </TD>
				   	  <TD bgcolor="">					       
					  </TD> 
					  
					    <TD bgcolor="">					    				       
					  </TD> 
					  
					</logic:equal>	
					
					
					
							    
					   
					 
					</TR>
					<tr>
					<TD bgcolor="">					    				       
					 </TD> 
					 <td colspan="4">
					 
					    
					 </td>
					</tr>
					<%index++; %>
                 
                </logic:iterate>
                
               <TR>
				<TD bgcolor="">
					  </TD>
					  <TD>						
					    <html:submit> <bean:message key="label.capture" />   </html:submit>						    
					  </TD>
					  <TD bgcolor="">
					  </TD>
					  	  <TD bgcolor="">
					       
					  </TD> 
					  
					    <TD bgcolor="">
					    				       
					  </TD> 
					  
					</TR>
					
	            </table>

			    </html:form>

		    </logic:present>
		    
		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

   
 if ( document.getElementsByName("id_order")[0] != null )
	{
	  document.getElementsByName("id_order")[0].focus();
	}
  else
    {
	  next = document.getElementsByName("item[0].value");
	  next[0].focus();
    }
  
  
//-->
</SCRIPT>

</html:html>
