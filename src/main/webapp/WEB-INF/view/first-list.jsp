<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<% 	response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %> 

<html>
<head>
	<title>Kategorie produktów</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<!-- Obsługa logout -->
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
	</script>
	
	<div id="wrapper">
		<h3>Zalogowany jako: ${pageContext.request.userPrincipal.name} | 
			<a href="javascript:formSubmit()">Logout</a></h3>
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
<!-- 					Dodać ilośc prod w kategorii -->
				</tr>
				<c:forEach var="category" items="${categoryList}">
					<c:url var="selectCategory" value="/products">
						<c:param name="category" value="${category.id}" />
					</c:url>
					<tr>
						<td>${category.tag}</td>
						<td>${category.name}</td>
						<td>
							<a href="${selectCategory}">Select</a>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a href="${selectCategory}">Edit</a>	
							</sec:authorize>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>