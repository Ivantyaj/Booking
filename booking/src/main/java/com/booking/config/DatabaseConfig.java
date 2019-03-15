package com.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.booking.service"})
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();
        sessionFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.booking.model.entity");
        sessionFactory.setJpaProperties(hibernateProperties());
        return sessionFactory;
    }


    @Bean
    public DataSource dataSource() {
        String dbDriver = getProperty("db.driver");
        String dbUrl = getProperty("db.url");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(getProperty("db.username"));
        dataSource.setPassword(getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(
                entityManagerFactory().getObject());
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty(
                "hibernate.dialect", getProperty("hibernate.dialect"));
        hibernateProperties.setProperty(
                "hibernate.show_sql", "false");

        return hibernateProperties;
    }

    private String getProperty(String s) {
        return env.getRequiredProperty(s);
    }
}
