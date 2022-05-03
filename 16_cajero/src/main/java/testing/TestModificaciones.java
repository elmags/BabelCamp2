package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import dtos.CuentaDto;
import service.CajeroService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestModificaciones {

	@Autowired
	CajeroService cService;
	
	/* Test ingresarDinero */
	@Test
	void test1Modificaciones() {
		CuentaDto cuenta = new CuentaDto(1000, 41567, "ahorro");
		cService.ingresarDinero(cuenta, 20);
		assertEquals(cService.mostrarSaldo(cuenta), 41587);
	}
	
	/* Test extraerDinero */
	@Test
	void test2Modificaciones() {
		CuentaDto cuenta = new CuentaDto(1000, 41587, "ahorro");
		cService.extraerDinero(cuenta, 20);
		assertEquals(cService.mostrarSaldo(cuenta), 41567);
	}
	
	/* Test transaccion */
	@Test
	void test3Modificaciones() {
		CuentaDto cuentaExt = new CuentaDto(2000, 12900, "prueba2");
		CuentaDto cuentaIng = new CuentaDto(1234, 570, "ahorro");
		cService.transferencia(cuentaExt, 20, cuentaIng);
		assertEquals(cService.mostrarSaldo(cuentaExt), 12880);
		assertEquals(cService.mostrarSaldo(cuentaIng), 590);
	}
}
