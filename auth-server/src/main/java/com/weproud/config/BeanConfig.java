package com.weproud.config;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Logan. 81k
 */
@Configuration
public class BeanConfig {
    @Bean
    public RequestDumperFilter requestDumperFilter(){
        return new RequestDumperFilter();
    }
}
