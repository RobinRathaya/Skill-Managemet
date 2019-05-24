<%@ include file="header.jsp"%>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Add Questions <small>Create New Questions</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="container">
			<div class="row">
				<div class='col-md-2'></div>
				<form class="form-quest col-md-8" method="post" action="AddQuestion">
					<div>
						<div>
							<div class="form-group col-md-12">
								<label for="exampleFormControlSelect1">Select Topic</label> <select
									class="form-control select-topic"
									id="exampleFormControlSelect1" name="topic">
								</select>
							</div>
							<div class="form-group col-md-12">
								<label for="exampleFormControlTextarea1">Question</label>
								<textarea name="question" class="form-control"
									id="exampleFormControlTextarea1" rows="3" required></textarea>
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 1</label> <input
									type="text" name="option1" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 1" required>
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 2</label> <input
									type="text" name="option2" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 2" required>
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 3</label> <input
									type="text" name="option3" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 3" required>
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 4</label> <input
									type="text" name="option4" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 4" required>
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlSelect1">Answer select</label> <select
									class="form-control" id="exampleFormControlSelect1"
									name="answer">
									<option value="1">Option 1</option>
									<option value="2">Option 2</option>
									<option value="3">Option 3</option>
									<option value="4">Option 4</option>
								</select>
							</div>
							<div class="col-md-6">
								<button type="submit" class="btn-submit-ques btn btn-primary">Submit</button>
							</div>
						</div>
					</div>
				</form>
				<div class='col-md-2'></div>
			</div>
			<%
				if (request.getAttribute("message") != null) {
					String alertMsg = (String) request.getAttribute("message");
					if (alertMsg.equals("success")) {
						out.print("<script>alert(('Question added'));</script>");
					} else if (alertMsg.equals("failed")) {
						out.print("<script>alert(('Question failed to add'));</script>");
					}
					else{
						out.print("<script>alert(('Invalid input'));</script>");
					}
				}
			%>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>
<script>
$(document).ready(function() { $.ajax({url: "UploadQuestions", success:
function(res){ var data = JSON.parse(res); $(".select-topic").empty();
data.forEach((d,i) => { var index = (i + 1); $(".select-topic").append("<option value=" + d.id + ">" + d.name + "</option>"); }); }}); });
</script>
</body>
<%@ include file="footer.jsp"%>