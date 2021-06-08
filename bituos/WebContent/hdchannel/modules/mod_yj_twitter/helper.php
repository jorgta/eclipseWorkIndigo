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
// no direct access
defined('_JEXEC') or die('Restricted access');


function ago($timestamp){

	$format_time_array = explode(" ",$timestamp);
	$year = preg_grep("/^([0-9]{4})/", $format_time_array);
	$day = preg_grep("/^([0-9]{2})/", $format_time_array);
	$time = preg_grep("/^([0-9]{2}:[0-9]{2}:[0-9]{2})/", $format_time_array);
	$time = array_values($time);
	$time_array = explode(":",$time[0]);
	$month = preg_grep("/^([a-zA-Z]{3})/", $format_time_array);
	switch($month[1]){
		case 'Jan':
			$month_nr = 1;
		break;
		case 'Feb':
			$month_nr = 2;
		break;
		case 'Mar':
			$month_nr = 3;
		break;
		case 'Apr':
			$month_nr = 4;
		break;
		case 'May':
			$month_nr = 5;
		break;
		case 'Jun':
			$month_nr = 6;
		break;
		case 'Jul':
			$month_nr = 7;
		break;
		case 'Aug':
			$month_nr = 8;
		break;
		case 'Sep':
			$month_nr = 9;
		break;
		case 'Oct':
			$month_nr = 10;
		break;
		case 'Nov':
			$month_nr = 11;
		break;																					
		case 'Dec':
			$month_nr = 12;
		break;
		default :
			$month_nr = 1;
		break;
	}
	
	// Evaluate how much difference there is between local and GTM/UTC
	// Don't forget to correct for daylight saving time...
	$aNow = localtime();
	$iDelta = gmmktime(1, 1, 1, 1, 1, 1970) - mktime(1, 1, 1, 1, 1, 1970);
	
	$timestamp = mktime($time_array[0], $time_array[1], $time_array[2], $month_nr, $day[2], $year[5]);
	$difference = time() - ($timestamp + $iDelta);
	//if difference in smaller than 0 sec put the difference to 1 second
	if($difference <= 0){
		$difference = 1;
	}
	$periods = array("second", "minute", "hour", "day", "week", "month", "years");
	$lengths = array("60","60","24","7","4.35","12","10");
	for($j = 0; $difference >= $lengths[$j]; $j++){
		$difference /= $lengths[$j];
	}
	
	//$difference = floor($difference);
	$difference = round($difference);   
	if($difference != 1) $periods[$j].= "s";
	$text = "$difference $periods[$j] ".JText::_( 'TWITTER_AGO' );
	
	return $text;
}

/**
 * write a file
 */ 	
function write($filename,$content,$force_recreate=false){

	$stream = JFactory::getStream();

	if(intval(JVERSION) < 3){
		// Beef up the chunk size to a meg
		$stream->set('chunksize', (1024 * 1024 * 1024));
	}

	$open_mode = $force_recreate ? 'w' : 'a';
	
	if ($stream->open($filename, $open_mode)){

		if(version_compare(JVERSION, '3.0.0_alpha1', '<')) {
			$result = $stream->write($content);
		}else{
			$result = $stream->write($content,strlen($content));
		}
		$stream->chmod();
		$stream->close();

		return $result;
	}else{
		JError::raiseWarning(21, JText::sprintf('JLIB_FILESYSTEM_ERROR_WRITE_STREAMS', $file, $stream->getError()));

		return false;
	}

	return true;	
}

$twitter_user		= $params->get( 'twitter_user', '' );
$nr_article			= $params->get( 'nr_article', '20' );
$tweet_limit		= $params->get( 'tweet_limit', '0' );
$cache_limit		= $params->get( 'cache_limit', '60' );
$tweet_link			= $params->get( 'tweet_link', '1' );
$tweet_date			= $params->get( 'tweet_date', '1' );
$tweet_follow		= $params->get( 'tweet_follow', '1' );
$tweet_image		= $params->get( 'tweet_image', '1' );
//twitter api credentials
$twitter_consumerkey		= $params->get( 'twitter_consumerkey', 'FuBoqfqGecFqRJRZ5o5cPA' );
$twitter_consumersecret		= $params->get( 'twitter_consumersecret', 'L9V9XOnwrtOB3xH57Hv1bc7OUBu9b0WUBkGQxMXdT4' );
$twitter_accesstoken		= $params->get( 'twitter_accesstoken', '92844143-WEdNLoUdAk3UyjAUj72TWtxM3V2t4C0XMeOwYlpN3' );
$twitter_accesstokensecret	= $params->get( 'twitter_accesstokensecret', 'pTcYVUJt2hLIXoKnXL8pzCiK5Ajz4EYYv9muMdFswuA' );

$document	    	= JFactory::getDocument();
$document->addStyleSheet(JURI :: base().'/modules/mod_yj_twitter/css/stylesheet.css');

// make request
if(function_exists('curl_init') ) {
	$yj_twitter_refresh = 0;
	if(file_exists(JPATH_BASE . '/modules/mod_yj_twitter/lib/cache.php')){
		require_once JPATH_BASE . '/modules/mod_yj_twitter/lib/cache.php';

		$cache_time_limit = $tw_cache_time + ($cache_limit * 60);
		if($cache_time_limit < time() || $twitter_user != $twitter_user_cache){
			$yj_twitter_refresh = 1;
			$tw_response = "";
		}else{
			$tw_response = json_decode(stripslashes($tw_response));
			$yj_twitter_refresh = 0;
		}
	}else{
		$yj_twitter_refresh = 1;
	}
	
	if($yj_twitter_refresh == 1){
		require_once JPATH_BASE . '/modules/mod_yj_twitter/lib/twitteroauth.php';
		//$consumerkey = "FuBoqfqGecFqRJRZ5o5cPA";
		//$consumersecret = "L9V9XOnwrtOB3xH57Hv1bc7OUBu9b0WUBkGQxMXdT4";
		//$accesstoken = "92844143-WEdNLoUdAk3UyjAUj72TWtxM3V2t4C0XMeOwYlpN3";
		//$accesstokensecret = "pTcYVUJt2hLIXoKnXL8pzCiK5Ajz4EYYv9muMdFswuA";
		 
		function getConnectionWithAccessToken($cons_key, $cons_secret, $oauth_token, $oauth_token_secret) {
		  $connection = new TwitterOAuth($cons_key, $cons_secret, $oauth_token, $oauth_token_secret);
		  return $connection;
		}
		$connection = getConnectionWithAccessToken($twitter_consumerkey, $twitter_consumersecret, $twitter_accesstoken, $twitter_accesstokensecret);
		$tw_response = $connection->get("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=".$twitter_user."&count=".$nr_article);		

		$tw_response_cache 	= "<?php \$tw_response='" . addslashes(json_encode($tw_response)) . "'; \n\r\$tw_cache_time = '".time()."'; \n\r\$twitter_user_cache = '".$twitter_user."';?>";
		write(JPATH_BASE . '/modules/mod_yj_twitter/lib/cache.php', $tw_response_cache, true);
	}
	$tw_json = $tw_response;	
}else{
	$tw_json = new stdClass();
	$error	= new stdClass();
	$error->message = JText::_( 'TWITTER_CURL_NOT_FOUND' );
	$tw_json->errors = array($error);
}