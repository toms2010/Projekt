<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<% 	response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
	<title>Szczegóły produktu</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<!-- Obsługa logout -->
	<form action="<c:url value="/logout"/>}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
			function formLogout() {
				document.getElementById("logoutForm").submit();
			}
	</script>

	<div id="wrapper">
		<h3>
			Zalogowany jako: ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formLogout()">Logout</a>
		</h3>

		<div id="header">
			<h2>Dokładne parametry wybranego produktu</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<h3>Parametry dla : ${product.name}</h3>
			<p>
			<h4>${product.description}</h4>
			<form:form action="saveProductVO" method="POST">
				<c:forEach var="parameter" items="${products.productParameter}">
					<input type="checkbox" name="selected" value="${parameter.id}"> ${parameter.name}
				</c:forEach>
				<input type="submit" name="submit" value="Delete" >
			</form:form>	
	
				<table>
					<tr>
						<td>Kod : ${product.code}/</td>
						<td>
					</tr>
					<tr>
						<td>mH2O/</td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</table>

		</div>
	</div>
</body>

</html>


