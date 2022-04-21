package service;

import java.util.List;

import model.Alumno;

public interface AcademiaService {
	String alta(Alumno alumno);
	List<Alumno> buscar(String curso);
	List<String> cursos();
}
