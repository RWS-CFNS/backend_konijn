package nl.cfns.boot;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import lombok.extern.java.Log;
/*
for the rabbitMQ management console, go to http://127.0.0.1:15672/#/
for the H2 databse console, go to http://localhost:8090/h2-console/
for our github repository, go to https://github.com/RWS-CFNS/backend_konijn
for a heatmap datastring that can be used by a front-end application, access localhost:8090/heatmap/data

annotations below are for importing packages and profile configurations. 
EntityScan and EnableJpaRepositories have separate functionality and needed to be added separately 
 * */
 
@SpringBootApplication(scanBasePackages = "nl.cfns.base, "
		+ "nl.cfns.config, "
		+ "nl.cfns.entity, " 
		+ "nl.cfns.service, "
		+ "nl.cfns.controller "
		+ "nl.cfns.h2service"
		+ ", nl.cfns.simulate," //remove this final simulate package, if you want to turn off the simulator
		)

@Profile("dev") // profile for configurations. TODO: create diffent profile for production environment
@EntityScan("nl.cfns.entity") // add database object structures to main app
@EnableJpaRepositories("nl.cfns.repository")// find repository classes in repository package

@Configuration //configuration annotation after scanning, this may prevent scanning problems
@Log // to use logging in this class
public class BackendKonijnApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BackendKonijnApplication.class, args);
		log.info("program has started. this message is generated using Lombok");
	}
}

