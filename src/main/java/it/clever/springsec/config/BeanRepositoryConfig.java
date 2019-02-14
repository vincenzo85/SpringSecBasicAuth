package it.clever.springsec.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * File di configuazione dei bean delle risorse.
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:application.properties") })
public class BeanRepositoryConfig {

	/** Parametri connessione database. */
	@Value("${spring.datasource.url}")
	private String databaseUrl;
	@Value("${spring.datasource.username}")
	private String databaseUsername;
	@Value("${spring.datasource.password}")
	private String databasePassword;
	@Value("${spring.datasource.driver}")
	private String databaseDriverClassName;
	
	@Value("${spring.jpa.persistence.unit}")
	private String persistenceUnitName;
	
	@Value("${spring.jpa.show-sql}")
	private String showSql;
	
	@Value("${spring.jpa.hibernate.naming-strategy}")
	private String mappingStrategy;
	
	@Value("${hibernate.id.new_generator_mappings}")
	private String generatorStrategy;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String databaseDialect;
	

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		// Specifico qual'è la persistence unit
		em.setPersistenceUnitName(persistenceUnitName);

		// Setto tutte le info per la connession
		em.setDataSource(dataSource());
		
		em.setPackagesToScan(new String[] { "it.clever.springsec.entities" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	/**
	 * Metodo per la costruzione del DataSource per l'inzializzazione delle
	 * connessioni JPA.
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseDriverClassName);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}

	/**
	 * Metodo per il recupero delle informazioni legate ad Hibernate.
	 * 
	 * @return
	 */
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", databaseDialect);
		properties.setProperty("hibernate.show_sql", showSql);
		properties.put("hibernate.id.new_generator_mappings", generatorStrategy);
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager trxManager = new JpaTransactionManager();
		trxManager.setEntityManagerFactory(emf);
		return trxManager;
	}

}