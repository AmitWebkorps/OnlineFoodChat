<%@ include file="userNav.jsp"%>
<div class="container border-top border-bottom mt-4 text-center">
	<h4>My Cart</h4>
</div>
<div class="container mt-3">
	<div class="mb-3">
		<table class="table table-light table-sm table-hover">
			<tbody>
				<c:forEach items="${details}" var="element">
					<tr id="tr${element.id}" class="rem">
						<td scope="row"><img
							src="../images/${element.getMenu().getImageName()}"
							alt="No image found" width="100px" height="150px"></td>
						<td>${element.getMenu().getName()}</td>
						<td>${element.getMenu().getPrice()}*
							${element.getQuantity()} =
							${element.getMenu().getPrice()*element.getQuantity()}</td>
						<td class="d-flex"><button class="btn btn-danger p-1"
								onclick="sub(id${element.id})" type="button">-</button> <input
							type="number" class="form-control" name="quantity"
							id="id${element.id}" style="width: 60px;"
							value="${element.getQuantity()}" readonly>
							<button class="btn btn-success p-1"
								onclick="add(id${element.id})" type="button">+</button></td>
						<td><button class="btn btn-danger" type="button" onclick="deleteFromCart(${element.id})">delete</button>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<hr>
	<div class="card">
		<h5 class="card-header">Total</h5>
		<div class="card-body">
			<h5 class="card-title">Amount :</h5>
			<p class="card-text" id="amount">${total}</p>
			<button class="btn btn-primary" onclick="pay()">Pay Amount</button>
		</div>
	</div>
</div>