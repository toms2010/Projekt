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
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
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
				<form:form action="saveProduct" modelAttribute="productVO" method="POST">
					<h5>
						<h3>Zakres pomiarowy:</h3>
						<form:hidden path="rangeLow" value="0"/>
						<form:input path="rangeLow" readonly="true" />
						...
						<form:input path="rangeHigh" />
						<br/>
						<h3>Kabel:</h3>
						Dłogość: <form:input path="lenght" />  Typ kabla : <form:select path="cableType" items="${cableTypes}" />
					</h5>
					<h5>Wybierz dodatkowe opcje:</h5>
					<table>
						<c:forEach var="parameter" items="${product.productParameter}">
							<tr>
								<td><form:checkbox path="productParameterID" value="${parameter.id}" /></td>
								<td>${parameter.name}</td>
								<td>${parameter.value}</td>
							</tr>
						</c:forEach>
					</table>
					<br>
					<h5>Wykonania:</h5>
					<c:forEach var="design" items="${product.productDesign}" varStatus="rowItem">
						<form:radiobutton path="productDesignID" value="${design.id}" /> ${design.name}
						<br>
					</c:forEach>
					<br>
					
					<form:hidden path="name" value="${product.name}"/>
					<form:hidden path="tag" value="${product.code}"/>
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