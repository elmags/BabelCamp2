package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;
import model.Curso;

@Repository
public class AlumnosDaoImpl implements AlumnosDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Alumno findByUsuarioAndPassword(String usuario, String password) {
		String jpql = "SELECT a FROM Alumno a WHERE a.usuario = ?1 AND a.password = ?2";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, usuario);
		qr.setParameter(2, password);
		List<Alumno> alumno = qr.getResultList();
		return alumno.isEmpty() ? null : alumno.get(0);
	}

	@Override
	public List<Alumno> findByCurso(String nombre) {
		String jpql = "SELECT a FROM Alumno a join a.cursos c WHERE c.nombre = ?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, nombre);
		List<Alumno> alumnos = qr.getResultList();
		return alumnos.isEmpty() ? null : alumnos;
	}

	@Override
	public Alumno findById(String usuario) {
		return entityManager.find(Alumno.class, usuario);
	}

	@Transactional
	@Override
	public void update(Alumno alumno) {
		entityManager.merge(alumno);
	}

	@Override
	public List<Alumno> findAll() {
		String jpql = "SELECT a FROM Alumno a";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		List<Alumno> alumnos = qr.getResultList();
		return alumnos.isEmpty() ? null : alumnos;
	}
}
