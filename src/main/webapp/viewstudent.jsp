<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View student</title>
</head>
<body>
	<form action="StudentInfo" method="post">
		<div>

			search by name<input type="text" name="name"> <input
				type="hidden" name="request" value="viewbyname">
			<button type="submit">search</button>
		</div>
	</form>

	<div>
		<c:if test="${STUDENTDETAILS != null}">
			<div>name:${STUDENTDETAILS.name}</div>
			<div>email:${STUDENTDETAILS.email}</div>
			<div>phone number:${STUDENTDETAILS.phonenumber}</div>
			<div>user name:${STUDENTDETAILS.userName}</div>
			<div>password:${STUDENTDETAILS.password}</div>
		</c:if>
	</div>
	<div>
		<c:if test="${message != null}">
			<c:out value="${message }"/>
		</c:if>
	</div>
</body>
</html>