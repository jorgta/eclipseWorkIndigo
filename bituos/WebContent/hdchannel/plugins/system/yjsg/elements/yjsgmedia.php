<?php
/**
 * @package     Joomla.Libraries
 * @subpackage  Form
 *
 * @copyright   Copyright (C) 2005 - 2013 Open Source Matters, Inc. All rights reserved.
 * @license     GNU General Public License version 2 or later; see LICENSE
 */

defined('JPATH_PLATFORM') or die;

/**
 * Form Field class for the Joomla CMS.
 * Provides a modal media selector including upload mechanism
 *
 * @package     Joomla.Libraries
 * @subpackage  Form
 * @since       1.6
 */
class JFormFieldYjsgmedia extends JFormField
{
	/**
	 * The form field type.
	 *
	 * @var    string
	 * @since  1.6
	 */
	protected $type = 'Yjsgmedia';

	/**
	 * The initialised state of the document object.
	 *
	 * @var    boolean
	 * @since  1.6
	 */
	protected static $initialised = false;

	/**
	 * The authorField.
	 *
	 * @var    string
	 * @since  3.2
	 */
	protected $authorField;

	/**
	 * The asset.
	 *
	 * @var    string
	 * @since  3.2
	 */
	protected $asset;

	/**
	 * The link.
	 *
	 * @var    string
	 * @since  3.2
	 */
	protected $link;

	/**
	 * The authorField.
	 *
	 * @var    string
	 * @since  3.2
	 */
	protected $preview;

	/**
	 * The preview.
	 *
	 * @var    string
	 * @since  3.2
	 */
	protected $directory;

	/**
	 * The previewWidth.
	 *
	 * @var    int
	 * @since  3.2
	 */
	protected $previewWidth;

	/**
	 * The previewHeight.
	 *
	 * @var    int
	 * @since  3.2
	 */
	protected $previewHeight;

	/**
	 * Method to get certain otherwise inaccessible properties from the form field object.
	 *
	 * @param   string  $name  The property name for which to the the value.
	 *
	 * @return  mixed  The property value or null.
	 *
	 * @since   3.2
	 */
	public function __get($name)
	{
		switch ($name)
		{
			case 'authorField':
			case 'asset':
			case 'link':
			case 'preview':
			case 'directory':
			case 'previewWidth':
			case 'previewHeight':
				return $this->$name;
		}

		return parent::__get($name);
	}

	/**
	 * Method to set certain otherwise inaccessible properties of the form field object.
	 *
	 * @param   string  $name   The property name for which to the the value.
	 * @param   mixed   $value  The value of the property.
	 *
	 * @return  void
	 *
	 * @since   3.2
	 */
	public function __set($name, $value)
	{
		switch ($name)
		{
			case 'authorField':
			case 'asset':
			case 'link':
			case 'preview':
			case 'directory':
				$this->$name = (string) $value;
				break;

			case 'previewWidth':
			case 'previewHeight':
				$this->$name = (int) $value;
				break;

			default:
				parent::__set($name, $value);
		}
	}

	/**
	 * Method to attach a JForm object to the field.
	 *
	 * @param   SimpleXMLElement  $element  The SimpleXMLElement object representing the <field /> tag for the form field object.
	 * @param   mixed             $value    The form field value to validate.
	 * @param   string            $group    The field name group control value. This acts as as an array container for the field.
	 *                                      For example if the field has name="foo" and the group value is set to "bar" then the
	 *                                      full field name would end up being "bar[foo]".
	 *
	 * @return  boolean  True on success.
	 *
	 * @see 	JFormField::setup()
	 * @since   3.2
	 */
	public function setup(SimpleXMLElement $element, $value, $group = null)
	{
		$result = parent::setup($element, $value, $group);

		if ($result == true)
		{
			$assetField = $this->element['asset_field'] ? (string) $this->element['asset_field'] : 'asset_id';

			$this->authorField   = $this->element['created_by_field'] ? (string) $this->element['created_by_field'] : 'created_by';
			$this->asset         = $this->form->getValue($assetField) ? $this->form->getValue($assetField) : (string) $this->element['asset_id'];
			$this->link          = (string) $this->element['link'];
			$this->preview       = (string) $this->element['preview'];
			$this->directory     = (string) $this->element['directory'];
		}

		return $result;
	}

	/**
	 * Method to get the field input markup for a media selector.
	 * Use attributes to identify specific created_by and asset_id fields
	 *
	 * @return  string  The field input markup.
	 *
	 * @since   1.6
	 */
	protected function getInput()
	{
		

		$asset = $this->asset;

		if ($asset == '')
		{
			$asset = JFactory::getApplication()->input->get('option');
		}

		if (!self::$initialised)
		{
			$script[] = "
				(function ($) {
					 $(document).on('ready',function () {
						 
							$('.TipPreview').tooltip({
								'html': true,
								'placement': 'bottom',
								'container': 'body'
							 });
					
							$('.TipPreview').on('show.bs.tooltip', function () {
								
							   var findImg = $(this).parent().parent().parent().find('input').val();
							   if(findImg !=''){
								   findImg ='<img src=\"".JUri::root()."'+findImg+'\" />';
									$(this).attr('data-original-title', findImg);
							   }
							   
							});
							$('.resetMedia').on('click', function (event) {
								 event.preventDefault();
								 var parent = $(this).parent().parent().parent();
								 parent.find('input').val('');
								 parent.find('.TipPreview').attr('data-original-title', '". JText::_('JLIB_FORM_MEDIA_PREVIEW_EMPTY') ."');
							
							});
							
							$('.mediaInput').on('change', function (event) {
								
								var parent = $(this).parent().parent().parent();
								if($(this).val() !=''){
									findImg ='<img src=\"".JUri::root()."'+$(this).val()+'\" />';
									parent.find('.TipPreview').attr('data-original-title', findImg);
								}else{
									parent.find('.TipPreview').attr('data-original-title', '". JText::_('JLIB_FORM_MEDIA_PREVIEW_EMPTY') ."');
								}
							});
						
					});
				})(jQuery);
			";

			// Add the script to the document head.
			JFactory::getDocument()->addScriptDeclaration(implode("\n", $script));

			self::$initialised = true;
		}

		$html = array();
		// custom class
		$attrClass='';
		if(!empty($this->element['class'])){
			
			$attrClass=' '.$this->element['class'];
		}

		if ($this->value && file_exists(JPATH_ROOT . '/' . $this->value))
		{
			$folder = explode('/', $this->value);
			$folder = array_diff_assoc($folder, explode('/', JComponentHelper::getParams('com_media')->get('image_path', 'images')));
			array_pop($folder);
			$folder = implode('/', $folder);
		}
		elseif (file_exists(JPATH_ROOT . '/' . JComponentHelper::getParams('com_media')->get('image_path', 'images') . '/' . $this->directory))
		{
			$folder = $this->directory;
		}
		else
		{
			$folder = '';
		}
		// input
		$html[] ='<div id="' . $this->element['name'] . '_holder" class="inputGroupHolder'.$attrClass.'">';
		$html[] ='<div class="input-group">';
		$html[] ='<span class="input-group-btn">';
		$html[] ='<span id="img-preview" class="btn btn-default btn-sm TipPreview" title="'. JText::_('JLIB_FORM_MEDIA_PREVIEW_EMPTY') .'">';
		$html[] ='<i class="fa fa-eye"></i>';
		$html[] ='</span>';
		$html[] ='</span>';
		$html[] ='<input type="text"  name="' . $this->name . '" id="' . $this->id . '" class="form-control mediaInput" readonly="readonly" placeholder="'.JText::_('YJSG_SELECT_IMAGE').'" value="'. htmlspecialchars($this->value, ENT_COMPAT, 'UTF-8') . '">';
		$html[] ='<span class="input-group-btn">';
		$html[] ='<button id="'.$this->element['name'].'_openm" class="btn btn-default btn-sm" data-toggle="modal" data-target="#Modal_'.$this->element['name'].'">';
		$html[] ='Select';
		$html[] ='</button>';
		$html[] ='<button class="btn btn-default btn-sm resetMedia">';
		$html[] ='<i class="fa fa-times"></i>';
		$html[] ='</button>';
		$html[] ='</span>';
		$html[] ='</div>';
		$html[] ='</div>';
		$html[] ='';
		$html[] ='';
		
		//modal iframe
		$html[] = '<div class="modal fade modaliframe" id="Modal_'.$this->element['name'].'" tabindex="-1" role="dialog" aria-labelledby="Modal_'.$this->element['name'].'" aria-hidden="true" data-iframesrc="'.$this->link. 'index.php?option=com_media&amp;view=images&amp;tmpl=component&amp;asset='.$asset.'&amp;author=yjsg&amp;fieldid='.$this->id.'&amp;folder='.$folder.'">';
		$html[] = '<div class="modal-dialog">';
		$html[] = '<div class="modal-content">';
		$html[] = '<div class="modal-header">';
		$html[] = '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
		$html[] = '<h5 class="modal-title">'.JText::_('YJSG_SELECT_IMAGE').'</h5>';
		$html[] = '</div>';
		$html[] = '<div class="modal-body">';
		$html[] = '</div>';
		$html[] = '<div class="modal-footer">';
		$html[] = '<button type="button" class="btn btn-default" data-dismiss="modal">'.JText::_('YJSG_CLOSE').'</button>';
		$html[] = '</div>';
		$html[] = '</div><!-- /.modal-content -->';
		$html[] = '</div><!-- /.modal-dialog -->';
		$html[] = '</div><!-- /.modal -->';

		return implode("\n", $html);
	}
}