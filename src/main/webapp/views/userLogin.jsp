<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body class="bg-light">
    <%if(request.getAttribute("Success")!=null) {%>
   <div class="alert alert-primary" role="alert">
       <h4><%=request.getAttribute("Success")%></h4>
   </div>
   <% }%>
	<div class="container p-5 my-1">
		<form id="form" action="userLogin" method="POST">

			<h2>User Login</h2>
			<div class="mb-3">
				<label class="form-label">Enter Email</label> <input type="email"
					class="form-control" id="email" name="email" id="email">
			</div>

			<div class="mb-3">
				<label class="form-label">Enter Password</label> <input
					type="password" class="form-control" name="password" id="password">
			</div>

			<button type="button" class="btn btn-success" data-toggle="modal"
				data-target="#exampleModal" id="sendotp" onclick="send()">Login</button>

		</form>

		<div class="mx-auto" style="width: 200px;">
			<p>Don't have Account</p>
			<a href="userSignUpPage" class="btn btn-success mx-auto"
				style="width: 150px;">Register Now</a>
		</div>

	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Enter Otp</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="mb-3">
						<p id="result"></p>
						<input type="text"  id="otpInput" class="form-control">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" onclick="send()">Resend
						Otp</button>
					<button type="button" class="btn btn-primary"
						onclick="check()">Submit</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/client.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</html>