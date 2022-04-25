package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.ProductoService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestProductosService {
	
	@Autowired
	ProductoService pService;
	
	@Test
	void testProducto() {
		assertEquals(50.0, pService.buscarId(3).getPrecio());
		assertNull(pService.buscarId(800));	// Para simplificar se ha introducido aqui, pero es recomendable que cada assert tenga su propia función test
	}
	
	@Test
	void testMedia() {
		assertEquals(45.0, pService.mediaPrecioSeccion("hogar"));
	}
}
