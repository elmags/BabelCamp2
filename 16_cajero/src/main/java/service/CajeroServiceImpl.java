package service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converters.ConversorEntityDto;
import dao.CuentaDao;
import dao.MovimientoDao;
import dtos.CuentaDto;
import dtos.MovimientoDto;
import model.Cuenta;
import model.Movimiento;

@Service
public class CajeroServiceImpl implements CajeroService {

	CuentaDao cuentasDao;
	MovimientoDao movimientosDao;
	
	@Autowired
	ConversorEntityDto conversor;
	
	public CajeroServiceImpl(@Autowired CuentaDao cuentasDao, @Autowired MovimientoDao movimientosDao) {
		this.cuentasDao = cuentasDao;
		this.movimientosDao = movimientosDao;
	}

	@Override
	public CuentaDto validarCuenta(int numeroCuenta) {
		Optional<Cuenta> cuenta = cuentasDao.findById(numeroCuenta);
		if (cuenta.isPresent()) return conversor.cuentaToDto(cuenta.get());
		return null;
	}

	@Transactional
	@Override
	public void ingresarDinero(CuentaDto cuentaDto, double cantidad) {
		cuentaDto.setSaldo(cuentaDto.getSaldo() + cantidad);
		conversor.cuentaToDto(cuentasDao.save(conversor.dtoToCuenta(cuentaDto)));
		Calendar c1 = Calendar.getInstance();
		movimientosDao.save(new Movimiento(cuentaDto.getNumeroCuenta(), c1.getTime(), cantidad, "ingreso"));
	}

	@Transactional
	@Override
	public void extraerDinero(CuentaDto cuentaDto, double cantidad) {
		cuentaDto.setSaldo(cuentaDto.getSaldo() - cantidad);
		conversor.cuentaToDto(cuentasDao.save(conversor.dtoToCuenta(cuentaDto)));
		Calendar c1 = Calendar.getInstance();
		movimientosDao.save(new Movimiento(cuentaDto.getNumeroCuenta(), c1.getTime(), cantidad, "extracción"));
	}

	@Transactional
	@Override
	public boolean transferencia(CuentaDto cuentaDto, double cantidad, CuentaDto cuentaTransferenciaDto) {
		extraerDinero(cuentaDto, cantidad);
		ingresarDinero(cuentaTransferenciaDto, cantidad);
		return true;
	}

	@Override
	public double mostrarSaldo(CuentaDto cuentaDto) {
		return cuentaDto.getSaldo();
	}

	@Override
	public List<MovimientoDto> movimientos(CuentaDto cuentaDto, Date fechaInicio, Date fechaFin) {
		return movimientosDao.findBetweenDate(fechaInicio, fechaFin)
			   .stream().map(m -> conversor.movimientoToDto(m)).collect(Collectors.toList());
	}
}
