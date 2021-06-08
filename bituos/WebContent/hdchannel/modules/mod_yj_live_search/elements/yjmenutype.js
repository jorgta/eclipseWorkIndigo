/*======================================================================*\
|| #################################################################### ||
|| # Package - YJ Live Search 2.0									    ||
|| # Copyright (C) 2010  Youjoomla.com. All Rights Reserved.            ||
|| # Authors - Dragan Todorovic								            ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/

window.addEvent('domready', function() {
	
	
		var hide = $$('#jform_params_index_items,#jform_params_index_items-lbl,#jform_params_Search_K2_cat-lbl,#jform_params_Search_K2_cat,#jform_params_mysql_operator-lbl,#jform_params_mysql_operator,#jform_params_Search_K2_tags-lbl,#jform_params_Search_K2_tags,.imagesettings,#jform_params_show_image-lbl,#jform_params_show_image');
	
		var hide2 = $$('#jform_params_k2image_size-lbl,#jform_params_k2image_size,#jform_params_image_width-lbl,#jform_params_image_width');
	
	
	
		if( $('jform_params_feedtype0').checked ){
			hide.setStyle('display','none');
		}

		$('jform_params_feedtype0').addEvent('click', function(){
			if( this.checked ){
			hide.setStyle('display','none');
			}
		});
		
		$('jform_params_feedtype1').addEvent('click', function(){
			if( this.checked ){
			hide.setStyle('display','block');
			}
		});
		
		
		
		
		
		if( $('jform_params_show_image0').checked ){
			hide2.setStyle('display','none');
		}	
		
		
		$('jform_params_show_image0').addEvent('click', function(){
			if( this.checked ){
			hide2.setStyle('display','none');
			}
		});
		
		$('jform_params_show_image1').addEvent('click', function(){
			if( this.checked ){
			hide2.setStyle('display','block');
			}
		});		
		
		
		
});		
