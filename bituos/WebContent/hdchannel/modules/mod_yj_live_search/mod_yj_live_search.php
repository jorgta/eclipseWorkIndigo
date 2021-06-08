<?php
/*======================================================================*\
|| #################################################################### ||
|| # Package - YJ Live Search 2.0								        ||
|| # Copyright (C) 2012  Youjoomla.com. All Rights Reserved.            ||
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
	defined('_JEXEC') or die('Restricted access');
	require_once( dirname(__FILE__).'/helper.php' );
	$live_status			= $params->get( 'live_status', '1' );
	$search_button			= $params->get( 'search_button', '1' );
	$default_input			= $params->get( 'default_input', '' );
	$input_width			= $params->get( 'input_width', '210' );	
	$moduleclass_sfx		= $params->get( 'moduleclass_sfx', '' );
	$is_copy			 	= $params->get( 'is_copy');	
	$set_Itemid				= intval($params->get('set_itemid', 0));
	$get_yjseach 			= modYJLiveSearchHelper::modYJLiveSearchItems($params);
	$mitemid 				= $set_Itemid > 0 ? $set_Itemid : JRequest::getInt('Itemid');
	require(JModuleHelper::getLayoutPath('mod_yj_live_search'));
?>