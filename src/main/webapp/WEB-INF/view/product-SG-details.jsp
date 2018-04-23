<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%
    response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
%>

<html>
	<head>
	<title>Szczegóły produktu</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<div id="wrapper">
		<h3> Zalogowany jako: ${pageContext.request.userPrincipal.name}  </h3>
		<!-- Obsługa logout -->
		<form action="<c:url value="/logout"/>" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			 <input type="submit"value="Wyloguj" />
		</form>
		<div id="header">
			<h2>Dokładna specyfikacja</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<h3>Parametry dla : ${product.name}</h3>
			<h5>${product.description}</h5>
			<p>
			<h4>Podstawowe parametry urządzenia:</h4>
			<table>
				<c:forEach var="parameter" items="${product.productParameter}">
					<tr>
						<td>${parameter.name}</td>
						<td>${parameter.value}</td>
					</tr>
				</c:forEach>	
			</table>
			<h3>Szczegółowa specyfikacja:
			</h3>
				<form:form action="saveSGProduct" modelAttribute="productVO" method="POST" >
					<h5>Zakres pomiarowy:</h5>
					<form:input path="rangeHigh" /> mH2O
					<br/>
					<h5>Kabel:</h5>
					Typ kabla : 
					<form:select path="cableType">
						<c:forEach var="cable" items="${cableTypes}" varStatus="rowCount">
							<form:option value="${cable.id}">${cable.name} : ${cable.description}</form:option>
						</c:forEach>
					</form:select>
					Długość: <form:input path="lenght" /> 
					</h5>
					<h5>Wybierz dodatkowe wykonania:</h5>
					<table>
						<c:forEach var="design" items="${product.productDesign}">
							<tr>
								<td><form:checkbox path="productDesignID" value="${design.id}" /></td>
								<td>${design.name}</td>
								<td>${design.description}</td>
							</tr>
						</c:forEach>
					</table>
					<br>
					
					<form:hidden path="id" value="${product.id}"/>
					<form:hidden path="name" value="${product.name}"/>
					<form:hidden path="code" value="${product.code}"/>
					<form:hidden path="price" value="${product.price}"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="submit" name="submit" value="Zatwierdz">
				</form:form>
		</div>
	</div>
	<br>
	<form action="<c:url value="/products"/>" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
		<input type="hidden" name="categoryId" value="${product.category.id}" /> 
		<input type="submit" value="Wstecz" />
	</form>
</body>
</html>