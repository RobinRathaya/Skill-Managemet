<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page</title>


</head>
<body>
	<div>
		<a href="addstudent.jsp">Add student</a>
	</div>
	<div>
		<a href="viewstudent.jsp">View student</a>
	</div>
	<div>
		<form action="StudentInfo" method="post">
			<input type="hidden" name="request" value="viewall">
			<button type="submit">view all</button>
		</form>
	</div>
</body>
</html>