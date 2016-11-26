<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<!-- <meta name="x5-fullscreen" content="true">
<meta name="full-screen" content="yes"> -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<style type="text/css">
html, body {
	overflow: hidden;
	margin: 0px;
	color:#fff;
}
#workdiv {
	position:absolute;
	left: 0px;
	bottom: 0px;
	top: 0px;
	right: 0px;
	background-color: #333
}
#leftMouse {
	z-index : 10;
	width: 50%;
	height: 60px;
	background-color: #333;
	position:absolute;
	left: 0px;
	bottom: 0px;
	border-right:2px solid #ddd;
}

#rightMouse {
	z-index : 10;
	width:50%;
	height: 60px;
	background-color: #333; 
	position:absolute;
	right: 0px;
	bottom: 0px;
	border-left:2px solid #ddd;
}

</style>
<title>Hot Control</title>
</head>
<body>
	<div id="workdiv" >
		<div>V0.0.3-SNAPSHOT</div>
		<div>
			<span id="coordinate"></span>
		</div>
		<div>
			<span id="return"></span>
		</div>
		<div>
			<span id="startcurrent"></span>
		</div>
		<div>
			<span id="startTouch"></span>
		</div>
	</div>
	<div id="leftMouse"></div>
	<div id="rightMouse"></div>
</body>
<script src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	var delay = 100;
	var html = document.querySelector('html');
	
	html.addEventListener('touchmove', function(event) {
		event.preventDefault();
	});
	
	html.addEventListener('touchstart', function(event) {
		event.preventDefault();
	});
	
	var startTouch;
	var obj = document.querySelector('#workdiv');
	var lastDate = new Date();
	
	obj.addEventListener('touchmove', function(event) {
		var currentDate = new Date();
		if(currentDate.getTime() - lastDate.getTime() < delay) {
			return;
		} else {
			lastDate = currentDate;
		}
		event.preventDefault();
		// 如果这个元素的位置内只有一个手指的话
		if (event.targetTouches.length == 1) {
			var touch = event.targetTouches[0];
			var spanX = touch.pageX - pressX;
			var spanY = touch.pageY - pressY;
			$("#coordinate").text(touch.pageX + ", " + touch.pageY);

			$.get("coordinate.jsp?spanX="+spanX+"&spanY=" + spanY, function(data, status) {
				$('#return').text("Data: " + data + "nStatus: " + status);
			});
			pressX = touch.pageX;
			pressY = touch.pageY;
		} else if (event.targetTouches.length == 2) {
			var touch = event.targetTouches[0];
			var xa = touch.pageX - pressXA;
			touch = event.targetTouches[1];
			var xb = touch.pageX - pressXB;
			$.get("scroll.jsp?spanXA="+xa+"&spanXB=" + xb, function(data, status) {
				$('#return').text("Data: " + data + "nStatus: " + status);
			});
		}

	}, false);
	
	obj.addEventListener('touchstart', function(event) {
		event.preventDefault();
		
		// 如果这个元素的位置内只有一个手指的话
		if (event.targetTouches.length == 1) {
			var touch = event.targetTouches[0];
			// 把元素放在手指所在的位置
			pressX = touch.pageX;
			pressY = touch.pageY;
			$('#startcurrent').text(pressX + ',' + pressY);
			
			startTouch = new Date().getTime();
		} else if (event.targetTouches.length == 2) {
			var touch = event.targetTouches[0];
			// 把元素放在手指所在的位置
			pressXA = touch.pageX;
			pressYA = touch.pageY;
			
			touch = event.targetTouches[1];
			pressXB = touch.pageX;
			pressYB = touch.pageY;
		}
	}, false);
	
	obj.addEventListener('touchend', function(event) {
		event.preventDefault();
		if (new Date().getTime() - startTouch < delay) {
			$.get("click.jsp?click=0", function(data, status) {
				$('#return').text("Data: " + data + "nStatus: " + status);
			});
		}
	}, false);
	
	document.querySelector('#leftMouse').addEventListener('touchstart',function(){
		$('#return').text("leftMouse");
		$.get("click.jsp?click=0", function(data, status) {
			$('#return').text("Data: " + data + "nStatus: " + status);
		});
	});
	
	document.querySelector('#rightMouse').addEventListener('touchstart',function(){
		$('#return').text("rightMouse");
		$.get("click.jsp?click=1", function(data, status) {
			$('#return').text("Data: " + data + "nStatus: " + status);
		});
	});
</script>
<script type="text/javascript">
	$(function() {
		
	});
</script>
</html>