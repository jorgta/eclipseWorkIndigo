<!DOCTYPE html>
<html>
<head>

	<!--Meta tags-->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<!--Title-->
	<title></title>
		
	<!--Stylesheets-->
	<link rel="stylesheet" href="css/styles.css">


	
</head>
<body onload="changeTitle();">

<script type = "text/javascript">
   function changeTitle(){
	//alert("document.title = "+document.title);
	document.title = "Eliminar Producto";
	}
	
	
	var vReturnValue;
    function Cancel()
    {
    	vReturnValue = new Object();
    	vReturnValue.confirm = false;
    	window.returnValue = vReturnValue;
    	window.close();
    }
    function OK()
    {
    	vReturnValue = new Object();
        vReturnValue.confirm = true;      
        window.returnValue = vReturnValue;
        window.close();
    }
</script>

	
<div id="modal">
	<div id="heading">
		¿Esta Seguro que quiere hacer esto?</div>

	<div id="content">
		<p></p>
		
  

 <table>
  <tr>
  <td>
		<a href="#" onclick="OK()" class="button green close"><img src="images/tick.png">Yes</a>
  <td>
		<a href="#"  onclick="Cancel()"  class="button red close"><img src="images/cross.png">No</a>	
  </tr>		
</table>


		</div>
</div>
	





</body>
</html>



