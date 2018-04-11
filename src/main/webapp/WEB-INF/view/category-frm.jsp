<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% 	response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %> 

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dodaj/edytuj kategorie</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
	<!-- Obsługa logout -->
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<script>
			function formLogout() {
				document.getElementById("logoutForm").submit();
			}
	</script>
	<!-- Nagłówek z przyciskiem logout -->
	<div id="wrapper">
		<h3>Zalogowany jako: ${pageContext.request.userPrincipal.name} | 
			<a href="javascript:formLogout()">Logout</a></h3>
		<!-- Nagłówek z tytułem -->	
		<div id="header">
			<h2>Dodawanie kategori</h2>
<!-- 		TODO	JavaScripy do zmiany nagłówka w zależności czy dodane id -->
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<form:form action="saveCategory" method="post" modelAttribute="category" acceptCharset="UTF-8"> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				Numer id : <form:input path="id" readonly="true"/><br/>
				Nazwa kategorii: <form:input path="name" /><br/>
				Tag Kategorii <form:input path="tag" /><br/>
				<input type="submit" value="Zapisz" class="save"/>
			</form:form>
			JS: Jeśli nowy (id=null) to komunikat:
			"Możliwe utowrzenie kategorii jedynie dla produktów nie wymagających dodatkowej specyfikacji"
		</div>
	</div>
	
</body>
</html>
