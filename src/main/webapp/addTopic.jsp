<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<form method="POST" action="AddTopic">
  <div class="form-group">
    <label for="topic">Enter Topic Name</label>
    <input type="text" class="form-control" id="topic" placeholder="Enter topic" name="topic">
  </div>
 <button type="submit" class="btn btn-primary" name="submit">Submit</button>
</form>
</div>
	<div>
		<%
			if (request.getAttribute("message") != null)
				out.print(request.getAttribute("message"));
		%>
	</div>
</body>
</html>