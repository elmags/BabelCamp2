package service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converters.ConversorEntityDto;
import dao.AlumnosDao;
import dao.CursosDao;
import dtos.AlumnoDto;
import dtos.CursoDto;
import model.Alumno;
import model.Curso;

@Service
public class FormacionServiceImpl implements FormacionService {

	AlumnosDao alumnosDao;
	CursosDao cursosDao;
	
	@Autowired
	ConversorEntityDto conversor;
	
	public FormacionServiceImpl(@Autowired AlumnosDao alumnosDao, @Autowired CursosDao cursosDao) {
		this.alumnosDao = alumnosDao;
		this.cursosDao = cursosDao;
	}

	@Override
	public AlumnoDto validarUsuario(String usuario, String password) {
		return conversor.alumnoToDto(alumnosDao.findByUsuarioAndPassword(usuario, password));
	}

	@Override
	public List<CursoDto> cursosAlumno(String usuario) {
		return cursosDao.findByAlumno(usuario).stream().map(c -> conversor.cursoToDto(c)).collect(Collectors.toList());
	}

	@Override
	public List<CursoDto> cursos() {
		return cursosDao.findAll().stream().map(c -> conversor.cursoToDto(c)).collect(Collectors.toList());
	}

	@Override
	public List<AlumnoDto> alumnosCurso(String nombre) {
		return alumnosDao.findByCurso(nombre).stream().map(a -> conversor.alumnoToDto(a)).collect(Collectors.toList());
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
	public List<AlumnoDto> alumnos() {
		return alumnosDao.findAll().stream().map(a -> conversor.alumnoToDto(a)).collect(Collectors.toList());
	}
	
	@Override
	public boolean altaAlumno(AlumnoDto alumno) {
		Optional<Alumno> a = alumnosDao.findById(alumno.getUsuario());
		if (a.isEmpty()) {
			alumnosDao.save(conversor.dtoToAlumno(alumno));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean altaCurso(CursoDto curso) {
		Optional<Curso> c = cursosDao.findByNombre(curso.getNombre());
		if (c.isEmpty()) {
			cursosDao.save(conversor.dtoToCurso(curso));
			return true;
		}
		return false;
	}

	@Override
	public List<CursoDto> cursosNoMatriculados(String usuario) {
		return cursosDao.findCursos(usuario).stream().map(c -> conversor.cursoToDto(c)).collect(Collectors.toList());
	}

	@Override
	public List<CursoDto> cursosEntreFecha(Date fechaInicio, Date fechaFin) {
		return cursosDao.findBetweenDate(fechaInicio, fechaFin).stream().map(c -> conversor.cursoToDto(c)).collect(Collectors.toList());
	}
}
