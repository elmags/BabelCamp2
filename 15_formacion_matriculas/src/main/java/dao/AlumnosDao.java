package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Alumno;

public interface AlumnosDao extends JpaRepository<Alumno, String> {
	Alumno findByUsuarioAndPassword(String usuario, String password);
	@Query("SELECT a FROM Alumno a join a.matriculas m WHERE m.curso.nombre = ?1")
	List<Alumno> findByCurso(String nombre);
}
