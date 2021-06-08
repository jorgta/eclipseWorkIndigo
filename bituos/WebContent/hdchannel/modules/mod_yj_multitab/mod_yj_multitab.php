<?php
/*======================================================================*\
|| #################################################################### ||
|| # Youjoomla.com - YJ- Licence Number 84030-HM510437
|| # Licensed to - Raphael Sabin
|| # ---------------------------------------------------------------- # ||
|| # Copyright ©2006-2009 Youjoomla.com. All Rights Reserved.           ||
|| # This file may not be redistributed in whole or significant part. # ||
|| # ---------------- THIS IS NOT FREE SOFTWARE ---------------- #      ||
|| # http://www.youjoomla.com | http://www.youjoomla.com/license.html # ||
|| #################################################################### ||
\*======================================================================*/
  defined( '_JEXEC' ) or die( 'Direct Access to this location is not allowed.');

require_once (dirname(__FILE__).DIRECTORY_SEPARATOR.'helper.php');
	$module_template 		=  		$params->get('module_template','Default');
	$moduleclass_sfx		= $params->get('moduleclass_sfx',"_yjmutlitab");
	$module_pozi 			= $params->get('module_pozi','user1|user2');
	$module_title 			= $params->get('module_title','Title1|Title2');
	$ulis_width2 			= $params->get('ulis_width2','100px');
	$auto_width  			= $params->get('auto_width',1);
	$is_copy 				= $params->get('is_copy');
	$tab_height				= $params->get('tabs_height');
	$transtype 				= $params->get('transtype',1);
	$tab2mods 				= explode(",", $module_pozi);
	$titles 				= explode(",", $module_title);
	$ulis_width 			= 100/count ($titles);

	if ($auto_width == 1){
 		$li_width = number_format($ulis_width, 2, '.', ' ').'%';
 	}else{
		$li_width = $ulis_width2;
 	}
	if($tab_height !=''){
		$tab_height =' style="height:'.$tab_height.';"';
	}else{
		$tab_height='';
	}
modYJmultitabsHelper::getMultitabs($params);
require(JModuleHelper::getLayoutPath('mod_yj_multitab',''.$module_template.'/default'));
?>