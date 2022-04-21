package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Producto;
import service.ProductoService;

@CrossOrigin("*") // Hace que no se bloqueen las peticiones desde un origen distinto. Es lo mismo a la cabecera ('Access-Control-Allow-Origin', '*')
@Controller
public class ProductoController {
	
	@Autowired
	ProductoService pService;
	
	/* @ResponseBody -> Le dice a Spring que lo que devuelve el método es el body, 
	*  es decir, que lo que devuelve lo tiene que meter en el cuerpo de la respuesta HTTP,
	*  pero lo tiene que transformar a JSON (produces = MediaType.APPLICATION_JSON_VALUE) */
	@GetMapping(value="Buscador", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Producto> buscar(@RequestParam("seccion") String seccion) {
		return pService.buscar(seccion);
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
