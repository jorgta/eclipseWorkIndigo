
//var hosturl = "http://localhost:8080/eventAdmin/";

     

var hostname= window.location.hostname;//
var href= window.location.href;//"http://localhost:8080/eventAdmin/calendar.html"
var origin= window.location.origin;//"http://localhost:8080"
var pathname=window.location.pathname;// "/eventAdmin/calendar.html"
var proyect="eventAdmin";
var hosturl = origin + "/" + proyect + "/"  ;

function AjaxInformation(api) {

    this.api = api;
    this.hosturl = hosturl;
    this.url = this.hosturl + this.api;
    this.data =  null;
    this.datatype="json";
    

}


function openConnection(ajaxInformation)
{
    objResult = null;

    $.ajax({url: ajaxInformation.url, 
        async:false,
        cache: false,
        type: 'POST',
        datatype:ajaxInformation.url,
        //data:  {"data":ajaxInformation.data},
        data:  ajaxInformation.data,
        success: function(result){
            objResult = result;


        },error : function(jqXHR, status, error) {
            objResult = error;
        },
    
        // código a ejecutar sin importar si la petición falló o no
        complete : function(jqXHR, status) {
            //alert('Petición realizada');
            console.log('Petición realizada');
            
        }
        
        });

        return objResult;
}






