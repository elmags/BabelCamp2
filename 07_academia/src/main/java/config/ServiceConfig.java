package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@PropertySource(value = "classpath:config/application.properties")
@Configuration
@ComponentScan(basePackages = {"service"})
public class ServiceConfig {
	@Value("${driver}")
	String driver;
	@Value("${url}")
	String url;
	@Value("${user}")
	String user;
	@Value("${pwd}")
	String pwd;
	
	@Bean		// Lo llama al iniciarse
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pwd);
		
		return ds;
	}
	
	@Bean
	public JdbcTemplate template(DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
