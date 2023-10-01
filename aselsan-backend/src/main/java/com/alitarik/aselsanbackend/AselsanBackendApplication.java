package com.alitarik.aselsanbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
@EnableScheduling
public class AselsanBackendApplication {
  public static void main(String[] args) {
    SpringApplication.run(AselsanBackendApplication.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
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
