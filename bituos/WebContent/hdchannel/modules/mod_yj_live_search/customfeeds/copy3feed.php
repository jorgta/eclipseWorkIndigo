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
if(isset($_GET['search'])){

		// Set flag that this is a parent file
		if(!defined('_JEXEC')) define( '_JEXEC', 1 );
		define('JPATH_BASE', str_replace("modules".DIRECTORY_SEPARATOR."mod_yj_live_search".DIRECTORY_SEPARATOR."customfeeds","",dirname(__FILE__)) );
		require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'defines.php' );
		require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'framework.php' );		
		$mainframe =& JFactory::getApplication('site');
		$mainframe->initialise();
		jimport( 'joomla.methods' );
		jimport( 'joomla.html.parameter' );
		jimport( 'joomla.application.module.helper' );
		jimport( 'joomla.plugin.helper' );
		jimport('joomla.filesystem.file');
		jimport( 'joomla.filesystem.folder' );
		

//		//get the module params
		$db =& JFactory::getDBO();
		$query = "SELECT params"
		. "\n FROM #__modules "
		. "\n WHERE params REGEXP 'copy3'";
		$db->setQuery( $query );
		$row = $db->loadResult();
		if(intval(JVERSION) >= 3 ){	
			$params = new JRegistry ( $row );
		}else{
			$params = new JParameter( $row );
		}

		$resultsOnPage		= $params->get( 'search_limit', '20' );
		$intro_limit		= $params->get( 'intro_limit', '100' );
		
		
		$Search_Content		= $params->get( 'Search_Content', '1' );
		$Search_K2			= $params->get( 'Search_K2', '1' );
		$Search_Categories	= $params->get( 'Search_Categories', '1' );
		$Search_K2_cat		= $params->get( 'Search_K2_cat', '1' );	
		$Search_Weblinks	= $params->get( 'Search_Weblinks', '1' );
		$Search_Newsfeeds	= $params->get( 'Search_Newsfeeds', '1' );
		$Search_Contacts	= $params->get( 'Search_Contacts', '1' );
		$Search_K2_tags		= $params->get( 'Search_K2_tags', '1' );
		$mysql_operator		= $params->get( 'mysql_operator', 'REGEXP' );
		
		$index_items		= $params->get( 'index_items', '1' );	
		$show_image			= $params->get( 'show_image', '1' );
		global $k2image_size;
		$k2image_size		= $params->get( 'k2image_size', 'XS' );
		$image_width		= $params->get( 'image_width', '75' );
		
		
		$page 				= ((int)$_GET['page']);
		$start 				= ($page-1)*$resultsOnPage;
		$keyword			= urldecode(JRequest::getString('search'));
		$match				= JRequest::getWord('searchphrase', 'all');
		$ordering			= JRequest::getWord('ordering', 'newest');
		
		$page 				= ((int)$_GET['page']);
		$start 				= ($page-1)*$resultsOnPage;

		$k2_check			= JFolder::exists(JPATH_BASE.DIRECTORY_SEPARATOR."components".DIRECTORY_SEPARATOR."com_k2".DIRECTORY_SEPARATOR);
		
		if($Search_Content == 1 || $Search_Categories == 1 ){
				require_once (JPATH_BASE.DIRECTORY_SEPARATOR.'components'.DIRECTORY_SEPARATOR.'com_content'.DIRECTORY_SEPARATOR.'helpers'.DIRECTORY_SEPARATOR.'route.php');
		}
		if( $k2_check && ($Search_K2 == 1 || $Search_K2_cat == 1 || $Search_K2_tags == 1)){
				require_once (JPATH_BASE.DIRECTORY_SEPARATOR.'components'.DIRECTORY_SEPARATOR.'com_k2'.DIRECTORY_SEPARATOR.'helpers'.DIRECTORY_SEPARATOR.'route.php');
		}

	include_once('feed_sql.php');
	include_once('feed_functions.php');
	
	foreach ( $load_items as $key=> $row) :
		$item_image='';
		switch ($row->source) {
			case 'joomlacontent':
				$item_url 		= JRoute::_(ContentHelperRoute::getArticleRoute($row->id, $row->catid));
				$item_image		= JURI::base().yjls_yjme_art_image($row);
				break;
			case 'k2item':
				$item_url 		= urldecode(JRoute::_(K2HelperRoute::getItemRoute($row->id.':'.urlencode($row->alias), $row->catid.':'.urlencode($row->linkparam))));
				$item_image		= yjls_k2_yjme_art_image($row);
				break;	
			case 'joomlacategory':
				$item_url 		= JRoute::_(ContentHelperRoute::getCategoryRoute($row->id));
				$item_image		= JURI::base().yjls_yjme_art_image($row);
				break;	
			case 'k2category':
				$item_url 		= urldecode(JRoute::_(K2HelperRoute::getCategoryRoute($row->id.':'.urlencode($row->alias))));
				$item_image		= JURI::base().'media/k2/categories/'.$row->images;
				break;	
			case 'weblinks':
				$item_url 		= JRoute::_('index.php?option=com_weblinks&view=weblink&id='.$row->id.'');
				$item_image		= JURI::base().yjls_yjme_art_image($row);
				break;	
			case 'newsfeeds':
				$item_url 		= JRoute::_(NewsFeedsHelperRoute::getNewsfeedRoute($row->id, $row->catid));
				$item_image		='';
				break;	
			case 'contacts':
				$item_url 		= JRoute::_(ContactHelperRoute::getContactRoute($row->id,$row->catid));
				$item_image		= JURI::base().$row->images; 
				break;	
			case 'k2tags':
				$item_url 		= urldecode(JRoute::_(K2HelperRoute::getItemRoute($row->id.':'.urlencode($row->alias), $row->catid.':'.urlencode($row->linkparam))));
				$item_image		= yjls_k2_yjme_art_image($row);
				break;	
		}


			$item_title		= $row->title;
			$item_intro		= $row->descr.$row->descr_full;
			$item_url 		= str_replace("/modules/mod_yj_live_search/customfeeds","",$item_url);
			$item_url 		= str_replace("&amp;","&",$item_url);
			$item_image		= str_replace("/modules/mod_yj_live_search/customfeeds","",$item_image);

			if(( $show_image == 1 && isset($item_image) && $item_image !='' && (preg_match("/.jpg|.gif|.png|.bmp/i",$item_image)))){
				$item_image="<a href='".$item_url."'><img src='".$item_image."' alt='".$row->title."' border='0' width='".$image_width."'/></a>";
			}else{
				$item_image='';
			}
			
			
			$item_intro = preg_replace('/\{(.*)\}/','',$item_intro);
			$item_intro = str_replace("\r\n"," ",$item_intro);
			$item_intro = str_replace("\n"," ",$item_intro);
			$item_intro = str_replace("\r"," ",$item_intro);
			$item_intro = str_replace("\t"," ",$item_intro);
			$item_intro = strip_tags($item_intro);		
			$item_intro = addslashes($item_intro);		
		
			if( ($key >=$start) && ($key<=($start+$resultsOnPage-1)) ){
					$result['results'][] = array(
								'title'			=>$item_title,
								'url'			=>$item_url,
								'description'	=>substr(strip_tags($item_intro),0,$intro_limit),
								'image'			=>$item_image
								);
			}
	endforeach;
	
	
	$result['pages'] = ceil($total_results/$resultsOnPage);
	if(empty($_GET['search']))
		$result = array();
	$json = new JSON($result);
	echo $json->result;	
}
?>