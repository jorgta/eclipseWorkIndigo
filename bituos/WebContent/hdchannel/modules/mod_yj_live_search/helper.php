<?php
/*======================================================================*\
|| #################################################################### ||
|| # #YJ Live Search 2.0
|| # Youjoomla.com - YJ- Licence Number 84030-HM510437
|| # Licensed to - Raphael Sabin
|| # ---------------------------------------------------------------- # ||
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
// no direct access
defined('_JEXEC') or die('Restricted access');

JHtml::_('behavior.framework', true);
class modYJLiveSearchHelper
{
	function  modYJLiveSearchItems(&$params)
	{
		
		
			
		$live_status			= $params->get( 'live_status');
		$topDistance			= $params->get( 'topDistance', '35' );
		$duration				= $params->get( 'duration', '500' );
		$no_results				= $params->get( 'no_results', 'Sorry, no results for' );
		$is_copy			 	= $params->get( 'is_copy');
		$input_width			= $params->get( 'input_width', '210' );
		$feedtype				= $params->get( 'feedtype');
		$minLenght				= $params->get( 'minLenght');
		$maxLenght				= $params->get( 'maxLenght');
		$minCharTxt1			= $params->get( 'minCharTxt1');
		$minCharTxt2			= $params->get( 'minCharTxt2');			
		$res_width 				= $input_width + 10;
		
		if($is_copy =='defaultfeed'){
			$load_feed='';
		}else{
			$load_feed=$is_copy;
		}
			

		$document = &JFactory::getDocument();
		$document->addStyleSheet(JURI::base() . 'modules/mod_yj_live_search/css/stylesheet.css');
		
		
		if($live_status == 1){
		  $document->addScript(JURI::base() . 'modules/mod_yj_live_search/script/YJLiveSearch.js');
		  $document->addScriptDeclaration('
			  window.addEvent(\'domready\', function(){
					  new YJLiveSearch({
					  inputId:\''.$is_copy.'LiveSearch\', 
					  inputCopy:\' '.$is_copy.'\',
					  resultFeed:\''.JURI::base().'modules/mod_yj_live_search/'.$feedtype.'/'.$load_feed.'feed.php\', 
					  noResultsMessage:\''.$no_results.'&nbsp;\', 
					  topDistance:'.$topDistance.', 
					  duration:'.$duration.',
					  minLenght:'.$minLenght.',
					  maxLenght:'.$maxLenght.',
					  minCharTxt1:\''.$minCharTxt1.'&nbsp;\',
					  minCharTxt2:\'&nbsp;'.$minCharTxt2.'\'

				  });
			});');
		}
		
		$document->addCustomTag('
		<style type="text/css">
			.LiveSearchContainer.'.$is_copy.',
			.LiveSearchResults.'.$is_copy.' {
			width:'.$res_width.'px;
			}
		</style>
		');
		

	}
}
?>