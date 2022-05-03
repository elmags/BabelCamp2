package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	@Query("SELECT c FROM Curso c join c.alumnos a WHERE a.usuario = ?1")
	List<Curso> findByAlumno(String usuario);
}
