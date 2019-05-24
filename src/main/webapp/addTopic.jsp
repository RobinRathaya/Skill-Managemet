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
				<div class='col-md-2'></div>
				<form class="col-md-8 add-form" method="POST" action="AddTopics">
					<div class="form-group">
						<label class="add-label" for="exampleInputPassword1">Topic
							Name</label> <input type="text" class="form-control"
							id="exampleInputPassword1" placeholder="Topic Name" name="topic" required>
					</div>
					<button type="submit" class="btn btn-primary">Add Topic</button>
				</form>
				<div class='col-md-2'></div>
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
						out.print("<script>alert(('Topic failed to add'));</script>");
					}
						
				}
			%>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>
</body>
<%@ include file="footer.jsp"%>