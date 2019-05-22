<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View all student</title>
</head>
<body>

	<c:if test="${STUDENTlIST!=null }">
		<c:forEach var="studentInfo" items="${STUDENTlIST }">
			<div>
				<div>name: ${ studentInfo.name}</div>
				<div>email:${studentInfo.email }</div>
				<div>phonenumber:${ studentInfo.phonenumber}</div>
				<div>username:${studentInfo.userName }</div>
				<div>password:${studentInfo.password }</div>
				<form method="post" action="StudentInfo">
					<input type="hidden" name="request" value="editstudentprofile">
					<button type="submit" name="id" value=${studentInfo.id }>Edit</button>
				</form>
				<form method="post" action="StudentInfo">
					<input type="hidden" name="request" value="deletestudent">
					<button type="submit" name="id" value=${studentInfo.id }>Delete</button>
				</form>
			</div>

		</c:forEach>
	</c:if>

	<div>
		<c:if test="${deleteMessage!=null }">
			<c:out value="${deleteMessage }" />
		</c:if>
	</div>
	<div>
		<c:if test="${message!=null }">
			<c:out value="${message }" />
		</c:if>
	</div>
	<div>
		<c:if test="${updateMessage!=null }">
			<c:out value="${updateMessage }" />
		</c:if>
	</div>

</body>
</html>