package com.example.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.java.Log;

@SpringBootApplication // main app
@Profile("dev") // profile for configurations. TODO: create diffent profile for production environment
@ComponentScan(basePackages = "com.example.rabbit, com.example.configs") // add other classes to main app. autoconfiguration is automatically enabled here
@EntityScan("com.example.entities") // add database object structures to main app

@Configuration //configuration annotation after scanning, this may prevent scanning problems
@Log // to use logging in this class
public class BackendKonijnApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BackendKonijnApplication.class, args);
		log.info("program has started. this message is generated using Lombok");
	}
}

