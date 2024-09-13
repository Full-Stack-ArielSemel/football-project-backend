package com.dev;
import com.dev.objects.*;
//import com.dev.objects.LiveStanding;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;
import java.util.Set;

/**
 * Test config that turn on H2 in-memory database.
 * This mode is more convenient for fast starting.
 * Change property spring.profiles.active to "test" for run application in this mode.
 */

@Configuration
@Profile("production")
public class TestConfig {

    @Bean
    public Properties dataSource() throws Exception {
        Properties settings = new Properties();

        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUsername == null || dbPassword == null) {
            throw new NullPointerException("Database environment variables are not set.");
        }

        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, System.getenv("DB_URL"));
        settings.put(Environment.USER, System.getenv("DB_USERNAME"));
        settings.put(Environment.PASS, System.getenv("DB_PASSWORD"));
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        return settings;
    }

    @Bean
    public SessionFactory sessionFactory() throws Exception {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperties(dataSource());
        Set<Class<? extends Object>> entities = new Reflections("com.dev.objects").getSubTypesOf(Object.class);
        for (Class<? extends Object> clazz : entities) {
            configuration.addAnnotatedClass(clazz);
        }
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Team.class);
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(League.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}