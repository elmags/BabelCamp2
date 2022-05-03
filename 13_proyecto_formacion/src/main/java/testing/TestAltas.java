package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import model.Alumno;
import model.Curso;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestAltas {

	@Autowired
	FormacionService fService;
	
	/* Test altaAlumno */
	@Test
	void test1Altas() {
		Alumno alumno = new Alumno("user1", "pass1", "user1", "user1@email.com", 20);
		fService.altaAlumno(alumno);
		assertEquals(25, fService.alumnos().size());
	}
	
	/* Test altaCurso */
	@Test
	void test2Altas() {
		Curso curso = new Curso("curso1", 20, null, 20);
		fService.altaCurso(curso);
		assertEquals(19, fService.cursos().size());
	}
}
