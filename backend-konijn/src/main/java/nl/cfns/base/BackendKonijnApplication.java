package nl.cfns.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.java.Log;



//annotations below are for importing packages and profile configurations. 
//EntityScan and EnableJpaRepositories have separate functionality and needed to be added separately 
@SpringBootApplication(scanBasePackages = "nl.cfns.base, nl.cfns.config, "
		+ "nl.cfns.entity, " + "nl.cfns.service, nl.cfns.controller, nl.cfns.simulate") 
																												
@Profile("dev") // profile for configurations. TODO: create diffent profile for production environment
@EntityScan("nl.cfns.entity, nl.cfns.basicpojo") // add database object structures to main app
@EnableJpaRepositories("nl.cfns.repository")// find repository classes in repository package

@Configuration //configuration annotation after scanning, this may prevent scanning problems
@Log // to use logging in this class
public class BackendKonijnApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BackendKonijnApplication.class, args);
		log.info("program has started. this message is generated using Lombok");
	}
}

