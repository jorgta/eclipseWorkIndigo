<?php 
/*======================================================================*\
|| #################################################################### ||
|| # Package - YJ Tag Cloud										        ||
|| # Copyright (C) 2013  Youjoomla.com. All Rights Reserved.            ||
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
<!-- http://www.Youjoomla.com  Yj Tag Cloud for Joomla  starts here -->
<div id="yj_tagcloud" class="yj_tagcloud<?php echo $moduleclass_sfx; ?>">
<?php 
	if(!empty($tags[0])){
		foreach($tags[0] as $tags_row => $tags_name):
		
			//show only 20 characters from tags, settings of com_search component
			if(strlen($tags_name) > 20 ){
				$tags_name = substr($tags_name,0,20)."...";
			}else{
				 $tags_name = $tags_name;
			}
			$tagLink = JRoute::_('index.php?searchword='.$tags_name.'&amp;ordering=newest&amp;searchphrase=all&amp;'.$tagIemid.'option=com_search');
			
			$addSize = '';
			if($randomSize == 1){
				
				$addSize = ' yjtagsize'.$tags[1][$tags_row].'';
			}
			
?>
<a class="yjtag<?php echo $addSize ?>" href='<?php echo $tagLink ?>' title="<?php echo $tags_name ?>"><?php echo $tags_name ?></a>
<?php endforeach; }else{ ?>
You have no tags. Please add some tags in your items Meta Keywords textarea or add own tags in module manager.
<?php } ?>
</div>
<!-- http://www.Youjoomla.com YJ Tag Cloud for Joomla ends here -->