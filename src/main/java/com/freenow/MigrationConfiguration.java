package com.freenow;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class MigrationConfiguration {

//    @Autowired
//    public MigrationConfiguration(DataSource dataSource) {
//        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
//    }
//
//    /**
//     * Override default flyway initializer to do nothing
//     */
//    @Bean
//    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway, (f) ->{} );
//    }
//
//
//    /**
//     * Create a second flyway initializer to run after jpa has created the schema
//     */
//    @Bean
//    @DependsOn("entityManagerFactory")
//    FlywayMigrationInitializer delayedFlywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway, null);
//    }
}
