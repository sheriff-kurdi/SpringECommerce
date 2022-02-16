package com.kurdi.springecommerce.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.kurdi","infrastructure","domain","security","web"})
@EntityScan(basePackages = {"com.kurdi","infrastructure","domain","security","web"})
@ComponentScan(basePackages = {"com.kurdi","infrastructure","domain","security","web"})
@SpringBootApplication
public class SpringECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringECommerceApplication.class, args);

    }


}