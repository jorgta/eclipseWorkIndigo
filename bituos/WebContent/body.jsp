
<div id="holder2" class="holders">
	<!-- messages -->
	<div class="yjsg-system-msg">
		
	</div>
	<!-- end messages -->
		<!-- MID BLOCK -->
	<div id="midblock" class="sidebars sidebar-main">
		<div class="insidem">
			
	 <s:if test="#session.listModule != null"> 
	 <s:iterator value="#session.listModule" var="modules">
	     <s:if test="module == 'mod_main_text'">
	        <s:if test="active != 0"> 	
	               <s:iterator value="#session.listCategory" var="category" status="stat">
	                        
	                        <s:if test="module_id.module  == 'mod_main_text'">
	                        	
								   <s:if test="trash  == 0"> 			
										<div id="yjsgbodytop" class="yjsg_grid">
											<div id="bodytop1" class="yjsgxhtml only_mod yjsgsfx-custom1">
												<div class="yjsquare  custom1 modid98">
															<div class="h2_holder">
															    <h2 class="module_title">
															    
															    <span class="title_split titlesplit0"> <s:property value="name"/></span>										    
	<!-- 				 										        <span class="title_split titlesplit0">Morbi</span> <span class="title_split titlesplit1">facilisis</span> <span class="title_split titlesplit2">nunc</span> <span class="title_split titlesplit3">Etiam</span>  -->
															   </h2>
															</div>
															<div class="yjsquare_in">
	<!-- 														    <p>Nam vel diam fringilla diam dictum ultricies et eget elit. Aliquam erat volutpat. Donec purus tortor, eleifend in hendrerit sit amet, malesuada quis dui. Vivamus vel pulvinar diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas...</p> -->
															    <s:property value="description" escape="false"/>
															    <a class="template_readmore">Read more</a>
															</div>
												</div>
											</div>
										</div>	
								  </s:if> 
						    </s:if>
				  </s:iterator>
			</s:if>  		
		 </s:if> 								
	 </s:iterator>
	 </s:if> 









<!--  Multitabs Modules  -->
    <s:if test="#session.listModule != null"> 
	 <s:iterator value="#session.listModule" var="modules">
	     <s:if test="module == 'mod_tabs'">
	        <s:if test="active != 0"> 	<!--  Hidde or Show Multitabs Modules  -->

							<div id="yjsgbodybottom" class="yjsg_grid">
							<div id="bodybottom1" class="yjsgxhtml only_mod yjsgsfx-hdcmt">
							<div class="yjsquare  hdcmt modid100">
							
							
							
							
							<div class="yjsquare_in"><!-- http://www.Youjoomla.com  Youjoomla Multitabs Modules for Joomla 1.6 starts here -->
							<div id="tabs_holder">					 
							 <div id="tabs_container">
								<ul id="tabs">
								   
								   
								   <s:iterator value="#session.listCategory" var="category" status="stat">

								      <s:if test="module_id.module  == 'mod_tabs'"> 
								         <s:if test="ordering  == 1"> 
								              <s:if test="published  == 1"> 
											      <li style="width:120px"><s:property value="name"/></li>
											  </s:if>
										 </s:if>
										 <s:if test="ordering  != 1"> 
										      <s:if test="published  == 1"> 
											      <li style="width:120px"><s:property value="name"/></li>
											  </s:if>
										 </s:if>
									 		
									 		
									  </s:if>
								   </s:iterator>
<!-- 								   <li style="width:120px">In Theaters </li>
 								  <li style="width:120px">Coming Soon </li>
								  <li class="last" style="width:120px">Best of 2012 </li> -->  
								</ul>
								
								
					 		<div class="tab_content first_tab">
					 		<div class="tab_content_in first_tab"><!-- http://www.Youjoomla.com  Youyork Module Slider for Joomla 1.6 and UP starts here -->
							<div class="yy_container_out">
							    <div id="yy_container_intheaters" class="yy_container" style="width:770px;height:720px;">
							        <a class="linkForward"></a>
							            <div id="yy_slider_intheaters" class="yy_slider" style="width:770px;height:720px;">
							                                    <div class="yy_slideitems" style="width:770px;height:680px;">
							                       <div class="yy_slideitems_in" style="height:680px;">
							                                  
							                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
							<div class="yjme_holder">
							    <div class="yjme_item">
							    <div class="yjme_item_in">
							      <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/d61d44254608dd06ccdd2ff02982d14d_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven" >
							          <img src="hdchannel/media/k2/items/cache/d61d44254608dd06ccdd2ff02982d14d_M.jpg" alt="Safe Haven" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="/hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven">
							        Safe Haven      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							    </div>
							  </div>
							  
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem10">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/e31ace2a15a7c70645ad83df9ecd43b0_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/10-silver-linings-playbook"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/10-silver-linings-playbook" >
							          <img src="hdchannel/media/k2/items/cache/e31ace2a15a7c70645ad83df9ecd43b0_M.jpg" alt="Silver Linings Playbook" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/10-silver-linings-playbook">
							        Silver Linings Playbook      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem11">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/c82cc4e14a1d2c8c8ffff9840d24b558_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/11-a-good-day-to-die-hard"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/11-a-good-day-to-die-hard" >
							          <img src="hdchannel/media/k2/items/cache/c82cc4e14a1d2c8c8ffff9840d24b558_M.jpg" alt="A Good Day to Die Hard" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/11-a-good-day-to-die-hard">
							        A Good Day to Die Hard      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem12">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/3899dfe821816fbcb3db3e3b23f81585_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/12-dark-skies"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/12-dark-skies" >
							          <img src="hdchannel/media/k2/items/cache/3899dfe821816fbcb3db3e3b23f81585_M.jpg" alt="Dark Skies" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/12-dark-skies">
							        Dark Skies      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem7">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/9caa2793658f3cc387f216157300b1ce_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/7-the-last-exorcism"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/7-the-last-exorcism" >
							          <img src="hdchannel/media/k2/items/cache/9caa2793658f3cc387f216157300b1ce_M.jpg" alt="The Last Exorcism" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/7-the-last-exorcism">
							        The Last Exorcism      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        24-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem8 last">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/184b7cb84d7b456c96a0bdfbbeaa5f14_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/8-escape-from-planet-earth"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/8-escape-from-planet-earth" >
							          <img src="hdchannel/media/k2/items/cache/184b7cb84d7b456c96a0bdfbbeaa5f14_M.jpg" alt="Escape From Planet Earth" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/8-escape-from-planet-earth">
							        Escape From Planet Earth      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        24-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							  </div>                
							                       </div>
							                    </div>
							                                    <div class="yy_slideitems" style="width:770px;height:680px;">
							                       <div class="yy_slideitems_in" style="height:680px;">
							                                  
							                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
							<div class="yjme_holder">
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem1">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/2fa67f482133f1c934235b73c2a03954_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-item"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-item" >
							          <img src="hdchannel/media/k2/items/cache/2fa67f482133f1c934235b73c2a03954_M.jpg" alt="21 And Over" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-item">
							        21 And Over      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        23-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem5">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/ffee2447b152494b43d9816faaea83c8_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/5-jack-the-giant-slayer"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/5-jack-the-giant-slayer" >
							          <img src="hdchannel/media/k2/items/cache/ffee2447b152494b43d9816faaea83c8_M.jpg" alt="Jack the Giant Slayer" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/5-jack-the-giant-slayer">
							        Jack the Giant Slayer      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        24-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem6">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/ada9a09acea936d776a6f55c82778c43_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/6-identity-thief"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/6-identity-thief" >
							          <img src="hdchannel/media/k2/items/cache/ada9a09acea936d776a6f55c82778c43_M.jpg" alt="Identity Thief" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/6-identity-thief">
							        Identity Thief      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        24-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem2">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/e0a70f72bdae9885bfc32d7cd19a26a1_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/2-snitch"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/2-snitch" >
							          <img src="hdchannel/media/k2/items/cache/e0a70f72bdae9885bfc32d7cd19a26a1_M.jpg" alt="Snitch" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/2-snitch">
							        Snitch      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        24-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem3">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/94d43e327d9303539cb1e2aac7032668_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/3-dead-man-down"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/3-dead-man-down" >
							          <img src="hdchannel/media/k2/items/cache/94d43e327d9303539cb1e2aac7032668_M.jpg" alt="Dead Man Down" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/3-dead-man-down">
							        Dead Man Down      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        24-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem4 last">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/2ff2ba0051687eef5ca0459cf942940c_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death" >
							          <img src="hdchannel/media/k2/items/cache/2ff2ba0051687eef5ca0459cf942940c_M.jpg" alt="The ABCs of Death" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death">
							        The ABCs of Death      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        24-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							  </div>                
							                       </div>
							                    </div>
							                  
							            </div>
							        <a class="linkBackward"></a>
							    </div>
							    </div>			
							    </div>
								</div>
									
									
									
									
						    <div class="tab_content s_tab">
						   <div class="tab_content_in s_tab">
											<!-- http://www.Youjoomla.com  Youyork Module Slider for Joomla 1.6 and UP starts here -->
							<div class="yy_container_out">
							    <div id="yy_container_comingsoon" class="yy_container" style="width:770px;height:720px;">
							        <a class="linkForward"></a>
							            <div id="yy_slider_comingsoon" class="yy_slider" style="width:770px;height:720px;">
							                                    <div class="yy_slideitems" style="width:770px;height:680px;">
							                       <div class="yy_slideitems_in" style="height:680px;">
							                                  
							                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
							<div class="yjme_holder">
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem20">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/c889234799e865bbe90cee71f6cd2e53_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion" >
							          <img src="hdchannel/media/k2/items/cache/c889234799e865bbe90cee71f6cd2e53_M.jpg" alt="Oblivion" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion">
							        Oblivion      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem24">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/233826a67be66a810b23a263230da62e_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/24-the-last-stand"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/24-the-last-stand" >
							          <img src="hdchannel/media/k2/items/cache/233826a67be66a810b23a263230da62e_M.jpg" alt="The Last Stand" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/24-the-last-stand">
							        The Last Stand      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem19">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/f4b6dca0e2911082f0eb6e1df1a0e11d_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/19-star-trek-into-darkness"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/19-star-trek-into-darkness" >
							          <img src="hdchannel/media/k2/items/cache/f4b6dca0e2911082f0eb6e1df1a0e11d_M.jpg" alt="Star Trek into Darkness" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/19-star-trek-into-darkness">
							        Star Trek into Darkness      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem23">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/e2bf3b11df0b872112757f1c2fee6e32_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/23-hansel-and-gretel-witch-hunters"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/23-hansel-and-gretel-witch-hunters" >
							          <img src="hdchannel/media/k2/items/cache/e2bf3b11df0b872112757f1c2fee6e32_M.jpg" alt="Hansel and Gretel: Witch Hunters" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/23-hansel-and-gretel-witch-hunters">
							        Hansel and Gretel: Witch Hunters      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem21">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/9b2c4b44fb86522964124ed80d03c5e8_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/21-iron-man-3"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/21-iron-man-3" >
							          <img src="hdchannel/media/k2/items/cache/9b2c4b44fb86522964124ed80d03c5e8_M.jpg" alt="Iron Man 3" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/21-iron-man-3">
							        Iron Man 3      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem22 last">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/71f67488b0857639cee631943a3fc6fa_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/22-man-of-steel"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/22-man-of-steel" >
							          <img src="hdchannel/media/k2/items/cache/71f67488b0857639cee631943a3fc6fa_M.jpg" alt="Man of Steel" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/22-man-of-steel">
							        Man of Steel      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							  </div>                
							                       </div>
							                    </div>
							                                    <div class="yy_slideitems" style="width:770px;height:680px;">
							                       <div class="yy_slideitems_in" style="height:680px;">
							                                  
							                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
							<div class="yjme_holder">
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem13">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/48ee1e8a0a8f50dce4f8cb9ab418e211_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/13-oz-the-great-and-powerful"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/13-oz-the-great-and-powerful" >
							          <img src="hdchannel/media/k2/items/cache/48ee1e8a0a8f50dce4f8cb9ab418e211_M.jpg" alt="Oz the Great and Powerful" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/13-oz-the-great-and-powerful">
							        Oz the Great and Powerful      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem14">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/fd8b0f77d767f1f6640afba6916ff67c_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/14-admission"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/14-admission" >
							          <img src="hdchannel/media/k2/items/cache/fd8b0f77d767f1f6640afba6916ff67c_M.jpg" alt="Admission" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/14-admission">
							        Admission      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem15">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/fc1da7257992fc36032e11db3df7a664_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/15-bullet-to-the-head"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/15-bullet-to-the-head" >
							          <img src="hdchannel/media/k2/items/cache/fc1da7257992fc36032e11db3df7a664_M.jpg" alt="Bullet to the Head" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/15-bullet-to-the-head">
							        Bullet to the Head      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem18">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/4965657af186b9092c7a96976ffe881c_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/18-the-place-beyond-the-pines"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/18-the-place-beyond-the-pines" >
							          <img src="hdchannel/media/k2/items/cache/4965657af186b9092c7a96976ffe881c_M.jpg" alt="The Place Beyond the Pines" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/18-the-place-beyond-the-pines">
							        The Place Beyond the Pines      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem16">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/c9b002fe1bb0320831a8ae78670fdb6f_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/16-drak-circles"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/16-drak-circles" >
							          <img src="hdchannel/media/k2/items/cache/c9b002fe1bb0320831a8ae78670fdb6f_M.jpg" alt="Drak Circles" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/16-drak-circles">
							        Drak Circles      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        25-10-2011        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem17 last">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/077ab55046ce80eaf9a3ddea999597ca_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/17-g-i-joe-retaliation"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/17-g-i-joe-retaliation" >
							          <img src="hdchannel/media/k2/items/cache/077ab55046ce80eaf9a3ddea999597ca_M.jpg" alt="G.I. Joe: Retaliation" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/17-g-i-joe-retaliation">
							        G.I. Joe: Retaliation      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							  </div>                
							                       </div>
							                    </div>
							                  
							            </div>
							        <a class="linkBackward"></a>
							    </div>
							    </div>			</div>
									</div>
							
							
							
							
							
							
							
							<div class="tab_content s_tab">
							<div class="tab_content_in s_tab">
											<!-- http://www.Youjoomla.com  Youyork Module Slider for Joomla 1.6 and UP starts here -->
							<div class="yy_container_out">
							    <div id="yy_container_bestof2012" class="yy_container" style="width:770px;height:720px;">
							        <a class="linkForward"></a>
							            <div id="yy_slider_bestof2012" class="yy_slider" style="width:770px;height:720px;">
							                                    <div class="yy_slideitems" style="width:770px;height:680px;">
							                       <div class="yy_slideitems_in" style="height:680px;">
							                                  
							                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
							<div class="yjme_holder">
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem32">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/867519228d1d5325856fc61d710ded0e_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/32-the-bourne-legacy"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/32-the-bourne-legacy" >
							          <img src="hdchannel/media/k2/items/cache/867519228d1d5325856fc61d710ded0e_M.jpg" alt="The Bourne Legacy" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/32-the-bourne-legacy">
							        The Bourne Legacy      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem33">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/f7a0a54c92471ac4480e727e4ccf93df_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/33-battleship"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/33-battleship" >
							          <img src="hdchannel/media/k2/items/cache/f7a0a54c92471ac4480e727e4ccf93df_M.jpg" alt="Battleship" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/33-battleship">
							        Battleship      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem34">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/f710044bf79a4b1f5d8b085e5e5d9711_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/34-wrath-of-the-titans"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/34-wrath-of-the-titans" >
							          <img src="hdchannel/media/k2/items/cache/f710044bf79a4b1f5d8b085e5e5d9711_M.jpg" alt="Wrath of the Titans" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/34-wrath-of-the-titans">
							        Wrath of the Titans      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem31">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/fa55c8bad0e242eb7986dc1135b50adb_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/31-the-avengers"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/31-the-avengers" >
							          <img src="hdchannel/media/k2/items/cache/fa55c8bad0e242eb7986dc1135b50adb_M.jpg" alt="The Avengers" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/31-the-avengers">
							        The Avengers      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem36">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/9ecd376e5371efaef9aad9bc9143aed8_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/36-hotel-transylvania"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/36-hotel-transylvania" >
							          <img src="hdchannel/media/k2/items/cache/9ecd376e5371efaef9aad9bc9143aed8_M.jpg" alt="Hotel Transylvania" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/36-hotel-transylvania">
							        Hotel Transylvania      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem35 last">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/0b1ad7a7b79268a1f4558db78e092446_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman" >
							          <img src="hdchannel/media/k2/items/cache/0b1ad7a7b79268a1f4558db78e092446_M.jpg" alt="ParaNorman" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman">
							        ParaNorman      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							  </div>                
							                       </div>
							                    </div>
							                                    <div class="yy_slideitems" style="width:770px;height:680px;">
							                       <div class="yy_slideitems_in" style="height:680px;">
							                                  
							                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
							<div class="yjme_holder">
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem30">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/1c0ae2205709722b62e843abc0471a55_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/30-argo"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/30-argo" >
							          <img src="hdchannel/media/k2/items/cache/1c0ae2205709722b62e843abc0471a55_M.jpg" alt="Argo" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/30-argo">
							        Argo      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem28">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/01f1a05053c6242fcfa23075e5b963c1_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/28-the-amazing-spider-man"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/28-the-amazing-spider-man" >
							          <img src="hdchannel/media/k2/items/cache/01f1a05053c6242fcfa23075e5b963c1_M.jpg" alt="The Amazing Spider-Man" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/28-the-amazing-spider-man">
							        The Amazing Spider-Man      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem27">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/787ae9ec9023a82f5aa7e4c1a64f73cb_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/27-the-dark-knight-rises"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/27-the-dark-knight-rises" >
							          <img src="hdchannel/media/k2/items/cache/787ae9ec9023a82f5aa7e4c1a64f73cb_M.jpg" alt="The Dark Knight Rises" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/27-the-dark-knight-rises">
							        The Dark Knight Rises      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem29">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/fc34f61d23b74be53ee07d469bd32064_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/29-american-reunion"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/29-american-reunion" >
							          <img src="hdchannel/media/k2/items/cache/fc34f61d23b74be53ee07d469bd32064_M.jpg" alt="American Reunion" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/29-american-reunion">
							        American Reunion      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem26">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/feb4274796d93ff716e9650163a77fb8_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/26-the-hobbit-an-unexpected-journey"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/26-the-hobbit-an-unexpected-journey" >
							          <img src="hdchannel/media/k2/items/cache/feb4274796d93ff716e9650163a77fb8_M.jpg" alt="The Hobbit: An Unexpected Journey" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/26-the-hobbit-an-unexpected-journey">
							        The Hobbit: An Unexpected Journey      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							    <div class="yjme_item">
							    <div class="yjme_item_in yjmeitem25 last">
							            <div class="imageholder" style="width:100%;height:auto;float:none;">
							        <span class="yj_effect">
							        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/af2ef6a0e2c9c528b09655df79f3b312_XL.jpg"></a>
							        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter"></a>
							        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter" >
							          <img src="hdchannel/media/k2/items/cache/af2ef6a0e2c9c528b09655df79f3b312_M.jpg" alt="Abraham Lincoln: Vampire Hunter" />
							        </a>
							        </span>
							      </div>
							                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter">
							        Abraham Lincoln: Vampire Hunter      </a>
							                  <div class="item_details">
							                        <div class="item_cdate">
							        05-03-2013        </div>
							                      </div>
							                      </div>
							  </div>
							  </div>                
							                       </div>
							                    </div>
							                  
							            </div>
							        <a class="linkBackward"></a>
							    </div>
							    </div>			</div>
									</div>
							 
							</div>
							</div>
							</div>
							
							
							
							
							
							</div>
							</div>
							</div>	
			  </s:if> 			
       	 </s:if> 
	   </s:iterator>
	 </s:if> 

<!-- End  Multitabs Modules  -->





<div class="clearm"></div>
		</div>
		<!-- end mid block insidem class -->
	</div>
	<!-- end mid block div -->
	
	
	
	<!-- right block -->
	<div id="rightblock" class="sidebars">
		<div class="inside">
			<div class="yjsquare  hdcym modid99"><div class="h2_holder"><h2 class="module_title"><span class="title_split titlesplit0">Recently</span> <span class="title_split titlesplit1">Added</span></h2></div><div class="yjsquare_in"><!-- http://www.Youjoomla.com  Youyork Module Slider for Joomla 1.6 and UP starts here -->
<div class="yy_container_out">
    <div id="yy_container" class="yy_container" style="width:385px;height:550px;">
        <a class="linkForward"></a>
            <div id="yy_slider" class="yy_slider" style="width:385px;height:550px;">
                                    <div class="yy_slideitems" style="width:385px;height:550px;">
                       <div class="yy_slideitems_in" style="height:510;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem9">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/d61d44254608dd06ccdd2ff02982d14d_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven" >
          <img src="hdchannel/media/k2/items/cache/d61d44254608dd06ccdd2ff02982d14d_XS.jpg" alt="Safe Haven" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven">
        Safe Haven      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        25-10-2011        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem4">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/2ff2ba0051687eef5ca0459cf942940c_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death" >
          <img src="hdchannel/media/k2/items/cache/2ff2ba0051687eef5ca0459cf942940c_XS.jpg" alt="The ABCs of Death" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death">
        The ABCs of Death      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        24-10-2011        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem12">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/3899dfe821816fbcb3db3e3b23f81585_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/12-dark-skies"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/12-dark-skies" >
          <img src="hdchannel/media/k2/items/cache/3899dfe821816fbcb3db3e3b23f81585_XS.jpg" alt="Dark Skies" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/12-dark-skies">
        Dark Skies      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        25-10-2011        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem11 last">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/c82cc4e14a1d2c8c8ffff9840d24b558_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/11-a-good-day-to-die-hard"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/11-a-good-day-to-die-hard" >
          <img src="hdchannel/media/k2/items/cache/c82cc4e14a1d2c8c8ffff9840d24b558_XS.jpg" alt="A Good Day to Die Hard" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/11-a-good-day-to-die-hard">
        A Good Day to Die Hard      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        25-10-2011        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
  </div>                
                       </div>
                    </div>
                                    <div class="yy_slideitems" style="width:385px;height:550px;">
                       <div class="yy_slideitems_in" style="height:510;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem18">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/4965657af186b9092c7a96976ffe881c_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/18-the-place-beyond-the-pines"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/18-the-place-beyond-the-pines" >
          <img src="hdchannel/media/k2/items/cache/4965657af186b9092c7a96976ffe881c_XS.jpg" alt="The Place Beyond the Pines" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/18-the-place-beyond-the-pines">
        The Place Beyond the Pines      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        05-03-2013        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem20">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/c889234799e865bbe90cee71f6cd2e53_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion" >
          <img src="hdchannel/media/k2/items/cache/c889234799e865bbe90cee71f6cd2e53_XS.jpg" alt="Oblivion" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion">
        Oblivion      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        05-03-2013        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem13">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/48ee1e8a0a8f50dce4f8cb9ab418e211_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/13-oz-the-great-and-powerful"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/13-oz-the-great-and-powerful" >
          <img src="hdchannel/media/k2/items/cache/48ee1e8a0a8f50dce4f8cb9ab418e211_XS.jpg" alt="Oz the Great and Powerful" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/13-oz-the-great-and-powerful">
        Oz the Great and Powerful      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        25-10-2011        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem21 last">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/9b2c4b44fb86522964124ed80d03c5e8_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/21-iron-man-3"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/21-iron-man-3" >
          <img src="hdchannel/media/k2/items/cache/9b2c4b44fb86522964124ed80d03c5e8_XS.jpg" alt="Iron Man 3" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/21-iron-man-3">
        Iron Man 3      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        05-03-2013        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
  </div>                
                       </div>
                    </div>
                                    <div class="yy_slideitems" style="width:385px;height:550px;">
                       <div class="yy_slideitems_in" style="height:510;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem28">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/01f1a05053c6242fcfa23075e5b963c1_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/28-the-amazing-spider-man"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/28-the-amazing-spider-man" >
          <img src="hdchannel/media/k2/items/cache/01f1a05053c6242fcfa23075e5b963c1_XS.jpg" alt="The Amazing Spider-Man" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/28-the-amazing-spider-man">
        The Amazing Spider-Man      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        05-03-2013        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem26">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/feb4274796d93ff716e9650163a77fb8_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/26-the-hobbit-an-unexpected-journey"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/26-the-hobbit-an-unexpected-journey" >
          <img src="hdchannel/media/k2/items/cache/feb4274796d93ff716e9650163a77fb8_XS.jpg" alt="The Hobbit: An Unexpected Journey" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/26-the-hobbit-an-unexpected-journey">
        The Hobbit: An Unexpected Journey      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        05-03-2013        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem25">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/af2ef6a0e2c9c528b09655df79f3b312_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter" >
          <img src="hdchannel/media/k2/items/cache/af2ef6a0e2c9c528b09655df79f3b312_XS.jpg" alt="Abraham Lincoln: Vampire Hunter" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter">
        Abraham Lincoln: Vampire Hunter      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        05-03-2013        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem35 last">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/0b1ad7a7b79268a1f4558db78e092446_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman" >
          <img src="hdchannel/media/k2/items/cache/0b1ad7a7b79268a1f4558db78e092446_XS.jpg" alt="ParaNorman" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman">
        ParaNorman      </a>
                  <div class="item_details">
                        <div class="item_cdate">
        05-03-2013        </div>
                      </div>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel.      </p>
                </div>
  </div>
  </div>                
                       </div>
                    </div>
                  
            </div>
        <a class="linkBackward"></a>
    </div>
    </div></div></div><div class="yjsquare  hdcym2 yj1 modid115"><div class="h2_holder"><h2 class="module_title"><span class="title_split titlesplit0">Most</span> <span class="title_split titlesplit1">Popular</span></h2></div><div class="yjsquare_in"><!-- http://www.Youjoomla.com  Youyork Module Slider for Joomla 1.6 and UP starts here -->
<div class="yy_container_out">
    <div id="yy_container_right" class="yy_container" style="width:340px;height:265px;">
        <a class="linkForward"></a>
            <div id="yy_slider_right" class="yy_slider" style="width:340px;height:265px;">
                                    <div class="yy_slideitems" style="width:340px;height:265px;">
                       <div class="yy_slideitems_in" style="height:225;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem4 last">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/2ff2ba0051687eef5ca0459cf942940c_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death" >
          <img src="hdchannel/media/k2/items/cache/2ff2ba0051687eef5ca0459cf942940c_XS.jpg" alt="The ABCs of Death" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death">
        The ABCs of Death      </a>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae.       </p>
                  <div class="item_details">
                <div class="item_cdate">
        24-10-2011        </div>
                        <div class="item_category">
        <a href="hdchannel/index.html/extras/k2/k2-channel/in-theaters">
          In Theaters           &nbsp;         </a>
        </div>
                        <div class="item_author">
                                <a href="hdchannel/index.html/extras/k2/k2-user">
                 Written by admin            </a>
                  </div>
              </div>
                  <a class="item_readmore" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/4-the-abcs-of-death">
        <span>
          Read more        </span>
      </a>
          </div>
  </div>
  </div>                
                       </div>
                    </div>
                                    <div class="yy_slideitems" style="width:340px;height:265px;">
                       <div class="yy_slideitems_in" style="height:225;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem20 last">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/c889234799e865bbe90cee71f6cd2e53_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion" >
          <img src="hdchannel/media/k2/items/cache/c889234799e865bbe90cee71f6cd2e53_XS.jpg" alt="Oblivion" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion">
        Oblivion      </a>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae.       </p>
                  <div class="item_details">
                <div class="item_cdate">
        05-03-2013        </div>
                        <div class="item_category">
        <a href="hdchannel/index.html/extras/k2/k2-channel/coming-soon">
          Coming Soon           &nbsp;         </a>
        </div>
                        <div class="item_author">
                                <a href="hdchannel/index.html/extras/k2/k2-user">
                 Written by admin            </a>
                  </div>
              </div>
                  <a class="item_readmore" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/20-oblivion">
        <span>
          Read more        </span>
      </a>
          </div>
  </div>
  </div>                
                       </div>
                    </div>
                                    <div class="yy_slideitems" style="width:340px;height:265px;">
                       <div class="yy_slideitems_in" style="height:225;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem25 last">
            <div class="imageholder" style="width:95px;height:80px;float:left;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/af2ef6a0e2c9c528b09655df79f3b312_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter"></a>
        <a class="item_image"  style="width:95px;height:80px;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter" >
          <img src="hdchannel/media/k2/items/cache/af2ef6a0e2c9c528b09655df79f3b312_XS.jpg" alt="Abraham Lincoln: Vampire Hunter" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter">
        Abraham Lincoln: Vampire Hunter      </a>
                  <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae.       </p>
                  <div class="item_details">
                <div class="item_cdate">
        05-03-2013        </div>
                        <div class="item_category">
        <a href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012">
          Best of 2012           &nbsp;         </a>
        </div>
                        <div class="item_author">
                                <a href="hdchannel/index.html/extras/k2/k2-user">
                 Written by admin            </a>
                  </div>
              </div>
                  <a class="item_readmore" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/25-abraham-lincoln-vampire-hunter">
        <span>
          Read more        </span>
      </a>
          </div>
  </div>
  </div>                
                       </div>
                    </div>
                  
            </div>
        <a class="linkBackward"></a>
    </div>
        <div class="yy_bottom_nav_holder">
    	<div class="yy_ul_left">
          <ul id="yy_bottom-nav_right" class="yy_bottom_navigation">
                                      <li><a href="#" title="" class="yy_nav active"><span>1</span></a></li>
                                      <li><a href="#" title="" class="yy_nav"><span>2</span></a></li>
                                      <li><a href="#" title="" class="yy_nav"><span>3</span></a></li>
                             </ul>
       </div>
    </div>
    </div></div></div>
		</div>
	</div>
	<!-- end right block -->
	


</div>
<!-- end holder div -->

<div id="yjsg5" class="yjsg_grid"><div id="user11" class="yjsgxhtml only_mod yjsgsfx-custom2"><div class="yjsquare  custom2 modid129"><div class="h2_holder"><h2 class="module_title"><span class="title_split titlesplit0">Consectetur</span> <span class="title_split titlesplit1">adipiscing</span> <span class="title_split titlesplit2">ullam</span> <span class="title_split titlesplit3">corper</span> <span class="title_split titlesplit4">ornaretellus</span> <span class="title_split titlesplit5">mauris</span></h2></div><div class="yjsquare_in"><p>Donec auctor eleifend lorem, at tincidunt sapien mollis nec. Nunc arcu nisl, pulvinar blandit euismod non, feugiat in elit. Sed tincidunt, leo nec placerat porta, velit magna scelerisque massa, ornare semper mi orci ut est. Donec lacinia tempus urna eget blandit. Aliquam vitae tincidunt nibh. Cras eu ultricies purus. Vivamus vitae magna est. Aenean porttitor justo ut sapien fringilla vestibulum. Morbi dictum porttitor venenatis.</p></div></div><div class="yjsquare  hdcym3 modid119"><div class="yjsquare_in"><!-- http://www.Youjoomla.com  Youyork Module Slider for Joomla 1.6 and UP starts here -->
<div class="yy_container_out">
    <div id="yy_container_bottom" class="yy_container" style="width:1170px;height:380px;">
        <a class="linkForward"></a>
            <div id="yy_slider_bottom" class="yy_slider" style="width:1170px;height:380px;">
                                    <div class="yy_slideitems" style="width:1170px;height:380px;">
                       <div class="yy_slideitems_in" style="height:340;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem2">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/e0a70f72bdae9885bfc32d7cd19a26a1_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/2-snitch"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/2-snitch" >
          <img src="hdchannel/media/k2/items/cache/e0a70f72bdae9885bfc32d7cd19a26a1_S.jpg" alt="Snitch" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/2-snitch">
        Snitch      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem22">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/71f67488b0857639cee631943a3fc6fa_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/22-man-of-steel"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/22-man-of-steel" >
          <img src="hdchannel/media/k2/items/cache/71f67488b0857639cee631943a3fc6fa_S.jpg" alt="Man of Steel" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/22-man-of-steel">
        Man of Steel      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem35">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/0b1ad7a7b79268a1f4558db78e092446_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman" >
          <img src="hdchannel/media/k2/items/cache/0b1ad7a7b79268a1f4558db78e092446_S.jpg" alt="ParaNorman" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/35-paranorman">
        ParaNorman      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem9">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/d61d44254608dd06ccdd2ff02982d14d_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven" >
          <img src="hdchannel/media/k2/items/cache/d61d44254608dd06ccdd2ff02982d14d_S.jpg" alt="Safe Haven" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/9-safe-haven">
        Safe Haven      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem10 last">
           <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/e31ace2a15a7c70645ad83df9ecd43b0_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/10-silver-linings-playbook"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/10-silver-linings-playbook" >
          <img src="hdchannel/media/k2/items/cache/e31ace2a15a7c70645ad83df9ecd43b0_S.jpg" alt="Silver Linings Playbook" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/10-silver-linings-playbook">
        Silver Linings Playbook      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
  </div>                
                       </div>
                    </div>
                                    <div class="yy_slideitems" style="width:1170px;height:380px;">
                       <div class="yy_slideitems_in" style="height:340;">
                                  
                            <!-- Powered by YJ Module Engine find out more at www.youjoomla.com -->
<div class="yjme_holder">
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem30">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/1c0ae2205709722b62e843abc0471a55_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/30-argo"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/30-argo" >
          <img src="hdchannel/media/k2/items/cache/1c0ae2205709722b62e843abc0471a55_S.jpg" alt="Argo" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/30-argo">
        Argo      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem34">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/f710044bf79a4b1f5d8b085e5e5d9711_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/34-wrath-of-the-titans"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/34-wrath-of-the-titans" >
          <img src="hdchannel/media/k2/items/cache/f710044bf79a4b1f5d8b085e5e5d9711_S.jpg" alt="Wrath of the Titans" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/34-wrath-of-the-titans">
        Wrath of the Titans      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem33">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/f7a0a54c92471ac4480e727e4ccf93df_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/33-battleship"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/33-battleship" >
          <img src="hdchannel/media/k2/items/cache/f7a0a54c92471ac4480e727e4ccf93df_S.jpg" alt="Battleship" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/best-of-2012/item/33-battleship">
        Battleship      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem3">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/94d43e327d9303539cb1e2aac7032668_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/3-dead-man-down"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/3-dead-man-down" >
          <img src="hdchannel/media/k2/items/cache/94d43e327d9303539cb1e2aac7032668_S.jpg" alt="Dead Man Down" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/in-theaters/item/3-dead-man-down">
        Dead Man Down      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
    <div class="yjme_item">
    <div class="yjme_item_in yjmeitem16 last">
            <div class="imageholder" style="width:100%;height:auto;float:none;">
        <span class="yj_effect">
        <a class="modal yj_preview" href="hdchannel/media/k2/items/cache/c9b002fe1bb0320831a8ae78670fdb6f_XL.jpg"></a>
        <a class="yj_more" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/16-drak-circles"></a>
        <a class="item_image"  style="width:100%;height:auto;" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/16-drak-circles" >
          <img src="hdchannel/media/k2/items/cache/c9b002fe1bb0320831a8ae78670fdb6f_S.jpg" alt="Drak Circles" />
        </a>
        </span>
      </div>
                  <a class="item_title" href="hdchannel/index.html/extras/k2/k2-channel/coming-soon/item/16-drak-circles">
        Drak Circles      </a>
                        <p class="item_intro">
        Sed venenatis bibendum nisl, eget iaculis tortor imperdiet      </p>
                </div>
  </div>
  </div>                
                       </div>
                    </div>
                  
            </div>
        <a class="linkBackward"></a>
    </div>
        <div class="yy_bottom_nav_holder">
    	<div class="yy_ul_left">
          <ul id="yy_bottom-nav_bottom" class="yy_bottom_navigation">
                                      <li><a href="#" title="" class="yy_nav active"><span>1</span></a></li>
                                      <li><a href="#" title="" class="yy_nav"><span>2</span></a></li>
                             </ul>
       </div>
    </div>
    </div></div></div></div></div>		




