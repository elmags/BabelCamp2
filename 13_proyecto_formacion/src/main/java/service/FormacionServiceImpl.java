package service;

import java.util.Date;
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
		Optional<Alumno> alumno = alumnosDao.findById(usuario);
		Optional<Curso> curso = cursosDao.findById(idCurso);
		if (alumno.isPresent()) {
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
	
	@Override
	public boolean altaAlumno(Alumno alumno) {
		Optional<Alumno> a = alumnosDao.findById(alumno.getUsuario());
		if (a.isEmpty()) {
			alumnosDao.save(alumno);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean altaCurso(Curso curso) {
		Optional<Curso> c = cursosDao.findByNombre(curso.getNombre());
		if (c.isEmpty()) {
			cursosDao.save(curso);
			return true;
		}
		return false;
	}

	@Override
	public List<Curso> cursosNoMatriculados(String usuario) {
		return cursosDao.findCursos(usuario);
	}

	@Override
	public List<Curso> cursosEntreFecha(Date fechaInicio, Date fechaFin) {
		return cursosDao.findBetweenDate(fechaInicio, fechaFin);
	}
}
