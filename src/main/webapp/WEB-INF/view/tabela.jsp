
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista</title>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Lista produktów</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Kod</th>
					<th>Nazwa</th>
					<th>Cena</th>
				</tr>
				<c:forEach var="tempProduct" items="${product}">
					<c:url var="selectLink" value="/customer/helloworld-form">
						<c:param name="product" value="${tempProduct.id}" />
					</c:url>
					<c:url var="updateLink" value="/customer/helloworld-form">
						<c:param name="product1" value="${tempProduct.id}" />
					</c:url>
					<tr>
						<td>${tempProduct.code.codeName}</td>
						<td>${tempProduct.name}</td>
						<td>${tempProduct.price}</td>
						<td>
							<a href="${selectLink}">Select</a> 
							<a href="${updateLink}" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Update</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>
