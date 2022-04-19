package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<Producto> buscar(String seccion) {
		String sql = "SELECT * FROM productos WHERE seccion = ?";
		return template.query(sql, (rs, f) -> new Producto(rs.getInt("idProducto"), rs.getString("nombre"),
				rs.getString("seccion"), rs.getDouble("precio"), rs.getInt("stock")), seccion);
	}

	@Override
	public void alta(Producto producto) {
		String sql = "INSERT INTO productos (nombre, seccion, precio, stock) VALUES (?, ?, ?, ?)";
		template.update(sql, producto.getNombre(), producto.getSeccion(), producto.getPrecio(), producto.getStock());
	}

	@Override
	public void baja(String nombre) {
		String sql = "DELETE FROM productos WHERE nombre = ?";
		template.update(sql, nombre);
	}

	@Override
	public void modificar(String nombre, double precio) {
		String sql = "UPDATE productos SET precio = ? WHERE nombre = ?";
		template.update(sql, precio, nombre);
	}

	@Override
	public Producto buscarId(int idProducto) {
		String sql = "SELECT * FROM productos where idProducto = ?";
		List<Producto> productos = template.query(sql, (rs, f) -> new Producto(rs.getInt("idProducto"), rs.getString("nombre"),
				rs.getString("seccion"), rs.getDouble("precio"), rs.getInt("stock")), idProducto);
		return productos.isEmpty() ? null : productos.get(0);
	}

}
