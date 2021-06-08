/*======================================================================*\
|| #################################################################### ||
|| # Package - Joomla Template based on YJSimpleGrid Framework          ||
|| # Copyright (C) 2010  Youjoomla.com. All Rights Reserved.            ||
|| # Authors - Dragan Todorovic and Constantin Boiangiu                 ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/
window.addEvent('domready', function () {
	

	var els = $$("#myTabTabs a");
	for (var i = 0, l = els.length; i < l; i++) {
		var el = els[i];
		
		if(el.get('href').match(/SETTINGS/g)){
			
			var newCLass = el.get('href').replace('#attrib-','');
			el.addClass(newCLass);
		}
	
	}
	
	var slides_range = new Array(21);
	
	for(var i=0;i<slides_range.length;i++){

		
		var images = $$('#jform_params_slide_image_'+i).get('value');
		
		if (images != '') {
			
			$$('.SLIDE_'+i+'_SETTINGS').addClass('slide_used');
		}

	 }

    // move order
    var tmplholder = $('copy_template');
    var tmplselect = $('jform_params_module_template-lbl').getParent().getParent();
    tmplholder.inject(tmplselect, 'bottom');
    var get_width = $('jform_params_thumb_width').get('value');
    var get_height = $('jform_params_thumb_height').get('value');
    $$('.delete_thumb').each(function (element, index) {
        var get_id = element.get('id');
        var find_input = get_id.replace('delete_slide_genthumb', 'input_slide_genthumb');
        var find_thumb = get_id.replace('delete_slide_genthumb', 'thumb_slide_genthumb');
        var find_response = get_id.replace('delete_slide_genthumb', 'response_slide_genthumb');
        var check_image = $(find_input).get('defaultValue');
        var find_element = $('elm_path').get('value');
        var find_site = $('site_path').get('value');
        $(find_response).fade('in');
        element.addEvent('click', function (el) {
            var check_existing = $(find_thumb).get('html');
            if (check_existing.length > 0) {
                var delete_image = $(find_thumb).getElement('img').get('src').replace(find_site, '');
            } else {
                delete_image = check_image;
            }
            var del_ajax = find_element + '?delete_thumb=' + delete_image;
            var DeleteThumb = new Request({
                url: del_ajax,
                onSuccess: function (response) {
                    data = JSON.decode(response);
                    if (data.message) {
                        $(find_thumb).set('html', '');
                        $(find_input).set('defaultValue', '');
                        $(find_response).set('html', data.message);
                    } else {
                        $(find_response).set('html', 'Not able to delete ' + check_image);
                    }
                }
            });
            if (check_image != '' || check_existing.length > 0) {
                DeleteThumb.send();
            }
        });
    });
    $$('.create_thumb').each(function (element, index) {
        var get_id = element.get('id');
        var find_image = get_id.replace('link_slide_genthumb', 'jform_params_slide_image');
        var find_thumb = get_id.replace('link_slide_genthumb', 'thumb_slide_genthumb');
        var find_clear = get_id.replace('link_slide_genthumb', 'clear_slide_image');
        var find_input = get_id.replace('link_slide_genthumb', 'input_slide_genthumb');
        var find_response = get_id.replace('link_slide_genthumb', 'response_slide_genthumb');
        var find_opener = get_id.replace('link_slide_genthumb', 'opener_slide_image');
        var find_site = $('site_path').get('value');
        var find_element = $('elm_path').get('value');
        var image_value = $(find_image).get('value');
        $(find_thumb).setStyles({
            width: get_width.toInt(),
            height: get_height.toInt()
        });
        var check_ajax = find_element + '?check_thumb=' + image_value;
        var CheckThumb = new Request({
            url: check_ajax,
            onSuccess: function (response) {
                data = JSON.decode(response);
                if (data.message) {
                    $(find_thumb).set('html', '<a class="yjmodal" href="' + find_site + image_value + '"><img src="' + find_site + 'images/upload_slides/thumbnails/' + data.message + '?refresh='+Math.random()+'" alt ="" /></a>');
                    $(find_input).set('defaultValue', 'images/upload_slides/thumbnails/' + data.message);
                    SqueezeBox.assign($$('a.yjmodal'), {
                        parse: 'rel'
                    });
                }
            }
        });
        if (image_value != '') {
            CheckThumb.send();
        }
        $(find_clear).addEvent('click', function (el) {
            $(find_thumb).set('html', '');
            $(find_input).set('defaultValue', '');
            $(find_image).set('value', '');
            $(find_thumb).addClass('cleared');
        });
        $(find_image).fireEvent("change");
        $(find_image).addEvent('change', function (el) {
            $(find_thumb).removeClass('cleared');
        });
        element.addEvent('click', function (el) {
            $(find_response).fade('in');
            var fresh_image = $(find_image).value;
            var check_cleared = $(find_thumb).hasClass('cleared');
            if (fresh_image != '') {
                var make_image = fresh_image;
            } else {
                make_image = image_value;
            }
            var ajaxpath = find_element + '?get_width=' + get_width + '&get_height=' + get_height + '&makethumb=' + make_image;
            var CreateThumb = new Request({
                url: ajaxpath,
                onSuccess: function (response) {
                    data = JSON.decode(response);
                    if (data.message) {
                        $(find_thumb).set('html', '<a class="yjmodal" href="' + find_site + make_image + '"><img src="' + find_site + 'images/upload_slides/thumbnails/' + data.message + '?refresh='+Math.random()+'" alt ="" /></a>');
                        $(find_input).set('defaultValue', 'images/upload_slides/thumbnails/' + data.message);
                        $(find_response).set('html', '');
                        SqueezeBox.assign($$('a.yjmodal'), {
                            parse: 'rel'
                        });
                        //console.log(data.message);
                    } else if (data.error_exists) {
                        $(find_thumb).set('html', '<a class="yjmodal" href="' + find_site + make_image + '"><img src="' + find_site + 'images/upload_slides/thumbnails/' + data.error_exists + '?refresh='+Math.random()+'" alt ="" /></a>');
                        $(find_input).set('defaultValue', 'images/upload_slides/thumbnails/' + data.error_exists);
                        $(find_response).set('html', 'Thumbnail ' + data.error_exists + ' already exists');
                        $(find_thumb).fireEvent("change");
                        (function () {
                            $(find_response).fade('out');
                        }).delay(3000);
                        SqueezeBox.assign($$('a.yjmodal'), {
                            parse: 'rel'
                        });
                    } else {
                        $(find_response).set('html', data.error);
                    }
                }
            });
            if ((image_value != '' || fresh_image != '') && !check_cleared) {
                CreateThumb.send();
            } else {
                $(find_response).set('html', 'Select image first');
            }
        });
    }); // dndn
});