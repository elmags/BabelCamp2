package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;

@Service
public class AcademiaServiceImpl implements AcademiaService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	@Override
	public String alta(Alumno alumno) {
		if(buscarAux(alumno) == null) {
			entityManager.persist(alumno);
			return "alta";
		}
		return "existe";
	}

	@Override
	public List<Alumno> buscar(String curso) {
		String jpql = "SELECT a FROM Alumno a WHERE a.curso = ?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, curso);
		List<Alumno> alumnos = qr.getResultList();
		return alumnos;
	}
	
	@Override
	public List<String> cursos() {
		String jpql = "SELECT distinct(curso) FROM Alumno a";
		TypedQuery<String> qr = entityManager.createQuery(jpql, String.class);
		List<String> cursos = qr.getResultList();
		return cursos;
	}
	
	/**
	 * Método auxiliar llamado al realizar el alta.
	 * Comprueba que el alumno no se encuentra en la bbdd.
	 * 
	 * @param alumno -> Alumno que se desea comprobar si existe en la bbdd.
	 * @return Devuelve el alumno si lo ha encontrado,
	 * 		   en caso contrario devuelve null.
	 */
	private Alumno buscarAux(Alumno alumno) {
		String jpql = "SELECT a FROM Alumno a WHERE a.nombre = ?1";
		TypedQuery<Alumno> qr = entityManager.createQuery(jpql, Alumno.class);
		qr.setParameter(1, alumno.getNombre());
		List<Alumno> alumnos = qr.getResultList();
		return alumnos.isEmpty() ? null : alumnos.get(0);
	}
}
