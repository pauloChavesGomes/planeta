package com.planeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan ( basePackages = { "com.planeta" })
public class PlanetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetaApplication.class, args);
		
	}
	
	

}
