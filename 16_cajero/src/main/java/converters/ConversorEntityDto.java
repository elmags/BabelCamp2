package converters;

import dtos.CuentaDto;
import dtos.MovimientoDto;
import model.Cuenta;
import model.Movimiento;

public interface ConversorEntityDto {
	CuentaDto cuentaToDto(Cuenta cuenta);
	Cuenta dtoToCuenta(CuentaDto dto);
	MovimientoDto movimientoToDto(Movimiento movimiento);
	Movimiento dtoToMovimiento(MovimientoDto dto);
}
