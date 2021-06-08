$(document).ready(function(){
	
	//Sidebar Accordion Menu:
		
		$("#main-nav li ul").hide(); // Hide all sub menus
		$("#main-nav li a.current").parent().find("ul").slideToggle("slow"); // Slide down the current menu item's sub menu
		


					
		$("#main-nav li a.nav-top-item").click( // When a top menu item is clicked...
			function () {
				
				$(this).parent().siblings().find("ul").slideUp("normal"); // Slide up all sub menus except the one clicked
				$(this).next().slideToggle("normal"); // Slide down the clicked sub menu
				
				//3:39 PM 13-07-2012 por jorge
				$("#main-nav li a.current").removeClass("current");
				$(this).addClass("current");
				
						
				return false;
			}
		);
		
			
		//3:39 PM 13-07-2012 por jorge
		$("#main-nav ul li a").click( // When a top menu item is clicked...
			function () {
				
				
				$("#main-nav ul li a").removeClass("current");				
				$(this).addClass("current");
				return true;
			}
		);
		
		$("#main-nav li a.no-submenu").click( // When a menu item with no sub menu is clicked...
			function () {
				window.location.href=(this.href); // Just open the link instead of a sub menu
				return false;
			}
		); 

    // Sidebar Accordion Menu Hover Effect:
		
		$("#main-nav li .nav-top-item").hover(
			function () {
				$(this).stop().animate({ paddingRight: "25px" }, 200);
			}, 
			function () {
				$(this).stop().animate({ paddingRight: "15px" });
			}
		);

    //Minimize Content Box
		
		$(".content-box-header h3").css({ "cursor":"s-resize" }); // Give the h3 in Content Box Header a different cursor
		$(".closed-box .content-box-content").hide(); // Hide the content of the header if it has the class "closed"
		$(".closed-box .content-box-tabs").hide(); // Hide the tabs in the header if it has the class "closed"
		
		$(".content-box-header h3").click( // When the h3 is clicked...
			function () {
			  $(this).parent().next().toggle(); // Toggle the Content Box
			  $(this).parent().parent().toggleClass("closed-box"); // Toggle the class "closed-box" on the content box
			  $(this).parent().find(".content-box-tabs").toggle(); // Toggle the tabs
			}
		);

    // Content box tabs:
		
		$('.content-box .content-box-content div.tab-content').hide(); // Hide the content divs
		$('ul.content-box-tabs li a.default-tab').addClass('current'); // Add the class "current" to the default tab
		$('.content-box-content div.default-tab').show(); // Show the div with class "default-tab"
		
		//1:27 PM 19-07-2012  jorge
		$('.content-box h3').text($('ul.content-box-tabs li a.default-tab').text());
		
		$('.content-box ul.content-box-tabs li a').click( // When a tab is clicked...
			function() { 
				$(this).parent().siblings().find("a").removeClass('current'); // Remove "current" class from all tabs
				$(this).addClass('current'); // Add class "current" to clicked tab
				var currentTab = $(this).attr('href'); // Set variable "currentTab" to the value of href of clicked tab
				$(currentTab).siblings().hide(); // Hide all content divs
				$(currentTab).show(); // Show the content div with the id equal to the id of clicked tab
				
				
				//11:23 AM 17-07-2012 por jorge
				if ($(this).attr('id') == "single")
				{				
				  $('ul.shortcut-buttons-set').removeClass('hidden');			  
				}
				else
				{				 
				  $('ul.shortcut-buttons-set').addClass('hidden');					  		  
				}
				
				$('.content-box h3').text($(this).text());
				  
				$('.content-box-content div.notification').text('');
				$('.content-box-content div.notification').removeClass();	
				$('.content-box-content div.notification').addClass('notification  png_bg');
				
				return false; 
			}
		);
		
		//12:18 PM 18-07-2012 por jorge 
	  if($('#single').attr("class") == "default-tab current")
	    $('ul.shortcut-buttons-set').removeClass('hidden');
	  else
	    $('ul.shortcut-buttons-set').addClass('hidden');	
	    
       
    //Close button:
		
		$(".close").click(
			function () {
				$(this).parent().fadeTo(400, 0, function () { // Links with the class "close" will close parent
					$(this).slideUp(400);
				});
				return false;
			}
		);

    // Alternating table rows:
		
		$('tbody tr:even').addClass("alt-row"); // Add class "alt-row" to even table rows
	
	
	var scrollPosition = 0;
	//3:39 PM 13-07-2012 por jorge
	// Alternating pagination:            
	$(".pagination a.number").click(
			
			function () {
			 // scrollPosition = $(window).scrollTop();
			  $(".pagination a.number").removeClass("current");				
			  $(this).addClass("current");	
			  
			  //alert($(this).attr("onclick"));
              
			return false;
			}
			
		);
		
    
    //12:10 PM 19-07-2012  jorge
    
    $("table.mytabla span.input-notification").each(function() {
				     if ($(this).text() != '')
			   	     { 			   	      
			   	       $(this).addClass('error');
			   	     }
			   	     else
			   	     {
			   	       $(this).removeClass("error");			   	     
			   	     }
		   	    }
     );
    
     


   
    // Check all checkboxes when the one in a table head is checked:
		
		$('.check-all').click(
			function(){
				$(this).parent().parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));   
			}
		);

    // Initialise Facebox Modal window:
		
		$('a[rel*=modal]').facebox(); // Applies modal window to any link with attribute rel="modal"

    // Initialise jQuery WYSIWYG:
		
		$(".wysiwyg").wysiwyg(); // Applies WYSIWYG editor to any textarea with the class "wysiwyg"
		
		
		
		

});
  
  
  