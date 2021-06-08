/*======================================================================*\
|| #################################################################### ||
|| # Copyright ©2006-2012 Youjoomla.com. All Rights Reserved.           ||
|| # This file may not be redistributed in whole or significant part. # ||
|| # ---------------- THIS IS NOT FREE SOFTWARE ---------------- #      ||
|| # http:www.youjoomla.com | http:www.youjoomla.com/license.html # ||
|| #################################################################### ||
\*======================================================================*/
/**
 * YJ Live Search
 * @version		2.0.1
 * @MooTools version 1.2; 1.3; 1.4
 * @Copyright Youjoomla.com
 * @License commercial
 * @author		Constantin Boiangiu <info [at] constantinb.com>
 */

var YJLiveSearch = new Class({
	Implements: [Options],
	options:{
		inputId: 'LiveSearch',
		inputCopy:'',
		resultFeed:'search.php',
		noResultsMessage:'',
		topDistance: 25,
		duration: 200,
		minLenght: 3,
		maxLenght: 8,
		minCharTxt1:'Minimum',
		minCharTxt2:'characters required'
		
	},					   
						   
	initialize: function(options) {
		this.setOptions(options);
		this.start();		
	},
	
	start: function(){
		$(this.options.inputId).addClass('LiveSearchStop').set({'autocomplete':'off'});		
		this.currentPage = 1;		
		/*results div*/
		this.searchResultsContainer = new Element('div',{
			'class':'LiveSearchContainer'+this.options.inputCopy,
			'styles':{
				'top':-1000,
				'left':-1000,
				'display':'block'
			}
		}).inject(document.body);	
		this.header = new Element('div',{
			'class':'LiveSearchHeader'
		}).inject(this.searchResultsContainer);		
		
		this.searchResults = new Element('div', {
			'class':'LiveSearchResults'+this.options.inputCopy
		}).inject(this.searchResultsContainer);		
		
		this.fadeSearch = new Fx.Morph(this.searchResults, {
			duration:this.options.duration, 
			wait:false,
			link:'cancel'
		});		
		/*input event*/
		//$(this.options.inputId).addEvent('keyup', this.request.bind(this));

		// lets count chars and than run ajax

		$(this.options.inputId).addEvent('keyup', function(event){
		  if( ($(this.options.inputId).value.length > this.options.minLenght || ($(this.options.inputId).value.length == this.options.minLenght)) && $(this.options.inputId).value.length < this.options.maxLenght){
			  this.request(true);
		  }else if($(this.options.inputId).value.length < this.options.minLenght){
			  		var noresults1 = $$('.noResults.showresults');
			  		var noresults2 = $$('.noResults.minchars');
					if(noresults2.length  == 0){
					new Element('div',{
						'class':'noResults minchars',
						'html':this.options.minCharTxt1 + this.options.minLenght + this.options.minCharTxt2
					}).inject(this.searchResults);
					}
					noresults1.dispose();
					this.toggleSearch(true);
					
		  }
		}.bind(this));			
		/*clear result on input click*/		
		$(this.options.inputId).addEvent('click', function(event){	
			this.toggleSearch(false);			
		}.bind(this));			
		/*clear results on body click*/		
		$(document.body).addEvent('click', function(event){
			this.toggleSearch(false);			
		}.bind(this));		
	},
	
	request: function(){
		$(this.options.inputId).removeClass('LiveSearchStop');
		$(this.options.inputId).addClass('LiveSearchLoading');
		var request = new Request.JSON({
			url: this.createURL(),						   
			onSuccess: function(jsonObj) {
				$(this.options.inputId).addClass('LiveSearchStop');
				this.searchResults.empty();
				this.JsonResponse = new Hash(jsonObj.results);
				
			//	console.log();
			
		
				if(this.JsonResponse.getLength() == 0){
					new Element('div',{
						'class':'noResults showresults',
						'html':this.options.noResultsMessage+$(this.options.inputId).value
					}).inject(this.searchResults);
				}else{
					this.addResults();
				};					
				this.toggleSearch( true );				
				if(jsonObj.pages > 1){
					this.setNavigation(jsonObj.pages);
				}				
			}.bind(this)
		}).get();		
	},
	
	addResults: function(){
		this.JsonResponse.each(function(elem){
			var resultContainer = new Element('div',{
				'class':'LiveSearchResult'
			});
			var mainAnchor = new Element('a',{
				'href':elem.url,
				'html':elem.title
			});
			var expandAnchor = new Element('a',{
				'href':'#',
				'class':'LiveSearchExpandDetails'
			});
			var details = new Element('div',{
				'class':'LiveSearchExpander',
				'html':elem.description,
				'styles':{
					'display':'none',
					'opacity':0
				}
			});
			var images = new Element('div',{
				'class':'LiveSearchImg',
				'html':elem.image
			});
			resultContainer.adopt(mainAnchor,expandAnchor,details).inject(this.searchResults);
			details.grab(images,'top');					
			
			var effect = new Fx.Morph(details, {
				duration:this.options.duration, 
				wait:false,
				link:'cancel'
			});					
			
			expandAnchor.addEvent('click',function(event){ 
				event.stop();
				var opacity = details.getStyle('opacity');
				if(opacity > 0){
					effect.start({'opacity':0}).chain(function(){
						details.setStyle('display','none')});
				}
				else{
					details.setStyle('display','block');
					effect.start({'opacity':1});
				}
			});
			
			resultContainer.addEvent('mouseover',function(){
				resultContainer.addClass('LiveSearchResultHover');
				mainAnchor.addClass('hovered');
			});
			resultContainer.addEvent('mouseout',function(){
				resultContainer.removeClass('LiveSearchResultHover');
				mainAnchor.removeClass('hovered');
				resultContainer.addClass('LiveSearchResult');									  
			});			
		}.bind(this));
	},
	
	setNavigation: function(pages){
		if( pages > this.currentPage ){
			var next = new Element('a',{
				'href':'#',
				'class':'next',
				'html':'next'
			}).inject(this.searchResults);
			next.addEvent('click',function(event){
				event.stop();						   
				this.currentPage+=1;
				this.navigate = true;
				this.request();
			}.bind(this));
		}
		if( this.currentPage > 1 ){
			var prev = new Element('a',{
				'href':'#',
				'class':'back',
				'html':'back'
			}).inject(this.searchResults);
			prev.addEvent('click',function(event){
				event.stop();	
				this.currentPage-=1;
				this.navigate = true;
				this.request();
			}.bind(this));
		}
	},
	
	createURL: function(){
		return this.options.resultFeed+
			   '?search='+$(this.options.inputId).value+
			   '&page='+this.currentPage;
	},
	
	toggleSearch: function(showResults){
		var opacity = showResults ? 1 : 0;
		/*input position*/		
		if(showResults || this.navigate){
			this.searchResultsContainer.setStyles({'top':$(this.options.inputId).getTop() + this.options.topDistance ,'left':$(this.options.inputId).getLeft()});
		}
		else{
			this.searchResultsContainer.setStyles({'top':-1000 ,'left':-1000});
			this.currentPage = 1;
		}		
		if(!this.navigate)
			this.fadeSearch.start({'opacity':opacity});			
		
		this.navigate = false;		
	}
});