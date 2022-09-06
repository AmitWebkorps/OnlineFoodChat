<%@ include file="clientNav.jsp"%>
<c:if test="${Sucess != null }">
	<div class="alert alert-success" role="alert">
		<h5><%=request.getAttribute("Sucess")%></h5>
	</div>
</c:if>
<c:if test="${Failed != null }">
	<div class="alert alert-warning" role="alert">
		<h4><%=request.getAttribute("Failed")%></h4>
	</div>
</c:if>
<div class="container mt-3">
	<h3>Dish Details</h3>
	<form class="container" action="addDish" method="POST"
		enctype="multipart/form-data">
		<div class="mb-3">
			<label class="form-label">Enter Dish Name</label> <input type="text"
				class="form-control" name="name" id="name">
		</div>
		<div class="mb-3">
			<label class="form-label">Enter Dish Price</label> <input
				type="number" class="form-control" name="price" id="price">
		</div>
		<div class="mb-3">
			<label class="form-label">Enter Dish Price</label> <input type="file"
				class="form-control" name="image" id="imageName">
		</div>
		<button type="submit" class="btn btn-success mx-auto">Add New
			Dish</button>
	</form>
</div>