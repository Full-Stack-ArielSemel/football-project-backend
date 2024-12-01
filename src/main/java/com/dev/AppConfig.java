package com.dev;

import com.dev.models.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@Profile("production")
public class AppConfig implements WebMvcConfigurer{

    @Value("${DB_URL}")
    private String dbUrl;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    
        return new HikariDataSource(config);
    }
    
    @Bean
    public Properties hibernateProperties() {
        Properties settings = new Properties();
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "none");
        settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        return settings;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) throws Exception {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

        // Set Hibernate properties
        configuration.setProperties(hibernateProperties());

        // Register entities explicitly
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(League.class);
        configuration.addAnnotatedClass(Team.class);

        // Set the DataSource
        configuration.getProperties().put(Environment.DATASOURCE, dataSource);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*"); 
    }
}
