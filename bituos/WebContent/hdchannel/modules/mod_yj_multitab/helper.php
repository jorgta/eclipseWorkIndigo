<?php
/**
* @version		$Id: helper.php 15198 2010-03-05 09:06:05Z ian $
* @package		Joomla
* @copyright	Copyright (C) 2005 - 2010 Open Source Matters. All rights reserved.
* @license		GNU/GPL, see LICENSE.php
* Joomla! is free software. This version may have been modified pursuant
* to the GNU General Public License, and as distributed it includes or
* is derivative of works licensed under the GNU General Public License or
* other free or open source software licenses.
* See COPYRIGHT.php for copyright notices and details.
*/

// no direct access
defined('_JEXEC') or die('Restricted access');
$document = JFactory::getDocument();
JHtml::_('behavior.framework',true); 
require_once (JPATH_SITE.DIRECTORY_SEPARATOR.'components'.DIRECTORY_SEPARATOR.'com_content'.DIRECTORY_SEPARATOR.'helpers'.DIRECTORY_SEPARATOR.'route.php');

class modYJmultitabsHelper
{
	static function getMultitabs($params)
	{
	$moduleclass_sfx		= $params->get('moduleclass_sfx',"_yjmutlitab");
	$module_pozi 			= $params->get('module_pozi','user1|user2');
	$module_title 			= $params->get('module_title','Title1|Title2');
	$ulis_width2 			= $params->get('ulis_width2','100px');
	$auto_width  			= $params->get('auto_width',1);
	$is_copy 				= $params->get('is_copy');
	$transtype 				= $params->get('transtype',1);
	$tab2mods 				= explode(",", $module_pozi);
	$titles 				= explode(",", $module_title);
	$ulis_width 			= 100/count ($titles);

	if ($auto_width == 1){
 		$li_width = $ulis_width.'%';
 	}else{
		$li_width = $ulis_width2;
 	}
	if($transtype == 1){
		$fade='fade';
	}else{
		$fade='';
	}

	$document = JFactory::getDocument();
	$document->addStyleSheet(JURI::base() . 'modules/mod_yj_multitab/css/mod_yj_multitab.css');  
	$document = JFactory::getDocument();
	$document->addScript(JURI::base() . 'modules/mod_yj_multitab/src/mod_yj_multitab'.$fade.'12.js');
	$document->addScriptDeclaration("
	window.addEvent('load', function(){
				new Tabs({
						 container:'tabs_container".$is_copy."',
						 tabsContainer:'tabs".$is_copy."',
						 classContent:'tab_content".$is_copy."'
						 
				});
			});
	");
	
	}
	
}
?>