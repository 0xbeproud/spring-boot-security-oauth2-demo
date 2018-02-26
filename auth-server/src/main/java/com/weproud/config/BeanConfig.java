package com.weproud.config;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Logan. 81k
 */
@Configuration
public class BeanConfig {
    @Bean
    @Profile("default")
    public RequestDumperFilter requestDumperFilter(){
        return new RequestDumperFilter();
    }
}
