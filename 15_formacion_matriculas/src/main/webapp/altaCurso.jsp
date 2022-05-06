<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta de un curso</title>
<link rel="stylesheet" href="css/altas.css">
</head>
<body>
	<div class="content">
		<div class="container">
			<h2>Alta de un curso</h2>
			<form action="AltaCurso" method="post">
				<div class="caja"><input class="text" type="text" name="nombre" placeholder="Nombre" /></div>
				<div class="caja"><input class="text" type="number" name="duracion" placeholder="Duracion" /></div>
				<div class="caja"><input class="text" type="number" name="precio" placeholder="Precio" /></div>
				<div class="caja">Fecha de inicio: <input class="text" type="date" name="fechaInicio" placeholder="Fecha de inicio" /></div>
				<div><input class="boton" type="submit" value="Guardar" /></div>
				<a href="menu.jsp">Menú</a>
			</form>
		</div>
	</div>
</body>
</html>