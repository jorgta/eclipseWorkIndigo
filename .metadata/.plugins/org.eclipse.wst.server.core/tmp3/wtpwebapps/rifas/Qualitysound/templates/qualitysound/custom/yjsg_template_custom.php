<?php
/*======================================================================*\
|| #################################################################### ||
|| # Package - Joomla Template based on YJSimpleGrid Framework          ||
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
defined( '_JEXEC' ) or die( 'Restricted index access' );
/*  
  !!! DO NOT EDIT THIS FILE. IT MIGHT BE CHANGED ON UPDATE
  this file is custom for this template only. if you wish to edit or add 
  own code to the template, use template_name/custom/yjsg_custom_params.php
  
*/
/*

	this variable is for custom link color. you can use it to style any HTML elements
	see instructions here:
	http://www.yjsimplegrid.com/documentation/advanced/elements-styling-based-on-link-color.html

*/
	
$cc_css ="a{color:".$style_color.";}";
$cc_css .="[class*='yjsg-button-color']{background:".$style_color.";}[class*='yjsg-button-color']:hover{background:".$yjsg_color->darker('5%').";}";
$cc_css .="#typosticky.yjsg-sticky.fixed{max-width:".$css_width.$css_widthdefined.";margin:0 auto;padding:15px;}";
$cc_css .=".yjsg-sub-heading,.yjsg-sticky-menu a.active-scroll,[data-sticky-block] a.active-scroll:before{border-color:".$style_color.";}";
$cc_css .="[class*='facolor'].fa:before{color:".$style_color.";}";


if($topMenuLocation == 0){
	
	$cc_css	.= '#logo {	float:none;	margin:0 auto;}.yjsgheadergw{width:100%!important;}';
}

// grayscale switch
$grayscale 	 = $this->params->get("grayscale");
if($grayscale == 1){
	
	
	$yjsg_js.="var grayscale ='1';";
	$cc_css .=".yjgrayscale {opacity:0;}";
	
}else{
	$yjsg_js.="var grayscale ='0';";
}

// custom bootstrap buttons backgrounds per style. Bypassing the template link color and going with template hover instead 
switch ($css_file) {
    case 'royal':
        $valid_color = '58aedf';
        break;
    case 'pink':
        $valid_color = 'df588a';
        break;
    case 'brick':
        $valid_color = 'ef6065';
        break;
     case 'mint':
        $valid_color = '18af90';
        break;
        default:
        $valid_color = '58aedf';
}

/* K2 CSS */
if (JFactory::getApplication()->input->get( 'option' ) == 'com_k2' 
	|| JModuleHelper::getModule( 'k2_content' )
	|| JModuleHelper::getModule( 'k2_tools' )
	|| JModuleHelper::getModule( 'k2_comments' )
	){
		$document->addStyleSheet($yj_site.'css/customk.css');
}

/*JS IMAGE EFFECT */
$YjsgCustomJS	= array();
$YjsgCustomJS[] = $yj_site.'src/styles.js';

/* TOP MENU LOCATION */
//$topMenuLocation = 1; // 1 = inside the header block next to logo

$DDverticalTopOffset = 32;// SmoothDropdown


// IE8 css fixes
if ($yjsgBrowser->Name == 'msie' && $yjsgBrowser->Version == '8.0'){
	$document->addStyleDeclaration(".yy_slideitems_in{height:auto!important;}img.yjgrayscale {filter: gray;}img.yjgrayscale {filter: none;}");
}

/* BG style switch*/
$default_bg 	 = $this->params->get("default_bg");
$mybgstyles = array(
        'grungebg', 'noise','noisedark'
);
$default_bg_style = 'grungebg';
//*
if ( isset($_GET['change_bg']) && !empty($_GET['change_bg']) ) {
        // check if style is valid
		unset( $_SESSION['admin_change'] );
        if( in_array( $_GET['change_bg'], $mybgstyles ) ){
				
                $_SESSION['frontend_bg'] = $_GET['change_bg'];
                $_SESSION['frontend_changed_bg'] = true;
                $valid_bg_styles = $_GET['change_bg'];

        }else {
                // else set to default style
                $valid_bg_styles = $default_bg_style;
        }

} else {
        // second case, checkes for admin changes or front-end changes

        if ( isset($_SESSION['frontend_changed_bg']) && in_array( $_SESSION['frontend_bg'], $mybgstyles ) && !isset( $_SESSION['admin_change'] ) ){
				unset( $_SESSION['admin_change'] );
                $valid_bg_styles = $_SESSION['frontend_bg'];

        }else if( isset( $_SESSION['admin_change'] ) ){
				unset( $_SESSION['frontend_changed_bg'] );
				unset( $_SESSION['frontend_bg'] );
                $valid_bg_styles = $this->params->get("default_bg");

        }else {
                $valid_bg_styles = $default_bg;
        }
}
$bg_file = in_array( $valid_bg_styles, $mybgstyles ) ? $valid_bg_styles : $default_bg_style;
/* Background style class */
switch ($bg_file) {
    case "grungebg":
    $bgstyle = "grungebg";
	break;
    case "noise":
    $bgstyle = "noise";
    break;
    case "noisedark":
    $bgstyle = "noisedark";
    break;
    default:
    $bgstyle = "grungebg";
}
?>