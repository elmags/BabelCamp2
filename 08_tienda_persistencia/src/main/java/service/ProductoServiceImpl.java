package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Producto> buscar(String seccion) {
		String jpql = "SELECT p FROM Producto p WHERE p.seccion = ?1";
		TypedQuery<Producto> qr = entityManager.createQuery(jpql, Producto.class);
		qr.setParameter(1, seccion);
		List<Producto> productos = qr.getResultList();
		return productos;
	}
	
	@Transactional
	@Override
	public void alta(Producto producto) {
		entityManager.persist(producto);
	}

	@Transactional
	@Override
	public void baja(String nombre) {
		String jpql = "DELETE FROM Producto p WHERE p.nombre = ?1";
		Query qr = entityManager.createQuery(jpql);
		qr.setParameter(1, nombre);
		qr.executeUpdate();
	}

	@Transactional
	@Override
	public void modificar(String nombre, double precio) {
		String jpql = "UPDATE Producto p SET p.precio = ?1 WHERE p.nombre = ?2";
		Query qr = entityManager.createQuery(jpql);
		qr.setParameter(1, precio);
		qr.setParameter(2, nombre);
		qr.executeUpdate();
	}

	@Override
	public Producto buscarId(int idProducto) {
		return entityManager.find(Producto.class, idProducto);
	}

	@Override
	public Producto buscarNombre(String nombre) {
		String jpql = "SELECT p FROM Producto p WHERE p.nombre = ?1";
		TypedQuery<Producto> qr = entityManager.createQuery(jpql, Producto.class);
		qr.setParameter(1, nombre);
		List<Producto> productos = qr.getResultList();
		return productos.isEmpty() ? null : productos.get(0);
	}

	@Override
	public double mediaPrecioSeccion(String seccion) {
		String jpql = "SELECT avg(p.precio) FROM Producto p WHERE p.seccion = ?1";
		TypedQuery<Double> qr = entityManager.createQuery(jpql, Double.class);
		qr.setParameter(1, seccion);
		return qr.getSingleResult();
	}

}
