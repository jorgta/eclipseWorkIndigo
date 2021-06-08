<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<jsp:include page="top.jsp" />
<TR><TD><br /></TD></TR>

<TR>
	<TD>
			<TABLE border="1" class="table_campo">
			  <TR>
				  <TD class="table_encabezado">
				     <bean:message key="label.company.name" />
				  </TD>
				  <TD>
				     <bean:message key="label.contact.company.full.name" />
				  </TD>
			  </TR>
			  <TR>
				  <TD class="table_encabezado">
				     <bean:message key="label.phone" />
				  </TD>
				  <TD>
				    <bean:message key="label.contact.phone.number1" />
				  </TD>
			  </TR>
			  
			  <TR>
				  <TD class="table_encabezado">
				     <bean:message key="label.Permission.of.Security.Publishes.Policeman" />
				  </TD>
				  <TD>
				    <bean:message key="label.Permission.of.Security.Publishes.Policeman.number" />
				  </TD>
			  </TR>
			  
			  <TR>
				  <TD class="table_encabezado">
				     <bean:message key="label.bussiness.id" />
				  </TD>
				  <TD>
				    <bean:message key="label.bussiness.id.number" />
				  </TD>
			  </TR>
			  
			  <TR>
				  <TD class="table_encabezado">
				     <bean:message key="label.fax" />
				  </TD>
				  <TD>
				    <bean:message key="label.contact.phone.fax" />
				  </TD>
			  </TR>
			  <TR>
				  <TD class="table_encabezado">
				     <bean:message key="label.email" />
				  </TD>
				  <TD colspan="2">
					<A HREF='mailto:<bean:message key="label.contact.general.email" />'> <bean:message key="label.contact.general.email" /> </A>
				  </TD>
			  </TR>
			  <TR>
				  <TD class="table_encabezado">
				     <bean:message key="label.web.address" />
				  </TD>
				  <TD colspan="2">
					<a href='<bean:message key="label.contact.link" />'> <bean:message key="label.contact.link" />  </a>
				  </TD>
			  </TR>
			</TABLE>
	</TD>
</TR>


<jsp:include page="bottom.jsp" />


