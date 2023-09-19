package com.example.rabbit;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//simulation class for sending messages to backend locally
@Component("runner")
@Configuration
@EnableAsync // for scheduling classes in parallel
public class Runner implements CommandLineRunner {
	private CountDownLatch latch = new CountDownLatch(1);

	private final RabbitTemplate rabbitTemplate;

	public Runner(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Scheduled(fixedRate = 3000)
	@Async
	void simulateMessages() {
		System.out.println("Sending message...");
		for (int i = 0; i < 2; i++) {
			rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz",
				"Hello from RabbitMQ!");
		}
	}

	@Override
	public void run(String... args) throws Exception {
		getLatch().await(10, TimeUnit.MILLISECONDS); // latch for waiting before receiver has started
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
