<%@ include file="clientNav.jsp"%>
<div class="container mt-3">
	<h3>Menu Table</h3>
		<div class="mb-3">
			<table class="table table-light table-sm table-hover">
				<thead class="table-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Image</th>
						<th scope="col">Product Id</th>
						<th scope="col">Name</th>
						<th scope="col">Price</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>

				<tbody>
					<%
						int i = 1;
					%>
					<c:forEach items="${dish}" var="element">
						<tr>
							<th scope="row"><%=i++%></th>
							<td scope="row"><img
								src="../images/${element.getImageName()}" alt="No image found"
								width="100px" height="150px"></td>
							<td>${element.getId()}</td>
							<td>${element.getName()}</td>
							<td>${element.getPrice()}</td>
							<form action="deleteDish" method="POST">
							<input type="text" value="${element.getId()}" name="id" hidden>
						    <td><button class="btn btn-danger" type="submit">delete</button></td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	<form class="container" action="/addrestroname" method="POST">
		<a class="btn btn-success mx-auto" href="addDishPage">Add New Dish</a>
	</form>
</div>