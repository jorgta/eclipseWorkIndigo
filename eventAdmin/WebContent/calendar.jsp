<!doctype html>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*,com.bituos.*, com.google.gson.Gson"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>

<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>TOAST UI Calendar</title>
   
  
    
    <link rel="stylesheet" href="tui.calendar-master/examples/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/tui-time-picker.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/tui-date-picker.css">    
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/dist/tui-calendar.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/default.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/icons.css">

</head>
<body>
 
      <%
            HttpSession ses = request.getSession();
            BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
            String visibleUserData = (String)ses.getAttribute("visibleUserData");   
            String clientNumber = (String)ses.getAttribute("clientNumber"); 
            String clientName = (String)ses.getAttribute("clientName"); 
            String total = (String)ses.getAttribute("total"); 

            BeanList listEdit_currentList = (BeanList) ses.getAttribute( "listEdit_currentList" );							    
            BeanListOptions listEdit_currentOption = (BeanListOptions) ses.getAttribute( "listEdit_currentOption" );							    
            BeanListOptionMenu listEdit_currentMenu = (BeanListOptionMenu) ses.getAttribute( "listEdit_currentMenu" );							    
            BeanSaloon quoteNew_currentSaloon = (BeanSaloon) ses.getAttribute( "quoteNew_currentSaloon" );							    
            BeanFlower quoteNew_currentFlower = (BeanFlower) ses.getAttribute( "quoteNew_currentFlower" );							    
            BeanMusic quoteNew_currentMusic = (BeanMusic) ses.getAttribute( "quoteNew_currentMusic" );							    
            String clString;
            String coString;
            String cmString;
            String csString;
            String cfString;
            String cmuString;


            String from = (String)ses.getAttribute( "from");	


            if ( clientNumber == null )
					 clientNumber = new String("");
		         
		         if ( clientName == null )
			           clientName = new String("");
			         
		         if ( total == null )
		        	 total = new String("");
			         
				 if ( listEdit_currentList != null ) 
				   clString = new Integer( listEdit_currentList.getId() ).toString(); 
				 else
				   clString = "-1";
					 
				 if ( listEdit_currentOption != null ) 
				   coString = new Integer( listEdit_currentOption.getId() ).toString(); 
				 else
				   coString = "-1";
					 
				 if ( listEdit_currentMenu != null ) 
				   cmString = new Integer( listEdit_currentMenu.getId() ).toString(); 
				 else
				   cmString = "-1";
		         
				 if ( quoteNew_currentSaloon != null ) 
				   csString = new Integer( quoteNew_currentSaloon.getId() ).toString(); 
				 else
				   csString = "-1";
			         
				 if ( quoteNew_currentFlower != null ) 
				   cfString = new Integer( quoteNew_currentFlower.getId() ).toString(); 
				 else
				   cfString = "-1";
			         
				 if ( quoteNew_currentMusic != null ) 
					   cmuString = new Integer( quoteNew_currentMusic.getId() ).toString(); 
				 else
					 cmuString = "-1";

            
             
      
      %>
      
      <input type="hidden" id="from" name="from" value="<%=from%>" />
      <input type="hidden" id="currentPrice" name="clientnumber" value="<%=clString%>" />
      <input type="hidden" id="clientname" name="clientname" value="<%=clientName%>" />
      <input type="hidden" id="clientnumber" name="clientnumber" value="<%=clientNumber%>" />




        
    <div id="lnb">
        <div class="lnb-new-schedule">
            <button id="btn-new-schedule" type="button" class="btn btn-default btn-block lnb-new-schedule-btn" data-toggle="modal">
                Nuevo Evento</button>
        </div>
        <div id="lnb-calendars" class="lnb-calendars">
            <div>
                <div class="lnb-calendars-item">
                    <label>
                        <input class="tui-full-calendar-checkbox-square" type="checkbox" value="all" checked>
                        <span></span>
                        <strong>Todos</strong>
                    </label>
                </div>
            </div>
            <div id="calendarList" class="lnb-calendars-d1">
            </div>
        </div>
        <div class="lnb-footer">
            © Bituos.
        </div>
    </div>
    <div id="right">
        <div id="menu">
            <span class="dropdown">
                <button id="dropdownMenu-calendarType" class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="true">
                    <i id="calendarTypeIcon" class="calendar-icon ic_view_month" style="margin-right: 4px;"></i>
                    <span id="calendarTypeName">Lista</span>&nbsp;
                    <i class="calendar-icon tui-full-calendar-dropdown-arrow"></i>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu-calendarType">
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-daily">
                            <i class="calendar-icon ic_view_day"></i>Diario
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weekly">
                            <i class="calendar-icon ic_view_week"></i>Semanal
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-monthly">
                            <i class="calendar-icon ic_view_month"></i>Mensual
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weeks2">
                            <i class="calendar-icon ic_view_week"></i>2 semanas
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weeks3">
                            <i class="calendar-icon ic_view_week"></i>3 semanas
                        </a>
                    </li>
                    <li role="presentation" class="dropdown-divider"></li>
                    <li role="presentation">
                        <a role="menuitem" data-action="toggle-workweek">
                            <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-workweek" checked>
                            <span class="checkbox-title"></span>Mostrar fines de semana
                        </a>
                    </li>
                    <li role="presentation">
                        <a role="menuitem" data-action="toggle-start-day-1">
                            <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-start-day-1">
                            <span class="checkbox-title"></span>Comienza la semana el lunes
                        </a>
                    </li>
                    <li role="presentation">
                        <a role="menuitem" data-action="toggle-narrow-weekend">
                            <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-narrow-weekend">
                            <span class="checkbox-title"></span>Más estrecho que los días laborables
                        </a>
                    </li>
                </ul>
            </span>
            <span id="menu-navi">
                <button type="button" class="btn btn-default btn-sm move-today" data-action="move-today">Hoy</button>
                <button type="button" class="btn btn-default btn-sm move-day" data-action="move-prev">
                    <i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
                </button>
                <button type="button" class="btn btn-default btn-sm move-day" data-action="move-next">
                    <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
                </button>
            </span>
           
            <span id="renderRange" class="render-range"></span>
        </div>
        <div id="calendar"></div>
    </div>

 

<script src="tui.calendar-master/examples/js/jquery-3.2.1.slim.min.js"></script>
<script src="tui.calendar-master/examples/js/bootstrap.min.js"></script>
<script src="tui.calendar-master/examples/js/tui-code-snippet.min.js"></script>
<script src="tui.calendar-master/examples/js/tui-dom.js"></script>
<script src="tui.calendar-master/examples/js/tui-time-picker.min.js"></script>
<script src="tui.calendar-master/examples/js/tui-date-picker.min.js"></script>
<script src="tui.calendar-master/examples/js/moment.min.js"></script>
<!--<script src="tui.calendar-master/examples/js/chance.min.js"></script>-->
<script src="tui.calendar-master/dist/tui-calendar.js"></script>


<!--ajax-->
<script src="tui.calendar-master/examples/js/jquery.min.js"></script>
<!--end ajax-->

<script src="tui.calendar-master/examples/js/data/connection.js"></script>

<script src="tui.calendar-master/examples/js/data/calendars.js"></script>
<script src="tui.calendar-master/examples/js/data/schedules.js"></script>
 


<script src="tui.calendar-master/examples/js/load.js"></script>
         

</body>
</html>