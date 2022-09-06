<%@ include file="clientNav.jsp"%>

<div class="row justify-content-md-center mt-5 ">
	<div class="card col-lg-6 p-3 bg-light">
		<h3 class="text-align-center">Edit Profile</h3>
		<hr>
		<form class="container" action="addDish" method="POST"
			enctype="multipart/form-data">
			<div class="mb-3">
				<label class="form-label">Name</label> <input type="text"
					class="form-control" name="name" value="${client.getName()}">
			</div>
			<div class="mb-3">
				<label class="form-label">Email</label> <input type="text"
					class="form-control" name="price" value="${client.getEmail()}">
			</div>
			<div class="mb-3">
				<label class="form-label">Phone</label> <input type="number"
					class="form-control" name="price" value="${client.getPhone()}">
			</div>

			<button type="submit" class="btn btn-success mx-auto">Update</button>
		</form>
	</div>

</div>

