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
				<div class="form-contain">
					<div class="row">
						<form class="col s12" method="post" action="UploadQuestions"
							enctype="multipart/form-data">
							<div class="row">
								<select class="form-control" id="exampleFormControlSelect1">
								</select>
							</div>
							<div class="add-flex">
								<div class="row btn-imp">
									<a class="btn btn-success" href="Documents/Questions.xlsx">
										Download Template </a>
								</div>
								<div class="row upload-ques">
									<div class="input-field col s12">
										<input class="fileUpload" type="file" name="file"
											id="attach_file">
									</div>
								</div>
							</div>
							<div class="row">
								<button class="btn btn-primary" type="submit">Import
									Questions</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>
<script>
		$(document).ready(function() {
			$.ajax({url: "UploadQuestions", success: function(res){
				var data = JSON.parse(res);
				$("select").empty();
				data.forEach((d,i) => {
					var index = (i + 1);
					$("select").append("<option value=" + d + ">" + d + "</option>");	
				});
			}});
		});
		
	</script>
</body>
</body>
<%@ include file="footer.jsp"%>

