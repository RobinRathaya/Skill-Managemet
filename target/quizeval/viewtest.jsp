<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Test list</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<script>
	$(document).ready(
			function() {
				$('#upcomingtest').click(
						function() {
							$('#quizlist').empty();
							$.post('TestManagement', {
								request : 'UpcomingTest'
							}, function(data) {
								var quizList = JSON.parse(data);
								for (var i = 0; i < quizList.length; i++) {
									$('#quizlist').append(
											(i + 1) + "." + quizList[i].name
													+ "\n");
								}
							})
						});

				$('#CompletedTest').click(
						function() {
							$('#quizlist').empty();
							$.post('TestManagement', {
								request : 'CompletedTest'
							}, function(data) {
								var quizList = JSON.parse(data);
								for (var i = 0; i < quizList.length; i++) {
									$('#quizlist').append(
											(i + 1) + "." + quizList[i].name
													+ "\n");
								}
							})
						});

				$('#CancelTest').click(
						function() {
							$('#quizlist').empty();
							$.post('TestManagement', {
								request : 'CancelTest'
							}, function(data) {
								var quizList = JSON.parse(data);
								for (var i = 0; i < quizList.length; i++) {
									$('#quizlist').append(
											(i + 1) + "." + quizList[i].name
													+ "\n");
								}
							})
						});

			});
</script>

</head>
<body>
	<div>
		<button type="submit" id="upcomingtest">Upcoming test</button>
	</div>
	<div>
		<button type="submit" id="CompletedTest">Completed test</button>
	</div>

	<div>
		<button type="submit" id="CancelTest" name="quizId" value="3">Cancel
			test</button>
	</div>
	<%
		if (request.getAttribute("message") != null) {
			out.print(request.getAttribute("message"));
		}
	%>

	<div id="quizlist"></div>


</body>
</html>