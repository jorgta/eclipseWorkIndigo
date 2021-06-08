<?php
/*======================================================================*\
|| #################################################################### ||
|| # Youjoomla.com - YJ- Licence Number 84030-HM510437
|| # Licensed to - Raphael Sabin
|| # ---------------------------------------------------------------- # ||
|| # Copyright (C) since 2007 Youjoomla.com. All Rights Reserved.       ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/

// no direct access
defined('_JEXEC') or die('Restricted access');

JHtml::_('behavior.framework',true);
require_once (JPATH_SITE.DIRECTORY_SEPARATOR.'components'.DIRECTORY_SEPARATOR.'com_content'.DIRECTORY_SEPARATOR.'helpers'.DIRECTORY_SEPARATOR.'route.php');

class modYouyorkSLiderhHelper
{
	function getYouyorkSliderItems($params)
	{
		$is_copy					= $params->get   ('is_copy');
		 $slider_width           	= $params->get   ('slider_width');
		 $visible_modules       	= $params->get   ('visible_modules','2');
		 $autoslide    			 	= $params->get   ('autoslide');
		 $show_bottom_nav		 	= $params->get   ('show_bottom_nav');
		 $items_width 				=  number_format(($slider_width/$visible_modules),0, '.', '');
		 $effectDuration			= $params->get   ('effectDuration');
		 
		 if($show_bottom_nav == 1){
			 $add_nav =",
					  'screenNavsContainer':'yy_bottom-nav". $is_copy."',
					  'screenNavsElem':'a.yy_nav'";
		 }else{
			 $add_nav ="";
		 }
			 

		$document = JFactory::getDocument();

		$document->addStyleSheet(JURI::base() . 'modules/mod_youyork_slider/css/stylesheet.css');
		$document->addScript(JURI::base() . 'modules/mod_youyork_slider/src/youyork_slider12.js');
		
	 
		$document->addScriptDeclaration("
		  window.addEvent('load', function(){
			  new YouYorkModuleSlider({
				  container : 'yy_container". $is_copy."', 
				  items :'.yy_slideitems',
				  itemWidth : ".$items_width.",
				  visibleItems: ".$visible_modules.",
				  effectDuration : ". $effectDuration.",
				  autoSlide : ". $autoslide .",
				  mouseEventSlide: ". $autoslide .",
				  navigation: {
					  'forward':'linkForward', 
					  'back':'linkBackward'".$add_nav."
				  }
			  });
		  });
		");

	}
}
?>