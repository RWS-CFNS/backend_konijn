package com.example.rabbit;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//simulation class for sending messages to backend locally
@Component("runner")
@Configuration
@EnableAsync // for scheduling classes in parallel
public class Runner implements CommandLineRunner {
	private CountDownLatch latch = new CountDownLatch(1000000000); // long latch temporary to prevent app from stopping

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
		for (int i = 0; i < 2; i++) {
			rabbitTemplate.convertAndSend(BackendKonijnApplication.topicExchangeName, "foo.bar.baz",
				"Hello from RabbitMQ!");
		}
	}

	@Override
	public void run(String... args) throws Exception {
		getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
