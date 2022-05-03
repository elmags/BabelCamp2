package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestFormacionService {

	@Autowired
	FormacionService fService;
	
	/* Test validarUsuario */
	@Test
	void test1Formacion() {
		assertNotNull(fService.validarUsuario("admin", "a"));
		assertNull(fService.validarUsuario("admin", "aaa"));
	}
	
	/* Test cursosAlumno */
	@Test
	void test2Formacion() {
		assertEquals(2, fService.cursosAlumno("aaa").size());
		assertEquals(7, fService.cursosAlumno("webmvc").size());
	}
	
	/* Test cursos */
	@Test
	void test3Formacion() {
		assertEquals(19, fService.cursos().size());
	}
	
	/* Test alumnosCurso */
	@Test
	void test4Formacion() {
		assertEquals(8, fService.alumnosCurso("java 10").size());
		assertEquals(4, fService.alumnosCurso("kafka").size());
	}
	
	/* Test cursos no matriculado */
	@Test
	void test5Formacion() {
		assertEquals(14, fService.cursosNoMatriculados("admin").size());
	}
	
	/* Test cursos entre dos fechas */
	@Test
	void test6Formacion() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.set(2021, 1, 20);
		c2.set(2022, 3, 20);
		Date d1 = c1.getTime();
		Date d2 = c2.getTime();
		assertEquals(3, fService.cursosEntreFecha(d1, d2).size());
	}
}
