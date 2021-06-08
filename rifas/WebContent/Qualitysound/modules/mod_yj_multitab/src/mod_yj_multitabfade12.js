/*======================================================================*\
|| #################################################################### ||
|| # Copyright ©2006-2009 Youjoomla.com. All Rights Reserved.           ||
|| # This file may not be redistributed in whole or significant part. # ||
|| # ---------------- THIS IS NOT FREE SOFTWARE ---------------- #      ||
|| # http://www.youjoomla.com | http://www.youjoomla.com/license.html # ||
|| #################################################################### ||
\*======================================================================*/
/**
 * TabbedContent - mootools 1.2.4 tabs
 * @version		1.0.0
 * @MooTools version 1.2.4
 * @author		Constantin Boiangiu <info [at] constantinb.com>
 * Copyright Youjoomla.com
 */

var Tabs = new Class({	
	Implements: [Options],
	options: {
   			container: null,
			tabsContainer: null,
			classContent: null
		},
	
	initialize: function(options){
		this.setOptions(options);
		this.elements = $$(this.options.elements);
		this.start();	
	},	
	start: function(){
		this.tabs = $(this.options.tabsContainer).getElements('li');
		this.tabsContent = $(this.options.container).getElements('.'+this.options.classContent);	
		this.selectedTab = Cookie.read('selectedTab') ? Cookie.read('selectedTab').toInt() : 0;
		this.addFunctionality();		
	},
	
	addFunctionality: function(){
		this.tabs.each(function(tab, i){
			this.tabsContent[i]['fx'] = new Fx.Morph(this.tabsContent[i], {duration:400, wait:false, transition:Fx.Transitions.Sine.easeIn});
			
			this.tabsContent[i]['opacity'] = [0.001,1];			
			
			tab.addEvent('click', function(){
				this.selectTab(i);
			}.bind(this));
			
			if(i!==this.selectedTab){
				this.tabsContent[i].setStyles({'display':'none'});			
			}else{
				this.tabsContent[i].setStyles({'display':'block'});	
				tab.addClass('selected');
				
			}			
		}.bind(this));
	},
	
	selectTab: function(tab){
		if( tab == this.selectedTab ) return;
		/* deselect previous tab */
		this.tabs[this.selectedTab].removeClass( 'selected' );
		if(this.selectedTab == this.tabs.length-1) 
			this.tabs[this.selectedTab].addClass( 'last' );
		/* select current tab */
		this.tabs[tab].addClass( 'selected' );
		this.tabs[tab].setStyles({'display':'block'});	
		this.showContent(tab);
	},
	
	showContent: function(tab){
		this.tabsContent[this.selectedTab]['fx'].start({'opacity':[1,0.001]}).chain(function(){
			this.tabsContent[this.selectedTab].setStyle('display','none');
			this.tabsContent[tab].setStyles({'display':'block','opacity':[0.001,1]});
			this.tabsContent[tab]['fx'].start({'opacity':[0.001,1]});
			/* change selected tab */
			this.selectedTab = tab;
			Cookie.write('selectedTab', tab);
		}.bind(this));	
	}
});