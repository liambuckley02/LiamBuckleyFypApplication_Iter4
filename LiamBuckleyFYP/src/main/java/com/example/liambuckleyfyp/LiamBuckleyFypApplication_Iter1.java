//Line 10 and 11 are the only changes made to the original file used with GitHub Co-pilot
package com.example.liambuckleyfyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication // Marks this class as a Spring Boot application
@EnableJpaRepositories(basePackages = "com.example.liambuckleyfyp.repository") // Enables JPA repositories and specifies the base package to scan for repository interfaces
@EntityScan(basePackages = "com.example.liambuckleyfyp.model") // Specifies the base package to scan for JPA entities
public class LiamBuckleyFypApplication_Iter1 {
    public static void main(String[] args) {
        SpringApplication.run(LiamBuckleyFypApplication_Iter1.class, args); // Starts the Spring Boot application
    }
}