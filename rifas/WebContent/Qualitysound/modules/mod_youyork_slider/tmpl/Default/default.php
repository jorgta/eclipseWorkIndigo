<?php
/*======================================================================*\
|| #################################################################### ||
|| # Copyright (C) 2006-2010 Youjoomla.com. All Rights Reserved.        ||
|| # This file may not be redistributed in whole or significant part. # ||
|| # Copyright (C) since 2007 Youjoomla.com. All Rights Reserved.       ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/
// no direct access//
defined('_JEXEC') or die('Restricted access'); 
$chek_module = JModuleHelper::getModule( 'youyork_slider' );?>
<!-- http://www.Youjoomla.com  Youyork Module Slider for Joomla 1.6 and UP starts here -->
<div class="yy_container_out">
    <div id="yy_container<?php echo $is_copy ?>" class="yy_container" style="width:<?php echo $slider_width ?>;height:<?php echo $slider_height ?>;">
        <a class="linkForward"></a>
            <div id="yy_slider<?php echo $is_copy ?>" class="yy_slider" style="width:<?php echo $slider_width -0 ?>px;height:<?php echo $slider_height ?>;">
                <?php  for($m = 0;$m<count($slide2mods);$m++){
                                $slide2_out = JModuleHelper::getModules($slide2mods[$m]);
                                foreach (array_keys($slide2_out) as $o) {
                                    
                                    $getmodule 				= JModuleHelper::getModule( ''.$slide2_out[$o]->name.'', ''.$slide2_out[$o]->title.'' );
                                    $mt_attribs['style'] 	= 'raw';
                                    
                                    if($slide2mods[$m] == $chek_module->position){
                                        $mt_module = 'You canot use module position '.$slide2mods[$m].'. It is taken by the slider ';
                                    }else{
                                        $mt_module 				= JModuleHelper::renderModule( $getmodule, $mt_attribs );
                                    }
                                    
                                ?>
                    <div class="yy_slideitems" style="width:<?php echo $items_width ?>px;height:<?php echo $yy_slideitems_height ?>px;">
                       <div class="yy_slideitems_in" style="height:<?php echo $yy_slideitems_height ?>px;">
                                <?php if ($showtitle == 1 ):?>
                                            <div class="yy_module_title">
                                                <?php echo $titles[$m] ?>
                                            </div>
                                <?php endif; ?>  
                            <?php echo $mt_module  ?>                
                       </div>
                    </div>
                <?php } } ?>  
            </div>
        <a class="linkBackward"></a>
    </div>
    <?php if($show_bottom_nav == 1){ ?>
    <div class="yy_bottom_nav_holder">
    	<div class="yy_ul_left">
          <ul id="yy_bottom-nav<?php echo $is_copy ?>" class="yy_bottom_navigation">
                   <?php  $add_active = '';
                       $number_of_slides = count($slide2mods) /$visible_modules;
                       $number_of_slides = round($number_of_slides);
                       $number_of_slides = range(1,$number_of_slides);
                      foreach($number_of_slides as $nav_link){ 
                      if($nav_link==1){
                          $add_active=" active";
                      }else{
                          $add_active ='';
                      }
                   ?>
                   <li><a href="#" title="" class="yy_nav<?php echo $add_active?>"><span><?php echo $nav_link ?></span></a></li>
                   <?php }?>
          </ul>
       </div>
    </div>
    <?php } ?>
</div>