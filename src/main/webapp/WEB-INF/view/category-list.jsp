<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<title>Kategorie produktów</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<!-- Nagłówek z przyciskiem logout -->
	<div id="wrapper">
		<h3>Zalogowany jako: ${pageContext.request.userPrincipal.name}</h3>
		<!-- Obsługa logout -->
		<form action="<c:url value="/logout"/>" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			<input type="submit" value="Wyloguj" class="add-button" />
		</form>

		<!-- Nagłówek z tytułem -->
		<div id="header">
			<h2>Lista kategorii produktów</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<!-- Przycisk dodawania nowej kategorii -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<form action="<c:url value="/adm/addCategory"/>" method="get">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
					<input type="submit" value="Dodaj kategorie" class="add-button" />
				</form>
			</sec:authorize>

			<!-- Tabela -->
			<table>
				<tr>
					<th>Tag</th>
					<th>Nazwa</th>
					<th>Ilość produktów</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="category" items="${categoryList}">
					<tr>
						<td>${category.tag}</td>
						<td>${category.name}</td>
						<td>${category.size}</td>
						<td>
							<form action="<c:url value="/products"/>" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<input type="hidden" name="categoryId" value="${category.id}" />
								<input type="submit" value="Wybierz" />
							</form>
						</td>
						<td><sec:authorize access="hasRole('ROLE_ADMIN')">
								<form action="<c:url value="/adm/editCategory"/>" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
									<input type="hidden" name="categoryId" value="${category.id}" />
									<input type="submit" value="Edytuj" />
								</form>
							</sec:authorize></td>
						<td><sec:authorize access="hasRole('ROLE_ADMIN')">
								<form action="<c:url value="/adm/deleteCategory"/>" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
									<input type="hidden" name="categoryId" value="${category.id}" />
									<input type="submit" value="Usuń" />
								</form>
							</sec:authorize></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>