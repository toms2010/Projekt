<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Szczegóły</title>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Wybieranie dokładnych parametrów produktu</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<h3>product.name</h3>
			<form:form action="saveProduct" modelAttribute="product"
				method="POST">

				<table>
					<tbody>
						<tr>
							<td>Kod : product.code/</td>
						</tr>
						<tr>
							<td><form:input path="rangeLow" /></td>
						</tr>
						<tr>
							<td>...</td>
						</tr>
						<tr>
							<td><form:input path="rangeHigh" /></td>
						</tr>
						<tr>
							<td>mH2O/</td>
							<td><input type="submit" value="Save" class="save" /></td>
						</tr>
					</tbody>
				</table>

				
				<input type="text" name="studentName"
					placeholder="What's your name?" />
				<input type="submit" />

			</form:form>
		</div>
	</div>
</body>

</html>


