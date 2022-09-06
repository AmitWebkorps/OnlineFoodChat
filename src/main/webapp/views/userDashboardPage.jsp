<%@ include file="userNav.jsp"%>
<h3 class="border-bottom mt-2 pb-2">Common Restro:-</h3>
<div class="container mt-4">
	<div class="row">
	 <c:forEach items="${dish}" var="element">
		<div class="col-lg-3">
		 <div class="card">
				<img class="card-img-top" src="/images/pastry.jpg"
					alt="Card image cap" height="230" width="150">
				<div class="card-body">
					<h5 class="card-title">${element.getClientEntity().getName()}</h5>
					<a href="#" class="btn btn-success text-center">Go somewhere</a>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>