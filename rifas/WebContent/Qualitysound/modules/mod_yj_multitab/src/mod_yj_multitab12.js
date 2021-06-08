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
			//this.tabsContent[i]['fx'] = new Fx.Morph(this.tabsContent[i], {duration:400, wait:false, transition:Fx.Transitions.Sine.easeIn});
			this.tabsContent[i]['fx'] = new Fx.Morph(this.tabsContent[i], {duration:400, wait:false, transition:Fx.Transitions.Sine.easeIn});
			
			
			
			
			
			var size = this.tabsContent[i].getCoordinates();
			this.tabsContent[i]['height'] = size.height;			
			
			tab.addEvent('click', function(){
				this.selectTab(i);
			}.bind(this));
			
			if(i!==this.selectedTab){
				this.tabsContent[i].setStyles({'display':'none'});
				this.tabsContent[i].addClass( 'deselected' );		
			}else{
				tab.addClass('selected');
				this.tabsContent[i].removeClass( 'deselected' );
				this.tabsContent[i].addClass( 'imselected' );
			}			
		}.bind(this));
	},
	
	selectTab: function(tab){
		if( tab == this.selectedTab ) return;
		/* deselect previous tab */
		this.tabs[this.selectedTab].removeClass( 'selected' );
		this.tabsContent[this.selectedTab].removeClass('imselected');
		this.tabsContent[this.selectedTab].addClass('deselected');
		if(this.selectedTab == this.tabs.length-1) 
			this.tabs[this.selectedTab].addClass( 'last' );
		/* select current tab */
		this.tabs[tab].addClass( 'selected' );
		this.tabsContent[tab].removeClass( 'deselected' );
		this.tabsContent[tab].addClass( 'imselected' );
		this.showContent(tab);
	},
	
	showContent: function(tab){
		this.tabsContent[this.selectedTab]['fx'].start({'height':35, 'opacity':0}).chain(function(){
			this.tabsContent[this.selectedTab].setStyle('display','none');
			this.tabsContent[tab].setStyles({'display':'block', 'height':35, 'opacity':0});
			var geth = this.tabsContent[tab].getElement('.tab_content_in').getSize().y;
			this.tabsContent[tab]['fx'].start({'height':[35,geth], 'opacity':1});
			/* change selected tab */
			this.selectedTab = tab;
			//Cookie.write('selectedTab', tab);
		}.bind(this));	
	}
});