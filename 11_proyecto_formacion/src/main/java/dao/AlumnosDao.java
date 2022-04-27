package dao;

import java.util.List;

import model.Alumno;
import model.Curso;

public interface AlumnosDao {
	Alumno findByUsuarioAndPassword(String usuario, String password);
	List<Alumno> findByCurso(String nombre);
	Alumno findById(String usuario);
	void update(Alumno alumno);
	List<Alumno> findAll();
}
