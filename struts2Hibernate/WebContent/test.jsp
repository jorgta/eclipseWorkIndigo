<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>jQuery BlockUI Plugin (v2)</title>

<link rel="stylesheet" type="text/css" media="screen" href="BlockUI/css/jq.css" />
<script type="text/javascript" src="BlockUI/js/jquery.min.js"></script>
<script type="text/javascript" src="BlockUI/js/jquery.blockUI.js?v2.38"></script>






<script type="text/javascript">


	$(document).ready(function() {
    $('#demo2').click(function() {
        $.blockUI({ css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        } });

        //setTimeout($.unblockUI, 2000);
    });
	
});
</script>

</head>
<body>

    
      
        <button id="demo2">Run</button>
	
        



</body></html>