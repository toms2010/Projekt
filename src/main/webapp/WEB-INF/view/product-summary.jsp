<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
			<h2>Wybrano produkt: ${productVO.name}</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		
		<h1>${productVO.name}/${productVO.rangeLow}...${productVO.rangeHigh} ${productVO.unit}</h1>
		
		<h2>Cena to : ${totalPrice}</h2>
		
			
		</div>
	</div>

</body>
</html>
