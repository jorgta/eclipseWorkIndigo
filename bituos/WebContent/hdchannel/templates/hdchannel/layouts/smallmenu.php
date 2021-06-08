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

if ($this->countModules('smallmenu')) { ?> 
     	<div id="smallmenu_holder" style="font-size:<?php echo $css_font; ?>;width:<?php echo $css_width.$css_widthdefined; ?>;">
			<div id="smallmenu">
				<jdoc:include type="modules" name="smallmenu" style="yjplain" />
			</div>
		</div>
<?php } ?>