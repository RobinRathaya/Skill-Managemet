<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Add Student <small>Add New Student</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="container">
			<div class="row">
				<div class='col-md-2'></div>
				<form class="form-quest col-md-8" id="studentInfoForm"
					action="StudentInfo" method="post">
					<div>
						<div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Name</label> <input
									type="text" class="form-control" id="name" placeholder="Name"
									name="name" required pattern="[A-Za-z]{1,}"
									title="Cannot Contain Numbers">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Email</label> <input
									type="email" class="form-control" id="email"
									placeholder="Email" name="email" required
									pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
									title="Invalid Email Address">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Phone Number</label> <input
									type="text" class="form-control" id="phonenumber"
									placeholder="Phone Number" name="phonenumber" required
									pattern="[7-9]{1}[0-9]{9}" title="Enter a Valid Phone Number">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">User Name</label> <input
									type="text" class="form-control" id="username"
									placeholder="UserName" pattern="[a-zA-Z0-9._-]{1,}" title="Can contain Text Numbers and . _ -" name="username" required>
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="password"
									placeholder="Password" name="password" required>
							</div>
							<div class="col-md-3"></div>
							<div class="form-group text-center col-md-6">
								<input type="hidden" name="request" value="addstudent"
									id="hidden">
								<button type="submit" class="btn btn-primary">Add
									Student</button>
							</div>
							<div class="col-md-3"></div>
						</div>
					</div>
				</form>
				<div class='col-md-2'></div>
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
<!-- <script>
	$(document).ready(function() {
		var url = new URL(window.location.href);
		var studentId = url.searchParams.get('id');
		if(studentId!=null)
		{
			console.log('edit student');
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
						#('#studentInfoForm').attr('action','studentInfo');
						$('#hidden').val('updateprofile');
				}
			}
		})
		}
		console.log('add student');
	});
</script> -->
<%@ include file="footer.jsp"%>