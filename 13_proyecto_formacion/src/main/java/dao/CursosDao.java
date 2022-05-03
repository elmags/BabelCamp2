package dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	@Query("SELECT c FROM Curso c join c.alumnos a WHERE a.usuario = ?1")
	List<Curso> findByAlumno(String usuario);
	@Query("SELECT c FROM Curso c WHERE c.nombre = ?1")
	Optional<Curso> findByNombre(String nombre);
	@Query("SELECT c FROM Curso c WHERE c NOT IN (SELECT c FROM Curso c join c.alumnos a WHERE a.usuario = ?1)")
	List<Curso> findCursos(String usuario);
	@Query("SELECT c FROM Curso c WHERE c.fechaInicio BETWEEN ?1 AND ?2")
	List<Curso> findBetweenDate(Date fechaInicio, Date fechaFin);
}
