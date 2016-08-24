<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
	<title>CPU and Memory Monitor</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

</head>
	
<body>
	<h1>CPU Info</h1>
	<div id="cpu"></div>
	<h1>MEM Info</h1>
	<div id="mem"></div>
</body>

<script type="text/javascript">
$(document).ready(function() {
	
	var id = $('#cpu').attr("id");
	$.ajax({
		url : '/servermonitor/status/info?item=all',
		type : 'GET',
		dataType : 'json',
		success: function(data){
			if(data.status == "success"){
				$('#cpu').text(data.info.cpu);
				$('#mem').text(data.info.mem);
			}else{
				alert(data.message);
			}
		}
	});
})
</script>
