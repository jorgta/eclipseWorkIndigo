// JavaScript Document
window.addEvent('load', function(){
	$$('#toolbar-apply,#toolbar-save,#toolbar-save-new,#toolbar-save-copy').each(function(item){
		item.addEvent('click',function(event){
			event.preventDefault();
			var myRequest = new Request({
				url: '../modules/mod_yj_twitter/lib/js/ajax/clear_cache.php',
				onSuccess: function(responseText){
					
				}
			});
			 
			myRequest.send();
		});
	});
});