package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import model.Curso;
import service.FormacionService;

@CrossOrigin("*")
@Controller
public class FormacionController {

	@Autowired
	FormacionService fService;
	
	@PostMapping(value = "Validar")
	public String validar(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		Alumno alumno = fService.validarUsuario(usuario, password);
		if (alumno != null) return "menu";
		else return "errorLog";
	}
	
	@GetMapping(value="AlumnosCurso", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> alumnosPorCurso(@RequestParam("nombre") String nombre) {
		return fService.alumnosCurso(nombre);
	}
	
	@GetMapping(value="Alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> alumnos() {
		return fService.alumnos();
	}
	
	@GetMapping(value="CursosAlumno", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursosPorAlumno(@RequestParam("usuario") String usuario) {
		return fService.cursosAlumno(usuario);
	}
	
	@GetMapping(value="Cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursos() {
		return fService.cursos();
	}
}
