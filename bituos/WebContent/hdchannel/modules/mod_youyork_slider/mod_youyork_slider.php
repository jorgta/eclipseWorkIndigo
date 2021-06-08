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
require_once (dirname(__FILE__).DIRECTORY_SEPARATOR.'helper.php');

$module_template 			= $params->get('module_template','Default');
$slider_width           	= $params->get('slider_width');
$slider_height          	= $params->get('slider_height');
$module_title 				= $params->get('module_title','Title1|Title2');
$titles 					= explode(",", $module_title);
$showtitle					= $params->get('showtitle',1);
$show_title   			 	= $params->get('show_title');
$module_pozi 				= $params->get('module_pozi','user1|user2');
$slide2mods 				= explode(",", $module_pozi);
$is_copy 					= $params->get('is_copy');
$visible_modules       	 	= $params->get('visible_modules','2');
$show_bottom_nav		 	= $params->get   ('show_bottom_nav');


$yy_slideitems_height 		= $slider_height -40;
$items_width 				=  number_format(($slider_width/$visible_modules),0, '.', '');


$youyork_slides 			= modYouyorkSLiderhHelper::getYouyorkSliderItems($params);
require(JModuleHelper::getLayoutPath('mod_youyork_slider',''.$module_template.'/default'));
?>