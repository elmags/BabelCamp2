<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="login">
		<h2>Validar usuario</h2>
		<p> El usuario no se ha podido validar </p>
		<form action="Validar" method="post">
			<div class="caja"><input class="text" type="text" name="usuario" placeholder="Nombre de usuario" /></div>
			<div class="caja"><input class="text" type="password" name="password" placeholder="Contraseña" /></div>
			<div><input class="boton" type="submit" value="Validar" /></div>
		</form>
	</div>
</body>
</html>