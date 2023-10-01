package com.alitarik.aselsanbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@RestController
@EnableScheduling
@OpenAPIDefinition
public class AselsanBackendApplication {
  public static void main(String[] args) {
    SpringApplication.run(AselsanBackendApplication.class, args);
  }

  @Bean
  public WebMvcConfigurer configure() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry reg) {
        reg.addMapping("/**").allowedOrigins("*").allowedMethods("*");
      }
    };
  }
}
