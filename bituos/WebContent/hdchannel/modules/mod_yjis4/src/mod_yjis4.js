/*!
 * YJSlide - image slider
 * @version		4.0
 * @MooTools version 1.3+
 * Copyright Youjoomla.com
 * @authors Dragan Todorovic and Constantin Boiangiu
 *
 **/
var YJSlide = new Class({
    Implements: [Options],
    options: {
        outerContainer: null, // outer items container 
        innerContainer: null, // inner items container 
        elements: null, // css class for slides 
        navigation: {
            'forward': null, // forward link id
            'back': null // backward link id
        },
        navigationThumb: {
            forward: null,
            back: null,
            tnavHolder: null,
            container: null,
            elements: null,
            outer: null,
            visibleItems: 0,
            showThumbs: null,
            thumbsOpacity: 0.5,
            showThumbArrows: 1,
            beltOrientation: 1
        },
        slideType: 0, //0 : fade; 1 : scroll; 2 : scrollfade
        orientation: 1, //  0 : vertical; 1 : horizontal
        slideTime: 3000,
        duration: 600,
        tooltips: 0,
        thumbTooltips: 0,
        navTooltips: 0,
        autoslide: 1,
        navInfo: null,
        navLinks: null,
        navHolder: null,
        slideWidth: null,
        slideHeight: null,
        slideImages: null
    },
    initialize: function (options) {
        this.setOptions(options);
        this.elements = $$(this.options.elements);
        if (this.options.navigationThumb.beltOrientation == 0) {
            this.calcThumbs();
        } else {
            this.calcThumbsVert();
        }
        this.start();
        if (window.getScrollSize().x < 980) {
            $(this.options.outerContainer).setStyle('opacity', 0);
            var MainContainer = $(this.options.outerContainer);
            var MainHeight = this.options.slideHeight;
            var checkImages = MainContainer.getElements('.yjisImg').get('src');
            var LoadImages = Asset.images(checkImages, {
                /*                onComplete: function () {
                    (function () {
                            MainContainer.morph({
                             opacity: [0, 1]
                            });
                    }).delay(250);
                }*/
            });
        }
    },
    start: function () {
        this.currentElement = 0;
        this.direction = 1; // -1: back; 1:forward
        this.elements = $(this.options.innerContainer).getElements(this.options.elements);
        this.showEffect = {};
        this.hideEffect = {};
        this.slideGetHeight = $(this.options.outerContainer).getElement('.yjis_slidesinfo').get('html').toInt();
        if (this.options.slideType !== 0) {
            if (this.options.orientation == 1) {
                this.showEffect.left = [this.options.slideWidth.toInt() + 200, 0];
                this.hideEffect.left = [0, this.options.slideWidth.toInt() + 200];
            } else {
                this.showEffect.top = [this.slideGetHeight, 0];
                this.hideEffect.top = [0, this.slideGetHeight];
            }
        }
        if (this.options.slideType !== 1) {
            this.showEffect.opacity = [0, 1];
            this.hideEffect.opacity = [1, 0];
        }
        /* slides */
        this.elements.each(function (el, i) {
            el.setStyles({
                'display': 'block',
                'position': 'absolute',
                'top': 0,
                'left': 0,
                'z-index': (100 - i)
            });
            (function () {
                el.removeClass('starter');
            }).delay(700);
            if (this.options.slideType !== 1 && i !== this.currentElement) el.setStyle('opacity', 0);
            this.elements[i]['fx'] = new Fx.Morph(el, {
                link: 'chain',
                duration: this.options.duration
            });
            if (i !== this.currentElement) this.elements[i]['fx'].start(this.hideEffect);
            el.addEvent('mouseover', function (event) {
                if (this.period != null) {
                    clearTimeout(this.period);
                }
            }.bind(this));
            el.addEvent('mouseout', function (event) {
                if (this.options.autoslide == 0) {
                    this.period = this.rotateSlides.periodical(this.options.slideTime, this);
                }
            }.bind(this));
        }.bind(this));
        /* add tooltips if set */
        if (this.options.tooltips && $(this.options.innerContainer)) {
            var YJS_tips = new Tips($(this.options.innerContainer).getElements('.YJS_link'), {
                className: 'YJS_tips',
                fixed: true,
                'offset': {
                    y: 20,
                    x: 10
                },
                onShow: function (tip) {
                    tip.setStyle('display', 'block');
                    tip.morph({
                        opacity: [0, 1]
                    });
                },
                onHide: function (tip) {
                    tip.morph({
                        opacity: [1, 0]
                    });
                    (function () {
                        tip.setStyle('display', 'none');
                    }).delay(300);
                }
            });
        }
        /* autoslide on command */
        if (!this.options.autoslide) {
            this.period = this.rotateSlides.periodical(this.options.slideTime, this);
        }
        /* add navigation */
        this.setNavigation();
        /* swipe it baby :) */
        this.setSwipeeNav();
        if (this.options.navLinks) this.secondNavigation();
        if (this.options.navigationThumb.showThumbs == 1) {
            this.setThumbNavigation();
            this.thumbNavigation();
            $$(this.options.navigationThumb.elements).morph({
                opacity: [1, this.options.navigationThumb.thumbsOpacity]
            });
            $$(this.options.navigationThumb.elements)[0].morph({
                opacity: [this.options.navigationThumb.thumbsOpacity, 1]
            });
            $$('.tharrows').setStyle('opacity', 0.5);
            var tharrows = $$('.tharrows');
            tharrows.each(function (el) {
                var fx = new Fx.Morph(el, {
                    duration: 300,
                    'link': 'cancel'
                });
                el.addEvents({
                    mouseenter: function () {
                        fx.start({
                            'opacity': '1',
                        });
                    },
                    mouseleave: function () {
                        fx.start({
                            'opacity': '0.5',
                        });
                    }
                });
            });
            $(this.options.navigationThumb.container).addEvent('mousewheel', function (event) {
                event.preventDefault();
                var dir = event.wheel > 0 ? 1 : -1;
                var el = this.currentElement - dir;
                //var el = this.currentElement-event.wheel;
                if (event.wheel > 0 && el < 0) el = this.navElements.length - 1;
                if (event.wheel < 0 && el > this.navElements.length - 1) el = 0;
                if (this.options.autoslide == 1) {
                    $clear(this.period);
                    this.resetAutoslide();
                }
                this.nextSlide(el);
            }.bind(this));
        }
    },
    rotateSlides: function () {
        var next = this.currentElement + this.direction;
        if (next < 0) next = this.elements.length - 1;
        if (next > this.elements.length - 1) next = 0;
        this.nextSlide(next);
        this.addnavClass();
    },
    addnavClass: function () {
        var currentElement = this.currentElement + 1;
        $$(this.options.navLinks).each(function (el, i) {
            var currNavEle = i + 1;
            if (currNavEle == currentElement) {
                el.addClass('active');
            } else {
                el.removeClass('active');
            }
        });
    },
    nextSlide: function (slide) {
        if (slide == this.currentElement) return;
        this.elements[this.currentElement].setStyle('z-index', '50').removeClass('active').addClass('inactive');
        this.elements[this.currentElement]['fx'].start(this.hideEffect);
        this.elements[slide].setStyle('z-index', '100').addClass('active').removeClass('inactive');
        this.elements[slide]['fx'].start(this.showEffect);
        var current = this.currentElement;
        this.currentElement = slide;
        if ($(this.options.navInfo)) {
            $(this.options.navInfo).set('html', 'Link ' + (slide + 1) + ' of ' + this.elements.length);
        }
        if (this.options.navigationThumb.showThumbs == 1) {
            this.navElements.removeClass('selected').morph({
                opacity: [this.options.navigationThumb.thumbsOpacity, this.options.navigationThumb.thumbsOpacity]
            });
            this.navElements[slide].addClass('selected').morph({
                opacity: [this.options.navigationThumb.thumbsOpacity, 1]
            });
            /* slide to element */
            var navTo = slide - this.correction < 0 ? 0 : slide - this.correction;
            if (slide < current && this.options.navigationThumb.visibleItems == 2) {
                navTo = navTo - 1 < 0 ? 0 : navTo - 1;
            }
            var firstLast = this.navElements.length - this.options.navigationThumb.visibleItems;
            if (navTo > firstLast) {
                navTo = firstLast;
            }
            if (navTo + this.correction >= this.navElements.length - this.correction) {
                if (this.options.navigationThumb.visibleItems % 2 !== 0) {
                    navTo = (this.navElements.length - 1) - this.correction * 2;
                } else {
                    navTo = slide - this.correction;
                    var firstLast = this.navElements.length - this.options.navigationThumb.visibleItems;
                    if (navTo > firstLast) {
                        navTo = firstLast;
                    }
                }
            }
            this.navScroll.toElement(this.navElements[navTo]);
        }
    },
    setNavigation: function () {
        if (!$(this.options.navigation.forward)) return;
        $(this.options.navigation.forward).addEvent('click', function (event) {
            event.preventDefault();
            this.direction = 1;
            this.resetAutoslide();
            this.rotateSlides();
        }.bind(this));
        $(this.options.navigation.back).addEvent('click', function (event) {
            event.preventDefault();
            this.direction = -1;
            this.resetAutoslide();
            this.rotateSlides();
        }.bind(this));
    },
    setSwipeeNav: function () {
        var swiper = $$(this.options.elements);
        if (Browser.Features.Touch) {
            swiper.addEvent('swipe', function (event) {
                event.preventDefault();
                swiper.store('swipe:cancelVertical', true);
                swiper.store('swipe:distance', 20);
                if (event.direction == 'right') {
                    this.direction = 1;
                } else {
                    this.direction = -1;
                }
                this.resetAutoslide();
                this.rotateSlides();
            }.bind(this));
        }
    },
    resetAutoslide: function () {
        if (this.period != null) {
            clearTimeout(this.period);
            this.period = this.rotateSlides.periodical(this.options.slideTime, this);
        }
    },
    secondNavigation: function () {
        this.navElements = $$(this.options.navLinks);
        this.navElements.each(function (el, i) {
            el.addEvent('click', function (event) {
                event.preventDefault();// fix stop
                this.resetAutoslide();
                this.nextSlide(i);
                this.addnavClass();
            }.bind(this));
        }.bind(this));
        /* add tooltips if set */
        if (this.options.navTooltips && $(this.options.navHolder)) {
            var YJS_tips = new Tips($(this.options.navHolder).getElements('.YJS_link'), {
                className: 'YJS_tips',
                fixed: true,
                'offset': {
                    y: -60,
                    x: 0
                },
                onShow: function (tip) {
                    tip.morph({
                        opacity: [0, 1]
                    })
                },
                onHide: function (tip) {
                    tip.morph({
                        opacity: [1, 0]
                    })
                }
            });
        }
    },
    setThumbNavigation: function () {
        if (!$(this.options.navigationThumb.forward)) return;
        $(this.options.navigationThumb.forward).addEvent('click', function (event) {
            event.preventDefault();// fix stop
            this.direction = 1;
            this.resetAutoslide();
            this.rotateSlides();
        }.bind(this));
        $(this.options.navigationThumb.back).addEvent('click', function (event) {
            event.preventDefault();// fix stop
            this.direction = -1;
            this.resetAutoslide();
            this.rotateSlides();
        }.bind(this));
    },
    thumbNavigation: function () {
        this.navElements = $$(this.options.navigationThumb.elements);
        this.navElements.each(function (el, i) {
            if (i == this.currentElement) {
                this.navScroll.toElement(el);
                el.addClass('selected').morph({
                    opacity: [this.options.navigationThumb.thumbsOpacity, 1]
                });
            }
            el.addEvent('click', function (event) {
                event.preventDefault();// fix stop
                this.resetAutoslide();
                this.nextSlide(i);
                this.addnavClass();
            }.bind(this));
        }.bind(this));
        /* add tooltips if set */
        if (this.options.thumbTooltips && $(this.options.navigationThumb.container)) {
            var YJS_tips = new Tips($(this.options.navigationThumb.container).getElements('.YJS_link'), {
                className: 'YJS_tips',
                fixed: true,
                'offset': {
                    y: -55,
                    x: 8
                },
                onShow: function (tip) {
                    tip.morph({
                        opacity: [0, 1]
                    })
                },
                onHide: function (tip) {
                    tip.morph({
                        opacity: [1, 0]
                    })
                }
            });
        }
    },
    calcThumbsVert: function () {
        if (this.options.navigationThumb.showThumbs == 1) {
            var navEl = $$(this.options.navigationThumb.elements)[0];
            var tsize = navEl.getDimensions({
                computeSize: true,
                styles: ['margin', 'padding', 'border']
            });
            // make the belt size do not allow to exceed the slide width
            var thumbWidth = tsize.totalWidth;
            var thumbHeight = tsize.totalHeight;
            var containerHeight = thumbHeight * this.options.navigationThumb.visibleItems;
            var sliderTotal = this.options.slideHeight.toInt();
            var correctVisible = 0;
            var maxItems = Math.round(sliderTotal / thumbHeight);
            var maxFitSize = maxItems * thumbHeight;
            if (this.options.navigationThumb.visibleItems >= maxItems) {
                this.options.navigationThumb.visibleItems = (maxFitSize - thumbHeight) / thumbHeight;
                containerHeight = maxFitSize - thumbHeight;
                correctVisible = this.options.navigationThumb.visibleItems - (containerHeight / thumbHeight);
            }
            $$(this.options.navigationThumb.elements).setStyles({
                display: 'block',
                float: 'left',
                margin: 0
            });
            $(this.options.navigationThumb.outer).setStyles({
                width: thumbWidth,
                height: containerHeight - 16
            }).morph({
                opacity: [0, 1]
            });
            $(this.options.navigationThumb.container).setStyles({
                height: 3000,
                width: thumbWidth,
            });
            $(this.options.navigationThumb.tnavHolder).setStyles({
                height: containerHeight,
                width: thumbWidth
            });
            // position arrows
            if (this.options.navigationThumb.showThumbs == 1) {
                var getBeltHeight = $(this.options.navigationThumb.tnavHolder).clientHeight;
                var getTharrowsHeight = $(this.options.navigationThumb.forward).getStyle('height').toInt();
                var setTharrowsPos = (getBeltHeight / 2) - (getTharrowsHeight / 2);
                var thisBeltArrows = $(this.options.navigationThumb.tnavHolder).getElements('.tharrows');
                thisBeltArrows.setStyles({
                    'top': setTharrowsPos,
                    display: 'none'
                });
            }
            this.navElements = $(this.options.navigationThumb.container).getElements(this.options.navigationThumb.elements);
            this.navScroll = new Fx.Scroll(this.options.navigationThumb.outer, {
                link: 'cancel',
                duration: 800,
                transition: Fx.Transitions.Quad.easeInOut
            });
            if (this.options.navigationThumb.visibleItems < 3) {
                correctVisible = 1;
            }
            this.correction = Math.round((this.options.navigationThumb.visibleItems - correctVisible) / 2.00001);
        }
        if ($(this.options.navigation.forward) && this.options.navigationThumb.beltOrientation == 1) {
            var getSlideHeight = $(this.options.outerContainer).clientHeight;
            var getSlidearrowsHeight = $(this.options.navigation.forward).getStyle('height').toInt();
            var thisSlideArrows = $(this.options.outerContainer).getElements('.slidearrows');
            var setSlidearrowsPos = (getSlideHeight / 2) - (getSlidearrowsHeight / 2);
            thisSlideArrows.setStyles({
                'top': setSlidearrowsPos,
                display: 'block',
                opacity: 0.5
            });
            $$('.slidearrows').each(function (el) {
                var fx = new Fx.Morph(el, {
                    duration: 300,
                    'link': 'cancel'
                });
                el.addEvents({
                    mouseenter: function () {
                        fx.start({
                            'opacity': '1',
                        });
                    },
                    mouseleave: function () {
                        fx.start({
                            'opacity': '0.5',
                        });
                    }
                });
            });
        }
    },
    calcThumbs: function () {
        if (this.options.navigationThumb.showThumbs == 1) {
            var navEl = $$(this.options.navigationThumb.elements)[0];
            var tsize = navEl.getDimensions({
                computeSize: true,
                styles: ['margin', 'padding', 'border']
            });
            // make the belt size do not allow to exceed the slide width
            var thumbWidth = tsize.totalWidth;
            var containerWidth = thumbWidth * this.options.navigationThumb.visibleItems;
            var sliderTotal = this.options.slideWidth.toInt();
            var correctVisible = 0;
            var maxItems = Math.round(sliderTotal / thumbWidth);
            var maxFitSize = maxItems * thumbWidth;
            if (this.options.navigationThumb.visibleItems >= maxItems) {
                this.options.navigationThumb.visibleItems = (maxFitSize - thumbWidth) / thumbWidth;
                containerWidth = maxFitSize - thumbWidth;
                correctVisible = this.options.navigationThumb.visibleItems - (containerWidth / thumbWidth);
            }
            $(this.options.navigationThumb.outer).setStyle('width', containerWidth).morph({
                opacity: [0, 1]
            });
            // position arrows
            if (this.options.navigationThumb.showThumbs == 1 && $(this.options.navigationThumb.forward)) {

                var getBeltHeight = $(this.options.navigationThumb.tnavHolder).clientHeight;
                var getTharrowsHeight = $(this.options.navigationThumb.forward).getStyle('height').toInt();
                var setTharrowsPos = (getBeltHeight / 2) - (getTharrowsHeight / 2);
                var thisBeltArrows = $(this.options.navigationThumb.tnavHolder).getElements('.tharrows');
                thisBeltArrows.setStyles({
                    'top': setTharrowsPos,
                    display: 'block'
                });
            }
            this.navElements = $(this.options.navigationThumb.container).getElements(this.options.navigationThumb.elements);
            this.navScroll = new Fx.Scroll(this.options.navigationThumb.outer, {
                link: 'cancel',
                duration: 800,
                transition: Fx.Transitions.Quad.easeInOut
            });
            if (this.options.navigationThumb.visibleItems < 3) {
                correctVisible = 1;
            }
            this.correction = Math.round((this.options.navigationThumb.visibleItems - correctVisible) / 2.00001);
        }
        if ($(this.options.navigation.forward)) {
            var getSlideHeight = $(this.options.outerContainer).clientHeight;
            var getSlidearrowsHeight = $(this.options.navigation.forward).getStyle('height').toInt();
            var thisSlideArrows = $(this.options.outerContainer).getElements('.slidearrows');
            var setSlidearrowsPos = (getSlideHeight / 2) - (getSlidearrowsHeight / 2);
			
            thisSlideArrows.setStyles({
                'top': setSlidearrowsPos,
                display: 'block',
                opacity: 0.5
            });
            $$('.slidearrows').each(function (el) {
                var fx = new Fx.Morph(el, {
                    duration: 300,
                    'link': 'cancel'
                });
                el.addEvents({
                    mouseenter: function () {
                        fx.start({
                            'opacity': '1',
                        });
                    },
                    mouseleave: function () {
                        fx.start({
                            'opacity': '0.5',
                        });
                    }
                });
            });
        }
    }
});

function setArrows() {
    var isParent = $$('.YJIS_outer');
    isParent.each(function (el) {
        var getArrows = el.getElements('.slidearrows');
        var getThumbArrows = el.getElements('.tharrows');
        if (getThumbArrows.length > 0) {
            var getBelt = el.getElement('.thumbContainer').getSize().y;
            var setThumbsArrowsPos = (getBelt / 2) - (getThumbArrows[0].getSize().y / 2);
            //console.log(setThumbsArrowsPos);
            getThumbArrows.setStyles({
                'top': setThumbsArrowsPos,
                display: 'block',
                opacity: 0.5
            });
        }
        if (getArrows.length > 0) {
            var setArrowsPos = (el.getSize().y / 2) - (getArrows[0].getSize().y / 2);
            getArrows.setStyles({
                'top': setArrowsPos,
                display: 'block',
                opacity: 0.5
            });
        }
    });
}

function YjisRespond() {
    var isHolder = $$('.YJIS_outer');
    isHolder.each(function (el) {
        var isHolderimg = el.getElements('.yjisImg')[0].getSize().y;
        var isHolderInfo = el.getElements('.yjis_slidesinfo')[0].get('html').toInt();
        var getInner = el.getElement('.YJS_slidesholder');
        var checkImages = el.getElements('.yjisImg').get('src');
        var getImage = el.getElements('.yjisImg')[0].getSize().y;
        var getInfo = el.getElement('.yjis_slidesinfo').get('html').toInt();
        var getInfoWidth = el.getElement('.yjis_slidesinfo').get('class').split(' ');
        var defaultWidth = getInfoWidth[1].replace('getw_', '').toInt();
        //console.log(defaultWidth);
        if (el.getParent().getSize().x <= defaultWidth) {
            el.addClass('yjis_respond');
            el.getElements('.YJIS_outer,.YJIS_inner,.YJS_slidesholder,.thumbContainer.horizontal').addClass('yjis_respond');
        } else {
            el.removeClass('yjis_respond');
            el.getElements('.YJIS_outer,.YJIS_inner,.YJS_slidesholder,.thumbContainer.horizontal').removeClass('yjis_respond');
        }
        var findNavcId = 'navContainer' + el.get('id').replace('YJSlide_outer', '');
        if (window.getScrollSize().x < 980) {
            //console.log(getImage);
            if (getImage < getInfo) {
                el.setStyle('height', getImage);
                getInner.setStyle('height', getImage);
                if ($(findNavcId)) {
                    $(findNavcId).setStyle('width', el.getSize().x);
                }
                setArrows();
            }
        } else {
            el.setStyle('height', getInfo);
            getInner.setStyle('height', getInfo);
            if ($(findNavcId)) {
                $(findNavcId).setStyle('width', el.getSize().x);
            }
        }
    });
}
//when the dom is ready
window.addEvent('domready', function () {
    if (window.getScrollSize().x < 980) {
        window.fireEvent('resize');
    }
    //store titles and text //rel="lightbox[yjgallery]"
    $$('.YJS_link,.bnav,.thumb').each(function (element, index) {
        var check_elem_title = element.get('title');
        if (check_elem_title) {
            var content = check_elem_title.split('::');
            element.store('tip:title', content[0]);
            element.store('tip:text', content[1]);
        }
    });
    var mboxes = $$('.YJS_mboxp');
    if (mboxes.length > 0) {
        mboxes.each(function (el) {
            var get_rel_data = el.getElement('.YJS_reldata');
            el.set('rel', get_rel_data.get('html'));
            var savetitle = el.get('title');
            var getimage = el.getElement('img');
            getimage.addEvent('click', function () {
                el.set('title', savetitle);
            });
            // hide nasty a tooltip	
            el.addEvents({
                mouseenter: function () {
                    el.erase('title');
                },
                // bring it back 
                mouseleave: function () {
                    el.set('title', savetitle);
                }
            });
        });
    }
});
window.addEvent('resize', function () {
    YjisRespond();
});
window.addEvent('load', function () {
    YjisRespond();
    if (window.getScrollSize().x < 980) {
        $$('.YJIS_outer').morph({
            opacity: [0, 1]
        });
    }
});
// packager build Mobile/Swipe
/*
---

name: Element.defineCustomEvent

description: Allows to create custom events based on other custom events.

authors: Christoph Pojer (@cpojer)

license: MIT-style license.

requires: [Core/Element.Event]

provides: Element.defineCustomEvent

...
*/
(function () {[Element, Window, Document].invoke('implement', {
        hasEvent: function (event) {
            var events = this.retrieve('events'),
                list = (events && events[event]) ? events[event].values : null;
            if (list) {
                var i = list.length;
                while (i--) if (i in list) {
                    return true;
                }
            }
            return false;
        }
    });
    var wrap = function (custom, method, extended) {
        method = custom[method];
        extended = custom[extended];
        return function (fn, name) {
            if (extended && !this.hasEvent(name)) extended.call(this, fn, name);
            if (method) method.call(this, fn, name);
        };
    };
    var inherit = function (custom, base, method) {
        return function (fn, name) {
            base[method].call(this, fn, name);
            custom[method].call(this, fn, name);
        };
    };
    var events = Element.Events;
    Element.defineCustomEvent = function (name, custom) {
        var base = events[custom.base];
        custom.onAdd = wrap(custom, 'onAdd', 'onSetup');
        custom.onRemove = wrap(custom, 'onRemove', 'onTeardown');
        events[name] = base ? Object.append({}, custom, {
            base: base.base,
            condition: function (event, name) {
                return (!base.condition || base.condition.call(this, event, name)) && (!custom.condition || custom.condition.call(this, event, name));
            },
            onAdd: inherit(custom, base, 'onAdd'),
            onRemove: inherit(custom, base, 'onRemove')
        }) : custom;
        return this;
    };
    Element.enableCustomEvents = function () {
        Object.each(events, function (event, name) {
            if (event.onEnable) event.onEnable.call(event, name);
        });
    };
    Element.disableCustomEvents = function () {
        Object.each(events, function (event, name) {
            if (event.onDisable) event.onDisable.call(event, name);
        });
    };
})();
/*
---

name: Browser.Features.Touch

description: Checks whether the used Browser has touch events

authors: Christoph Pojer (@cpojer)

license: MIT-style license.

requires: [Core/Browser]

provides: Browser.Features.Touch

...
*/
Browser.Features.Touch = (function () {
    try {
        document.createEvent('TouchEvent').initTouchEvent('touchstart');
        return true;
    } catch (exception) {}
    return false;
})();
// Android doesn't have a touch delay and dispatchEvent does not fire the handler
Browser.Features.iOSTouch = (function () {
    var name = 'cantouch', // Name does not matter
        html = document.html,
        hasTouch = false;
    if (!html.addEventListener) return false;
    var handler = function () {
        html.removeEventListener(name, handler, true);
        hasTouch = true;
    };
    try {
        html.addEventListener(name, handler, true);
        var event = document.createEvent('TouchEvent');
        event.initTouchEvent(name);
        html.dispatchEvent(event);
        return hasTouch;
    } catch (exception) {}
    handler(); // Remove listener
    return false;
})();
/*
---

name: Swipe

description: Provides a custom swipe event for touch devices

authors: Christopher Beloch (@C_BHole), Christoph Pojer (@cpojer), Ian Collins (@3n)

license: MIT-style license.

requires: [Core/Element.Event, Custom-Event/Element.defineCustomEvent, Browser.Features.Touch]

provides: Swipe

...
*/
(function () {
    var name = 'swipe',
        distanceKey = name + ':distance',
        cancelKey = name + ':cancelVertical',
        dflt = 50;
    var start = {}, disabled, active;
    var clean = function () {
        active = false;
    };
    var events = {
        touchstart: function (event) {
            if (event.touches.length > 1) return;
            var touch = event.touches[0];
            active = true;
            start = {
                x: touch.pageX,
                y: touch.pageY
            };
        },
        touchmove: function (event) {
            if (disabled || !active) return;
            var touch = event.changedTouches[0],
                end = {
                    x: touch.pageX,
                    y: touch.pageY
                };
            if (this.retrieve(cancelKey) && Math.abs(start.y - end.y) > 10) {
                active = false;
                return;
            }
            var distance = this.retrieve(distanceKey, dflt),
                delta = end.x - start.x,
                isLeftSwipe = delta < -distance,
                isRightSwipe = delta > distance;
            if (!isRightSwipe && !isLeftSwipe) return;
            event.preventDefault();
            active = false;
            event.direction = (isLeftSwipe ? 'left' : 'right');
            event.start = start;
            event.end = end;
            this.fireEvent(name, event);
        },
        touchend: clean,
        touchcancel: clean
    };
    Element.defineCustomEvent(name, {
        onSetup: function () {
            this.addEvents(events);
        },
        onTeardown: function () {
            this.removeEvents(events);
        },
        onEnable: function () {
            disabled = false;
        },
        onDisable: function () {
            disabled = true;
            clean();
        }
    });
})();