package org.micro.apps.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 * @author tibinatomy
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.micro.apps.common")
public class SecurityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApiApplication.class, args);
    }

}

