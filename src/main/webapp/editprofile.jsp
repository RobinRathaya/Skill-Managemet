<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Edit student profile
		</h1>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="container">
			<div class="row">
				<form class="form-quest"  action="StudentInfo"
					method="post">
					<div class="container">
						<div class="row">
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Name</label> <input
									type="text" class="form-control" id="name" placeholder="Name"
									name="name">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Email</label> <input
									type="email" class="form-control" id="email"
									placeholder="Email" name="email">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Phone Number</label> <input
									type="number" class="form-control" id="phonenumber"
									placeholder="Phone Number" name="phonenumber">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">User Name</label> <input
									type="text" class="form-control" id="username"
									placeholder="@UserName" name="username">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="password"
									placeholder="Password" name="password">
							</div>
							<div class="col-md-3"></div>
							<div class="form-group text-center col-md-6">
								<input type="hidden" name="request" value="updateprofile"
									id="hidden">
									<input type="hidden" name="id" id="updateStudentId">
								<button type="submit" class="btn btn-primary">Update profile</button>
							</div>
							<div class="col-md-3"></div>
						</div>
					</div>
				</form>
				<c:if test="${MESSAGE!=null }">
					<div>${MESSAGE }</div>
				</c:if>
			</div>



		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>
</body>
<script>
	$(document).ready(function() {
		var url = new URL(window.location.href);
		var studentId = url.searchParams.get('id');
		if (studentId != null) {
			$.ajax({
				url : 'StudentInfo',
				type : 'post',
				data : {
					request : 'editstudentprofile',
					id : studentId
				},
				success : function(data) {
					var studentInfo = JSON.parse(data);
					if (data != null) {
						$('#name').val(studentInfo.name);
						$('#email').val(studentInfo.email);
						$('#phonenumber').val(studentInfo.phonenumber);
						$('#username').val(studentInfo.userName);
						$('#password').val(studentInfo.password);
						$('#updateStudentId').val(studentId);
					}
				}
			})
		}
	});
</script>
<%@ include file="footer.jsp"%>