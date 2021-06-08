<?php
/*======================================================================*\
|| #################################################################### ||
|| # Package - YJ Tag Cloud										        ||
|| # Copyright (C) 2013  Youjoomla.com. All Rights Reserved.            ||
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
	
	

	// Include the syndicate functions only once
	require_once( dirname(__FILE__).'/helper.php' );
	
	//get the module params
	$use_tag 			= $params->get( 'use_tag', '1' );	
	$own_tags 			= $params->get( 'own_tags', '' );	
	$tag_limit 			= $params->get( 'tag_limit', 50 );	
	$moduleclass_sfx 	= $params->get('moduleclass_sfx', '');	
	$randomSize			= $params->get('randomSize');
	$chooseContent		= $params->get('chooseContent');
	$setItemid			= $params->get('setItemid');	

	$tags = modYJTagsHelper::getTags($use_tag, $own_tags, $tag_limit,$chooseContent);
	
	if(empty($setItemid)){
		$tagIemid = '';
	}else{
		$tagIemid = 'Itemid='.$setItemid.'&amp;';
	}

	require(JModuleHelper::getLayoutPath('mod_yj_tagcloud'));		
?>