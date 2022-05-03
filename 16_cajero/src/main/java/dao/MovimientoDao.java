package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Movimiento;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer>{
//	@Query("SELECT m FROM Movimiento m WHERE m.idCuenta = ?1 IN (?2)")
//	List<Movimiento> findMovimientos(int idCuenta, List<Movimiento> movimientos);
	@Query("SELECT m FROM Movimiento m WHERE m.fecha BETWEEN ?1 AND ?2")
	List<Movimiento> findBetweenDate(Date fechaInicio, Date fechaFin);
}
