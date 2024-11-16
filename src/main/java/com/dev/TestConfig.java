package com.dev;

import com.dev.objects.Game;
import com.dev.objects.League;
import com.dev.objects.Team;
import com.dev.objects.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.util.Properties;
import java.util.Set;

@Configuration
@Profile("production")
public class TestConfig {

    @Value("${DB_URL}")
    private String dbUrl;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;


    @Bean
    public Properties dataSource() throws Exception {

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, dbUrl);
        settings.put(Environment.USER, dbUsername);
        settings.put(Environment.PASS, dbPassword);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);

        System.out.println(dbUsername);
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