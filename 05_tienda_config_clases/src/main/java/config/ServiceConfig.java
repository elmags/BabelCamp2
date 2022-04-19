package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {"service"})		// Sirve para decirle a Spring que paquetes debe escanear
public class ServiceConfig {
	
	@Bean		// Lo llama al iniciarse
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/tienda");
		ds.setUsername("root");
		ds.setPassword("");
		
		return ds;
	}
	
	@Bean
	public JdbcTemplate template(DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
