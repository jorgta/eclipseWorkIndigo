<?php
/**
 * @version		$Id: user.php 1248 2011-10-19 17:15:18Z joomlaworks $
 * @package		K2
 * @author		JoomlaWorks http://www.joomlaworks.gr
 * @copyright	Copyright (c) 2006 - 2011 JoomlaWorks Ltd. All rights reserved.
 * @license		GNU/GPL license: http://www.gnu.org/copyleft/gpl.html
 */

// no direct access
defined('_JEXEC') or die('Restricted access');

// Get user stuff (do not change)
$user = &JFactory::getUser();

?>

<!-- Start K2 User Layout -->

<div id="k2Container" class="userView<?php if($this->params->get('pageclass_sfx')) echo ' '.$this->params->get('pageclass_sfx'); ?> worldtour">

	<?php if($this->params->get('show_page_title') && $this->params->get('page_title')!=$this->user->name): ?>
	<!-- Page title -->
	<div class="componentheading<?php echo $this->params->get('pageclass_sfx')?>">
		<?php echo $this->escape($this->params->get('page_title')); ?>
	</div>
	<?php endif; ?>

	<?php if($this->params->get('userFeed')): ?>
	<!-- RSS feed icon -->
	<div class="k2FeedIcon">
		<a href="<?php echo $this->feed; ?>" title="<?php echo JText::_('K2_SUBSCRIBE_TO_THIS_RSS_FEED'); ?>">
			<span><?php echo JText::_('K2_SUBSCRIBE_TO_THIS_RSS_FEED'); ?></span>
		</a>
		<div class="clr"></div>
	</div>
	<?php endif; ?>

	<?php if ($this->params->get('userImage') || $this->params->get('userName') || $this->params->get('userDescription') || $this->params->get('userURL') || $this->params->get('userEmail')): ?>
	<div class="userBlock">
	
		<?php if(isset($this->addLink) && JRequest::getInt('id')==$user->id): ?>
		<!-- Item add link -->
		<span class="itemAddLink">
			<a class="modal" rel="{handler:'iframe',size:{x:990,y:550}}" href="<?php echo $this->addLink; ?>">
				<?php echo JText::_('K2_POST_A_NEW_ITEM'); ?>
			</a>
		</span>
		<?php endif; ?>
	
		<?php if ($this->params->get('userImage') && !empty($this->user->avatar)): ?>
		<img src="<?php echo $this->user->avatar; ?>" alt="<?php echo $this->user->name; ?>" style="width:<?php echo $this->params->get('userImageWidth'); ?>px; height:auto;" />
		<?php endif; ?>
		
    <div class="comment_holder">
		<?php if ($this->params->get('userName')): ?>
		<h2><?php echo $this->user->name; ?></h2>
		<?php endif; ?>
		
		<?php if ($this->params->get('userDescription') && isset($this->user->profile->description)): ?>
		<p class="userDescription"><?php echo $this->user->profile->description; ?></p>
		<?php endif; ?>
		
		<?php if ($this->params->get('userURL') || $this->params->get('userEmail')): ?>
		<p class="userAdditionalInfo">
			<?php if ($this->params->get('userURL') && isset($this->user->profile->url) && $this->user->profile->url): ?>
			<span class="userURL">
				<?php echo JText::_('K2_WEBSITE_URL'); ?>: <a href="<?php echo $this->user->profile->url; ?>" target="_blank" rel="me"><?php echo $this->user->profile->url; ?></a>
			</span>
			<?php endif; ?>

			<?php if ($this->params->get('userEmail')): ?>
			<span class="userEmail">
				<?php echo JText::_('K2_EMAIL'); ?>: <?php echo JHTML::_('Email.cloak', $this->user->email); ?>
			</span>
			<?php endif; ?>	
		</p>
		<?php endif; ?>
    </div>        

		<div class="clr"></div>
		
		<?php echo $this->user->event->K2UserDisplay; ?>
		
		<div class="clr"></div>
	</div>
	<?php endif; ?>



	<?php if(count($this->items)): ?>
	<!-- Item list -->
	<div class="itemList">
		<?php foreach ($this->items as $item): ?>
		
		<!-- Start K2 Item Layout -->
		<div class="itemView<?php if(!$item->published || ($item->publish_up != $this->nullDate && $item->publish_up > $this->now) || ($item->publish_down != $this->nullDate && $item->publish_down < $this->now)) echo ' userItemViewUnpublished'; ?><?php echo ($item->featured) ? ' userItemIsFeatured' : ''; ?>">
		
			<!-- Plugins: BeforeDisplay -->
			<?php echo $item->event->BeforeDisplay; ?>
			
			<!-- K2 Plugins: K2BeforeDisplay -->
			<?php echo $item->event->K2BeforeDisplay; ?>
		
			<div class="itemHeader">			
			  <?php if($item->params->get('userItemTitle')): ?>
			  <!-- Item title -->
			  <h3 class="itemTitle">
					<?php if(isset($item->editLink)): ?>
					<!-- Item edit link -->
					<span class="itemEditLink">
						<a class="modal" rel="{handler:'iframe',size:{x:990,y:550}}" href="<?php echo $item->editLink; ?>">
							<?php echo JText::_('K2_EDIT_ITEM'); ?>
						</a>
					</span>
					<?php endif; ?>

			  	<?php if ($item->params->get('userItemTitleLinked') && $item->published): ?>
					<a href="<?php echo $item->link; ?>">
			  		<?php echo $item->title; ?>
			  	</a>
			  	<?php else: ?>
			  	<?php echo $item->title; ?>
			  	<?php endif; ?>
			  	<?php if(!$item->published || ($item->publish_up != $this->nullDate && $item->publish_up > $this->now) || ($item->publish_down != $this->nullDate && $item->publish_down < $this->now)): ?>
			  	<span>
		  			<sup>
		  				<?php echo JText::_('K2_UNPUBLISHED'); ?>
		  			</sup>
	  			</span>
	  			<?php endif; ?>
			  </h3>
			  <?php endif; ?>
              
		  </div>
		
		  <!-- Plugins: AfterDisplayTitle -->
		  <?php echo $item->event->AfterDisplayTitle; ?>
		  
		  <!-- K2 Plugins: K2AfterDisplayTitle -->
		  <?php echo $item->event->K2AfterDisplayTitle; ?>

		  <div class="itemBody">
		
			  <!-- Plugins: BeforeDisplayContent -->
			  <?php echo $item->event->BeforeDisplayContent; ?>
			  
			  <!-- K2 Plugins: K2BeforeDisplayContent -->
			  <?php echo $item->event->K2BeforeDisplayContent; ?>
		
			  <?php if($item->params->get('userItemImage') && !empty($item->imageGeneric)): ?>
			  <!-- Item Image -->
			  <div class="itemImageBlock" style="width:<?php echo $item->params->get('itemImageGeneric'); ?>px; height:auto;">
				  <span class="itemImage">
				    <a href="<?php echo $item->link; ?>" title="<?php if(!empty($item->image_caption)) echo K2HelperUtilities::cleanHtml($item->image_caption); else echo K2HelperUtilities::cleanHtml($item->title); ?>">
                        <img class="yjgrayscale" src="<?php echo $item->imageGeneric; ?>" alt="<?php if(!empty($item->image_caption)) echo K2HelperUtilities::cleanHtml($item->image_caption); else echo K2HelperUtilities::cleanHtml($item->title); ?>" style="width:<?php echo $item->params->get('itemImageGeneric'); ?>px; height:auto;" />
                    </a>
				  </span>
				  <div class="clr"></div>
			  </div>
			  <?php endif; ?>
				
				<?php if($item->params->get('userItemDateCreated')): ?>
				<!-- Date created -->
				<span class="itemDateCreated">
					<?php echo JHTML::_('date', $item->created , JText::_('YJ_DATE_FORMAT')); ?>
				</span>
				<?php endif; ?>

				<?php if($item->params->get('userItemCategory')): ?>
				<!-- Item category name -->
				<div class="itemCategory">
					<span><?php echo JText::_('K2_PUBLISHED_IN'); ?></span>
					<a href="<?php echo $item->category->link; ?>"><?php echo $item->category->name; ?></a>
				</div>
				<?php endif; ?>
			
			<?php if($item->params->get('userItemCommentsAnchor') && ( ($item->params->get('comments') == '2' && !$this->user->guest) || ($item->params->get('comments') == '1')) ): ?>
			<!-- Anchor link to comments below -->
			<div class="itemCommentsLink">
				<?php if(!empty($item->event->K2CommentsCounter)): ?>
					<!-- K2 Plugins: K2CommentsCounter -->
					<?php echo $item->event->K2CommentsCounter; ?>
				<?php else: ?>
					<?php if($item->numOfComments > 0): ?>
					<a href="<?php echo $item->link; ?>#itemCommentsAnchor">
						<?php echo $item->numOfComments; ?> <?php echo ($item->numOfComments>1) ? JText::_('K2_COMMENTS') : JText::_('K2_COMMENT'); ?>
					</a>
					<?php else: ?>
					<a href="<?php echo $item->link; ?>#itemCommentsAnchor">
						<?php echo JText::_('K2_BE_THE_FIRST_TO_COMMENT'); ?>
					</a>
					<?php endif; ?>
				<?php endif; ?>
			</div>
			<?php endif; ?>
		  
			  <?php if($item->params->get('userItemIntroText')): ?>
			  <!-- Item introtext -->
			  <div class="itemIntroText">
			  	<?php echo $item->introtext; ?>
			  </div>
			  <?php endif; ?>
		
				<div class="clr"></div>

			  <!-- Plugins: AfterDisplayContent -->
			  <?php echo $item->event->AfterDisplayContent; ?>
			  
			  <!-- K2 Plugins: K2AfterDisplayContent -->
			  <?php echo $item->event->K2AfterDisplayContent; ?>
		
			  <div class="clr"></div>
		  </div>
		
		  <?php if($item->params->get('userItemCategory') || $item->params->get('userItemTags')): ?>
		  <div class="itemLinks">

			  <?php if($item->params->get('userItemTags') && count($item->tags)): ?>
			  <!-- Item tags -->
			  <div class="itemTagsBlock">
				  <span><?php echo JText::_('K2_TAGGED_UNDER'); ?></span>
				  <ul class="itemTags">
				    <?php foreach ($item->tags as $tag): ?>
				    <li><a href="<?php echo $tag->link; ?>"><?php echo $tag->name; ?></a></li>
				    <?php endforeach; ?>
				  </ul>
				  <div class="clr"></div>
			  </div>
			  <?php endif; ?>

				<div class="clr"></div>
		  </div>
		  <?php endif; ?>  
		
			<div class="clr"></div>

			<?php if ($item->params->get('userItemReadMore')): ?>
			<!-- Item "read more..." link -->
			<div class="itemReadMore">
				<a class="k2ReadMore" href="<?php echo $item->link; ?>">
					<?php echo JText::_('YJ_READ_MORE'); ?>
				</a>
			</div>
			<?php endif; ?>
			
			<div class="clr"></div>

		  <!-- Plugins: AfterDisplay -->
		  <?php echo $item->event->AfterDisplay; ?>
		  
		  <!-- K2 Plugins: K2AfterDisplay -->
		  <?php echo $item->event->K2AfterDisplay; ?>
			
			<div class="clr"></div>
		</div>
		<!-- End K2 Item Layout -->
		
		<?php endforeach; ?>
	</div>

	<!-- Pagination -->
	<?php if(count($this->pagination->getPagesLinks())): ?>
	<div class="k2Pagination">
		<?php echo $this->pagination->getPagesLinks(); ?>
		<div class="clr"></div>
		<?php //echo $this->pagination->getPagesCounter(); ?>
	</div>
	<?php endif; ?>
	
	<?php endif; ?>

</div>

<!-- End K2 User Layout -->
