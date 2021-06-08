<?php
/*======================================================================*\
|| #################################################################### ||
|| # Package - YJ Image Slider 4.0  							        ||
|| # Copyright (C) 2010  Youjoomla.com. All Rights Reserved.            ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/
	defined('_JEXEC') or die('Direct Access to this location is not allowed.');
	/**
	 * Parameters
	 */
	global $is_yjis_copy;
	$is_yjis_copy				= $module->id;
	$who 						= strtolower($_SERVER['HTTP_USER_AGENT']); 
	$yj_mod_name 				= basename(dirname(__FILE__));
	$document					= JFactory::getDocument();
	$class_fx 					= $params->get('moduleclass_sfx');
	$swidth 					= $params->get('swidth');
	$sheight 					= $params->get('sheight');
	$sorient 					= $params->get('sorient');
	$start_slide 				= $params->get('start_slide');
	$type_slider 				= $params->get('type_slider');
	$stime 						= $params->get('stime');
	$sduration 					= $params->get('sduration');
	$smenu 						= $params->get('smenu');
	$pagination 				= $params->get('pagination');
	$tooltips 					= $params->get('tooltips');
	$thumbs_tooltips 			= $params->get('thumbs_tooltips');
	$navs_tooltips 				= $params->get('navs_tooltips');
	$is_copy 					= $is_yjis_copy;
	$order 						= $params->get('order');
	$yjspacer 					= $params->get('yjspacer');
	$pagination_counter 		= $params->get('pagination_counter');  
	$thumb_pagination 			= $params->get('thumb_pagination');
	$thumb_width				= $params->get('thumb_width');
	$thumb_height				= $params->get('thumb_height');
	$visibleItems				= $params->get('visibleItems');
	$hide_all_intros			= $params->get('hide_all_intros');
	$hide_all_descriptions		= $params->get('hide_all_descriptions');
	$module_template			= $params->get('module_template','Default');
	$thumb_arrows				= $params->get('thumb_arrows');
	$beltPosition				= $params->get('beltPosition');
	$mbox_theme					= $params->get('mbox_theme');
	$beltOrientation			= $params->get('beltOrientation');
	
	if($beltOrientation == 0){
		$beltDir =' horizontal';
	}else{
		$beltDir =' vertical';
	}
	
	jimport('joomla.filesystem.file');
	require_once (dirname(__FILE__).DIRECTORY_SEPARATOR.'helper.php');
	$slides 					= YJisHelp::YJisItems($params);	
	require(JModuleHelper::getLayoutPath(''.$yj_mod_name.'',''.$module_template.'/default'));