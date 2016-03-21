package elections.dao.config;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan(basePackages = { "elections.dao.service" })
@EnableJpaRepositories(basePackages = { "elections.dao.repositories" })
@Configuration
public class DaoConfig {

	// constantes
	final private static String URL = "jdbc:mysql://localhost:3306/dbelections";
	final private static String USER = "admin";
	final private static String PASSWD = "admin";
	final private static String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
	final static private String[] ENTITIES_PACKAGES = { "elections.dao.entities" };

	// Datasource : définit la source de données
	// la source de données [tomcat-jdbc]
	@Bean
	public DataSource dataSource() {
		// source de données TomcatJdbc
		DataSource dataSource = new DataSource();
		// configuration accès JDBC
		dataSource.setDriverClassName(DRIVER_CLASSNAME);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWD);
		dataSource.setUrl(URL);
		// une connexion ouverte initialement
		dataSource.setInitialSize(1);
		// résultat
		return dataSource;
	}

	// le provider JPA
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	// EntityManagerFactory : manage JPA Peristence
	@Bean
	public EntityManagerFactory entityManagerFactory(JpaVendorAdapter jpaVendorAdapter, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(packagesToScan());
		factory.setDataSource(dataSource);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	// Transaction manager
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

	@Bean
	public String[] packagesToScan() {
		return ENTITIES_PACKAGES;
	}

}
