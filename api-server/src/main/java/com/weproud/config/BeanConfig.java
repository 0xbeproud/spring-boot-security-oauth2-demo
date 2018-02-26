package com.weproud.config;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Logan. 81k
 */
@Configuration
public class BeanConfig {
    @Autowired
    private RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
