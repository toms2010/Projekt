<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Szczegóły</title>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Szczegóły : ${product.name}</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<form action="processFormVersionTwo" method="GET">

				<input type="text" name="studentName"
					placeholder="What's your name?" /> <input type="submit" />

			</form>
		</div>
	</div>
</body>

</html>


