<?php
/*======================================================================*\
|| #################################################################### ||
|| # Youjoomla.com - YJ- Licence Number 84030-HM510437
|| # Licensed to - Raphael Sabin
|| # ---------------------------------------------------------------- # ||
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

define( '_JEXEC', 1 ); 
//header("Content-Type: application/json");

$get_file_info  = pathinfo(__FILE__);
$jpath = preg_replace('/(\btemplates\b|\bmodules\b|\bcomponents\b|\bplugins\b)(.*)/','',$get_file_info['dirname']);
define('JPATH_BASE',rtrim($jpath,DIRECTORY_SEPARATOR));

require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'defines.php' );
require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'framework.php' );

jimport('joomla.filesystem.file');
jimport('joomla.filesystem.folder');
jimport('joomla.plugin.helper');
jimport('joomla.session.session');

$mainframe = JFactory::getApplication('administrator');
$mainframe->initialise();

$TwitterCache = JPATH_BASE . DIRECTORY_SEPARATOR.'modules'.DIRECTORY_SEPARATOR.'mod_yj_twitter'.DIRECTORY_SEPARATOR.'lib'.DIRECTORY_SEPARATOR.'cache.php';

if(JFile::exists($TwitterCache)){
	JFile::delete($TwitterCache);
}