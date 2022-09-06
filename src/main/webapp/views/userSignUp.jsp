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
	<%
		if (request.getAttribute("Failer") != null) {
	%>
	<div class="alert alert-warning" role="alert">
		<h4><%=request.getAttribute("Failer")%></h4>
	</div>
	<%
		}
	%>
	<div class="container p-5 my-5">
		<form action="userSignUp" method="POST">
			<h2>User SignUp</h2>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Enter
					Name</label> <input type="text" class="form-control"
					id="exampleInputEmail1" name="name" aria-describedby="emailHelp">
			</div>

			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Enter
					Email</label> <input type="email" class="form-control"
					id="exampleInputEmail1" name="email" aria-describedby="emailHelp">
			</div>

			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Enter
					Password</label> <input type="password" class="form-control"
					name="password" id="exampleInputPassword1">
			</div>

			<button type="submit" class="btn btn-success">SignUp</button>

		</form>

		<div class="mx-auto" style="width: 200px;">
			<p>Already have Account</p>
			<a href="login.jsp" class="btn btn-success mx-auto"
				style="width: 150px;">Login Now</a>
		</div>

	</div>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</html>