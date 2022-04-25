package service;

import java.util.List;

import model.Producto;

public interface ProductoService {
	
	/**
	 * Método que busca en la base de datos alumnos por curso.
	 * 
	 * @param seccion -> Seccion del producto
	 * @return
	 */
	List<Producto> buscar(String seccion);
	
	/**
	 * Método que añade un Alumno a la base de datos.
	 * 
	 * @param alumno -> Alumno que se desea insertar en la base de datos
	 * @return "alta" 	-> En caso de que se añada correctamente el alumno
	 * 					   se redirige a la página alta.
	 * 		   "existe" -> En caso contrario, se redirige a la pagina existe,
	 * 					   que muestra un mensaje indicando que el usuario
	 * 					   ya se encuentra en la bbdd y permite dar de alta
	 *					   a otro usuario.
	 */
	void alta(Producto producto);
	void baja(String nombre);
	void modificar(String nombre, double precio);
	Producto buscarId(int idProducto);
	Producto buscarNombre(String nombre);
	double mediaPrecioSeccion(String seccion);
}
