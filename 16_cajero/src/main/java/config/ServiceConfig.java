package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // Habilitar la transaccionalidad mediante anotaciones
@PropertySource(value = "classpath:config/application.properties")
@Configuration
@ComponentScan(basePackages = { "service", "converters"}) // Sirve para decirle a Spring que paquetes debe escanear
@EnableJpaRepositories(basePackages = {"dao"}, 	// Le dice en que paquete se encuentran las interfaces que debe implementar
					// Hay que especificarle a Spring los nombres de los metodos encargados de crear los objetos entityManager y transactionManager
					   entityManagerFactoryRef = "factory", transactionManagerRef = "txManager")
public class ServiceConfig {
	@Value("${driver}")
	String driver;
	@Value("${url}")
	String url;
	@Value("${user}")
	String user;
	@Value("${pwd}")
	String pwd;

	@Bean // Lo llama al iniciarse
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pwd);

		return ds;
	}

	// Adaptador de Hibernate
	@Bean
	public HibernateJpaVendorAdapter adapter() {
		HibernateJpaVendorAdapter adp = new HibernateJpaVendorAdapter();
		adp.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adp;
	}

	// Factoria EntityManager: Objeto para acceder a capa de persistencia con JPA
	@Bean
	public LocalContainerEntityManagerFactoryBean factory(DataSource dataSource, HibernateJpaVendorAdapter adapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("CajeroPU"); // nombre de la unidad de persistencia
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("model");
		Properties props = new Properties();
		props.put("hibernate.enable_lazy_load_no_trans", true);
		factory.setJpaProperties(props);
		factory.setJpaVendorAdapter(adapter);
		return factory;
	}

	// Gestor de transacción
	@Bean
	public JpaTransactionManager txManager(LocalContainerEntityManagerFactoryBean factory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory.getObject());
		return manager;
	}
}
