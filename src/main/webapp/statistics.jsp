<%@ include file="header.jsp"%>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Student Management <small>Manage Students Here</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="container main-cont-stude">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div id="error">No results found</div>
					<div id="deleteMessage"></div>
					<table class="table">
						<thead class="thead">
							<tr class="table-brd">
								<th scope="col">Sno</th>
								<th scope="col">Name</th>
								<th scope="col">Email</th>
								<th scope="col">Phone number</th>
								<th scope="col">User Name</th>
								<th scope='col'></th>
							</tr>
						</thead>
						<tbody class="tbody">
						</tbody>
					</table>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$
								.ajax({
									url : 'StudentInfo',
									type : 'post',
									data : {
										request : 'viewall'
									},
									success : function(data) {
										var allStudentInfo = JSON.parse(data);
										if (allStudentInfo.length === 0) {
											$('#error').show();
											$('.table').hide();
											$('.thead').hide();
											$('#deleteMessage').hide();
										} else {
											$('#error').hide();
											$('.table').show();
											$('.thead').show();
											$('#deleteMessage').hide();
											for (var i = 0; i < allStudentInfo.length; i++) {
												$('.tbody').append(
														'<th scope="row">'
																+ (i + 1)
																+ '</th>');
												$('.tbody')
														.append(
																'<td>'
																		+ allStudentInfo[i].name
																		+ '</td>');
												$('.tbody')
														.append(
																'<td>'
																		+ allStudentInfo[i].email
																		+ '</td>');
												$('.tbody')
														.append(
																'<td>'
																		+ allStudentInfo[i].phonenumber
																		+ '</td>');
												$('.tbody')
														.append(
																'<td>'
																		+ allStudentInfo[i].userName
																		+ '</td>');
												$('.tbody')
														.append(
																'<td style="padding-top: 10px;"><button class="btn-warning btn editStudent" value='+allStudentInfo[i].id+'>Edit</button>&nbsp;&nbsp;&nbsp; <button class="btn-danger btn deleteStudent" value='+allStudentInfo[i].id+'>Delete</button></td>');
											}
										}
									}
								});

						$(document).on('click', '.deleteStudent', function(e) {
							var studentId = e.target.value;
							$.ajax({
								url : 'StudentInfo',
								type : 'post',
								data : {
									request : 'deletestudent',
									id : studentId
								},
								success : function(data) {
									if (data != null) {
										alert('deleted successfully');
										window.location = "statistics.jsp";
									}

								}
							});

						});

						$(document).on(
								'click',
								'.editStudent',
								function(e) {
									var studentId = e.target.value;
									window.location = "editprofile.jsp?id="
											+ studentId;
								});

					});
</script>
</body>
<%@ include file="footer.jsp"%>
