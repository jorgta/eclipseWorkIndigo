
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML XHTML 1.0 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>



<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Frontier JQuery Calendar</title>

<!-- Include CSS for JQuery Frontier Calendar plugin (Required for calendar plugin) -->
<link rel="stylesheet" type="text/css" href="monthCalendarSchedule/css/frontierCalendar/jquery-frontier-cal-1.3.2.css" />

<!-- Include CSS for color picker plugin (Not required for calendar plugin. Used for example.) -->
<link rel="stylesheet" type="text/css" href="monthCalendarSchedule/css/colorpicker/colorpicker.css" />

<!-- Include CSS for JQuery UI (Required for calendar plugin.) -->
<link rel="stylesheet" type="text/css" href="monthCalendarSchedule/css/jquery-ui/smoothness/jquery-ui-1.8.1.custom.css" />

<!--
Include JQuery Core (Required for calendar plugin)
** This is our IE fix version which enables drag-and-drop to work correctly in IE. See README file in js/jquery-core folder. **
-->
<script type="text/javascript" src="monthCalendarSchedule/js/jquery-core/jquery-1.4.2-ie-fix.min.js"></script>

<!-- Include JQuery UI (Required for calendar plugin.) -->
<script type="text/javascript" src="monthCalendarSchedule/js/jquery-ui/smoothness/jquery-ui-1.8.1.custom.min.js"></script>

<!-- Include color picker plugin (Not required for calendar plugin. Used for example.) -->
<script type="text/javascript" src="monthCalendarSchedule/js/colorpicker/colorpicker.js"></script>

<!-- Include jquery tooltip plugin (Not required for calendar plugin. Used for example.) -->
<script type="text/javascript" src="monthCalendarSchedule/js/jquery-qtip-1.0.0-rc3140944/jquery.qtip-1.0.js"></script>

<!--
	(Required for plugin)
	Dependancies for JQuery Frontier Calendar plugin.
    ** THESE MUST BE INCLUDED BEFORE THE FRONTIER CALENDAR PLUGIN. **
-->
<script type="text/javascript" src="monthCalendarSchedule/js/lib/jshashtable-2.1.js"></script>

<!-- Include JQuery Frontier Calendar plugin -->
<script type="text/javascript" src="monthCalendarSchedule/js/frontierCalendar/jquery-frontier-cal-1.3.2.min.js"></script>

</head>
<body style="background-color: #aaaaaa;" onload="pageload();">

<!-- Some CSS for our example. (Not required for calendar plugin. Used for example.)-->
<style type="text/css" media="screen">
/*
Default font-size on the default ThemeRoller theme is set in ems, and with a value that when combined 
with body { font-size: 62.5%; } will align pixels with ems, so 11px=1.1em, 14px=1.4em. If setting the 
body font-size to 62.5% isn't an option, or not one you want, you can set the font-size in ThemeRoller 
to 1em or set it to px.
http://osdir.com/ml/jquery-ui/2009-04/msg00071.html
*/
body { font-size: 62.5%; }
.shadow {
	-moz-box-shadow: 3px 3px 4px #aaaaaa;
	-webkit-box-shadow: 3px 3px 4px #aaaaaa;
	box-shadow: 3px 3px 4px #aaaaaa;
	/* For IE 8 */
	-ms-filter: "progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#aaaaaa')";
	/* For IE 5.5 - 7 */
	filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#aaaaaa');
}
</style>

<script type="text/javascript">
var jfcalplugin;
$(document).ready(function(){	

	var clickDate = "";
	var clickAgendaItem = "";
	
	/**
	 * Initializes calendar with current year & month
	 * specifies the callbacks for day click & agenda item click events
	 * then returns instance of plugin object
	 */
	   jfcalplugin = $("#mycal").jFrontierCal({
		date: new Date(),
		dayClickCallback: myDayClickHandler,
		agendaClickCallback: myAgendaClickHandler,
		agendaDropCallback: myAgendaDropHandler,
		agendaMouseoverCallback: myAgendaMouseoverHandler,
		applyAgendaTooltipCallback: myApplyTooltip,
		agendaDragStartCallback : myAgendaDragStart,
		agendaDragStopCallback : myAgendaDragStop,
		dragAndDropEnabled: true
	}).data("plugin");
	
	
	
	/**
	 * Do something when dragging starts on agenda div
	 */
	function myAgendaDragStart(eventObj,divElm,agendaItem){
		// destroy our qtip tooltip
		if(divElm.data("qtip")){
			divElm.qtip("destroy");
		}	
	};
	
	/**
	 * Do something when dragging stops on agenda div
	 */
	function myAgendaDragStop(eventObj,divElm,agendaItem){
		//alert("drag stop");
	};
	
	/**
	 * Custom tooltip - use any tooltip library you want to display the agenda data.
	 * for this example we use qTip - http://craigsworks.com/projects/qtip/
	 *
	 * @param divElm - jquery object for agenda div element
	 * @param agendaItem - javascript object containing agenda data.
	 */
	function myApplyTooltip(divElm,agendaItem){

		// Destroy currrent tooltip if present
		if(divElm.data("qtip")){
			divElm.qtip("destroy");
		}
		
		var displayData = "";
		
		var title = agendaItem.title;
		var startDate = agendaItem.startDate;
		var endDate = agendaItem.endDate;
		var allDay = agendaItem.allDay;
		var data = agendaItem.data;
		displayData += "<br><b>" + title+ "</b><br><br>";
		if(allDay){
			displayData += "(All day event)<br><br>";
		}else{
			
			/**********modificado por jorge**********/
			
			var start_date = startDate.getDate();
			var start_month = startDate.getMonth() + 1; //Months are zero based
			var start_year = startDate.getFullYear();
			var start_hour = startDate.getHours();
			var start_min = startDate.getMinutes();
			
			if(start_month < 10)
				start_month = "0" + start_month.toString();
				
			if(start_date < 10)
				start_date = "0" + start_date.toString();		
			
			if(start_hour < 10)
				start_hour = "0" + start_hour.toString();
			
			if(start_min < 10)
				start_min = "0" + start_min.toString();
				
			
			var start = start_year  + "-" + start_month + "-" + start_date +":" + start_hour + ":" + start_min;
			
			
			
			
			var end_date = endDate.getDate();
			var end_month = endDate.getMonth() + 1; //Months are zero based
			var end_year = endDate.getFullYear();
			var end_hour = endDate.getHours();
			var end_min = endDate.getMinutes();
				
				

			if(end_month < 10)
				end_month = "0" + end_month.toString();
				
			if(end_date < 10)
				end_date = "0" + end_date.toString();		
			
			if(end_hour < 10)
				end_hour = "0" + end_hour.toString();
			
			if(end_min < 10)
				end_min = "0" + end_min.toString();
				
			
			var end = end_year  + "-" + end_month + "-" + end_date +":" + end_hour + ":" + end_min;

			
			displayData += "<b>Inicio:</b> " + start + "<br>" + "<b>Fin:</b> " + end + "<br><br>";
		}
		for (var propertyName in data) {
			displayData += "<b><font size='3' face='Georgia, Arial' color='white'>" + propertyName + "</font>:</b> " + data[propertyName] + "<br>"
		}
		
		// use the user specified colors from the agenda item.
		var backgroundColor = agendaItem.displayProp.backgroundColor;
		var foregroundColor = agendaItem.displayProp.foregroundColor;
		var myStyle = {
			border: {
				width: 5,
				radius: 10
			},
			padding: 10, 
			textAlign: "left",
			tip: true,
			name: "dark" // other style properties are inherited from dark theme		
		};
		if(backgroundColor != null && backgroundColor != ""){
			myStyle["backgroundColor"] = backgroundColor;
		}
		if(foregroundColor != null && foregroundColor != ""){
			myStyle["color"] = foregroundColor;
		}
		// apply tooltip
		divElm.qtip({
			content:  displayData,
			position: {
				corner: {
					tooltip: "bottomMiddle",
					target: "topMiddle"			
				},
				adjust: { 
					mouse: true,
					x: 0,
					y: -15
				},
				target: "mouse"
			},
			show: { 
				when: { 
					event: 'mouseover'
				}
			},
			style: myStyle
		});

	};

	/**
	 * Make the day cells roughly 3/4th as tall as they are wide. this makes our calendar wider than it is tall. 
	 */
	jfcalplugin.setAspectRatio("#mycal",0.75);

	/**
	 * Called when user clicks day cell
	 * use reference to plugin object to add agenda item
	 */
	function myDayClickHandler(eventObj){
		// Get the Date of the day that was clicked from the event object
		var date = eventObj.data.calDayDate;
		// store date in our global js variable for access later
		clickDate = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
		// open our add event dialog
		
//*******************************************modificado por jorge**********************************************/		
		//$('#add-event-form').dialog('open');
		
	};
	
	/**
	 * Called when user clicks and agenda item
	 * use reference to plugin object to edit agenda item
	 */
	function myAgendaClickHandler(eventObj){
		// Get ID of the agenda item from the event object
		var agendaId = eventObj.data.agendaId;		
		// pull agenda item from calendar
		var agendaItem = jfcalplugin.getAgendaItemById("#mycal",agendaId);
		clickAgendaItem = agendaItem;
		$("#display-event-form").dialog('open');
		
	};
	
	/**
	 * Called when user drops an agenda item into a day cell.
	 */
	function myAgendaDropHandler(eventObj){
		// Get ID of the agenda item from the event object
		var agendaId = eventObj.data.agendaId;
		// date agenda item was dropped onto
		var date = eventObj.data.calDayDate;
		// Pull agenda item from calendar
		var agendaItem = jfcalplugin.getAgendaItemById("#mycal",agendaId);		
		alert("You dropped agenda item " + agendaItem.title + 
			" onto " + date.toString() + ". Here is where you can make an AJAX call to update your database.");
	};
	
	/**
	 * Called when a user mouses over an agenda item	
	 */
	function myAgendaMouseoverHandler(eventObj){
		var agendaId = eventObj.data.agendaId;
		var agendaItem = jfcalplugin.getAgendaItemById("#mycal",agendaId);
		//alert("You moused over agenda item " + agendaItem.title + " at location (X=" + eventObj.pageX + ", Y=" + eventObj.pageY + ")");
	};
	/**
	 * Initialize jquery ui datepicker. set date format to yyyy-mm-dd for easy parsing
	 */
	$("#dateSelect").datepicker({
		showOtherMonths: true,
		selectOtherMonths: true,
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		dateFormat: 'yy-mm-dd'
	});
	
	/**
	 * Set datepicker to current date
	 */
	$("#dateSelect").datepicker('setDate', new Date());
	/**
	 * Use reference to plugin object to a specific year/month
	 */
	$("#dateSelect").bind('change', function() {
		var selectedDate = $("#dateSelect").val();
		var dtArray = selectedDate.split("-");
		var year = dtArray[0];
		// jquery datepicker months start at 1 (1=January)		
		var month = dtArray[1];
		// strip any preceeding 0's		
		month = month.replace(/^[0]+/g,"")		
		var day = dtArray[2];
		// plugin uses 0-based months so we subtrac 1
		
		
		/****************************************jorge********************/
		//if(parseInt(month-1) != 0)
		  jfcalplugin.showMonth("#mycal",year,parseInt(month-1).toString());
		//else
		//  jfcalplugin.showMonth("#mycal",year,parseInt(month).toString());

		
		
		
		
		//alert(parseInt(month-1)); //cuando es enero parseInt(month-1) = 0 y por eso da error
	});	
	/**
	 * Initialize previous month button
	 */
	$("#BtnPreviousMonth").button();
	$("#BtnPreviousMonth").click(function() {
		jfcalplugin.showPreviousMonth("#mycal");
		// update the jqeury datepicker value
		var calDate = jfcalplugin.getCurrentDate("#mycal"); // returns Date object
		var cyear = calDate.getFullYear();
		// Date month 0-based (0=January)
		var cmonth = calDate.getMonth();
		var cday = calDate.getDate();
		// jquery datepicker month starts at 1 (1=January) so we add 1
		$("#dateSelect").datepicker("setDate",cyear+"-"+(cmonth+1)+"-"+cday);
		//alert((cmonth+1));
		return false;
	});
	/**
	 * Initialize next month button
	 */
	$("#BtnNextMonth").button();
	$("#BtnNextMonth").click(function() {
		jfcalplugin.showNextMonth("#mycal");
		// update the jqeury datepicker value
		var calDate = jfcalplugin.getCurrentDate("#mycal"); // returns Date object
		var cyear = calDate.getFullYear();
		// Date month 0-based (0=January)
		var cmonth = calDate.getMonth();
		var cday = calDate.getDate();
		// jquery datepicker month starts at 1 (1=January) so we add 1
		$("#dateSelect").datepicker("setDate",cyear+"-"+(cmonth+1)+"-"+cday);		
		return false;
	});
	
	/**
	 * Initialize delete all agenda items button
	 */
	$("#BtnDeleteAll").button();
	$("#BtnDeleteAll").click(function() {	
		jfcalplugin.deleteAllAgendaItems("#mycal");	
		return false;
	});		
	
	/**
	 * Initialize iCal test button
	 */
	$("#BtnICalTest").button();
	$("#BtnICalTest").click(function() {
		// Please note that in Google Chrome this will not work with a local file. Chrome prevents AJAX calls
		// from reading local files on disk.		
		jfcalplugin.loadICalSource("#mycal",$("#iCalSource").val(),"html");	
		return false;
	});	

	/**
	 * Initialize add event modal form
	 */
	$("#add-event-form").dialog({
		autoOpen: false,
		height: 400,
		width: 400,
		modal: true,
		buttons: {
			'Add Event': function() {

				var what = jQuery.trim($("#what").val());
			
				if(what == ""){
					alert("Please enter a short event description into the \"what\" field.");
				}else{
				
					var startDate = $("#startDate").val();
					var startDtArray = startDate.split("-");
					var startYear = startDtArray[0];
						
					var startMonth = startDtArray[1];		
					var startDay = startDtArray[2];
					
					startMonth = startMonth.replace(/^[0]+/g,"");
					startDay = startDay.replace(/^[0]+/g,"");
					var startHour = jQuery.trim($("#startHour").val());
					var startMin = jQuery.trim($("#startMin").val());
					var startMeridiem = jQuery.trim($("#startMeridiem").val());
					startHour = parseInt(startHour.replace(/^[0]+/g,""));
					if(startMin == "0" || startMin == "00"){
						startMin = 0;
					}else{
						startMin = parseInt(startMin.replace(/^[0]+/g,""));
					}
					if(startMeridiem == "AM" && startHour == 12){
						startHour = 0;
					}else if(startMeridiem == "PM" && startHour < 12){
						startHour = parseInt(startHour) + 12;
					}

					var endDate = $("#endDate").val();
					var endDtArray = endDate.split("-");
					var endYear = endDtArray[0];
					
					var endMonth = endDtArray[1];		
					var endDay = endDtArray[2];
						
					endMonth = endMonth.replace(/^[0]+/g,"");

					endDay = endDay.replace(/^[0]+/g,"");
					var endHour = jQuery.trim($("#endHour").val());
					var endMin = jQuery.trim($("#endMin").val());
					var endMeridiem = jQuery.trim($("#endMeridiem").val());
					endHour = parseInt(endHour.replace(/^[0]+/g,""));
					if(endMin == "0" || endMin == "00"){
						endMin = 0;
					}else{
						endMin = parseInt(endMin.replace(/^[0]+/g,""));
					}
					if(endMeridiem == "AM" && endHour == 12){
						endHour = 0;
					}else if(endMeridiem == "PM" && endHour < 12){
						endHour = parseInt(endHour) + 12;
					}
					
					
					var startDateObj = new Date(parseInt(startYear),parseInt(startMonth)-1,parseInt(startDay),startHour,startMin,0,0);
					var endDateObj = new Date(parseInt(endYear),parseInt(endMonth)-1,parseInt(endDay),endHour,endMin,0,0);
                    
					
					jfcalplugin.addAgendaItem(
						"#mycal",
						what,
						startDateObj,
						endDateObj,
						false,
						{
							fname: "Santa",
							lname: "Claus",
							leadReindeer: "Rudolph",
							myDate: new Date(),
							myNum: 42
						},
						{
							backgroundColor: $("#colorBackground").val(),
							foregroundColor: $("#colorForeground").val()
						}
					);

					$(this).dialog('close');

				}
				
			},
			Cancel: function() {
				$(this).dialog('close');
			}
		},
		open: function(event, ui){
			// initialize start date picker
			$("#startDate").datepicker({
				showOtherMonths: true,
				selectOtherMonths: true,
				changeMonth: true,
				changeYear: true,
				showButtonPanel: true,
				dateFormat: 'yy-mm-dd'
			});
			// initialize end date picker
			$("#endDate").datepicker({
				showOtherMonths: true,
				selectOtherMonths: true,
				changeMonth: true,
				changeYear: true,
				showButtonPanel: true,
				dateFormat: 'yy-mm-dd'
			});
			// initialize with the date that was clicked
			$("#startDate").val(clickDate);
			$("#endDate").val(clickDate);
			// initialize color pickers
			$("#colorSelectorBackground").ColorPicker({
				color: "#333333",
				onShow: function (colpkr) {
					$(colpkr).css("z-index","10000");
					$(colpkr).fadeIn(500);
					return false;
				},
				onHide: function (colpkr) {
					$(colpkr).fadeOut(500);
					return false;
				},
				onChange: function (hsb, hex, rgb) {
					$("#colorSelectorBackground div").css("backgroundColor", "#" + hex);
					$("#colorBackground").val("#" + hex);
				}
			});
			//$("#colorBackground").val("#1040b0");		
			$("#colorSelectorForeground").ColorPicker({
				color: "#ffffff",
				onShow: function (colpkr) {
					$(colpkr).css("z-index","10000");
					$(colpkr).fadeIn(500);
					return false;
				},
				onHide: function (colpkr) {
					$(colpkr).fadeOut(500);
					return false;
				},
				onChange: function (hsb, hex, rgb) {
					$("#colorSelectorForeground div").css("backgroundColor", "#" + hex);
					$("#colorForeground").val("#" + hex);
				}
			});
			//$("#colorForeground").val("#ffffff");				
			// put focus on first form input element
			$("#what").focus();
		},
		close: function() {
			// reset form elements when we close so they are fresh when the dialog is opened again.
			$("#startDate").datepicker("destroy");
			$("#endDate").datepicker("destroy");
			$("#startDate").val("");
			$("#endDate").val("");
			$("#startHour option:eq(0)").attr("selected", "selected");
			$("#startMin option:eq(0)").attr("selected", "selected");
			$("#startMeridiem option:eq(0)").attr("selected", "selected");
			$("#endHour option:eq(0)").attr("selected", "selected");
			$("#endMin option:eq(0)").attr("selected", "selected");
			$("#endMeridiem option:eq(0)").attr("selected", "selected");			
			$("#what").val("");
			//$("#colorBackground").val("#1040b0");
			//$("#colorForeground").val("#ffffff");
		}
	});
	
	/**
	 * Initialize display event form.
	 */
	$("#display-event-form").dialog({
		autoOpen: false,
		height: 400,
		width: 400,
		modal: true,
		buttons: {		
			Cancel: function() {
				$(this).dialog('close');
			}/*,
			'Detalle': function() {
				alert("Make your own edit screen or dialog!");
				
			},
			'Delete': function() {
				if(confirm("¿Está seguro que desea eliminar este evento del calendario?")){
					if(clickAgendaItem != null){
						jfcalplugin.deleteAgendaItemById("#mycal",clickAgendaItem.agendaId);						
					}
					$(this).dialog('close');
				}
			}	*/
		
		},
		open: function(event, ui){
			if(clickAgendaItem != null){
				var title = clickAgendaItem.title;
				var startDate = clickAgendaItem.startDate;
				var endDate = clickAgendaItem.endDate;
				var allDay = clickAgendaItem.allDay;
				var data = clickAgendaItem.data;
				// in our example add agenda modal form we put some fake data in the agenda data. we can retrieve it here.
				$("#display-event-form").append(
					"<br><b> <font size='3' face='Georgia, Arial' color='maroon'>" + title+ "</font></b><br><br>"		
				);				
				if(allDay){
					$("#display-event-form").append(
						"(Evento todo el Día)<br><br>"				
					);				
				}else{
					/**********modificado por jorge**********/
					
					var start_date = startDate.getDate();
					var start_month = startDate.getMonth() + 1; //Months are zero based
					var start_year = startDate.getFullYear();
					var start_hour = startDate.getHours();
					var start_min = startDate.getMinutes();
					
					if(start_month < 10)
						start_month = "0" + start_month.toString();
						
					if(start_date < 10)
						start_date = "0" + start_date.toString();		
					
					if(start_hour < 10)
						start_hour = "0" + start_hour.toString();
					
					if(start_min < 10)
						start_min = "0" + start_min.toString();
						
					
					var start = start_year  + "-" + start_month + "-" + start_date +":" + start_hour + ":" + start_min;
					
					
					
					
					var end_date = endDate.getDate();
					var end_month = endDate.getMonth() + 1; //Months are zero based
					var end_year = endDate.getFullYear();
					var end_hour = endDate.getHours();
					var end_min = endDate.getMinutes();
						
						

					if(end_month < 10)
						end_month = "0" + end_month.toString();
						
					if(end_date < 10)
						end_date = "0" + end_date.toString();		
					
					if(end_hour < 10)
						end_hour = "0" + end_hour.toString();
					
					if(end_min < 10)
						end_min = "0" + end_min.toString();
						
					
					var end = end_year  + "-" + end_month + "-" + end_date +":" + end_hour + ":" + end_min;
					
					
					
					
					
					
					$("#display-event-form").append(
						"<b><font size='3' face='Georgia, Arial' color='maroon'>Inicio:</font></b> <font size='3' face='Georgia, Arial' color='maroon'>" + start + "</font><br>" +
						"<b><font size='3' face='Georgia, Arial' color='maroon'>Fin:</font></b> <font size='3' face='Georgia, Arial' color='maroon'>" + end +   "</font><br><br>"				
					);				
				}
				for (var propertyName in data) {
					$("#display-event-form").append("<b><font size='3' face='Georgia, Arial' color='maroon'>" + propertyName + ":</font></b> <font size='3' face='Georgia, Arial' color='maroon'>" + data[propertyName] + "</font><br>");
				}			
			}		
		},
		close: function() {
			// clear agenda data
			$("#display-event-form").html("");
		}
	});	 

	/**
	 * Initialize our tabs
	 */
	$("#tabs").tabs({
		/*
		 * Our calendar is initialized in a closed tab so we need to resize it when the example tab opens.
		 */
		show: function(event, ui){
			if(ui.index == 1){
				jfcalplugin.doResize("#mycal");
			}
		}	
	});
	
});




function populateCalendar(year,month,day,hourStartTimeHour,hourEndTime,asunto,firstName,manager,idventa)
{

	
var startDateObj = new Date(parseInt(year),parseInt(month),parseInt(day),hourStartTimeHour,0,0,0);
var horainicial=startDateObj.getHours(); //horainicial como parametro
var horafinal;
var what = jQuery.trim($("#what").val());
what =  asunto  ;
var hex ="FF0000";

var endDateObj;
if( (hourStartTimeHour + hourEndTime) > 24) 
{
   horafinal =(hourStartTimeHour + hourEndTime) - 24;
   endDateObj = new Date(parseInt(year),parseInt(month),parseInt(day + 1),horafinal,0,0,0);
}
else
{
   horafinal =(hourStartTimeHour + hourEndTime);
   endDateObj = new Date(parseInt(year),parseInt(month),parseInt(day),horafinal,0,0,0);
 }	



var rpt = "<a href='./rpt.do?process=sales&p3="+idventa+"&target=''><font size='2' face='Georgia, Arial' color='maroon'>Ver</font></a>";


//setMonth si me pasa la fecha a posicionar el calendario
  /* if(dateToSearch != '')
   {
	 alert(dateToSearch);
     var start_date = startDateObj.getDate();
     var start_month = startDateObj.getMonth() + 1; //Months are zero based
     var start_year = startDateObj.getFullYear();


     $("#dateSelect").datepicker("setDate",start_year+"-"+(start_month)+"-"+start_date);

	 var selectedDate = $("#dateSelect").val();
	 var dtArray = selectedDate.split("-");
	 var year = dtArray[0];
	// jquery datepicker months start at 1 (1=January)		
	 var month = dtArray[1];
	// strip any preceeding 0's		
	 month = month.replace(/^[0]+/g,"")		
	 var day = dtArray[2];
	
     jfcalplugin.showMonth("#mycal",year,parseInt(month-1).toString());
   }*/
   
   //end setMonth



$("#colorSelectorBackground div").css("backgroundColor", "#" + hex);
$("#colorBackground").val("#" + hex);

// add new event to the calendar
jfcalplugin.addAgendaItem(
	"#mycal",
	what,
	startDateObj,
	endDateObj,
	false,
	{
		 Cliente : firstName,
		 Detalle: rpt,
		 Responsable:  manager,		
		"Id de Venta" : idventa  

	},
	{
		backgroundColor: $("#colorBackground").val(),
		foregroundColor: $("#colorForeground").val()
	}
);


}

function setDateMonth(year,month,day)
{
	var startDateObj = new Date(parseInt(year),parseInt(month),parseInt(day),0,0,0,0);
	
	
	//setMonth si me pasa la fecha a posicionar el calendario
	 
	 
     var start_date = startDateObj.getDate();
     var start_month = startDateObj.getMonth() + 1; //Months are zero based
     var start_year = startDateObj.getFullYear();


     $("#dateSelect").datepicker("setDate",start_year+"-"+(start_month)+"-"+start_date);

	 var selectedDate = $("#dateSelect").val();
	 var dtArray = selectedDate.split("-");
	 var year = dtArray[0];
	// jquery datepicker months start at 1 (1=January)		
	 var month = dtArray[1];
	// strip any preceeding 0's		
	 month = month.replace(/^[0]+/g,"")		
	 var day = dtArray[2];
	
     jfcalplugin.showMonth("#mycal",year,parseInt(month-1).toString());
	  
	   
	   //end setMonth
	
}


</script>





		
	<div id="tabs-2">

		<div id="example" style="margin: auto; width:80%;">
		
		<br>
		

		
		<br><br>

		<div id="toolbar" class="ui-widget-header ui-corner-all" style="padding:3px; vertical-align: middle; white-space:nowrap; overflow: hidden;">
			<button id="BtnPreviousMonth">Anterior</button>
			<button id="BtnNextMonth">Siguiente</button>
			&nbsp;&nbsp;&nbsp;
			Fecha: <input type="text" id="dateSelect" size="20"/>
			&nbsp;&nbsp;&nbsp;
			<!--  <button id="BtnDeleteAll">Delete All</button>
		
			<button id="test" onClick="populateCalendar(2012,1,14,20,6,'Boda');">Test</button>-->
		</div>

		<br>

		<!--
		You can use pixel widths or percentages. Calendar will auto resize all sub elements.
		Height will be calculated by aspect ratio. Basically all day cells will be as tall
		as they are wide.
		-->
		<div id="mycal"></div>

		</div>

		<!-- debugging-->
		<div id="calDebug"></div>

		<!-- Add event modal form -->
		<style type="text/css">
			//label, input.text, select { display:block; }
			fieldset { padding:0; border:0; margin-top:25px; }
			.ui-dialog .ui-state-error { padding: .3em; }
			.validateTips { border: 1px solid transparent; padding: 0.3em; }
		</style>
		  
		<div id="add-event-form" title="Add New Event">
			<p class="validateTips">All form fields are required.</p>
			<form>
			<fieldset>
				<label for="name">What?</label>
				<input type="text" name="what" id="what" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;"/>
				<table style="width:100%; padding:5px;">
					<tr>
						<td>
							<label>Start Date</label>
							<input type="text" name="startDate" id="startDate" value="" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;"/>				
						</td>
						<td>&nbsp;</td>
						<td>
							<label>Start Hour</label>
							<select id="startHour" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;">
								<option value="12" SELECTED>12</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
							</select>				
						<td>
						<td>
							<label>Start Minute</label>
							<select id="startMin" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;">
								<option value="00" SELECTED>00</option>
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
							</select>				
						<td>
						<td>
							<label>Start AM/PM</label>
							<select id="startMeridiem" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;">
								<option value="AM" SELECTED>AM</option>
								<option value="PM">PM</option>
							</select>				
						</td>
					</tr>
					<tr>
						<td>
							<label>End Date</label>
							<input type="text" name="endDate" id="endDate" value="" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;"/>				
						</td>
						<td>&nbsp;</td>
						<td>
							<label>End Hour</label>
							<select id="endHour" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;">
								<option value="12" SELECTED>12</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
							</select>				
						<td>
						<td>
							<label>End Minute</label>
							<select id="endMin" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;">
								<option value="00" SELECTED>00</option>
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
							</select>				
						<td>
						<td>
							<label>End AM/PM</label>
							<select id="endMeridiem" class="text ui-widget-content ui-corner-all" style="margin-bottom:12px; width:95%; padding: .4em;">
								<option value="AM" SELECTED>AM</option>
								<option value="PM">PM</option>
							</select>				
						</td>				
					</tr>			
				</table>
				<table>
					<tr>
						<td>
							<label>Background Color</label>
						</td>
						<td>
							<div id="colorSelectorBackground"><div style="background-color: #333333; width:30px; height:30px; border: 2px solid #000000;"></div></div>
							<input type="hidden" id="colorBackground" value="#333333">
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>
							<label>Text Color</label>
						</td>
						<td>
							<div id="colorSelectorForeground"><div style="background-color: #ffffff; width:30px; height:30px; border: 2px solid #000000;"></div></div>
							<input type="hidden" id="colorForeground" value="#ffffff">
						</td>						
					</tr>				
				</table>
			</fieldset>
			</form>
		</div>
	 
		
		<div id="display-event-form" title="Asunto de Agenda">
			
		</div>		

		<p>&nbsp;</p>

	</div><!-- end example tab -->



<p>&nbsp;</p>


</body>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
function pageload()
{

<% 
	HttpSession ses = request.getSession();
	List listCalendar = (List)ses.getAttribute("listCalendar");
	GregorianCalendar c = new GregorianCalendar();
	//TODO:recibir la fecha en la que se posicionara el calendario desde availabilitySaloonEventType.jsp la cual se pondra en sesion en AvailabilitySaloonEventTypeAction
	GregorianCalendar setDate = new GregorianCalendar();
	Date date_event = (Date)ses.getAttribute("date_event");
	
	
	
	//BeanQuote beanQuote = null;
	BeanSale beanSale = null;
	Iterator iter = listCalendar.iterator();
		
	while ( iter.hasNext() )
	  {
		//beanQuote = (BeanQuote) iter.next();
		beanSale = (BeanSale) iter.next();

		c.setTime( beanSale.getDate_event() );

		out.write("populateCalendar(" 
				  + c.get( Calendar.YEAR) 
				  + ", " + ( c.get( Calendar.MONTH ) + 0 ) 
				  + ", " + c.get( Calendar.DATE) 
				  + ", " + beanSale.getId_hour().getId()
				  + ", " + beanSale.getHourQuantity() 
				  + ", '" + beanSale.getList_description() + "'"
				  + ", '" + beanSale.getId_client().getName() + "'"
				  + ", '" + beanSale.getId_user().getId_company().getFull_name() + "'"
				  + ", '" + beanSale.getId() + "'"
				  + ");"
				 );
		out.write("\n");
	  }
	
	if(date_event != null)
	{   
		setDate.setTime( date_event );
		out.write("setDateMonth(" 
				  + setDate.get( Calendar.YEAR) 
				  + ", " + ( setDate.get( Calendar.MONTH )  ) 
				  + ", " + setDate.get( Calendar.DATE) 			 
				  + ");"
				 );
		out.write("\n");
	}
	%>
  
}
  
</SCRIPT>

</html:html>

