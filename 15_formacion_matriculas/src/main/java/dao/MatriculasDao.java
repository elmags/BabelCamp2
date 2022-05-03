package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Matricula;
import model.MatriculaPK;

public interface MatriculasDao extends JpaRepository<Matricula, MatriculaPK> {
	@Query("SELECT m FROM Matricula m join m.curso c WHERE c.fechaInicio BETWEEN ?1 AND ?2")
	List<Matricula> findBetweenDate(Date fechaInicio, Date fechaFin);
}
