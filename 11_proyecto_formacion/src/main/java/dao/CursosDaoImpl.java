package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import model.Curso;

@Repository
public class CursosDaoImpl implements CursosDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Curso findById(int idCurso) {
		return entityManager.find(Curso.class, idCurso);
	}

	@Override
	public List<Curso> findAll() {
		String jpql = "SELECT c FROM Curso c";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		List<Curso> cursos = qr.getResultList();
		return cursos.isEmpty() ? null : cursos;
	}

	@Override
	public List<Curso> findByAlumno(String usuario) {
		String jpql = "SELECT c FROM Curso c join c.alumnos a WHERE a.usuario = ?1";
		TypedQuery<Curso> qr = entityManager.createQuery(jpql, Curso.class);
		qr.setParameter(1, usuario);
		List<Curso> cursos = qr.getResultList();
		return cursos.isEmpty() ? null : cursos;
	}

}
