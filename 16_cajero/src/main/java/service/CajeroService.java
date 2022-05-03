package service;

import java.util.Date;
import java.util.List;

import dtos.CuentaDto;
import dtos.MovimientoDto;

public interface CajeroService {
	CuentaDto validarCuenta(int numeroCuenta);
	void ingresarDinero(CuentaDto cuentaDto, double cantidad);
	void extraerDinero(CuentaDto cuentaDto, double cantidad);
	boolean transferencia(CuentaDto cuentaDto, double cantidad, CuentaDto cuentaTranferencia);
	double mostrarSaldo(CuentaDto cuentaDto);
	List<MovimientoDto> movimientos(CuentaDto cuentaDto, Date fechaInicio, Date fechaFin);
}
