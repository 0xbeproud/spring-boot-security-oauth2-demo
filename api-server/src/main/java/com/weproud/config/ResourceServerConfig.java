package com.weproud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Logan. 81k
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{


//    @Primary
//    @Bean
//    public RemoteTokenServices tokenServices(){
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:8181/oauth/check_token");
//        tokenServices.setClientId("app1");
//        tokenServices.setClientSecret("app1");
//        return tokenServices;
//    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers("/login", "/login/**").permitAll();

    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("oauth2-resource");
    }
}
