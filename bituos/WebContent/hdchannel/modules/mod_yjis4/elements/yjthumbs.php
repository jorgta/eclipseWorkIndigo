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

class JFormFieldYjthumbs extends JFormField
{
	/**
	* Element name
	*
	* @access	protected
	* @var		string
	*/
	
	var	$type = 'Yjthumbs';
public function getInput()
	{

		$html = '
		<div class="make_thumbs">
		<div class="thumbresponse" id="response_'.$this->fieldname.'"></div>
		<div class="make_thumb_holder">
			<div class="clearbuttons"></div>
			<div class="button2-left">
				<div class="blank">
					<a class="create_thumb btn" id="link_'.$this->fieldname.'" title="'.JText::_('Create Thumbnail').'"  href="javascript:;">'.JText::_('Create Thumbnail').'</a>
				</div>
			</div>
			<div class="clearbuttons"></div>
			<div class="button2-left">
				<div class="blank">
					<a class="delete_thumb btn btn-danger" id="delete_'.$this->fieldname.'" title="'.JText::_('Delete Thumbnail').'"  href="javascript:;">'.JText::_('Delete Thumbnail').'</a>
				</div>
			</div>
			
		</div>
		';
		$value = htmlspecialchars(html_entity_decode($this->value, ENT_QUOTES), ENT_QUOTES);
		
       	$html .= '<div class="YJSG_sbox '.$this->element['name'].'"><input type="text" class="thumpaths" name="'.$this->name.'" id="input_'.$this->fieldname.'" value="'.$this->value.'" /></div>';
		$html .= '<div class="thumb_holder" id="thumb_'.$this->fieldname.'"></div>';
		$html .='</div>';
		return $html;		  

	}
/*	public function getLabel() {
		return false;
	}*/
}
