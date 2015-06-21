package com.todo.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(
				dataSource());
		builder.scanPackages("com.todo.model").addProperties(
				getHibernateProperties());

		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return prop;
	}

	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		String dburl = System.getenv("OPENSHIFT_POSTGRESQL_DB_URL");

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");

		if (dburl != null) {
			String dbhost = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
			String dbport = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
			String dbuser = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
			String dbpassword = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");
			
			ds.setUrl("jdbc:postgresql://" + dbhost + ":"  + dbport + "/todo");
			ds.setUsername(dbuser);
			ds.setPassword(dbpassword);
		} else {
			ds.setUrl("jdbc:postgresql://localhost:5432/todoDB");
			ds.setUsername("postgres");
			ds.setPassword("post");
		}
		return ds;
	}

	@Bean(name = "dataSource")
	@Profile("test")
	public BasicDataSource testDataSource() {

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/todoTestDB");
		ds.setUsername("postgres");
		ds.setPassword("post");
		return ds;
	}

	@Bean
	public HibernateTransactionManager txManager() {
		return new HibernateTransactionManager(sessionFactory());
	}
}
