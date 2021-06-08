/**
 * Youyork Module Slider
 * @version		2.0.1
 * @MooTools version 1.2, 1.3, 1.4
 * @author		Constantin Boiangiu <info [at] constantinb.com>
 * Copyright Youjoomla.com
 */

var YouYorkModuleSlider = new Class({
	Implements: [Options],					 
	options: {
		container : null, /* items container */
		items : null, /* CSS class for each item - mention it as .Class */
		itemWidth : null,
		visibleItems: null,
		effectDuration : 1000,
		autoSlide : 3000,
		mouseEventSlide: 1000,
		navigation: {
			'forward' : null, 
			'back' : null,
			'screenNavsContainer':null,
			'screenNavsElem':null
		}
	},
	
	initialize: function(options) {
		this.setOptions(options);
		if( !this.options.container || !this.options.itemWidth || !this.options.visibleItems ) return;
		this.container = $(this.options.container);
		this.items = this.container.getElements(this.options.items);
		this.totalItems = this.items.length;
		this.currentElement = 0;
		this.direction = 1; /* -1:reverse ; 1: forward */
		
		if( this.options.autoSlide < 1 ){
			this.options.autoSlide = false;
		}
		
		this.start();	
	},
	
	start: function(){
		this.items.each(function(item, i){
								 
			var leftDist = i * this.options.itemWidth; 
			item.setStyles({
				'position':'absolute',
				'top':0,
				'left' : leftDist
			});
			var fx = new Fx.Morph(item, {
				duration:this.options.effectDuration, 
				wait:false, 
				transition:Fx.Transitions.Sine.easeOut
			});
			this.items[i]['fx'] = fx;
			this.items[i]['leftDist'] = leftDist;
			
			item.addEvent('mouseenter', function(event){
				if( this.options.autoSlide )
					clearTimeout(this.period);
			}.bind(this));
			
			item.addEvent('mouseleave', function(event){
				if( this.options.autoSlide )
					this.period = this.autoSlide();							   
			}.bind(this));
			
			
			
		}.bind(this));	


		// navig mousenter
		if(this.options.navigation.screenNavsContainer){
			$(this.options.navigation.screenNavsContainer).addEvent('mouseenter', function(event){
				if( this.options.autoSlide )
				clearTimeout(this.period);
			}.bind(this));
		}
		
		// navig mouseleave
		if(this.options.navigation.screenNavsContainer){
		  $(this.options.navigation.screenNavsContainer).addEvent('mouseleave', function(event){
			  if( this.options.autoSlide )
				  this.period = this.autoSlide();							   
		  }.bind(this));
		}
		
		if( this.options.autoSlide ){
			this.period = this.autoSlide();
		}
		
		this.inject();		
	},
	
	visibleFwd: function(){
		var t = new Array();
		t.push(this.currentElement);
		var el = this.currentElement;
		for( var i=1; i<this.options.visibleItems+2; i++ ){
			el = el+1 < this.totalItems ? el+1 : 0;
			t.push(el);	
		};
		return t;
	},
	
	rotateForward: function( toElem ){
		if (this.items[this.currentElement]['leftDist']!==0) return;
		var slides = this.visibleFwd();
		this.items.each( function( item, i ){
			var leftDist = item['leftDist'] - this.options.itemWidth;			
			
			if( slides.contains(i) ){
				item['fx'].start({'left': leftDist}).chain(function(){
					
					if( leftDist < 0 )				
					{
						leftDist = this.totalItems*this.options.itemWidth+(this.direction*leftDist);				
						item.setStyle('left', leftDist)
					}				
					this.items[i]['leftDist'] = leftDist;
					
					
				}.bind(this));
			}else{
				if( leftDist < 0 )				
				{
					leftDist = this.totalItems*this.options.itemWidth+(this.direction*leftDist);				
					item.setStyle('left', leftDist)
				}				
				this.items[i]['leftDist'] = leftDist;
			}
			
		}.bind(this));
		
		this.currentElement = this.currentElement+1 < this.totalItems ? this.currentElement+1 : 0;
		this.setActiveBNav();				
	},
	
	visibleBkw: function(){
		var t = new Array();
		t.push( this.currentElement-1 < 0 ? this.totalItems-1 : this.currentElement-1 );
		t.push( this.currentElement );
		var el = this.currentElement;
		for( var i=1; i<this.options.visibleItems+2; i++ ){
			el = el+1 < this.totalItems ? el+1 : 0;
			t.push(el);	
		};
		return t;
	},
	
	rotateBackwards: function(){
		if (this.items[this.currentElement]['leftDist']!==0) return;
		var movedElement = this.items[ this.currentElement -1 ] || this.items.getLast();
		movedElement.setStyle('left',-this.options.itemWidth);
		movedElement['leftDist'] = -this.options.itemWidth;
		
		var t = this.visibleBkw();
		
		this.items.each( function(item, i){
			
			var leftDist = item['leftDist'] + this.options.itemWidth;
			
			if( t.contains(i) ){
				item['fx'].start({'left': leftDist}).chain(function(){
					this.items[i]['leftDist'] = leftDist;
				}.bind(this));
			}else{
				item.setStyle('left', leftDist);
				this.items[i]['leftDist'] = leftDist;
			}
			
		}.bind(this) );
		
		this.currentElement = this.currentElement-1 < 0 ? this.totalItems-1 : this.currentElement-1;
		this.setActiveBNav();
	},
	
	setActiveBNav: function(){
		var bNavC = this.options.navigation.screenNavsContainer,
			bNavE = this.options.navigation.screenNavsElem;

		if( !bNavC || !bNavE ){
			return;
		}
		
		var bNavElems = $(bNavC).getElements(bNavE),
			cNav = Math.floor(this.currentElement/this.options.visibleItems);
		
		if( bNavElems[cNav] ){
			bNavElems.removeClass('active');
			bNavElems[cNav].addClass('active');
		}
			
	},	
	
	autoSlide: function( mouseEvent ){
		var rotateType = this.direction == 1 ? this.rotateForward : this.rotateBackwards;
		return rotateType.periodical( mouseEvent ? this.options.mouseEventSlide : this.options.autoSlide, this);
	},
	
	inject: function(){
		//var forward = new Element('a').set({'class':this.options.navigation.forward, 'id':'forward'}).inject(this.container);	
		//var back = new Element('a').set({'class':this.options.navigation.back, 'id':'back'}).inject(this.container);
		var forward = this.container.getElement('.'+this.options.navigation.forward+'');
		var back  = this.container.getElement('.'+this.options.navigation.back+'');
		//*
		forward.addEvent('click', this.navEvent.bind(this).pass(1));		
		back.addEvent('click', this.navEvent.bind(this).pass(-1));
		//*/	
		
		forward.addEvent('click', this.resetSlide.bind(this));
		back.addEvent('click', this.resetSlide.bind(this));
		
		forward.addEvent('click', function(event){
			event.stop();
			clearTimeout(this.period);
			this.direction = 1;
			
			if( this.items[this.currentElement]['leftDist'] == 0 )
				this.rotateForward.bind(this).delay(50);
			
		}.bind(this));
		
		back.addEvent('click', function(event){
			event.stop();
			clearTimeout(this.period);
			this.direction = -1;
			
			if( this.items[this.currentElement]['leftDist'] == 0 )
				this.rotateBackwards.bind(this).delay(50);
			
		}.bind(this));

		// bottom navigation functionality
		
		var bNavC = this.options.navigation.screenNavsContainer,
			bNavE = this.options.navigation.screenNavsElem;

		if( !bNavC || !bNavE ){
			return;
		}
		
		var bNavElems = $(bNavC).getElements(bNavE),
			self = this;
		this.activeBNav = 0;
		bNavElems.each(function(el, i){
			el.addEvent('click', function(e){
				e.preventDefault();
				bNavElems.removeClass('active');
				el.addClass('active');

				var gotoEl = i * self.options.visibleItems;
				
				if( self.currentElement < gotoEl ){
					self.gotoNextElem(gotoEl);
				}else if( self.currentElement > gotoEl ){
					self.gotoPrevElem(gotoEl);
				}
				
				self.activeBNav = i;
			});
		})
			
	},
	
	gotoNextElem: function(index){
		var jump	= index - this.currentElement;
		
		this.items.each(function(item, e){
			var leftDist	= item['leftDist'] - jump*this.options.itemWidth;			
				//console.log(leftDist);
			
				item['fx'].start({'left': leftDist}).chain(function(){
					
					if( leftDist < 0 )				
					{
						leftDist = this.totalItems * this.options.itemWidth + leftDist;				
						item.setStyle('left', leftDist)
					}				
					this.items[e]['leftDist'] = leftDist;
					
				}.bind(this));
			
			
		}.bind(this));
		this.currentElement = index;
	},
	
	gotoPrevElem: function( index ){
		var jump	= this.currentElement - index;
		
		var oo = 1;
		for (var ii = this.currentElement-1; ii >= index; ii--){
			this.items[ii].setStyle('left', -this.options.itemWidth*oo);
			this.items[ii]['leftDist'] = -this.options.itemWidth*oo;
			oo++;
		}

		this.items.each(function(item, e){
			var leftDist = item['leftDist'] + jump * this.options.itemWidth;			
				//console.log(leftDist);
			
				item['fx'].start({'left': leftDist}).chain(function(){
					
					if( leftDist < 0 )				
					{
						leftDist = this.totalItems * this.options.itemWidth + leftDist;				
						item.setStyle('left', leftDist)
					}				
					this.items[e]['leftDist'] = leftDist;
					
				}.bind(this));
			
			
		}.bind(this));
		this.currentElement = index;
	},

	navEvent: function(direction){
		this.direction = direction;
		clearTimeout(this.period);
		this.period = this.autoSlide(true);
	},
	
	resetSlide: function(){
		clearTimeout(this.period);
		this.period = this.autoSlide();
	}
});