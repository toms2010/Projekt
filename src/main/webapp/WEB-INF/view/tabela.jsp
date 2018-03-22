
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista</title>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Lista produktów</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Nazwa</th>					
					<th>Cena</th>
				</tr>
				<c:forEach var="tempProduct" items="${product}">
					<tr>
						<td>${tempProduct.name}</td>
						<td>${tempProduct.price}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>
