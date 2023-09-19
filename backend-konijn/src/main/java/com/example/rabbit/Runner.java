package com.example.rabbit;


import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("runner")
@Configuration
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Bean
	@Scheduled(fixedRate = 100)
	void simulateMessages() {
		System.out.println("Sending message...");
		for (int i = 0; i < 100; i++) {
			rabbitTemplate.convertAndSend(BackendKonijnApplication.topicExchangeName, "foo.bar.baz",
				"Hello from RabbitMQ!");
		}
	}

	@Override

	public void run(String... args) throws Exception {
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

}
