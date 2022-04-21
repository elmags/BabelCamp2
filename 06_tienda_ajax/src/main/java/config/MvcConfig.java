package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
public class MvcConfig implements WebMvcConfigurer {
	
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}

	// Localizaci�n de navegaciones est�ticas
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// Cuando llega la petici�n inicial ("/") te redirige a inicio
		registry.addViewController("/").setViewName("inicio");
	}

	// Localizaci�n de archivos est�ticos (im�genes, videos, css, js, ...)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*").addResourceLocations("/");
		/*registry.addResourceHandler("/*.gif").addResourceLocations("/imagenes");
		registry.addResourceHandler("/*.css").addResourceLocations("/estilos");*/ // Archivos con extensi�n css se encuentran en la carpeta estilos
	}	
}
