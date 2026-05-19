package com.kneu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// головний клас Spring Boot застосунку для ІДЗ
// скануємо ТІЛЬКИ пакет Lab8 - щоб не було конфлікту з MVC-сутностями з Lab7
@SpringBootApplication
@ComponentScan(basePackages = "Lab8")
@EntityScan(basePackages = "Lab8.entity")
@EnableJpaRepositories(basePackages = "Lab8.repository")
public class AviaCompanyIdzApplication {
    public static void main(String[] args) {
        SpringApplication.run(AviaCompanyIdzApplication.class, args);
    }
}
