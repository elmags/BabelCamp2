package service;

import java.util.List;

import model.Alumno;

public interface AcademiaService {
	
	/**
	 * Método que añade un Alumno a la base de datos.
	 * Previamente comprueba que el alumno no este en la bbdd.
	 * 
	 * @param alumno -> Alumno que se desea insertar en la base de datos.
	 * @return "alta" 	-> En caso de que se añada correctamente el alumno
	 * 					   se redirige a la página alta.
	 * 		   "existe" -> En caso contrario, se redirige a la pagina existe,
	 * 					   que muestra un mensaje indicando que el usuario
	 * 					   ya se encuentra en la bbdd y permite dar de alta
	 *					   a otro usuario.
	 */
	String alta(Alumno alumno);
	
	/**
	 * Método que busca en la base de datos alumnos por curso.
	 * 
	 * @param curso -> Curso del alumno que se quiere encontrar 
	 * @return Devuelve los alumnos encontrados que pertenecen a dicho curso,
	 * 		   si no encuentra devuelve null.
	 */
	List<Alumno> buscar(String curso);
	
	/**
	 * Método que busca en la base de datos todos los cursos.
	 *  
	 * @return Devuelve todos los cursos que aparecen en la bbdd.
	 */
	List<String> cursos();
}
