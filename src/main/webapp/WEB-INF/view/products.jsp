<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>Lista produktów</title>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Lista produktów ${product[0].category.name}</h2>
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
					<c:url var="selectLink" value="/product-specifics">
						<c:param name="product" value="${tempProduct.id}" />
					</c:url>
					<c:url var="updateLink" value="/new-product-form">
						<c:param name="product1" value="${tempProduct.id}" />
					</c:url>
					<tr>
						<td>${tempProduct.code}</td>
						<td>${tempProduct.name}</td>
						<td>${tempProduct.price}</td>
						<td>
							<a href="${selectLink}">Select</a> 
						</td>
						<td>
							<a href="${updateLink}" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Update</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>
