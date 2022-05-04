<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validar</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="login">
		<h2>Validar número de cuenta</h2>
		<p> El usuario no se ha podido validar </p>
		<form action="Validar" method="post">
			<div class="caja"><input class="text" type="text" name="numeroCuenta" placeholder="Número de cuenta" /></div>
			<div><input class="boton" type="submit" value="Validar" /></div>
		</form>
	</div>
</body>
</html>