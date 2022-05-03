package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	@PostMapping(value = "Validar")
	public String validar(@RequestParam("numeroCuenta") int numeroCuenta, HttpSession session) {
		CuentaDto cuenta = cService.validarCuenta(numeroCuenta);
		if (cuenta != null) {
			session.setAttribute("numeroCuenta", cuenta.getNumeroCuenta());
			return "menu";
		}
		else return "errorValidar";
	}
	
	@PostMapping(value = "Ingresar")
	public String ingresar(@RequestParam("cantidad") double cantidad, HttpSession session) {
		cService.ingresarDinero((CuentaDto) session.getAttribute("numeroCuenta"), cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Extraer")
	public String extraer(@RequestParam("cantidad") double cantidad, HttpSession session) {
		cService.extraerDinero((CuentaDto) session.getAttribute("numeroCuenta"), cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Transferencia")
	public String trasnferir(@RequestParam("cantidad") double cantidad, @RequestParam("ncTransferecia") int ncTransferecia, HttpSession session) {
		CuentaDto cuentaTrans = cService.validarCuenta((int) session.getAttribute("ncTransferecia"));
		cService.transferencia((CuentaDto) session.getAttribute("numeroCuenta"), cantidad, cuentaTrans);
		return "menu";
	}
	
	@GetMapping(value = "Saldo", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody double saldo(HttpSession session) {
		return cService.mostrarSaldo((CuentaDto) session.getAttribute("numeroCuenta"));
	}
	
	@GetMapping(value = "Movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> movimientos(@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d1,
			  											 @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d2,
			  											 HttpSession session) {
		return cService.movimientos((CuentaDto) session.getAttribute("numeroCuenta"), d1, d2);
	}
}
