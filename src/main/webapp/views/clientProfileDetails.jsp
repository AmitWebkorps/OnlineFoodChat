<%@ include file="clientNav.jsp"%>
<div class="container emp-profile mt-3">
	<div class="row">
		<div class="col-md-4">
			<div class="profile-img">
				<img src="../images/user.png" width="200px" alt="" />

			</div>
		</div>
		<div class="col-md-6">
			<div class="profile-head">
				<h5>${details.getName()}</h5>
				<ul class="nav nav-tabs mt-5" id="myTab" role="tablist">
					<li class="nav-item"><a class="nav-link active" id="home-tab"
						data-toggle="tab" href="#home" role="tab" aria-controls="home"
						aria-selected="true">About</a></li>
					<li class="nav-item"><a class="nav-link" id="profile-tab"
						data-toggle="tab" href="#profile" role="tab"
						aria-controls="profile" aria-selected="false">Plan</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-2">
			<a href="getProfileDetails" class="btn btn-primary text-light"
				name="btnAddMore">Edit Profile</a>
		</div>
	</div>

	<div class="col-md-8 offset-md-4">
		<div class="tab-content profile-tab" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<div class="row">
					<div class="col-md-6">
						<label>User Id</label>
					</div>
					<div class="col-md-6">
						<p>${details.getName()}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Name</label>
					</div>
					<div class="col-md-6">
						<p>${details.getName()}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Email</label>
					</div>
					<div class="col-md-6">
						<p>${details.getEmail()}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Phone</label>
					</div>
					<div class="col-md-6">
						<p>${details.getPhone()}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Restaurant</label>
					</div>
					<div class="col-md-6">
						<p>${details.getRestro()}</p>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="profile" role="tabpanel"
				aria-labelledby="profile-tab">
				<div class="row">
					<div class="col-md-6">
						<label>PLan ID</label>
					</div>
					<div class="col-md-6">
						<p>${details.getPlan().id}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Buying Date</label>
					</div>
					<div class="col-md-6">
						<p>${details.getPlan().buyingDate}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Expiry Date</label>
					</div>
					<div class="col-md-6">
						<p>${details.getPlan().expiryDate}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Amount</label>
					</div>
					<div class="col-md-6">
						<p>${details.getPlan().amount}INR</p>
					</div>
				</div>
				<div class="row">
					<c:choose>
						<c:when test="${status==active}">
							<div class="col-md-6">
								<label>Availability</label>
							</div>
							<div class="col-md-6">
								<p>6 months</p>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
