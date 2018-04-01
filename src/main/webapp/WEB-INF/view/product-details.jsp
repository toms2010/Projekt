<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<% response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
	<title>Szczegóły produktu</title>
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
			Zalogowany jako: ${pageContext.request.userPrincipal.name} |
			<a href="javascript:formLogout()">Logout</a>
		</h3>

		<div id="header">
			<h2>Dokładna specyfikacja</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<h3>Parametry dla : ${product.name}</h3>
			<h5>${product.description}</h5>
			<p>
				<form:form action="saveProductVO" method="POST">
					<h5>
						Zakres pomiarowy: <input type="text" name="rangeLow" />...
						<input type="text" name="rangeHigh" /> 
						<select name="jednostka">
							<option value="kPa">Pa</option>
							<option value="kPa" selected>kPa</option>
							<option value="kPa">MPa</option>
						</select>
					</h5>
					Uzupełnij zakres pomiarowy. Zakres odnosi się do ciśnienia atmosferycznego, dla pomiaru ciśnienia bezwzględnego jako początek zakresu podać 100 kPA
					<h5>Wybierz dodatkowe opcje:</h5>
					<table>
						<c:forEach var="parameter" items="${product.productParameter}">
							<tr>
								<td><input type="checkbox" name="selected"
									value="${parameter.id}"></td>
								<td>${parameter.name}</td>
								<td>${parameter.value}</td>
							</tr>
						</c:forEach>
					</table>

					<h5>Wykonania:</h5>
					<c:forEach var="design" items="${product.productDesign}">
						<input type="radio" name="nazwa" value="${design.id}" /> ${design.name}
						<br>
					</c:forEach>
					<br>
					<input type="submit" name="submit" value="Zatwierdz">
				</form:form>
		</div>
	</div>
</body>

</html>


