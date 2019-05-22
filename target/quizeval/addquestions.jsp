<%@ include file="header.jsp"%>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Add Topic <small>Create New Topic</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="container">
			<div class="row">
				<form class="form-quest" method="post" action="AddQuestion">
					<div class="container">
						<div class="row">
							<div class="form-group col-md-12">
								<label for="exampleFormControlSelect1">Select Topic</label> <select
									class="form-control select-topic"
									id="exampleFormControlSelect1" name="topic">
								</select>
							</div>
							<div class="form-group col-md-12">
								<label for="exampleFormControlTextarea1">Question</label>
								<textarea name="question" class="form-control"
									id="exampleFormControlTextarea1" rows="3"></textarea>
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 1</label> <input
									type="text" name="option1" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 1">
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 2</label> <input
									type="text" name="option2" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 2">
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 3</label> <input
									type="text" name="option3" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 3">
							</div>
							<div class="form-group col-md-6">
								<label for="exampleFormControlInput1">Option 4</label> <input
									type="text" name="option4" class="form-control"
									id="exampleFormControlInput1" placeholder="Option 4">
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
			</div>
			<%
				if (request.getAttribute("message") != null) {
					String alertMsg = (String) request.getAttribute("message");
					if (alertMsg.equals("success")) {
						out.print("<script>alert(('Topic add successfully'));</script>");
					} else if (alertMsg.equals("failed")) {
						out.print("<script>alert(('Topic failed to add'));</script>");
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