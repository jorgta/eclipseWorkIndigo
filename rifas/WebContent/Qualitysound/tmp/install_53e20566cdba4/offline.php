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
defined( '_JEXEC' ) or die( 'Restricted access' );
$app 							= JFactory::getApplication();
$yjsg_params 					= $app->getTemplate(true)->params;
$template 						= $this->template;
$check_style_param 				= $yjsg_params->get("yjsg_get_styles");
if(isset($check_style_param)){
	$get_style_value			= explode('|',$yjsg_params->get("yjsg_get_styles"));
	$yjsg_get_styles			= $get_style_value[0];	
	$default_link_color			= $get_style_value[1];
	$site_link_color			= '#'.$default_link_color;	
}else{
 	$yjsg_get_styles			= $yjsg_params->get("default_color");
	$default_link_color			= '';
}
$default_font_family          	= $yjsg_params->get("default_font_family");
$logo_height                  	= $yjsg_params->get("logo_height");
$default_font                 	= $yjsg_params->get("default_font");
require( YJSGCORE_PATH );

if(intval(JVERSION) >= 3 && $app->getCfg('offline_image')){
	$logo = JURI::base().$app->getCfg('offline_image');
}else{
	$logo 		= $this->baseurl.'/templates/'.$template.'/images/'.$yjsg_get_styles.'/logo.png';
	if($yjsg_params->get("logo_image")){
	  $logo 	= JURI::base().$yjsg_params->get("logo_image");
	}
}
?>
<!DOCTYPE html>
<html xml:lang="<?php echo $this->language; ?>" lang="<?php echo $this->language; ?>" dir="<?php echo $this->direction; ?>" class="yjsg-page-offline">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title><?php echo $app->getCfg('sitename') ?></title>
		<?php if($app->getCfg('MetaDesc')): ?>
		<meta name="description" content="<?php echo $app->getCfg('MetaDesc') ?>" />
		<?php endif ; ?>
		<?php if($app->getCfg('MetaKeys')): ?>
		<meta name="keywords" content="<?php echo $app->getCfg('MetaKeys') ?>" />
		<?php endif ; ?>
		<link href="<?php echo JURI::base(); ?>templates/<?php echo $template ?>/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
		<link href="<?php echo JURI::base(); ?>plugins/system/yjsg/legacy/css/template.css" rel="stylesheet" type="text/css" />
		<link href="<?php echo JURI::base(); ?>templates/<?php echo $template ?>/css/<?php echo $yjsg_get_styles; ?>.css" rel="stylesheet" type="text/css" />
		
		<?php if($bootstrap_here) { ?>
		<link href="<?php echo YJSG_ASSETS; ?><?php echo $bootstrap_version ?>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<?php } ?>
		<style type="text/css">a{color:#<?php echo $default_link_color?>;}</style>
		
	</head>
	<body id="stylef<?php echo $default_font_family ?>" class="yjsg-offline">
		<div id="frame">
			<jdoc:include type="message" />
			<div class="userpageswrap offline">
				<div class="userpages">
					<div id="header" style="height:<?php echo $logo_height?>;">
						<img src="<?php echo $logo ?>" alt="site_logo" />
					</div>
					<form action="index.php" method="post" name="login" id="form-login">
						<fieldset class="input">
							<p class="lead yjsgcenter">
								<?php echo $app->getCfg('offline_message'); ?>
							</p>
							<p id="form-login-username">
								<label for="username"><?php echo JText::_('YJSG_USERNAME') ?></label>
								<br />
								<input name="username" id="username" type="text" class="inputbox" alt="<?php echo JText::_('YJSG_USERNAME') ?>" size="18" />
							</p>
							<p id="form-login-password">
								<label for="password"><?php echo JText::_('YJSG_PASSWORD') ?></label>
								<br />
								<input type="password" name="password" class="inputbox" size="18" alt="<?php echo JText::_('YJSG_PASSWORD') ?>" id="passwd" />
							</p>
							<p id="form-login-remember">
								<label for="remember"><?php echo JText::_('YJSG_REMEMBER') ?></label>
								<input type="checkbox" name="remember" class="remeberbox" value="yes" alt="<?php echo JText::_('YJSG_REMEMBER') ?>" id="remember" />
							</p>
							<button type="submit" tabindex="3" name="Submit" class="btn btn-small button"><?php echo JText::_('YJSG_LOGIN') ?></button>
						</fieldset>
						<input type="hidden" name="option" value="com_users" />
						<input type="hidden" name="option" value="com_users" />
						<input type="hidden" name="task" value="user.login" />
						<input type="hidden" name="return" value="<?php echo base64_encode(JURI::base()) ?>" />
						<?php echo JHtml::_('form.token'); ?>
					</form>
				</div>
			</div>
		</div>
			<script type="text/javascript" src="<?php echo YJSG_ASSETS.'src/libraries/jquery.min.js' ?>"></script>
		<script type="text/javascript" src="<?php echo YJSG_ASSETS.'src/libraries/jquery-noconflict.js' ?>"></script>
		<?php if($bootstrap_here) { ?>
		<script type="text/javascript" src="<?php echo YJSG_ASSETS.$bootstrap_version.'/js/bootstrap.min.js' ?>"></script>
		<?php } ?>
		<script type="text/javascript" src="<?php echo YJSG_ASSETS.'src/yjsg.site.plugins.js' ?>"></script>
		<script type="text/javascript" src="<?php echo YJSG_ASSETS.'src/yjsg.site.js' ?>"></script>
		<script type="text/javascript">
			var logo_w = <?php echo $logo_per_width ?>;
			var site_w = <?php echo $css_width ?>;
			var site_f = '<?php echo $css_font ?>';
			var sp='<?php echo $sp ?>';
			var tp ='<?php echo $this->template ?>';
			var compileme =<?php echo $compileme ?>;
			var fontc ='<?php echo $fontc ?>';
			var yver ='<?php echo $jver[0] ?>';
			var yjsglegacy='1';
			var yjsgrtl='<?php echo $text_direction ?>';
			var bootstrapv='<?php echo $bootstrap_version ?>';
			var offcanvas=0;
			var offcanvasW='<?php echo $offCanvasWidth ?>';
		</script>
	</body>
</html>