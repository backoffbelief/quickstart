<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chat</title>
</head>
<body>
	<script type="text/javascript">
		var socket;
		if (!window.WebSocket) {
			window.WebSocket = window.MozWebSocket;
		}
		if (window.WebSocket) {
			socket = new WebSocket("ws://192.168.80.128:8070/ws");
			socket.onmessage = function(event) {
				var ta = document.getElementById('responseText');
				var msg = JSON.parse(event.data);
				ta.value = ta.value + '\n' + msg.name+":"+msg.msg;
			};
			socket.onopen = function(event) {
				var ta = document.getElementById('responseText');
				ta.value = "连接开启!";
			};
			socket.onclose = function(event) {
				var ta = document.getElementById('responseText');
				ta.value = ta.value + "连接被关闭";
			};
		} else {
			alert("你的浏览器不支持！");
		}
		var _nickname;
		
		function send(nickname, message) {
			if (!window.WebSocket) {
				return;
			}
			if (socket.readyState == WebSocket.OPEN) {
				if(!_nickname){
					__nickname= nickname;
					
					var obj = {"id": 100,"nickName":__nickname};
					socket.send(JSON.stringify(obj));
				}
				var obj = {"id": 101,"msg":message};
				socket.send(JSON.stringify(obj));
			} else {
				alert("连接没有开启.");
			}
		}
	</script>
	<form onsubmit="return false;">
		<input type="text" name="message" value="Hello, World!"><input
			type="button" value="发送消息"
			onclick="send(this.form.message.value)">
		<h3>输出：</h3>
		<textarea id="responseText" style="width: 500px; height: 300px;"></textarea>
		<input type="button" onclick="javascript:document.getElementById('responseText').value=''" value="清空">
	</form>
</body>
</html>
