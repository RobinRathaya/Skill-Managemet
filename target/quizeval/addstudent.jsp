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
				<form class="form-quest" action="StudentInfo" method="post">
					<div class="container">
						<div class="row">
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Name</label> <input
									type="text" class="form-control" id="exampleInputPassword1"
									placeholder="Name" name="name">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Email</label> <input
									type="email" class="form-control" id="exampleInputPassword1"
									placeholder="Email" name="email">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Phone Number</label> <input
									type="number" class="form-control" id="exampleInputPassword1"
									placeholder="Phone Number" name="phonenumber">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">User Name</label> <input
									type="text" class="form-control" id="exampleInputPassword1"
									placeholder="@UserName" name="username">
							</div>
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password" name="password">
							</div>
							<div class="col-md-3"></div>
							<div class="form-group text-center col-md-6">
								<input type="hidden" name="request" value="addstudent">
								<button type="submit" class="btn btn-primary">Add
									Student</button>
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
<%@ include file="footer.jsp"%>