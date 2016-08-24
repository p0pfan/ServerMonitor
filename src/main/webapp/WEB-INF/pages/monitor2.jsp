<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	
	<title>CPU and Memory Monitor</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<div>
	<div>
		<form action="/servermonitor/status/disconnect">
			<input type="submit" value="DISCONNECT"> 				
		</form>
		<h3><input type="checkbox" id="refresh">AUTO RELOAD</h3>

	</div>
	<hr>
	<h1>CPU Info</h1>
	<c:forEach items="${cpu_status}" var="one_status">
		<h5 id="first">${one_status}</h5>
	</c:forEach>
	<hr>
	<h1>Mem Info</h1>
	<c:forEach items="${mem_status}" var="one_status">
		<h5 id="second">${one_status}</h5>
	</c:forEach>
	<hr>
	<div id="CpuContainer" style="width: 50%; height: 400px"></div>
	<div id="MemContainer" style="width: 50%; height: 400px"></div>
</div>
<script type="text/javascript">
var memNum = 0;
var cpuNum = 0;
var ynum = 0;
$(document).ready(function() {
	var cpu = $("#first").text();
	var mem = $("#second").text();
	memNum = parseFloat(mem.substring(0, mem.indexOf(".") + 3));
	cpuNum = parseFloat(cpu.substring(0, cpu.indexOf(".") + 3));

	b("CpuContainer", "CPU Usage Trend");
	b("MemContainer", "Memory Usage Trend");
	('#refresh').click(setInterval("a()", 1000));
})
function a() {
	if ($("#refresh").prop("checked")) {
		$.ajax({
			url : '/servermonitor/status/refresh',
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				if(data.status!="false"){
					$("#first").text(data.cpu_status);
					$("#second").text(data.mem_status);
					memNum = parseFloat(data.mem_status.substring(0,
							data.mem_status.indexOf(".") + 3));
					cpuNum = parseFloat(data.cpu_status.substring(0,
							data.cpu_status.indexOf(".") + 3));
				}else{
					self.location = '/servermonitor/status/connect';
				}
				//alert(data.cpu_status);
			}
		})

	}
}

function b(id, chartName) {
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	var chart;
	$('#' + id).highcharts({
		
		chart : {
			type : 'spline',
			animation : Highcharts.svg, // don't animate in old IE               
			marginRight : 10,
			events : {
				load : function() {

					// set up the updating of the chart each second             
					var series = this.series[0];
					setInterval(function() {

						if (id == "CpuContainer") {
							ynum = cpuNum;

						} else if (id == "MemContainer")
							ynum = memNum;

						var x = (new Date()).getTime(), // current time         
						y = ynum;
						series.addPoint([ x, y ], true, true);
					}, 1000);
				}
			}
		},
		title : {
			text : chartName,
		},
		xAxis : {
			type : 'datetime',
			tickPixelInterval : 150
		},
		yAxis : {
			title : {
				text : 'Usage Rate'
			},
			plotLines : [ {
				value : 0,
				width : 1,
				color : '#808080'
			} ]
		},
		tooltip : {
			formatter : function() {
				return '<b>'
						+ this.series.name
						+ '</b><br/>'
						+ Highcharts.dateFormat(
								'%Y-%m-%d %H:%M:%S', this.x)
						+ '<br/>'
						+ Highcharts.numberFormat(this.y, 2);
			}
		},
		legend : {
			enabled : false
		},
		exporting : {
			enabled : false
		},
		series : [ {
			name : 'Usage Rate',
			data : (function() {
				// generate an array of random data                             
				var data = [], time = (new Date()).getTime(), i;
				for (i = -19; i <= 0; i++) {
					data.push({
						x : time + i * 1000,
						y : 0
					});
				}
				return data;
			})()
		} ]
	});
};
</script>
</html>
