package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import model.Alumno;
import model.Curso;

@Service
public class EscuelaServiceImpl implements EscuelaService {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Alumno> alumnosCurso(String nombre) {
		String jpql = "SELECT a FROM Alumno a WHERE a.curso.denominacion = ?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, nombre);
		List<Alumno> alumnos = qr.getResultList();
		return alumnos;
	}

	@Override
	public List<Alumno> alumnosCursoDuracion(int duracionMax) {
		String jpql = "SELECT a FROM Alumno a WHERE a.curso.duracion <= ?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, duracionMax);
		List<Alumno> alumnos = qr.getResultList();
		return alumnos;
	}

	@Override
	public Curso cursoMatriculadoAlumno(String dni) {
		String jpql = "SELECT c FROM Curso c join c.alumnos a WHERE a.dni = ?1";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		qr.setParameter(1, dni);
		return qr.getSingleResult();
	}

	@Override
	public List<Curso> cursosAlumnosSenior(int edad) {
		String jpql = "SELECT c FROM Curso c join c.alumnos a WHERE a.edad >= ?1";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		qr.setParameter(1, edad);
		List<Curso> cursos = qr.getResultList();
		return cursos;
	}

	@Override
	public double edadMediaCurso(String nombre) {
		String jpql = "SELECT avg(a.edad) FROM Alumno a WHERE a.curso.denominacion = ?1";
		TypedQuery<Double> qr = entityManager.createQuery(jpql, Double.class);
		qr.setParameter(1, nombre);
		return qr.getSingleResult();
	}

	@Override
	public double precioCurso(String email) {
		String jpql = "SELECT c.precio FROM Curso c join c.alumnos a WHERE a.email = ?1";
		TypedQuery<Double> qr = entityManager.createQuery(jpql, Double.class);
		qr.setParameter(1, email);
		List<Double> precios = qr.getResultList();
		return precios.isEmpty() ? null : precios.get(0);
	}
}
