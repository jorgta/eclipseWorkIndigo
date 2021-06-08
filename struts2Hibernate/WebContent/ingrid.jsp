<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>
<TITLE>Ingrid. the jQuery Datagrid</TITLE>
<META content="text/html; charset=windows-1252" http-equiv="Content-Type"><!-- this stylesheet is for page styling only -->
<STYLE type="text/css" media="all">@import "css/site.css";</STYLE>
<!-- include jquery lib -->
<SCRIPT type="text/javascript" src="ingridTheme/jquery.js"></SCRIPT>
<!-- include ingrid lib -->
<SCRIPT type="text/javascript" src="ingridTheme/jquery.ingrid.js"></SCRIPT>
<!-- ingrid default stylesheet -->
<STYLE type="text/css" media="all">@import "ingridTheme/ingrid.css";</STYLE>
<!-- to make ingrid save her state (selected rows, page number, column sort & direction); just include the jQ cookie plugin -->
<SCRIPT type="text/javascript" src="ingridTheme/jquery.cookie.js"></SCRIPT>
<!-- initialize -->
<SCRIPT type="text/javascript">
$(document).ready(
	function() {
		
		// create the grid
		// returns a jQ object with a 'g' property - that's ingrid
		var mygrid1 = $("#table1").ingrid({ 
										url: 'data.jsp',
										height: 350,
										savedStateLoad: true,
										rowClasses: ['grid-row-style1','grid-row-style1','grid-row-style2','grid-row-style1','grid-row-style1','grid-row-style3'],
										onRowSelect: function(tr, selected){											
											var str 		= selected ? 'SELECTED' : 'UNSELECTED';
											var tr_html	= $(tr).html();											
											alert( str + ' Row. InnerHTML is : ' + tr_html);																						
										}										
									});
		

		$('#jump20').click(function(){
			// the 'g' object is ingrid - call methods like so:
			mygrid1.g.p.setPage(20)
		});
		
		$('#sel-rows').click(function(){
			// the 'g' object is ingrid - call methods like so:
			var rows = mygrid1.g.getSelectedRows();			
			for (i=0; i<rows.length; i++) {				
				var str = 'SELECTED ROW ' + i + " - InnerHTML is : \n";
				alert( str + $(rows[i]).html() );
			}			
		});
				
	}
); 
</SCRIPT>

<META name="GENERATOR" content="MSHTML 9.00.8112.16430"></HEAD>
<BODY>

<DIV class="demo"></DIV>

<TABLE id="table1">
  <THEAD>
  <TR>
    <TH>Col 0</TH>
    <TH>Col 1</TH>
    <TH>Col 2</TH>
    <TH>Col 3</TH></TR></THEAD>
  <TBODY>
 <TR>
   
 <!--  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>
  <TR>
    <TD>static col 1</TD>
    <TD>static col 2</TD>
    <TD>static col 3</TD>
    <TD>static col 4</TD></TR>--></TBODY>
	</TABLE>
	
	
	
<HR>
</P></BODY>
</html:html>
