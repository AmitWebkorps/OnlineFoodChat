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
					<th scope="col">Delete/Edit</th>
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
						<td id="${element.getId()}">${element.getId()}</td>
						<td id="name${element.getId()}">${element.getName()}</td>
						<td id="price${element.getId()}">${element.getPrice()}</td>
						<form action="deleteDish" method="POST">
							<input type="text" value="${element.getId()}" name="id" hidden>
							<td><button class="btn-sm btn-danger" type="submit">delete</button>
								<button class="btn-sm btn-info" data-toggle="modal"
									data-target="#exampleModal" type="button" onclick="fillDetails(${element.getId()})">edit</button></td>
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
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Edit Dish</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="container" action="editDish" method="POST"
					enctype="multipart/form-data">
					<div class="mb-3">
						<input type="number" class="form-control" name="id" hidden="true"
							id="id">
					</div>
					<div class="mb-3">
						<label class="form-label">Dish Name</label> <input type="text"
							class="form-control" name="name" id="name" required>
					</div>
					<div class="mb-3">
						<label class="form-label">Enter Dish Price</label> <input
							type="number" class="form-control" name="price" id="price"
							required>
					</div>
					<div class="mb-3">
						<label class="form-label">Enter Dish Price</label> <input
							type="file" class="form-control" name="image" id="imageName"
							required>
					</div>
					<button type="submit" class="btn btn-success mx-auto">Save Changes</button>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    function fillDetails(id)
    {
    	$("#id").val(id);
    	$("#name").val($("#name"+id).html());
    	$("#price").val($("#price"+id).html());
    	
    }
</script>