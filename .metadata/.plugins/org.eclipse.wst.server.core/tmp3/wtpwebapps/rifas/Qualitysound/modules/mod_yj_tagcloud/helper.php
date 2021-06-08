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


class modYJTagsHelper {
	//get tags from content metatag
	static function getTags($use_tag, $own_tags, $tag_limit,$chooseContent){
		global $mainframe;
		
		$document = JFactory::getDocument();
		$document->addStyleSheet(JURI::base() . 'modules/mod_yj_tagcloud/css/stylesheet.css');
		
		
		$db					= JFactory::getDBO();
		$final_tags			= array();	
		$final_font_size 	= array();
		
		jimport('joomla.filesystem.folder');
		$k2_check			= JFolder::exists(JPATH_ROOT.DIRECTORY_SEPARATOR."components".DIRECTORY_SEPARATOR."com_k2".DIRECTORY_SEPARATOR);
		
		
		
		if($use_tag == 1 || $use_tag == 3){
			//get meta tags from db
			
			// Joomla
			$query ="SELECT metakey from #__content where state =1 and metakey<>''";
			
			// K2
			if($chooseContent == 2 && $k2_check){
				
				$query ="SELECT metakey from #__k2_items where published = 1 and metakey<>''";
				
			}
			// Both
			if($chooseContent == 3 && $k2_check){
				
				$query ="(SELECT metakey from #__k2_items where published = 1 and metakey<>'')
					 	 UNION
						 (SELECT metakey from #__content where state =1 and metakey<>'')
						 ";
			}
					
			$db->setQuery($query);
			$tags = $db->loadObjectList();
			
			foreach($tags as $tags_row => $tags_value){
				if(!empty($tags_value->metakey)){
					$split_tags = explode(",",$tags_value->metakey);
					foreach($split_tags as $split_tags_row => $split_tags_value){
						$font_size = rand(12,22);
						$final_font_size[] = $font_size;

						$final_tags[] = trim($split_tags_value);
					}
				}
			}
		}
		
		if($use_tag == 2 || $use_tag == 3){
			//get own tags
			$split_tags = explode(",",$own_tags);
			foreach($split_tags as $split_tags_row => $split_tags_value){
				$font_size = rand(12,22);
				$final_font_size[] = $font_size;
				
				$final_tags[] = trim($split_tags_value);
			}			
		}

		$final_tags = array_unique($final_tags);
		$final_tags = array_slice($final_tags, 0, $tag_limit);
		return array($final_tags,$final_font_size);
	}
}
?>