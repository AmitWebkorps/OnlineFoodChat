<%@ include file="userNav.jsp"%>
	<h3 class="m-2">Restro Name  :-  ${restro.restro}</h3>
<div class="container mt-3">
			    <h3 class="bg-dark text-center text-white">Menu</h3>
		<div class="mb-3" style="overflow-y: scroll;">
			<table class="table table-light table-sm table-hover">
				

				<tbody>
					<c:forEach items="${dish}" var="element">
						<tr>
							<td scope="row"><img
								src="../images/${element.getImageName()}" alt="No image found"
								width="100px" height="150px"></td>
							<td>${element.getName()}</td>
							<td>${element.getPrice()}</td>
						    <td class="d-flex"><button class="btn btn-danger p-1" onclick="sub(id${element.id})" type="button">-</button><input
				type="number" class="form-control" name="quantity" id="id${element.id}" style="width:60px;" value="0" readonly><button class="btn btn-success p-1" onclick="add(id${element.id})" type="button">+</button></td>
						    <td><button class="btn btn-primary" type="button">Add to Cart</button>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</div>