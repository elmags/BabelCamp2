package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Pagina;
import service.BuscadorService;

@Controller
public class BuscadorController {
	
	@Autowired // Delante del objeto que se quiere instanciar
	BuscadorService buscadorService;
	
	@GetMapping(value="Buscador")
	public String buscar(@RequestParam("tema") String tematica, HttpServletRequest request) {
		List<Pagina> paginas = buscadorService.buscar(tematica);
		request.setAttribute("paginas", paginas);
		return "listado"; // Spring debe interpretar "listado" como un nombre de pagina jsp
	}
	
	@PostMapping(value="Add")
	public String add(@RequestParam("direccion") String direccion,
						 @RequestParam("tematica") String tematica,
						 @RequestParam("descripcion") String descripcion) {
		buscadorService.add(direccion, tematica, descripcion);
		return "datos";
	}
	
	/* Recoge todos los parametros y los guarda en el objeto pagina
	@PostMapping(value="Add")
	public String buscar(@ModelAttribute Pagina pagina) {
		buscadorService.add(pagina);
		return "datos";
	}
	 */
}
