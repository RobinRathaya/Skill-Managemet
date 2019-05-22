<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Add Test <small>Create New Test</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="container">
			<div class="row">
				<form class="form-quest" action="TestManagement" method="post">
					<div class="container">
						<div class="row">
							<div class="form-group col-md-12">
								<label for="exampleFormControlSelect1">Example select</label> <select
									class="form-control select-topic"
									id="exampleFormControlSelect1" name="topic">
								</select>
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Quiz Name</label> <input
									type="text" class="form-control" id="exampleInputPassword1"
									placeholder="Quiz Name" name="quizname">
							</div>
							<div class="form-group col-md-6">
								<label for="exampleInputPassword2">Quiz Time</label> <input
									type="time" class="form-control" id="exampleInputPassword2"
									name="quiztime">
							</div>
							<div class="form-group col-md-6">
								<label for="exampleInputPassword2">Quiz Expiry Date</label> <input
									type="date" class="form-control" id="exampleInputPassword2"
									name="quizexpirydate">
							</div>
							<div class="col-md-3"></div>
							<div class="form-group col-md-6 text-center">
								<input type="hidden" name="request" value="addquiz">
								<button class="btn btn-primary" type="submit">Add Quiz</button>
							</div>
							<div class="col-md-3"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>

	<div>
		<%-- <c:if test="${MESSAGE!=null}">
			<c:out value="${MESSAGE}" />
		</c:if> --%>

		<%
			if (request.getAttribute("MESSAGE") != null) {
				out.print(request.getAttribute("MESSAGE"));
			}
		%>
	</div>

	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>
<script>
$(document).ready(function() { $.ajax({url: "UploadQuestions", success:
function(res){ var data = JSON.parse(res);
$(".select-topic").empty();
data.forEach((d,i) => { var index = (i + 1); $(".select-topic").append("<option value=" + d.id + ">" + d.name + "</option>"); }); }}); });
</script>
</body>
<%@ include file="footer.jsp"%>