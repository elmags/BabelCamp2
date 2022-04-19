package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Producto;
import service.ProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	ProductoService pService;
	
	@GetMapping(value="Buscador")
	public String buscar(@RequestParam("seccion") String seccion, HttpServletRequest request) {
		List<Producto> productos = pService.buscar(seccion);
		request.setAttribute("productos", productos);
		return "listado";
	}
	
	@PostMapping(value="Alta")
	public String alta(@ModelAttribute Producto producto) {
		pService.alta(producto);
		return "alta";
	}
	
	@GetMapping(value="Eliminar")
	public String baja(@RequestParam("nombre") String nombre) {
		pService.baja(nombre);
		return "eliminar";
	}
	
	@GetMapping(value="Modificar")
	public String modificar(@RequestParam("nombre") String nombre,
							@RequestParam("precio") double precio) {
		pService.modificar(nombre, precio);
		return "modificar";
	}
}
