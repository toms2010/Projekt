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
    <title>Wybrany produkt</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<div id="wrapper">
		<h3> Zalogowany jako: ${pageContext.request.userPrincipal.name}  </h3>
		<!-- Obsługa logout -->
		<form action="<c:url value="/logout"/>" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			<input type="submit" value="Wyloguj" class="add-button" />
		</form>

		<div id="header">
			<h2>Wybrano produkt: ${productVO.name}</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
		
<%-- 		<h1>${productVO.name}/${productVO.rangeLow}...${productVO.rangeHigh} ${productVO.unit}</h1> --%>
<!-- TODO kod -->
		${productVO.name}
		<h2>Cena to : ${totalPrice}</h2>
		
			
		</div>
	</div>
	
	<form action="<c:url value="/details"/>" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="productId" value="${productVO.id}" /> 
		<input type="hidden" name="productVO" value="${productVO}" /> 
		<input type="submit" value="Wstecz" />
<!-- 		TODO Wypełnianie -->
	</form>

</body>
</html>
