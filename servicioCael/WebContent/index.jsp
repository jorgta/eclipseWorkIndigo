<jsp:include page="top.jsp" />
<TR>
	<TD>
		<TABLE width="800">
		<TR>
			<TD align="right">
		
				<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
				<!--
				
				function ShowDate()
				  {
				    var d=new Date()
				    var weekday=new Array("Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado")
				    var monthname=new Array("Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic")
				    document.write(weekday[d.getDay()] + " ")
				    document.write(d.getDate() + ".")
				    document.write(monthname[d.getMonth()] + ".")
				    document.write(d.getFullYear())
				  }
				
				
				function makeDefault(element)
					{
						element.style.behavior='url(#default#homepage)'; 
						element.setHomePage('http://www.serviciocael.com');
					}
				
				//-->
				</SCRIPT>
				<TD>
				<TD>		 	
						
						  <BR>
						  <P class="generalTextHeader">
						  Inicios
						  </P>
						  
						  <P class="generalText">
						  La empresa Servicio Cael se estableció en Chihuahua, Chih, el 14 de octubre de 1996 como especialistas en el servicio personalizado de seguridad y vigilancia privada. Tomando ventaja de la tecnología y aplicándola a la satisfacción total de nuestros clientes, realizando una mezcla esencial entre equipo humano, inteligencia y honradez.<BR>
						  </P>
						  <BR> 
						  Ultima actualizaci&oacute;n: Tuesday, Sep 18, 2012
						  <BR>
						  
						  <A href="index.jsp" onclick="makeDefault(this)" >Hacer esta pagina mi pagina de inicio</A><BR>
						
						<BR>
						<BR>
				</TD>	
				<TD>
					<SCRIPT type="text/javascript">
			
						//new pausescroller(name_of_message_array, CSS_ID, CSS_classname, pause_in_miliseconds)
						
						new pausescroller(pausecontent, "pscroller1", "someclass", 3000)
						document.write("<br />")			
					</SCRIPT>
				</TD>
		</TR>

		</TABLE>
	</TD>		
</TR>

<jsp:include page="bottom.jsp" />