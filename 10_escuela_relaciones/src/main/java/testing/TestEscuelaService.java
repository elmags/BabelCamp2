package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.EscuelaService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestEscuelaService {
	
	@Autowired
	EscuelaService eService;
	
	/* Test alumnosCursos */
	@Test
	void test1Escuela() {
		assertEquals(4, eService.alumnosCurso("java").size()); 
	}	
	
	/* Test alumnosCursosDuracion */
	@Test
	void test2Escuela() {
		assertEquals(3, eService.alumnosCursoDuracion(100).size());
	}
	
	/* Test cursoMatriculadoAlumno */
	@Test
	void test3Escuela() {
		assertEquals(2, eService.cursoMatriculadoAlumno("1111A").getIdCurso());
	}
	
	/* Test cursosAlumnosSenior */
	@Test
	void test4Escuela() {
		assertEquals(3, eService.cursosAlumnosSenior(35).size());
	}
	
	/* Test edadMediaCurso */
	@Test
	void test5Escuela() {
		assertEquals(25.0, eService.edadMediaCurso("java"));
	}
	
	/* Test precioCurso */
	@Test
	void test6Escuela() {
		assertEquals(250.0, eService.precioCurso("primero@gmail.com"));
	}
}
