package service;

import java.util.List;

import model.Alumno;
import model.Curso;

public interface EscuelaService {
	List<Alumno> alumnosCurso(String nombre);
	List<Alumno> alumnosCursoDuracion(int duracionMax);
	Curso cursoMatriculadoAlumno(String dni);
	List<Curso> cursosAlumnosSenior(int edad);	// Lista de cursos en los que están matriculados alumnos con edad mayor al valor recibido
	double edadMediaCurso(String nombre);	// Devuelve la edad media de los alumnos matriculados en el curso indicado
	double precioCurso(String email);	// Devuelve el precio del curso en el que está matriculado el alumno cuyo email se recibe	
}
