<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFFF">

<form method="POST" action='chat' name="postForm">
<input type="hidden" name="action" value="post"/>
Message: <input type="text" name="message"/>
</form>

<br>
<br>

<a href="javascript:open('http://localhost:8080/chat/chat/chat', 640, 480 ,0 ,0 ,0 ,0 ,0 ,1 ,10 ,10 );">Click to open chat window</a>


</body>
</html>