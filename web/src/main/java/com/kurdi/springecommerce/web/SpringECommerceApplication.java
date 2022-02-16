package com.kurdi.springecommerce.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.kurdi"})
@EntityScan(basePackages = {"com.kurdi"})
@ComponentScan(basePackages = {"com.kurdi"})
@SpringBootApplication
public class SpringECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringECommerceApplication.class, args);

    }


}