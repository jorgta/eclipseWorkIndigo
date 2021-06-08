/*
---

name: Youjoomla Mouseover Effect

description: allows mouseover/out effects on image stories

author: Youjoomla

license: This file is NOT licensed under any BSD or MIT style license.
         Unauthorised use, editing, distribution is not allowed.

copyright: Youjoomla LLC 2012

version: 1.0

requires:
  - Core/String
  - Core/Fx
  - Core/Event
  - Core/Element
  - Core/Array
  - Core/Class
  - ScaleCSS3

provides: MooverEffect

...
*/
window.addEvent("domready", function() {
	// add tooltip to multimediabox and tags
	$$('.yjmboxes').each(function (el) {
			el.addClass('addtips');
			el.setAttribute('data-placement','top')
	});
	
	$$('.yj_tagcloud a').each(function (el) {
			el.addClass('addtips');
			el.setAttribute('data-placement','left')
	});	
	
$$('.issocial').each(function (el) {
    var fx = new Fx.Morph(el, {
      duration: 200,
      'link': 'cancel',
    });
    el.addEvents({
      mouseenter: function () {
        fx.start({
		  'margin-right':[20]
        });
      },
      mouseleave: function () {
        fx.start({
		  'margin-right':[10]
        });
      }
    });
  });
	
});
jQuery.noConflict();
(function($) {
  $(function() {
     
	 
	 
	 if($.browser.msie && $.browser.version == 8){
		 
		 var thisIsIe8 = true;
		 
	 }else{
		 
		 thisIsIe8 = false;
	 }
	 

	 if ( !thisIsIe8 ) {
		 
				$(window).load(function(){
						
						// Fade in images so there isn't a color "pop" document load and then on window load
						$("img.yjgrayscale").animate({opacity:1},1500);
						
						// clone image
						$('img.yjgrayscale').each(function(){
							var el = $(this);
							
							
							el.css({"position":"relative"}).wrap("<div class='img_wrapper' style='display:block'>").clone().addClass('img_yjgrayscale').css({"position":"absolute","z-index":"998","opacity":"0"}).insertBefore(el).queue(function(){
								var el = $(this);
								var getParentEl = el.parent().parent();
								el.parent().css({"width":getParentEl.width(),"height":getParentEl.height()});
								el.dequeue();
							});
							this.src = yjgrayscale(this.src);
						});
						
						// Fade image 
						$('img.yjgrayscale').mouseover(function(){
							$(this).parent().find('img:first').stop().animate({opacity:1}, 500);
						})
						$('.img_yjgrayscale').mouseout(function(){
							$(this).stop().animate({opacity:0}, 500);
						});		
					});
					
					// yjgrayscale w canvas method
					function yjgrayscale(src){
						var canvas = document.createElement('canvas');
						var ctx = canvas.getContext('2d');
						var imgObj = new Image();
						imgObj.src = src;
						canvas.width = imgObj.width;
						canvas.height = imgObj.height; 
						ctx.drawImage(imgObj, 0, 0); 
						var imgPixels = ctx.getImageData(0, 0, canvas.width, canvas.height);
						for(var y = 0; y < imgPixels.height; y++){
							for(var x = 0; x < imgPixels.width; x++){
								var i = (y * 4) * imgPixels.width + x * 4;
								var avg = (imgPixels.data[i] + imgPixels.data[i + 1] + imgPixels.data[i + 2]) / 3;
								imgPixels.data[i] = avg; 
								imgPixels.data[i + 1] = avg; 
								imgPixels.data[i + 2] = avg;
							}
						}
						ctx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
						return canvas.toDataURL();
					}	
					
			$(function() {
				
				var $container	= $('.yjme_holder.qspopout'),
					$articles	= $container.find('.yjme_item'),
					timeout;
				
				$articles.on( 'mouseenter', function( event ) {
						
					var $article	= $(this);
					$articles.stop().animate({opacity:1,marginTop:0}, 500);
					clearTimeout( timeout );
					timeout = setTimeout( function() {
						
						if( $article.hasClass('active') ) return false;
						
						$articles.not( $article.removeClass('blur').addClass('active') )
								 
								 .removeClass('active')
								 .stop().animate({
									 opacity:0.2,
									marginTop:20
									 
									 }, 500)
								 .addClass('blur');
						
					}, 65 );
					
				});
				
				$container.on( 'mouseleave', function( event ) {
					
					clearTimeout( timeout );
					$articles.removeClass('active blur');
					$articles.stop().animate({opacity:1,marginTop:0}, 500);
					
				});
			
			});
					
	 }
	 
  });
  
})(jQuery);