<%@ include file="userNav.jsp"%>
<div class="container border-top border-bottom mt-4 text-center">
	<h4>My Order</h4>
</div>
<div class="container mt-3">
	<div class="mb-3">
		<table class="table table-light table-sm">
			<thead class="table-dark">
				<tr>
					<th scope="col">Order ID</th>
					<th scope="col">Price</th>
					<th scope="col">Restro Name</th>
					<th scope="col">Order Status</th>
					<th scope="col">Order Time</th>
					<th scope="col">Cancel Order</th>
				</tr>
			</thead>
			<tbody>
			  <c:set var="status" scope="session" value="Pending" />
				<c:forEach items="${order}" var="element">
					<tr id="tr${element.getId()}" class="rem">
						<td>${element.getId()}</td>
						<td>${element.getPrice()}</td>
						<td>${element.getClient().getRestro()}</td>
						<td>${element.getOrderTime()}</td>
						<td id="${element.getId()}">${element.getOrderStatus()}</td>
						<td><c:choose>
								<c:when test="${element.getOrderStatus()==status}">
									<button class="btn-sm btn-danger" type="button"
										onclick="cancelOrder(${element.getId()})">Cancel
										Order</button>
								</c:when>
								<c:otherwise>
									<p></p>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>