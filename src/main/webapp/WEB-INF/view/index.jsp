<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start</title>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Lista kategorii produktów</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Tag</th>
					<th>Nazwa</th>
					<th>Akcja</th>
				</tr>
				<c:forEach var="category" items="${categoryList}">
					<c:url var="selectLink" value="/list">
						<c:param name="category" value="${category.id}" />
					</c:url>
					<tr>
						<td>${category.tag}</td>
						<td>${category.name}</td>
						<td><a href="${selectLink}">Select</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>