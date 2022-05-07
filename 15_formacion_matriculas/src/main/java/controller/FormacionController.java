package controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dtos.AlumnoDto;
import dtos.CursoDto;
import dtos.MatriculaDto;
import service.FormacionService;

@CrossOrigin("*")	// Permite llamadas de cualquier origen (Autorizacion CORS)
@Controller
public class FormacionController {

	@Autowired
	FormacionService fService;
	
	@PostMapping(value="Validar")
	public String validar(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		AlumnoDto alumno = fService.validarUsuario(usuario, password);
		if (alumno != null) return "menu";
		else return "errorLog";
	}
	
	@GetMapping(value="AlumnosCurso", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnosPorCurso(@RequestParam("nombre") String nombre) {
		return fService.alumnosCurso(nombre);
	}
	
	@GetMapping(value="Alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnos() {
		return fService.alumnos();
	}
	
	@GetMapping(value="CursosAlumno", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursosPorAlumno(@RequestParam("usuario") String usuario) {
		return fService.cursosAlumno(usuario);
	}
	
	@GetMapping(value="Cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursos() {
		return fService.cursos();
	}
	
	@PostMapping(value="Matricular")
	public String matricular(@RequestParam("usuario") String usuario, @RequestParam("idCurso") int idCurso) {
		System.out.println("1  " + idCurso + "  " + usuario);
		fService.matricular(usuario, idCurso);
		System.out.println("4");
		return "menu";
	}
	
	@PostMapping(value="AltaAlumno")
	public String altaAlumno(@ModelAttribute AlumnoDto alumno) {
		if (fService.altaAlumno(alumno)) {
			return "menu";
		}
		return "errorAltaAlumno";
	}
	
	@PostMapping(value="AltaCurso")
	public String altaCurso(@RequestParam("nombre") String nombre, @RequestParam("duracion") int duracion, @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam("precio") double precio) {
		if (fService.altaCurso(nombre, duracion, fechaInicio, precio)) {
			return "menu";
		}
		return "errorAltaCurso";
	}
	
	@GetMapping(value="CursosNoMatriculados", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursosNoMatriculados(@RequestParam("usuario") String usuario) throws ParseException {
		return fService.cursosNoMatriculados(usuario);
	}
	
//	@GetMapping(value="CursosEntreFecha", produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody List<Curso> cursosEntreFecha(@RequestParam("fechaInicio") String fecha1, @RequestParam("fechaFin") String fecha2) {
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//		Date d1, d2;
//		try {
//			d1 = formato.parse(fecha1);
//			d2 = formato.parse(fecha2);
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return fService.cursosEntreFecha(d1, d2);
//	}
	
	@GetMapping(value="Matriculas", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MatriculaDto> cursosEntreFecha(@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d1,
													  @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d2) {
		System.out.println("Matriculas");
		return fService.cursosEntreFecha(d1, d2);
	}
	
	@GetMapping(value="Alumno", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AlumnoDto getAlumno(@RequestParam("nombre") String nombre) {
		return fService.alumno(nombre);
	}
	
	@GetMapping(value="Curso", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CursoDto getCurso(@RequestParam("nombre") String nombre) {
		return fService.curso(nombre);
	}
}