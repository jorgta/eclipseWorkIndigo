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
// no direct access
defined('_JEXEC') or die;

if ($Search_Content == 1) {
	
	if($index_items == 1){
		$ji_index ="
		  WHERE i.title
		  ".$mysql_operator." '".$_GET['search']."'";
	}elseif($index_items == 2){
		$ji_index ="
		  WHERE i.introtext OR i.fulltext
		  ".$mysql_operator." '".$_GET['search']."'";
	}else{
		$ji_index ="
		  WHERE i.title
		  ".$mysql_operator." '".$_GET['search']."'
		  OR i.introtext OR i.fulltext
		  ".$mysql_operator." '".$_GET['search']."'";
	}
	$ji ="
		SELECT i.title AS title,i.id AS id,i.alias AS alias,i.introtext AS descr,i.fulltext AS descr_full,i.images AS images,ic.id AS catid,ic.alias AS linkparam,'joomlacontent' AS source 
		FROM #__content i 
		LEFT JOIN #__categories ic
		ON ic.id=i.catid
		".$ji_index."
		AND state =1";
}else{
	$ji='';
}
// k2 items	
if ($Search_K2 == 1 && $k2_check) {
	if($index_items == 1){
		$k2i_index ="
		  WHERE i.title
		  ".$mysql_operator." '".$_GET['search']."'";
	}elseif($index_items == 2){
		$k2i_index ="
		  WHERE i.introtext OR i.fulltext
		  ".$mysql_operator." '".$_GET['search']."'";
	}else{
		$k2i_index ="
		  WHERE i.title
		  ".$mysql_operator." '".$_GET['search']."'
		  OR i.introtext OR i.fulltext
		  ".$mysql_operator." '".$_GET['search']."'";
	}
	$k2i ="
        SELECT i.title AS title,i.id AS id,i.alias AS alias,i.introtext AS descr,i.fulltext AS descr_full,null AS images,ic.id AS catid,ic.alias AS linkparam,'k2item' AS source
        FROM #__k2_items i
        LEFT JOIN #__k2_categories ic
        ON ic.id=i.catid
		".$k2i_index."
        AND i.published =1";
}else{
	$k2i ='';
}
// joomla categories	
if ($Search_Categories == 1) {
	if($index_items == 1){
		$jc_index ="
		  AND i.title
		  ".$mysql_operator." '".$_GET['search']."'";
	}elseif($index_items == 2){
		$jc_index ="
		  AND i.description
		  ".$mysql_operator." '".$_GET['search']."'";
	}else{
		$jc_index ="
		  AND i.title
		  ".$mysql_operator." '".$_GET['search']."'
		  OR i.description
		  ".$mysql_operator." '".$_GET['search']."'";
	}
	$jc ="
        SELECT i.title AS title,i.id AS id,i.alias AS alias,i.description AS descr,null AS descr_full,i.params AS images,i.parent_id,i.extension AS linkparam,'joomlacategory' AS source
        FROM #__categories i
		WHERE i.extension ='com_content'
		".$jc_index."
        AND i.published =1";
}else{
	$jc='';
}
// k2 categories
if ($Search_K2_cat == 1 && $k2_check) {
	if($index_items == 1){
		$k2c_index ="
		  WHERE i.name
		  ".$mysql_operator." '".$_GET['search']."'";
	}elseif($index_items == 2){
		$k2c_index ="
		  WHERE i.description
		  ".$mysql_operator." '".$_GET['search']."'";
	}else{
		$k2c_index ="
		  WHERE i.name
		  ".$mysql_operator." '".$_GET['search']."'
		  OR i.description
		  ".$mysql_operator." '".$_GET['search']."'";
	}
	$k2c ="
        SELECT i.name AS title,i.id AS id,i.alias AS alias,i.description AS descr,null AS descr_full,i.image AS images,i.parent,i.params AS linkparam,'k2category' AS source
        FROM #__k2_categories i
		".$k2c_index."
        AND i.published =1";
}else{
	$k2c ='';
}
// joomla weblinks
if ($Search_Weblinks == 1) {
	if($index_items == 1){
		$jw_index ="
		  WHERE i.title
		  ".$mysql_operator." '".$_GET['search']."'";
	}elseif($index_items == 2){
		$jw_index ="
		  WHERE i.description
		  ".$mysql_operator." '".$_GET['search']."'";
	}else{
		$jw_index ="
		  WHERE i.title
		  ".$mysql_operator." '".$_GET['search']."'
		  OR i.description
		  ".$mysql_operator." '".$_GET['search']."'";
	}
	$jw ="
        SELECT i.title AS title,i.id AS id,i.alias AS alias,i.description AS descr,null AS descr_full,null AS images,i.catid,i.url AS linkparam,'weblinks' AS source
        FROM #__weblinks i
		".$jw_index."
        AND state =1";
}else{
	$jw ='';
}
// joomla newsfeeds	
if ($Search_Newsfeeds == 1) {
	require_once (JPATH_BASE.DIRECTORY_SEPARATOR.'components'.DIRECTORY_SEPARATOR.'com_newsfeeds'.DIRECTORY_SEPARATOR.'helpers'.DIRECTORY_SEPARATOR.'route.php');

	if($index_items == 1){
		$jnf_index ="
		  WHERE i.name
		  ".$mysql_operator." '".$_GET['search']."'";
	}elseif($index_items == 2){
		$jnf_index ="
		  WHERE i.link
		  ".$mysql_operator." '".$_GET['search']."'";
	}else{
		$jnf_index ="
		  WHERE i.name
		  ".$mysql_operator." '".$_GET['search']."'
		  OR i.link
		  ".$mysql_operator." '".$_GET['search']."'";
	}
	$jnf ="
        SELECT i.name AS title,i.id AS id,i.alias AS alias,i.link AS descr,null AS descr_full,null AS images,i.catid,NULL,'newsfeeds' AS source
        FROM #__newsfeeds i
		".$jnf_index."
        AND i.published =1";
}else{
	$jnf ='';
}
// joomla contacts	
if ($Search_Contacts == 1) {
	require_once (JPATH_BASE.DIRECTORY_SEPARATOR.'components'.DIRECTORY_SEPARATOR.'com_contact'.DIRECTORY_SEPARATOR.'helpers'.DIRECTORY_SEPARATOR.'route.php');

	if($index_items == 1){
		$jcn_index ="
		  WHERE i.name
		  ".$mysql_operator." '".$_GET['search']."'";
	}elseif($index_items == 2){
		$jcn_index ="
		  WHERE i.misc
		  ".$mysql_operator." '".$_GET['search']."'";
	}else{
		$jcn_index ="
		  WHERE i.name
		  ".$mysql_operator." '".$_GET['search']."'
		  OR i.misc
		  ".$mysql_operator." '".$_GET['search']."'";
	}
	$jcn ="
        SELECT i.name AS title,i.id AS id,i.alias AS alias,i.misc AS descr,null AS descr_full,i.image AS images,i.catid,i.webpage AS linkparam,'contacts' AS source
        FROM #__contact_details i
        WHERE i.name
        ".$mysql_operator." '".$_GET['search']."'
        AND i.published =1";
}else{
	$jcn='';
}

// k2 tags
if ($Search_K2_tags == 1 && $k2_check) {
	$k2t ="
	  SELECT i.title AS title,i.id AS id,i.alias AS alias,i.introtext AS descr,i.fulltext AS descr_full,it.name AS images,ic.id AS catid,ic.alias AS linkparam,'k2tags' AS source
	  FROM #__k2_items i
	  LEFT JOIN #__k2_categories ic
	  ON ic.id=i.catid
	  LEFT JOIN #__k2_tags_xref ix
	  ON ix.itemID=i.id
	  LEFT JOIN #__k2_tags it
	  ON ix.tagID=it.id
	  WHERE it.name
	  ".$mysql_operator." '".$_GET['search']."'
	  AND i.published =1";
}else{
	$k2t ="";
}

// run it
	$sql=array($ji,$k2i,$jc,$k2c,$jw,$jnf,$jcn,$k2t); // you can add own
	$sql=array_diff($sql, array(''));
	$UNION =' UNION ALL ';
	$sql=implode( $UNION, $sql );

	
	$db->setQuery( $sql );
	$load_items =  $db->loadObjectList();
	$total_results = count($load_items);
	$result = array();
?>