package dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MovimientoDto {
	private int idMovimiento;
	private int idCuenta;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecha;
	private double cantidad;
	private String operacion;
}
