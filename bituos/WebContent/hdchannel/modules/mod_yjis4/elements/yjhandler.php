<?php
/**
 * @package		Youjoomla Extend Elements
 * @author		Youjoomla.com
 * @website     Youjoomla.com 
 * @copyright	Copyright (c) 2007 - 2010 Youjoomla.com.
 * @license   PHP files are GNU/GPL V2. CSS / JS / IMAGES are Copyrighted Commercial
 */
//<field name="handler" type="yjhandler"/>   add once in xml to load custom codes
// Check to ensure this file is within the rest of the framework
defined('JPATH_BASE') or die();
/**
 * Renders a spacer element
 *
 * @package 	Joomla.Framework
 * @subpackage		Parameter
 * @since		1.5
 */

class JFormFieldYjHandler extends JFormField
{
	/**
	* Element name
	*
	* @access	protected
	* @var		string
	*/
	
	var	$type = 'YjHandler';
public function getInput()
	{
		jimport('joomla.filesystem.file');
		jimport('joomla.filesystem.folder');
		$assets_path = str_replace(JPATH_ROOT,rtrim(JURI::root(), "/"),dirname(__FILE__));
		$assets_path = str_replace('\\', '/',$assets_path);
		$document =& JFactory::getDocument();
		if(intval(JVERSION) >= 3 ){
			$document->addStyleSheet($assets_path.'/css/stylesheet30.css');
			$document->addScript($assets_path.'/src/script30.js');
		}else{
			$document->addStyleSheet($assets_path.'/css/stylesheet.css');
			$document->addScript($assets_path.'/src/script.js');
		}
		  $path 	= JPATH_ROOT.DIRECTORY_SEPARATOR."images".DIRECTORY_SEPARATOR."upload_slides"; 
		  $tpath 	= JPATH_ROOT.DIRECTORY_SEPARATOR."images".DIRECTORY_SEPARATOR."upload_slides".DIRECTORY_SEPARATOR."thumbnails";
		  
		  if(!JFolder::exists($path)){
		  	JFolder::create($path);
		  	$empty="";
 		  	JFile::write($path.DIRECTORY_SEPARATOR."index.html",$empty);
			JFactory::getApplication()->enqueueMessage('Folder upload_slides is created');
			
		  }
		  if(!JFolder::exists($tpath)){
		  	JFolder::create($tpath);
		  	$empty="";
			JFile::write($tpath.DIRECTORY_SEPARATOR."index.html",$empty);
			JFactory::getApplication()->enqueueMessage('Folder upload_slides/thumbnails is created');
			
		  }
		$html = '<input type="hidden" id="site_path" value="'.JURI::root().'" />';
		$html .= '<input type="hidden" id="elm_path" value="'.JURI::root().'modules/mod_yjis4/elements/yjsgupdate.php" />';
		echo $html;
	}
		public function getLabel() {
		return false;
	}
}
