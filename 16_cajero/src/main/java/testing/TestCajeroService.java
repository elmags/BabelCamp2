package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import dtos.CuentaDto;
import service.CajeroService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestCajeroService {
	
	@Autowired
	CajeroService cService;
	
	/* Test validarCuenta */
	@Test
	void test1Cajero() {
		assertNotNull(cService.validarCuenta(1000));
		assertNull(cService.validarCuenta(1));
	}
	
	/* Test mostrarSaldo */
	@Test
	void test2Cajero() {
		CuentaDto cuenta1 = new CuentaDto(3000, 670, "recibos");
		CuentaDto cuenta2 = new CuentaDto(4000, 880, "ahorro");
		assertEquals(cService.mostrarSaldo(cuenta1), 670);
		assertEquals(cService.mostrarSaldo(cuenta2), 880);
	}
	
//	/* Test movimientos */
//	@Test
//	void test6Cajero() {
//		CuentaDto cuenta = new CuentaDto(2000, 670, "recibos");
//		Calendar c1 = Calendar.getInstance();
//		Calendar c2 = Calendar.getInstance();
//		c1.set(2021, 1, 20);
//		c2.set(2022, 4, 20);
//		Date d1 = c1.getTime();
//		Date d2 = c2.getTime();
//		assertEquals(cService.movimientos(cuenta, d1, d2), 4);
//	}
}
