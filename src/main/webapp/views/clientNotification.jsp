<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="clientNav.jsp"%>
	<div class="container mt-2">
		<div class="row notification-container d-block">
			<h2 class="text-center">My Notifications</h2>
			<p class="dismiss text-right">
				<a id="dismiss-all" href="#">Dimiss All</a>
			</p>
			<c:forEach items="${notify}" var="element">
			<div class="card notification-card notification-invitation mb-2">
				<div class="card-body text-center">
					<table>
						<tr>
						    <td>${element.getUser().getName()}:-</td>
							<td style="width: 70%"><div class="card-title">
									${element.getClientNotification()}
								</div></td>
							<td style="width: 20%"><a href="#" class="btn btn-primary">View</a>
								<a href="#" class="btn btn-danger dismiss-notification">Dismiss</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>