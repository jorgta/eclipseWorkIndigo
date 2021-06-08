<?php
/*======================================================================*\
|| #################################################################### ||
|| # Package - YJ Live Search 2.0								        ||
|| # Copyright (C) 2010  Youjoomla.com. All Rights Reserved.            ||
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
		define('JPATH_BASE', str_replace("modules".DIRECTORY_SEPARATOR."mod_yj_live_search".DIRECTORY_SEPARATOR."feeds","",dirname(__FILE__)) );
		require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'defines.php' );
		require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'framework.php' );		
		$mainframe =& JFactory::getApplication('site');
		$mainframe->initialise();
		jimport( 'joomla.methods' );
		jimport( 'joomla.html.parameter' );
		jimport( 'joomla.application.module.helper' );
		jimport( 'joomla.plugin.helper' );
		require_once (JPATH_BASE.DIRECTORY_SEPARATOR.'administrator'.DIRECTORY_SEPARATOR.'components'.DIRECTORY_SEPARATOR.'com_search'.DIRECTORY_SEPARATOR.'helpers'.DIRECTORY_SEPARATOR.'search.php');


		class JSON
		{
			//public $result;
			var $result = '';
			/**
			 * Class constructor
			 *
			 * @param array $array
			 */
			//public function __construct( $array )
			function JSON( $array )
			{
				$this->json_encode($array);			
			}
			/**
			 * Json string builder. It calls itself if array value in key=>value pair is an array.
			 * It cand generate JSON strings from multidimensional array
			 *
			 * @param array $array
			 * @param string $separator
			 * @return void
			 */
			//private function json_encode( $array = array(), $separator = '' )
			function json_encode( $array = array(), $separator = '' )
			{
				$this->result .= '{';
				
				$pairs = array();
				foreach ($array as $key=>$value) 
				{
					if( is_array( $value ) )
					{
						$last_key = end( array_keys( $array ) );
						
						$this->result .= '"'.$key.'":';
						$this->json_encode( $value , ($key == $last_key ? '':',') );
					}else{
						$pairs[] = '"'.$key.'":"'.$value.'"'; 
					}
				}
				$this->result .= implode(',',$pairs).'}'.$separator;
			}
			
		}//end class code

//		//get the module params
		$db =& JFactory::getDBO();
		$query = "SELECT params"
		. "\n FROM #__modules "
		. "\n WHERE params REGEXP 'defaultfeed'";
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
		$Search_Weblinks	= $params->get( 'Search_Weblinks', '1' );
		$Search_Contacts	= $params->get( 'Search_Contacts', '1' );
		$Search_Categories	= $params->get( 'Search_Categories', '1' );
		$Search_Newsfeeds	= $params->get( 'Search_Newsfeeds', '1' );	
		$Search_K2			= $params->get( 'Search_K2', '1' );										
		
		$page = ((int)$_GET['page']);
		$start = ($page-1)*$resultsOnPage;
		$keyword		= urldecode(JRequest::getString('search'));
		$match			= JRequest::getWord('searchphrase', 'all');
		$ordering		= JRequest::getWord('ordering', 'newest');
		
		//$areas = array();

		JPluginHelper::importPlugin('search');
		$dispatcher = JDispatcher::getInstance();

		if($Search_Content == 0){
			$dispatcher->detach( 'plgSearchContent');
		}
		if($Search_Weblinks == 0){
			$dispatcher->detach( 'plgSearchWeblinks');
		}
		
		if($Search_Contacts == 0){
			$dispatcher->detach( 'plgSearchContacts');
		}
			
		if($Search_Categories == 0){
			$dispatcher->detach( 'plgSearchCategories');
		}
		
		if($Search_Newsfeeds == 0){
			$dispatcher->detach( 'plgSearchNewsfeeds');
		}
				
		if($Search_K2 == 0){
			$dispatcher->detach( 'plgSearchK2');
		}
				
		//perform the search and get the results
		$results = $dispatcher->trigger( 'oncontentsearch', array(
		$keyword,
		$match,
		$ordering));

		$rows = array();
		foreach($results AS $result2) {
			$rows = array_merge( (array) $rows, (array) $result2);
		}
		

		$total_results = count( $rows );

		if(!empty($rows)){
			foreach($rows as $rows_row => $rows_value){
				/* Limit the result for next or prev button. */			
				if( ($rows_row >=$start) && ($rows_row<=($start+$resultsOnPage-1)) ){
					/*
						keep the array structure from below. It's very important that you keep the same keys
					*/
					$rows_value->text = preg_replace('/\{(.*)\}/','',$rows_value->text);
					$rows_value->text = str_replace("\r\n"," ",$rows_value->text);
					$rows_value->text = str_replace("\n"," ",$rows_value->text);
					$rows_value->text = str_replace("\r"," ",$rows_value->text);
					$rows_value->text = str_replace("\t"," ",$rows_value->text);
					$rows_value->text = strip_tags($rows_value->text);		
					//$rows_value->text = addslashes($rows_value->text);
					$rows_value->text = htmlspecialchars($rows_value->text, ENT_COMPAT, 'UTF-8');
					$rows_value->text = substr($rows_value->text,0,$intro_limit);
									
					//generate itemid
					if ( strpos( $rows_value->href, 'http' ) == false ) {
						$url = parse_url( $rows_value->href );
						parse_str( @$url['query'], $link );
			
						// determines Itemid for Content items where itemid has not been included
						if ( isset($rows_value->type) && @$link['task'] == 'view' && isset($link['id']) && !isset($link['Itemid']) ) {
							$itemid 	= '';
							$_itemid = $mainframe->getItemid( $link['id'], 0 );
			
							if ($_itemid) {
								$itemid = '&Itemid='. $_itemid;
							}
			
							$rows_value->href = $rows_value->href . $itemid;
						}
					}//end if

					$result['results'][] = array(
					  'title'=>htmlspecialchars($rows_value->title, ENT_QUOTES), // NOT MUCH HERE , TITLE ONLY
					  'url'=>str_replace("modules/mod_yj_live_search/feeds/","",JRoute::_($rows_value->href)), 
					  'description'=>htmlspecialchars($rows_value->text, ENT_QUOTES)."..."
					);
				}//end if
			}
		}
	
		/*
			to add pagination in JavaScript, add the number of pages to the results array	
		*/
		$result['pages'] = @ceil($total_results/$resultsOnPage);
		/*
			if you want to make string length checks for example, just output the JSON object of an empty array if anything is wrong (string too short, empty etc. )
		*/
		if(empty($_GET['search']) || empty($rows) ) $result = array();
		/* class that transforms an array into a JSON object */
		//require_once('libs/JSON.class.php');
		/* feed it the array */
		$json = new JSON($result);
		/*...and output the result*/
		echo $json->result;	exit;
	}
?>