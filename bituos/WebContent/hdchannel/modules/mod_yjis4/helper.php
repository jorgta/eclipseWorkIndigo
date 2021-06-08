<?php
/**
 * @package		YJ Module Engine
 * @author		Youjoomla.com
 * @website     Youjoomla.com 
 * @copyright	Copyright (c) 2007 - 2011 Youjoomla.com.
 * @license   PHP files are GNU/GPL V2. CSS / JS / IMAGES are Copyrighted Commercial
 */

// no direct access
defined('_JEXEC') or die('Restricted access');
class YJisHelp
	{
		
		function YJisItems(&$params)
		{
			  global $is_yjis_copy;
			  $who = strtolower($_SERVER['HTTP_USER_AGENT']); // what browser they use.
			  /**
			   * Parameters
			   */
			  $class_fx 				= $params->get('moduleclass_sfx');
			  $swidth 					= $params->get('swidth');
			  $sheight 					= $params->get('sheight');
			  $sorient 					= $params->get('sorient');
			  $start_slide 				= $params->get('start_slide');
			  $type_slider 				= $params->get('type_slider');
			  $stime 					= $params->get('stime');
			  $sduration 				= $params->get('sduration');
			  $smenu 					= $params->get('smenu');
			  $pagination 				= $params->get('pagination');
			  $tooltips 				= $params->get('tooltips');
			  $thumbs_tooltips 			= $params->get('thumbs_tooltips');
			  $navs_tooltips 			= $params->get('navs_tooltips');
			  $is_copy 					= $is_yjis_copy;
			  $order 					= $params->get('order');
			  $yjspacer 				= $params->get('yjspacer');
			  $pagination_counter 		= $params->get('pagination_counter');  
			  $thumb_pagination 		= $params->get('thumb_pagination');
			  $thumb_width				= $params->get('thumb_width');
			  $thumb_height				= $params->get('thumb_height');
			  $visibleItems				= $params->get('visibleItems');
			  $hide_all_intros			= $params->get('hide_all_intros');
			  $hide_all_descriptions	= $params->get('hide_all_descriptions');
			  $thumbsOpacity			= $params->get('thumbsOpacity');
			  $beltPosition				= $params->get('beltPosition');
			  $thumb_arrows				= $params->get('thumb_arrows');
			  $mbox_theme				= $params->get('mbox_theme','Dark');
			  $beltOrientation			= $params->get('beltOrientation');
			  /**
			   * Load scripts and stylesheets
			   */
			  JHtml::_('behavior.framework',true);
			  $document = JFactory::getDocument();
			  $document->addStyleSheet(JURI::base() . 'modules/mod_yjis4/css/mod_yjis4.css');
			  if(preg_match("/msie 6/", $who)) {
			  $document->addStyleSheet(JURI::base() . 'modules/mod_yjis4/css/ifie.php');
			  }
			 $document->addScript(JURI::base() . 'modules/mod_yjis4/src/mod_yjis4.min.js');
			 //$document->addScript(JURI::base() . 'modules/mod_yjis4/src/mod_yjis4.js');
		  
			  /**
			   * display or disable navigation according to settings
			   */
			  $fwd = $smenu == 0 ? 'YJS_right':'null';
			  $bkwd = $smenu == 0 ? 'YJS_left':'null';
			  /**
			   * prepare js class parameters
			   */
			  $document->addScriptDeclaration("
				  var modpath ='".JURI::base()."';
				  window.addEvent('domready', function(){
					  new YJSlide({
						  outerContainer : 'YJSlide_outer$is_copy',
						  innerContainer : 'YJSlide_inner$is_copy',
						  elements: '.YJSlide_slide',
						  navigation: {
							  'forward':'$fwd$is_copy', 
							  'back':'$bkwd$is_copy'
						  },
						  navigationThumb: {
							  'forward':'thumbContainer_right$is_copy', 
							  'back':'thumbContainer_left$is_copy',
							  tnavHolder: 'thumbContainer$is_copy',
							  container: 'thumbContainer_belt$is_copy',
							  elements:'.thumbContainer_thumbLink$is_copy',
							  outer: 'thumbContainer_outer$is_copy',
							  visibleItems:$visibleItems,
							  showThumbs:$thumb_pagination,
							  thumbsOpacity:$thumbsOpacity,
							  showThumbArrows:$thumb_arrows,
							  beltOrientation:$beltOrientation
							  
						  },
						  slideType: $type_slider,
						  orientation: $sorient,
						  slideTime: $stime,
						  duration: $sduration,
						  tooltips: $tooltips,
						  thumbTooltips:$thumbs_tooltips,
						  navTooltips: $navs_tooltips,
						  autoslide: $start_slide,
						  navInfo: 'YJS_nav_info$is_copy',
						  navLinks: '.YJS_navLink$is_copy',
						  navHolder: 'navContainer$is_copy',
						  slideWidth: '$swidth',
						  slideHeight:'$sheight'
					  });
					  
				  });

				  if (typeof  Mediabox == 'object'){
					  window.addEvent(\"domready\", Mediabox.scanPage);
				  }
				  
			  ");
			  
			  /**
			   * slides order
			   */
			  $show_order = range(0,19);
			  if($order == 1)
			  {
				  srand((float)microtime()*1000000);
				  shuffle($show_order); 
			  }
			  /**
			   * Slides info
			   */
			  $slides = array();
			  foreach ($show_order as $i)
			  {
				  $image = $params->get('slide_image_'.($i+1));
				  if (empty($image) || $image==-1)  continue;		
				  
				  $slide = array(
					  'title' 				=> $params->get('slide_title_'.($i+1)),
					  'intro' 				=> $params->get('slide_intro_'.($i+1)),
					  'image'				=> $image,
					  // 'mbox' 				=> $params->get('mbox_'.($i+1)),
					  'mboxlink' 			=> $params->get('mboxlink_'.($i+1)),
					  'mboxtype' 			=> $params->get('mboxtype_'.($i+1)),
					  'video_width' 		=> $params->get('video_width_'.($i+1)),
					  'video_height' 		=> $params->get('video_height_'.($i+1)),
					  'image_group' 		=> $params->get('image_group_'.($i+1)),
					  'image_group_name' 	=> $params->get('image_group_name_'.($i+1)),
					  'link' 				=> $params->get('slide_link_'.($i+1)),
					  'open' 				=> $params->get('slide_open_'.($i+1)),
					  'description' 		=> $params->get('slide_description_'.($i+1)),
					  'thumbnail'			=> $params->get('slide_thumb_'.($i+1)),
					  'gen_thumbnail'		=> $params->get('slide_genthumb_'.($i+1))
				  );
				  $slides[] = $slide;
				  
				  foreach ($slides as $slide){
				   if(!empty($slide['mboxlink'])){
					  	$document->addScript(JURI::base() . 'modules/mod_yjis4/src/yjmmbox.js'); 
					  	$document->addStyleSheet(JURI::base() . 'modules/mod_yjis4/css/mediaboxAdv-'.$mbox_theme.'.css');
					 	break;
					  }
				  }
				  
				  
			  } 
			  
			  return $slides;
			
	    }
}

?>