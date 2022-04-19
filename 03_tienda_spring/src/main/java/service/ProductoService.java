package service;

import java.util.List;

import model.Producto;

public interface ProductoService {
	List<Producto> buscar(String seccion);
	void alta(Producto producto);
	void baja(String nombre);
	void modificar(String nombre, double precio);
}
