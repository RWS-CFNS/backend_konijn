package com.example.rabbit;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@Profile("dev") // profile for configurations
@ComponentScan(basePackages = "com.example.rabbit") // add other classes to app
@EntityScan("com.example.rabbit")


public class BackendKonijnApplication {

	@Async
	@Scheduled(fixedRate = 3000)
	void asyncRepeater() {
		System.out.println("asyncRepeater");

	}

	@Bean
	void Repeater() {
		System.out.println("Repeater2");
	}

	@Async
	@Scheduled(fixedRate = 3000)
	public void doSomething() { // show info about current threads
		System.out.println("Scheduled job is running with thread: " + Thread.currentThread().getName() + " at time: "
				+ LocalDateTime.now());
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BackendKonijnApplication.class, args);
	}
}

