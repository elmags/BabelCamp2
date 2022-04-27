package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import model.Alumno;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestFormacionService {

	@Autowired
	FormacionService fService;
	
	/* Test validarUsuario */
	@Test
	void test1Formacion() {
		Alumno a = fService.validarUsuario("admin", "a");
		assertEquals(a.getUsuario(), "admin");
		assertEquals(a.getPassword(), "a");
		assertEquals(a.getNombre(), "tomates");
		assertEquals(a.getEmail(), "servlet@gmail.com");
		assertEquals(a.getEdad(), 22);
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
		assertEquals(18, fService.cursos().size());
	}
	
	/* Test alumnosCurso */
	@Test
	void test4Formacion() {
		assertEquals(8, fService.alumnosCurso("java 10").size());
		assertEquals(4, fService.alumnosCurso("kafka").size());
	}
	
//	/* Test matriculado */
//	@Test
//	void test5Formacion() {
//		assertTrue(fService.matriculado("admin", 1));
//	}
//	
//	/* Test matricular con usuario existente */
//	@Test
//	void test6Formacion() {
//		if (!fService.matriculado("admin", 3)) {
//			assertTrue(fService.matricular("admin", 3));
//			assertEquals(5, fService.cursosAlumno("admin").size());
//		}
//		else {
//			assertFalse(fService.matricular("admin", 3));
//			assertEquals(5, fService.cursosAlumno("admin").size());
//		}
//	}
	
//	/* Test matricular con usuario que no existe */
//	@Test
//	void test7Formacion() {
//		assertFalse(fService.matricular("123445", 3));
//	}
}
