<?php
/*======================================================================*\
|| #################################################################### ||
|| # Copyright (C) 2006-2010 Youjoomla.com. All Rights Reserved.        ||
|| # This file may not be redistributed in whole or significant part. # ||
|| # ---------------- THIS IS NOT FREE SOFTWARE ---------------- #      ||
|| # http://www.youjoomla.com | http://www.youjoomla.com/license.html # ||
|| #################################################################### ||
\*======================================================================*/
// no direct access
defined('_JEXEC') or die('Restricted access'); ?>
<!-- http://www.Youjoomla.com  Youjoomla Multitabs Modules for Joomla 1.6 starts here -->
<div id="tabs_holder<?php echo $is_copy ?>">
 <div id="tabs_container<?php echo $is_copy ?>">
	<ul id="tabs<?php echo $is_copy ?>">
		<?php  for($t = 0;$t<count($titles);$t++){
			if($t == count($titles) - 1){
				$add_class = ' class="last"';
			}else{
				$add_class='';
				
			}
			 ?>
			<li<?php echo $add_class ?> style="width:<?php echo $li_width ?>"><?php echo $titles[$t]?> </li>
		<?php } ?>
	</ul>
<?php  for($m = 0;$m<count($tab2mods);$m++){

		$tabs2_out = JModuleHelper::getModules($tab2mods[$m]);
		
			$getn = count(array_keys($tabs2_out));
			foreach (array_keys($tabs2_out) as $key=>$o) {
				if($m ==0){
					$first = ' first_tab';
				}else{
					$first = ' s_tab';
				}
				$getmodule 				= JModuleHelper::getModule( ''.$tabs2_out[$o]->name.'', ''.$tabs2_out[$o]->title.'' );
				$mt_attribs['style'] 	= 'raw';
				$mt_module 				= JModuleHelper::renderModule( $getmodule, $mt_attribs );
			?>
 		<div class="tab_content<?php echo $is_copy ?><?php echo $first ?>">
 			<div class="tab_content_in<?php echo $is_copy ?><?php echo $first ?>"<?php echo $tab_height ?>>
				<?php echo $mt_module  ?>
			</div>
		</div>
<?php } } ?> 
	</div>
</div>