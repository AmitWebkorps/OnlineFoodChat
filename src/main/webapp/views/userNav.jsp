<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<title>Insert title here</title>
<style type="text/css">
.carousel .carousel-item {
	height: 600px;
}

.carousel-item img {
	position: absolute;
	object-fit: cover;
	top: 0;
	left: 0;
	min-height: 500px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between"
				id="navbarTogglerDemo01">
				<a class="navbar-brand" href="#">Online-Food-Chat</a>
				<div class="d-flex justify-content-around">
					<ul class="navbar-nav mr-2 me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link"
							aria-current="page" href="userDashboard"><i
								class="fa-solid fa-home"></i> Home</a></li>
						<li class="nav-item mx-4"><a class="nav-link" href="cart"><i
								class="fa-solid fa-cart-shopping"></i> Cart</a></li>
						<li class="nav-item mx-4"><a class="nav-link" href="Orders"><i
								class="fa-solid fa-bag-shopping"></i> Orders</a></li>
						<li class="nav-item mx-4"><a class="nav-link"
							href="Notification"><i class="fa-solid fa-bell"></i>
								Notification</a></li>
						<li class="nav-item mx-3"><a class="nav-link text-warning"
							href="logout">Logout</a></li>
					</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search"
							placeholder="Search" id="search" onkeyup="mySearch()">
					</form>
					<a class="btn btn-primary ml-3 "><i
						class="fa-regular fa-user"></i> <%=session.getAttribute("userName")%></a>
				</div>
			</div>
		</div>
	</nav>
	<div class="card col-lg-2 offset-lg-9 fixed-top mt-5 " id="box"
		style="display: none; height: 50p; overflow-y: scroll;">
		<table class="table">
			<tbody id="searchbox">
			</tbody>
		</table>
	</div>
</body>

<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="../js/client.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</html>