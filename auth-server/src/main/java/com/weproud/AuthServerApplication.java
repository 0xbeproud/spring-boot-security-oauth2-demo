package com.weproud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServerApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("member1 => " + passwordEncoder.encode("member1"));
            System.out.println("app1 => " + passwordEncoder.encode("app1"));

            System.out.println("member2 => " + passwordEncoder.encode("member2"));
            System.out.println("app2 => " + passwordEncoder.encode("app2"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
