<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Start test</title>
<style>
	#quizName {
		width: 180px;
		margin: 0 auto;
		margin-top: 30vh;
	}
</style>
</head>
<body>

	<div class='container'>
		<div class='row'>
			<div class='col-md-4'></div>
			<div class='col-md-4'>
				<div id="quizName"></div>
			</div>
			<div class='col-md-4'></div>
		</div>
	</div>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var url = new URL(window.location.href);
						var quiz_id = url.searchParams.get('quiz_id');
						$
								.post(
										'TestManagement',
										{
											request : 'GetQuizInfo',
											quizId : quiz_id
										},
										function(data) {
											var questionList = JSON.parse(data);
											console.log(questionList);
											$('#quizName')
													.append(
															'<div class="nameQuiz">Quiz Name: '
																	+ questionList.name
																	+ '</div><div class="nameTopic">Topic Name: '
																	+ questionList.topics.name
																	+ '</div><div class="questionNo">No of questions: '
																	+ questionList.noOfQuestion
																	+ '</div><div class="durationTest">Test Duration : '
																	+ questionList.durationTime.hour
																	+ ':'
																	+ questionList.durationTime.minute
																	+ '</div><div class="dateTest">Test Date: '
																	+ questionList.expiredDate.day
																	+ '/'
																	+ questionList.expiredDate.month
																	+ '/'
																	+ questionList.expiredDate.year
																	+ '</div><div class="quizStartBtn"><button class="btn btn-success startQuiz" value='+questionList.id+'>Start now</button></div>');
										});
						$(document).on(
								'click',
								'.startQuiz',
								function() {
									window.location = 'taketest.jsp?quiz_id='
											+ quiz_id;
								})

					});
</script>
</html>