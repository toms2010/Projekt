<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% 	response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodaj/edytuj kategorie</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-style.css">
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
			<c:choose>
				<c:when test="${category.id == null}">
					<h2>Dodawanie kategorii</h2>
				</c:when>
				<c:otherwise>
					<h2>Edycja kategori</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<form:form action="saveCategory" method="post" modelAttribute="category" acceptCharset="UTF-8">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				Numer id : <form:input path="id" disabled="true"/>
				<br />
				Nazwa kategorii: <form:input path="name" />
				<form:errors path="name" cssClass="error" />
				<br />
				Kod Kategorii <form:input path="code" />
				<form:errors path="code" cssClass="error" />
				<br />
				<input type="submit" value="Zapisz" class="save" />
			</form:form>
			<c:if test="${category.id == null}">
				<p>Możliwe utowrzenie kategorii jedynie dla produktów nie wymagających dodatkowej specyfikacji
			</c:if>
		</div>
	</div>
	<br>
	<form action="<c:url value="/category"/>" method="get">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
		<input type="submit" value="Wróć" class="add-button" />
	</form>
</body>
</html>
