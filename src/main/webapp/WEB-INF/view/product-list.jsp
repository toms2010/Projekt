<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<% 	response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
<title>Lista produktów</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<!-- Obsługa logout -->
	<form action="<c:url value="/logout"/>}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<script>
			function formLogout() {
				document.getElementById("logoutForm").submit();
			}
	</script>

	<div id="wrapper">
		<h3>
			Zalogowany jako: ${pageContext.request.userPrincipal.name} | <a href="javascript:formLogout()">Logout</a>
		</h3>

		<div id="header">
			<h2>Lista produktów: ${products[0].category.name}</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!-- Przycisk dodawania nowego produktu -->
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <form action="<c:url value="/adm/addProduct"/>" method="get">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
                    <input type="submit" value="Dodaj produkt" class="add-button" />
                </form>
            </sec:authorize>

			<table>
				<tr>
					<th>Kod</th>
					<th>Nazwa</th>
					<th>Cena</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="product" items="${products}">


					<tr>
						<td>${product.code}</td>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td>
							<form action="<c:url value="/details"/>" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<input type="hidden" name="productId" value="${product.id}" />
								<input type="submit" value="Wybierz" />
							</form>
						</td>
						<td><sec:authorize access="hasRole('ROLE_ADMIN')">
								<form action="<c:url value="/adm/editProduct"/>" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
									<input type="hidden" name="productId" value="${product.id}" />
									<input type="submit" value="Edytuj" />
								</form>
							</sec:authorize></td>
						<td><sec:authorize access="hasRole('ROLE_ADMIN')">
								<form action="<c:url value="/adm/deleteProduct"/>" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
									<input type="hidden" name="productId" value="${product.id}" />
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
