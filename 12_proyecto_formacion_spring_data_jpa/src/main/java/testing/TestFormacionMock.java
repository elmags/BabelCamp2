package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import dao.AlumnosDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;
import service.FormacionService;
import service.FormacionServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestFormacionMock {
	
	@Mock
	AlumnosDao alumnosDao;
	
	@Mock
	CursosDao cursosDao;
	
	List<Alumno> alumnos;
	List<Curso> cursos;
	
	FormacionService fService;
	
	@BeforeEach		// Antes de cada llamada al método test se ejecuta este metodo
	void init() {
		cursos = List.of(new Curso(1, "curso1", 100, null, 10), 
						 new Curso(2, "curso2", 200, null, 20),
						 new Curso(3, "curso3", 300, null, 30));
		alumnos = List.of(new Alumno("user1", "pass1", "nombre1", "user1@email.com", 10, List.of(cursos.get(0), cursos.get(1))),
						  new Alumno("user2", "pass2", "nombre2", "user2@email.com", 20, List.of(cursos.get(0))),
						  new Alumno("user3", "pass2", "nombre3", "user3@email.com", 30, List.of(cursos.get(2))),
						  new Alumno("user4", "pass4", "nombre4", "user4@email.com", 40, List.of(cursos.get(0), cursos.get(1), cursos.get(2))));
		
		when(alumnosDao.findByUsuarioAndPassword("user1", "pass1")).thenReturn(alumnos.get(0));
		when(alumnosDao.findByUsuarioAndPassword("user5", "pass5")).thenReturn(null);
		
		when(alumnosDao.findByCurso("curso1")).thenReturn(List.of(alumnos.get(0), alumnos.get(1), alumnos.get(3)));
		when(alumnosDao.findByCurso("curso4")).thenReturn(null);
		
		when(alumnosDao.findById("user1")).thenReturn(Optional.of(alumnos.get(0)));
		when(alumnosDao.findById("user5")).thenReturn(null);
		
		when(cursosDao.findByAlumno("user1")).thenReturn(alumnos.get(0).getCursos());
		when(cursosDao.findByAlumno("user5")).thenReturn(null);
		
		when(cursosDao.findById(1)).thenReturn(Optional.of(cursos.get(0)));
		when(cursosDao.findById(4)).thenReturn(null);
		
		when(cursosDao.findAll()).thenReturn(cursos);
		
		fService = new FormacionServiceImpl(alumnosDao, cursosDao);
	}
	
	/* Test validarUsuario */
	@Test
	void test1Formacion() {
		Alumno a = fService.validarUsuario("user1", "pass1");
		assertEquals(a.getUsuario(), "user1");
		assertEquals(a.getPassword(), "pass1");
		assertEquals(a.getNombre(), "nombre1");
		assertEquals(a.getEmail(), "user1@email.com");
		assertEquals(a.getEdad(), 10);
	}
	
	/* Test cursosAlumno */
	@Test
	void test2Formacion() {
		assertEquals(2, fService.cursosAlumno("user1").size());
		assertEquals(1, fService.cursosAlumno("user2").size());
	}
	
	/* Test cursos */
	@Test
	void test3Formacion() {
		assertEquals(3, fService.cursos().size());
	}
	
	/* Test alumnosCurso */
	@Test
	void test4Formacion() {
		assertEquals(3, fService.alumnosCurso("curso1").size());
//		assertEquals(1, fService.alumnosCurso("curso2").size());
	}
}
