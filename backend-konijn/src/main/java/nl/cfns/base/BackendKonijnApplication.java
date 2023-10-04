package nl.cfns.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.java.Log;

@SpringBootApplication // main app
@Profile("dev") // profile for configurations. TODO: create diffent profile for production environment
@ComponentScan(basePackages = "nl.cfns.base, nl.cfns.configs, nl.cfns.entities") // add other classes to main app. autoconfiguration is automatically enabled here
@EntityScan("nl.cfns.entities") // add database object structures to main app
@EnableJpaRepositories("nl.cfns.repositories")// find repository classes in repository package

@Configuration //configuration annotation after scanning, this may prevent scanning problems
@Log // to use logging in this class
public class BackendKonijnApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BackendKonijnApplication.class, args);
		log.info("program has started. this message is generated using Lombok");
	}
}

