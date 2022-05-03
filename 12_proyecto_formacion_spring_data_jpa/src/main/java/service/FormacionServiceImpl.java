package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AlumnosDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;

@Service
public class FormacionServiceImpl implements FormacionService {

	AlumnosDao alumnosDao;
	CursosDao cursosDao;
	
	public FormacionServiceImpl(@Autowired AlumnosDao alumnosDao, @Autowired CursosDao cursosDao) {
		this.alumnosDao = alumnosDao;
		this.cursosDao = cursosDao;
	}

	@Override
	public Alumno validarUsuario(String usuario, String password) {
		return alumnosDao.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public List<Curso> cursosAlumno(String usuario) {
		return cursosDao.findByAlumno(usuario);
	}

	@Override
	public List<Curso> cursos() {
		return cursosDao.findAll();
	}

	@Override
	public List<Alumno> alumnosCurso(String nombre) {
		return alumnosDao.findByCurso(nombre);
	}

	@Transactional
	@Override
	public boolean matricular(String usuario, int idCurso) {
		/* Alumno alumno = alumnosDao.findById(usuario).orElse(null); // Si no se ha encontrado el alumno devuelve null
		Curso curso = cursosDao.findById(idCurso).orElse(null);
		if (alumno != null && curso != null) {
			alumno.getCursos().add(curso);
			alumnosDao.save(alumno);
			return true;
		}
		return false; */
		
		Optional<Alumno> alumno = alumnosDao.findById(usuario);
		Optional<Curso> curso = cursosDao.findById(idCurso);
		if (alumno.isPresent() && curso.isPresent()) {
			Alumno a = alumno.get();
			a.getCursos().add(curso.get());
			alumnosDao.save(a);
			return true;
		}
		return false;
	}

	@Override
	public List<Alumno> alumnos() {
		return alumnosDao.findAll();
	}
	
	
}
