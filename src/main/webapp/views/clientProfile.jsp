<%@ include file="clientNav.jsp"%>

<div class="row justify-content-md-center mt-1">
	<div class="card p-4 my-1 col-lg-5">
		<h2>My Profile</h2>
		<hr>
		<form id="updateProfile" action="updateProfile" method="POST">
			<div class="mb-3">
				<label class="form-label">Enter Restro</label> <input type="text"
					class="form-control" id="text" name="restro" id="email" value = "${details.getRestro()}" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Enter Email</label> <input type="email"
					class="form-control" id="email" name="email" id="email" value = "${details.getEmail()}" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Enter Name</label> <input type="text"
					class="form-control" id="name" name="name" id="email"  value = "${details.getName()}" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Enter Phone</label> <input type="number"
					class="form-control" name="phone" id="phone"  value = "${details.getPhone()}" required>
			</div>
			<div >
				<button type="submit" class="btn btn-success btn-block">Update</button>
			</div>

		</form>
	</div>
</div>


