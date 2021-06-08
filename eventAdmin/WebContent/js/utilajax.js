function FAjax(url,metodo,data,valor,parameter)
{

    var datasend = data.fields; 
    datasend.searchParam= parameter ;
    datasend.searchValue= valor;	
 
 
    
 
	var ajaxInformation= new AjaxInformation(url);
    ajaxInformation.data = datasend;
    ajaxInformation.datatype = "html";
    
    loading();
    var result =openConnection(ajaxInformation);
    insertData(result);
    return;
    
   	 
}

 
function loading()
{
	var data= '<div id="loading" class="spinner-border text-secondary" role="status">' +
			  '<span class="sr-only">Loading...</span>' +
			  '</div>';
	
	$($('#GridView1').find('#HeaderStyle')).after(data);
}

function insertData(data)
{
  $('#loading').remove();
  $($('#GridView1').find('.AltRowStyle')).remove();	
  $($('#GridView1').find('.RowStyle')).remove();
  $($('#GridView1').find('.SelectedRowStyle')).remove();							    			   			   
  $($('#GridView1').find('#HeaderStyle')).after(data);
}

