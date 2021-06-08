<?php
/*======================================================================*\
|| #################################################################### ||
|| # Package - Joomla Template based on YJSimpleGrid Framework          ||
|| # Copyright (C) since 2007  Youjoomla.com . All Rights Reserved.     ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/
/* 
	Joomla! Template Based on YJSG Framework @since 2.0.0
*/
defined( '_JEXEC' ) or die( 'Restricted index access' );

if (!defined( 'YJSGRUN' )) {
	echo JText::_('YJSG_PLUGIN_NOT_FOUND');
	exit;
}
require( YJSGCORE_PATH );/* YJSGFramework main functions*/
?>
<!DOCTYPE html>
<html xml:lang="<?php echo $this->language; ?>" lang="<?php echo $this->language; ?>" dir="<?php echo $this->direction; ?>" class="<jdoc:include type="htmlclass" />">
<head>
<?php require( YJSG_HEAD );/* <head> files containing css , js and conditions */?>
</head>
<body id="stylef<?php echo $default_font_family ?>" class="yjsgbody style_<?php echo $css_file.$browserClassName.$rtlClass ?>">
	<?php require( TEMPLATEPATH.DS."layouts/smallmenu.php"); /* small menu */ ?>
	<div id="centertop" class="yjsgsitew">
		<?php require( YJSG_HEADERBLOCK );/* header - header grid */?>
		<?php 
		if($topMenuLocation == 0 ){ /* if topmenu location is inside the header we dont need it here */
			require( YJSG_TOPMENU );/* top menu */
		}
		?>
	</div>
	<!-- end centartop-->
	<div id="centerbottom" class="yjsgsitew">
		<div class="centerbottom_in">
			<?php yjsg_print_grid_area('yjsg1'); /* grid 1 top1=top5 */ ?>
			<?php yjsg_print_grid_area('yjsg2');/* grid 2 adv1-adv5*/ ?>
			<?php yjsg_print_grid_area('yjsg3');/*grid 3 user1-user5*/ ?>
			<?php yjsg_print_grid_area('yjsg4');/* grid4 user6-user10*/ ?>
			<?php require( $yjsg_loadlayout );/* mid grid - mainbody grids*/?>
			<?php require( YJSG_PATHWAY );/* pathway including breadcrumb module position */ ?>
			<?php yjsg_print_grid_area('yjsg5');/* grid 5 user11-user15*/ ?>
		</div>
	</div>
	<!-- end centerbottom-->
	<div id="centerfooter" class="yjsgsitew">
			<?php yjsg_print_grid_area('yjsg6',false,$yjsg6_before,$yjsg6_after);/* grid 6 user16-user20*/ ?>
			<?php yjsg_print_grid_area('yjsg7',false,$yjsg7_before,$yjsg7_after);/* grid 7 user21-user25*/ ?>
	</div>
	<?php require( YJSG_FOOTER );/* footer -  footer menu , copyright , YJSG logo , validation links*/?>
	<?php require( YJSG_NOTICES );/* IE6 and nonscript notices*/?>
	<?php 
    if($responsive_on == 1 && ($topmenu_off == 2 || $itemid == 0 )) {
        require( YJSG_MOBILEMENU );/* responsive menu select list loaded at the end . better for seo */
    }
    ?>
	<?php 
    if ($this->countModules('sidepanel') || $yjsgBotPanel_loaded || $yjsgTopPanel_loaded) { 
        require( YJSG_PANELS );/* Sliding panels */
    }
    ?>
	<?php 
    if ($this->countModules('offcanvas')) { 
        require( YJSG_OFFCANVAS );/* Off canvas panel */
    }
    ?>
</body>
</html>