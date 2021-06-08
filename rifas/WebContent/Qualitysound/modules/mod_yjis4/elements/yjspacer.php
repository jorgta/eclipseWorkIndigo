<?php
/**
 * @package		Youjoomla Extend Elements
 * @author		Youjoomla.com
 * @website     Youjoomla.com 
 * @copyright	Copyright (c) 2007 - 2010 Youjoomla.com.
 * @license   PHP files are GNU/GPL V2. CSS / JS / IMAGES are Copyrighted Commercial
 */

// Check to ensure this file is within the rest of the framework
defined('JPATH_BASE') or die();

/**
 * Renders a spacer element
 *
 * @package 	Joomla.Framework
 * @subpackage		Parameter
 * @since		1.5
 */

class JFormFieldYjSpacer extends JFormField
{
	/**
	* Element name
	*
	* @access	protected
	* @var		string
	*/
	
	var	$type = 'YjSpacer';

public function getInput()
	//function fetchElement($name, $value, &$node, $control_name)
	{

  
    	$value = $this->value;
		$options = array ();
		if ($value) {
		$document =& JFactory::getDocument();
			return '<div class="yjspacer_holder"><div class="yjspacer">'.JText::_($value).'</div></div>';
		} else {
			return '<hr />';
		}
	}
	
	
	public function getLabel() {
		return false;
	}
}
