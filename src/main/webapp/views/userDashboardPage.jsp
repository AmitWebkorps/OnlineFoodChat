<%@ include file="userNav.jsp"%>
<h3 class="border-bottom mt-2 pb-2">Common Restro:-</h3>

<div class="container mt-4">${restro}
	<div class="row">
	 <c:forEach items="${restro}" var="element">
		<div class="col-lg-3">
		 <div class="card">
				<img class="card-img-top" src="/images/pastry.jpg"
					alt="Card image cap" height="230" width="150">
				<div class="card-body">
					<h5 class="card-title">${element.getClient().getRestro()}</h5>
					<button type="button" class="btn btn-success text-center"></button>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>