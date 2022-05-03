package service;

import java.util.Date;
import java.util.List;

import model.Alumno;
import model.Curso;

public interface FormacionService {
	Alumno validarUsuario(String usuario, String password);
	List<Curso> cursosAlumno(String usuario);
	List<Curso> cursos();
	List<Alumno> alumnosCurso(String nombre);
	boolean matricular(String usuario, int idCurso);
	List<Alumno> alumnos();
	boolean altaAlumno(Alumno alumno);
	boolean altaCurso(Curso curso);
	List<Curso> cursosNoMatriculados(String usuario);
	List<Curso> cursosEntreFecha(Date fechaInicio, Date fechaFin);
}
