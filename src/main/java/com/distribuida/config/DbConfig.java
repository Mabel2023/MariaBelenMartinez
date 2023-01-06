package com.distribuida.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;


@ApplicationScoped //Componente CDI
public class DbConfig {
    //se usa en componentes CDI
    @Inject
    @ConfigProperty(name="db.user")//indica que valor queremos que vaya en esa variable
    String dbUser;

    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;

    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;


    @PostConstruct
    public void init(DataSource dataSource){
        Jdbi jdbi = Jdbi.create(dataSource);

    }

    @Produces
    @ApplicationScoped
      public DataSource dataSource(){
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl("jdbc:postgresql://localhost:5433/distribuida");
        ds.setUsername("postgres");
        ds.setPassword("123");
        return ds;
    }


}
