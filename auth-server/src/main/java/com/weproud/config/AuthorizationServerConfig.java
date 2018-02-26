package com.weproud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author Logan. 81k
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    // Spring Security OAuth에서 access_token, refresh_token을 저장하는 토큰 저장소에 대한 모든 CRUD는 TokenStore 인터페이스로 구현하게 되어 있다.
    // 기본 제공되는 구현체로는 InMemoryTokenStore, JdbcTokenStore, RedisTokenStore 클래스가 제공된다. 인터페이스만 구현하면 되므로 제3의 구현체를 작성해도 된다.
    //
    //출처: http://jsonobject.tistory.com/363 [지단로보트의 블로그]
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(this.dataSource);
    }

    // client_id, client_secret 등을 저장하는 클라이언트 저장소에 대한 모든 CRUD는 ClientDetailsService 인터페이스로 구현하게 되어 있다.
    //출처: http://jsonobject.tistory.com/363 [지단로보트의 블로그]
    @Bean
    @Primary
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(this.dataSource);
    }

    // username, password를 custom하게 처리할 수 있다.
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    // spring boot에 의해 생성되며, "user"라는 이름으로, "password"라는 패스워드와 함께 싱글 유저를 가집니다.
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired // oauth_client_details관련 service
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager)
//                .pathMapping("/oauth/confirm_access", "/oauth/confirm_access")
//                .pathMapping("/oauth/token", "/oauth/token")
//                .pathMapping("/oauth/check_token", "/oauth/check_token")
//                .pathMapping("/oauth/token_key", "/oauth/token_key")
//                .pathMapping("/oauth/authorize", "/oauth/authorize")
                .tokenStore(this.tokenStore())
                .userDetailsService(this.userDetailsService);
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(this.passwordEncoder).checkTokenAccess("isAuthenticated()");
    }

    // api의 요청 클라이어트 정보를 설정할 수 있다.
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(this.clientDetailsService);
//        clients.inMemory()
//                .withClient("my-trusted-client")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit") <-- access_token을 획득하기 위한 4가지 인증 방법을 설정한다.
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                .scopes("read", "write", "trust")
//                .resourceIds("oauth2-resource")
//                .accessTokenValiditySeconds(600)
//                .and()
//                .withClient("my-client-with-registered-redirect")
//                .authorizedGrantTypes("authorization_code")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "trust")
//                .resourceIds("oauth2-resource")
//                .redirectUris("http://anywhere?key=value")
//                .and()
//                .withClient("my-client-with-secret")
//                .authorizedGrantTypes("client_credentials", "password")
//                .authorities("ROLE_CLIENT")
//                .scopes("read")
//                .resourceIds("oauth2-resource")
//                .secret("secret");
    }
}
