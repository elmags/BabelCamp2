<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta de un alumno</title>
<link rel="stylesheet" href="css/altas.css">
</head>
<body>
	<div class="content">
		<div class="container">
			<h2>Alta de un alumno</h2>
			<div class="errores"><p class="error">No se ha podido realizar el alta</p></div>
			<form action="AltaAlumno" method="post">
				<div class="caja"><input class="text" type="text" name="usuario" placeholder="Nombre de usuario" /></div>
				<div class="caja"><input class="text" type="password" name="password" placeholder="Contraseña" /></div>
				<div class="caja"><input class="text" type="text" name="nombre" placeholder="Nombre" /></div>
				<div class="caja"><input class="text" type="email" name="email" placeholder="Email" /></div>
				<div class="caja"><input class="text" type="number" name="edad" placeholder="Edad" /></div>
				<div><input class="boton" type="submit" value="Guardar" /></div>
				<a href="menu.jsp">Menú</a>
			</form>
		</div>
	</div>
</body>
</html>