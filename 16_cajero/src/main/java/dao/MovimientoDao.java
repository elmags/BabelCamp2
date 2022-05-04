package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Movimiento;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer>{
	@Query("SELECT m FROM Movimiento m WHERE m.idCuenta = ?1 AND m.fecha BETWEEN ?2 AND ?3")
	List<Movimiento> findBetweenDate(int idCuenta, Date fechaInicio, Date fechaFin);
}
