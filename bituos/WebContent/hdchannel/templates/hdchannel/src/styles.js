/*======================================================================*\
|| #################################################################### ||
|| # Package - Joomla Template based on YJSimpleGrid Framework          ||
|| # Copyright (C) 2010  Youjoomla.com. All Rights Reserved.            ||
|| # Authors - Dragan Todorovic and Constantin Boiangiu                 ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/
window.addEvent("load", function() {
	/*Socials*/
	 $$('.hdcsim a').each(function (el) {
		el.set('tween', {
			duration: 300,
			link: 'cancel'
		});		 
        el.addEvents({
            mouseenter: function () {
                el.tween('background-position', '0 -52px');
            },
            mouseleave: function () {
                el.tween('background-position', '0 0px');
            }
        });
	 });
	/*Login module*/
	$$('.poping_links,.hdclm #login-form').each(function (el) {
		//alert(width);
		var fx = new Fx.Morph(el, {
		  duration: 100,
		  'link': 'cancel'
		});
		el.addEvents({
		  mouseenter: function () {
			fx.start({
			left:2,
			top:-2
			});
		  },
		  mouseleave: function () {
			fx.start({
			left:0,
			top:0
	
			});
		   }
		});
		});
	/*Hover effect*/
	$$('span.yj_effect').each(function (el) {
		var image			= el.getElement('img');
		var preview 		= el.getElement('.yj_preview');
		var more	 		= el.getElement('.yj_more');
		var small_width     = preview.getSize().x;
		var small_height    = preview.getSize().y;	
		var fx = new Fx.Morph(image, {
		  duration: 200,
		  'link': 'cancel'
		});
		var fx2 = new Fx.Morph(preview, {
		  duration: 250,
		  transition:Fx.Transitions.Bounce.easeOut,
		  'link': 'cancel'
		});
		var fx3 = new Fx.Morph(more, {
		  duration: 250,
		  transition:Fx.Transitions.Bounce.easeOut,
		  'link': 'cancel'
		});
		el.addEvents({
		  mouseenter: function () {
			fx.start({
				'opacity':[0.4],
			});
			fx2.start({
				left:0,
				top:0
			});
			fx3.start({
				right:0,
				bottom:0
			});
		  },
		  mouseleave: function () {
			fx.start({
				'opacity':[1],
			});
			fx2.start({
				left:-30,
				top:-30
			});
			fx3.start({
				right:-30,
				bottom:-30
			});
		   }
		});
	});
	/*K2 content*/
	$$('div.subCategory').each(function (el) {
		var image			= el.getElement('img');
		var fx = new Fx.Morph(image, {
		  duration: 200,
		  'link': 'cancel'
		});
		el.addEvents({
		  mouseenter: function () {
			fx.start({
				'opacity':[0.5],
			});
		  },
		  mouseleave: function () {
			fx.start({
				'opacity':[1],
			});
		   }
		});
	});
});

jQuery.noConflict();
(function ($) {
    $(function () {
			$(window).load(function () {
				// set yjme element height by highest to make sure floats are not broken
				var yjmeHeight = 0;
				var yjme;
				$(".hdcmt .yjme_item", yjme).each(function () {
					$this = $(this);
					if ($this.outerHeight() > yjmeHeight) {
						yjme = this;
						yjmeHeight = $this.outerHeight();
					}
				});
				$('.hdcmt .yjme_item').outerHeight(yjmeHeight);
			});
		})
	})
(jQuery);