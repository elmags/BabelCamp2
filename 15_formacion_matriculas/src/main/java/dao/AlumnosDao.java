package dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Alumno;

public interface AlumnosDao extends JpaRepository<Alumno, String> {
	Alumno findByUsuarioAndPassword(String usuario, String password);
	@Query("SELECT a FROM Alumno a join a.matriculas m WHERE m.curso.nombre = ?1")
	List<Alumno> findByCurso(String nombre);
	@Query("SELECT a FROM Alumno a WHERE a.nombre = ?1")
	Optional<Alumno> findByNombre(String nombre);
}
