<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit profile</title>
</head>
<body>
	<c:if test="${ STUDENTDETAILS!=null}">
		<form action="StudentInfo" method="post">
			<div>
				Name:<input type="text" name="name" value="${STUDENTDETAILS.name }">
			</div>
			<div>
				Email:<input type="text" name="email" value="${STUDENTDETAILS.email }">
			</div>
			<div>
				Phone number:<input type="text" name="phonenumber" value="${STUDENTDETAILS.phonenumber }">
			</div>
			<div>
				User Name:<input type="text" name="username" value="${STUDENTDETAILS.userName }">
			</div>
			<div>
				Password:<input type="password" name="password" value="${STUDENTDETAILS.password }">
			</div>
			<div>
				<input type="hidden" name="request" value="updateprofile">
				<button type="submit" name="id" value="${ STUDENTDETAILS.id}">Update</button>
			</div>
		</form>
	</c:if>
</body>
</html>