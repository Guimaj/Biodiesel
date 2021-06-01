package com.fei.projetodecantadorbe.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "progress.openedge.hdi")
    public DataSource dataSourceHdi() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "progress.openedge.corretor")
    public DataSource dataBaseCorretor() {
        return DataSourceBuilder.create().build();
    }
}
