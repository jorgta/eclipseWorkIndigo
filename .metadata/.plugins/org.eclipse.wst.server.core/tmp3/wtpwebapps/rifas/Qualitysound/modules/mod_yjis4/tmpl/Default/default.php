<?php
/**
 * @package		YJ Images Slider V4.0
 * @author		Youjoomla.com
 * @website     Youjoomla.com 
 * @copyright	Copyright (c) since 2007 Youjoomla.com.
 * @license   PHP files are GNU/GPL V2. CSS / JS / IMAGES are Copyrighted Commercial
 */
defined('_JEXEC') or die('Restricted access'); 

?>
<!-- http://www.Youjoomla.com  YJ Images Slider V4.0 Module for Joomla 1.6 and UP starts here -->
<div id="YJSlide_outer<?php echo $is_copy?>" class="slide YJIS_outer <?php echo $class_fx;?>" style="width:<?php echo $swidth;?>px; height:<?php echo $sheight;?>px;">
	<span id="slidesinfo_<?php echo $is_copy?>" class="yjis_slidesinfo getw_<?php echo $swidth;?>"><?php echo $sheight;?></span>
	<div id="YJSlide_inner<?php echo $is_copy?>" class="YJIS_inner" style="width:<?php echo $swidth;?>px;">
	<?php foreach ($slides as $key=> $slide):
	 if($key == 0){
		 $elactive =' starter active';
	 }else{
		 $elactive =' starter inactive';
	 }
	 
	 if($tooltips == 1 &&(!empty($slide['title']) && !empty($slide['description']))){
		 
		 $image_tooltip =' class="YJS_link yjisImg" title="'.$slide['title'].'::'.$slide['description'].'"';
		
	 }else{
		 
		 $image_tooltip =' class="yjisImg"';
	 }
	 if(!empty($slide['mboxlink']) &&(!empty($slide['title']) && !empty($slide['description']))){
		 $mbox_title = ' title="'.$slide['title'].'::'.$slide['description'].'"';
	 }else{
		 $mbox_title = '';
	 }
	 if($slide['image_group'] == 1){
		 $slides_groups =$slide['image_group_name'];
	 }else{
		 $slides_groups =$slide['image'];
	 }
	 if($slide['mboxtype']==1){
		 $video_size = ' '.$slide['video_width'].' '.$slide['video_height'];
	 }else{
		 $video_size ='';
	 }
	?>
		<div class="YJSlide_slide<?php echo $elactive?> YJS_slidesholder" style="width:<?php echo $swidth;?>px; height:<?php echo $sheight;?>px;">
		<?php if($slide['link'] || !empty($slide['mboxlink'])):?>
           <?php if(!empty($slide['mboxlink']) && empty($slide['link'])){?>
			<a href="<?php echo $slide['mboxlink']?>" class="popbox YJS_mboxp"<?php echo $mbox_title?>>       
            <span class="YJS_reldata">lightbox[<?php echo $slides_groups.$video_size?>]</span>
            <?php }else{ ?>
            <a href="<?php echo $slide['link']?>"  target="<?php echo $slide['open']==0 ? '_blank':'_self';?>">  
            <?php }?>	
		<?php endif;?>	

			<img src="<?php echo JURI::base().$slide['image'];?>" <?php echo  $image_tooltip ?> alt="<?php echo $slide['title'];?>" />
		<?php if($slide['link'] || !empty($slide['mboxlink'])):?></a><?php endif;?>
			
		<?php if ($slide['description'] && $hide_all_descriptions == 0):?>
			<div class="YJSlide_description"><?php echo $slide['description'];?></div>
		<?php endif;?>
		
		<?php if ($slide['intro'] && $hide_all_intros == 0):?>
			<div class="YJSlide_intro">
				<div class="YJSlide_intro_in">
					<?php echo $slide['intro'];?>
                </div>
            </div>
		<?php endif;?>
		
		</div>
		
	<?php endforeach;?>
	</div>
	<?php if($smenu==0):?>
	<a href="javascript:;" class="slidearrows slideLeft" title="previous" id="YJS_left<?php echo $is_copy?>"></a>
	<a href="javascript:;" class="slidearrows slideRight" title="next" id="YJS_right<?php echo $is_copy?>"></a>
	<?php endif;?>
    <?php if($thumb_pagination == 1): ?>
   <div id="thumbContainer<?php echo $is_copy?>" class="thumbContainer<?php echo $beltDir ?>" style="width:<?php echo $swidth;?>px;<?php echo $beltPosition?>">
   		<?php if($thumb_arrows == 1 ) : ?>
   		<a href="javascript:;" title="previous" id="thumbContainer_left<?php echo $is_copy?>" class="thumbContainer_left tharrows"></a> 
   		<a href="javascript:;" title="next" id="thumbContainer_right<?php echo $is_copy?>" class="thumbContainer_right tharrows"></a>
        <?php endif;?>
        <div id="thumbContainer_outer<?php echo $is_copy?>" class="thumbContainer_outer">
          <div id="thumbContainer_belt<?php echo $is_copy?>" class="thumbContainer_belt">
            <?php foreach ($slides as $key=>$slide):
            
               if($key == 0){
                   $active =' selected';
               }else{
                   $active ='';
               }
			   
			   if(isset($slide['gen_thumbnail']) && isset($slide['thumbnail'])){
				   $thumb_to_use = $slide['gen_thumbnail'];
				   
			   }elseif(isset($slide['gen_thumbnail']) && !isset($slide['thumbnail'])){
				   
				   $thumb_to_use = $slide['gen_thumbnail'];
				   
			   }else{
				   
				   $thumb_to_use = $slide['thumbnail'];
			   }
			   
			   $check_thumb = JPATH_BASE.DIRECTORY_SEPARATOR.$thumb_to_use;

               if (isset($thumb_to_use) && JFile::exists($check_thumb)){
                   $thumb_image = JURI::base().$thumb_to_use;
               }else{
                   $thumb_image = JURI::base().$slide['image'];
               }		
			   if($thumbs_tooltips == 1 && !empty($slide['title'])){
				   $thumb_tooltip 		=' title="Navigate to slide::'.$slide['title'].'"';
				   $thumb_tooltip_class =' YJS_link';
			   }else{
				   $thumb_tooltip 		='';
				   $thumb_tooltip_class ='';
			   }           
            ?>
              <a href="javascript:;" style="width:<?php echo $thumb_width?>px;height:<?php echo $thumb_height?>px;" class="thumbContainer_thumbLink<?php echo $is_copy?> thumb <?php echo $active.$thumb_tooltip_class ?>"<?php echo $thumb_tooltip ?>>
                        <img src="<?php echo $thumb_image ?>" alt="<?php echo $slide['title'];?>" width="<?php echo $thumb_width?>" height="<?php echo $thumb_height?>" />
                        </a>
            <?php endforeach;?>
          </div>
        </div>
      	<div class="clr_c"></div>
      </div>
    <?php endif;?>
    
</div>
<?php if($pagination==0):?>
<div id="navContainer<?php echo $is_copy?>" class="navContainer" style="width:<?php echo $swidth;?>px;">
	<?php if($pagination_counter == 1 ){ ?>
	<span id="YJS_nav_info<?php echo $is_copy?>" class="nav_info">Link 1 of <?php echo count($slides);?></span>
    <?php } ?>
	<?php foreach ($slides as $key=>$slide):
	 if($key == 0){
		 $active =' active';
	 }else{
		 $active ='';
	 }
	 
	 if($navs_tooltips == 1 && !empty($slide['title'])){
		 $nav_tooltip		=' title="Navigate to slide::'.$slide['title'].'"';
		 $nav_tooltip_class =' YJS_link';
	 }else{
		 $nav_tooltip 		='';
		 $nav_tooltip_class	='';
	 }

	?>
	<a href="#" class="YJS_navLink<?php echo $is_copy?> bnav<?php echo $active.$nav_tooltip_class ?>"<?php echo $nav_tooltip?>>
		<?php echo $key+1;?>
    </a>
	<?php endforeach;?>
</div>
<?php endif;?>