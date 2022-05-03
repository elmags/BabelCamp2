package dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	@Query("SELECT c FROM Curso c join c.matriculas m WHERE m.alumno.usuario = ?1")
	List<Curso> findByAlumno(String usuario);
	@Query("SELECT c FROM Curso c WHERE c.nombre = ?1")
	Optional<Curso> findByNombre(String nombre);
	@Query("SELECT c FROM Curso c WHERE c NOT IN (SELECT c FROM Curso c join c.matriculas m WHERE m.alumno.usuario = ?1)")
	List<Curso> findCursos(String usuario);
}
