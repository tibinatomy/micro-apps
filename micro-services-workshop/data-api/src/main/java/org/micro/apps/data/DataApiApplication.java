package org.micro.apps.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *
 * @author tibinatomy
 */
@SpringBootApplication
@EntityScan(basePackages = "org.micro.apps.common.dto")
public class DataApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataApiApplication.class, args);
    }

}

