package com.humga.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * We configured our Order and Authority entities to be fetched in LAZY mode, so they’re not being queried from the database.
 * We have to customize our JSON serialization, so it can handle Hibernate objects.
 *
 */
public class JsonConfig {
    @Bean
    public Hibernate5Module  hibernateModule() {
        return new Hibernate5Module();
    }
}
