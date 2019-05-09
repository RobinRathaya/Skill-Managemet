<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="AddTopic">
		Topic Name: <input type="text" name="topic" id="topic">
		<button type="submit">Submit</button>
	</form>
	<div>
		<%
			if (request.getAttribute("message") != null)
				out.print(request.getAttribute("message"));
		%>
	</div>
</body>
</html>