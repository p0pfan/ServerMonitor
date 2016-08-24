<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Connect</title>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

</head>
<body>
	<div>
		<label>Server IP：</label> <input
			type="text" name="serverIp" id="serverIp" value="${serverIP}">
	</div>
	<div>
		<label>User Name:</label> <input
			type="text" name="username" id="username">
	</div>
	<div>
		<label>Password:</label> <input
			type="password" name="password" id="password" value="">
	</div>
	<div>
		<input type="button" id="loginbtn" value="Connect">
	</div>
</body>


<script type="text/javascript">
	$(document).ready(function() {
		$('#loginbtn').click(function() {
			var that = this;
			var serverIp = $.trim($('#serverIp').val());
			var username=  $('#username').val();
			var password = $('#password').val();
			$.ajax({
				url: '/servermonitor/connect',
				type: 'POST',
				dataType: 'json',
				data: {serverIp:serverIp,
					   username:username,
					   password: password},
				success:function(data){
					if(data.status=="success"){
						self.location = '/servermonitor/status/monitor';
						/* location.reload(); */
					}else{
						 //location.reload();
						 $('#username').val('');
						 $('#password').val('');
						 alert(data.message)
					}
				}
			})
			});
	});
</script>

</html>