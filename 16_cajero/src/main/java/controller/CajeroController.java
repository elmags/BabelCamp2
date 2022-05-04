package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dtos.CuentaDto;
import dtos.MovimientoDto;
import service.CajeroService;

@Controller
public class CajeroController {

	@Autowired
	CajeroService cService;
	
	private CuentaDto cuentaValidada;
	
	@PostMapping(value = "Validar")
	public String validar(@RequestParam("numeroCuenta") int numeroCuenta) {
		CuentaDto cuenta = cService.validarCuenta(numeroCuenta);
		if (cuenta != null) {
//			session.setAttribute("numeroCuenta", cuenta.getNumeroCuenta());
			cuentaValidada = cuenta;
			return "menu";
		}
		else return "errorValidar";
	}
	
	@PostMapping(value = "Ingresar")
	public String ingresar(@RequestParam("cantidad") double cantidad) {
		cService.ingresarDinero(cuentaValidada, cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Extraer")
	public String extraer(@RequestParam("cantidad") double cantidad) {
		cService.extraerDinero(cuentaValidada, cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Transferencia")
	public String trasnferir(@RequestParam("cantidad") double cantidad, @RequestParam("ncTransferencia") int ncTransferecia) {
		CuentaDto cuentaTrans = cService.validarCuenta(ncTransferecia);
		cService.transferencia(cuentaValidada, cantidad, cuentaTrans);
		return "menu";
	}
	
	@GetMapping(value = "Saldo", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody double saldo() {
		return cService.mostrarSaldo(cuentaValidada);
	}
	
	@GetMapping(value = "Movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> movimientos(@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d1,
			  											 @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d2) {
		return cService.movimientos(cuentaValidada, d1, d2);
	}
}
