package com.example.rabbit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

import nl.cfns.base.BackendKonijnApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestBackendKonijnApplication {

	@Bean
	@ServiceConnection
	RabbitMQContainer rabbitContainer() {
		return new RabbitMQContainer(DockerImageName.parse("rabbitmq:latest"));
	}

	public static void main(String[] args) {
		SpringApplication.from(BackendKonijnApplication::main).with(TestBackendKonijnApplication.class).run(args);
	}

}
