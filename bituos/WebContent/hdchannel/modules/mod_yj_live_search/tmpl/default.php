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
// no direct access
defined('_JEXEC') or die('Restricted access');
?>
<!-- http://www.Youjoomla.com  Yj Live Live Search 2.0 for Joomla 2.5 starts here -->
<div class="yjlivecont <?php echo $is_copy?>">
       <form method="get" action="<?php echo JURI :: base() ?>index.php">
			<input type="text" name="searchword" id="<?php echo $is_copy?>LiveSearch" style="width:<?php echo $input_width ?>px;" value="<?php echo $default_input; ?>" onfocus="this.value='';" onblur="if(this.value==''){this.value='<?php echo $default_input; ?>'};"  class="yj_inputbox"/>
            <?php if($search_button == 1){?>
            <input type="submit" value="search" class="button" />
            <?php } ?>
            <input type="hidden" name="option" value="com_search" />
			<input type="hidden" name="searchphrase" value="all" />
            <input type="hidden" name="Itemid" value="<?php echo $mitemid; ?>" />
		</form>
</div>
<!-- http://www.Youjoomla.com  Yj Live Live Search 2.0  for Joomla 2.5 ends here -->