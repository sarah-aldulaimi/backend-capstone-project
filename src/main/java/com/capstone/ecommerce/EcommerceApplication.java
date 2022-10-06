package com.capstone.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EcommerceApplication {
	@CrossOrigin(origins = "http://localhost:8080")
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/users").allowedOrigins("http://localhost:8080").allowedOrigins("http://localhost:4200");
				registry.addMapping("/login").allowedOrigins("http://localhost:8080").allowedOrigins("http://localhost:4200");
				registry.addMapping("/orders").allowedOrigins("http://localhost:8080").allowedOrigins("http://localhost:4200");

			}
		};
	}
}
