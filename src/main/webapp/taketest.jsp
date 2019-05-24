<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Take test</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<style>
	.answers {
		padding: 6px 0;
		padding-left: 20px;
	}
</style>
<body>
<form id='fromSubmit'>
<div class='container'>
	<div class='row'>
		<div>
			<table class='col-md-8'>
				<tbody id="testQuestions"></tbody>
			</table>
		</div>
	</div>
	<div class='row' style='margin-top: 15px;'>
		<input type="hidden" name="request" value="endExam">
		<button type="submit" class='btn btn-success'>End exam</button>
	</div>
</div>
</form>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
$(document).ready(function() {
		var questionList;
		var answers = new Object();
		var url = new URL(window.location.href);
		var quiz_id = url.searchParams.get('quiz_id');
		$.post('TestManagement', {
			request : 'TakeTest',
		}, function(data) {
			questionList=JSON.parse(data);
			console.log(questionList);
			questionList.forEach(function(t,i){
				answers[t.id] = 0;
				$('#testQuestions').append('<tr><td>' +(i+1) +'. '+ t.question + '</td></tr><input type="hidden" name=question'+t.id+' class="question" value='+t.id+'>');
				var opt=t.options;
				var option=opt.split(',');
				option.forEach(function(temp,j){
					$('#testQuestions').append('<tr><td class="answers">'+'<input type="radio" name=answer'+(t.id)+' value='+(j+1)+'>'+temp+'</td></tr>');
				}) 
			})
		});
		$(document).on('click', 'input[type=radio]', function(e) {
			var id = e.target.name.split('answer')[1];
			var value = e.target.value;
			answers[id] = value;
		})
		$("#fromSubmit").submit(function(e) {
			e.preventDefault();
			var ans = [];
			questionList.forEach(function(q) {
				ans.push(q.options.split(','));
			});
			var i = 0;
			for(var key in answers) {
				var answ = answers[key] - 1;
				var finalAns;
				answers[key] = ans[i][answ];
				i++;
			}
			var answer=JSON.stringify(answers);
			$.post('TestManagement',{request:'endExam',answerList:answer},function(data){
				if(data=='success')
					{
					window.location='testresult.jsp';
					}
			});
			
			
		});
		
		
	});
</script>
</html>