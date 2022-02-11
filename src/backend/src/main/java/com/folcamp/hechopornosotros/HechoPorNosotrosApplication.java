package com.folcamp.hechopornosotros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class HechoPorNosotrosApplication {
	public static void main(String[] args) {
		SpringApplication.run(HechoPorNosotrosApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("https://hecho-por-nosotros.herokuapp.com/","http://localhost:8080","https://storehechoxnosotros.s3.us-east-2.amazonaws.com","https://hecho-por-nosotros.herokuapp.com/puntodeventa","http://localhost:4200","https://login-lucas.web.app/login").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
}


