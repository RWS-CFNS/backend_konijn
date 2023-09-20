package com.example.rabbit;


//for using File objects, print functions
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


//simulation class for sending messages to backend locally
@Component("runner")
@Configuration
public class Runner implements CommandLineRunner {
	private CountDownLatch latch = new CountDownLatch(1);

	private final RabbitTemplate rabbitTemplate;

	public Runner(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	// send some test messages every x seconds
	@Scheduled(fixedRate = 3000)
	@Async
	void simulateMessages() throws IOException {
		// create example box with values
		Measuringbox box = new Measuringbox();
		box.setName("exampleBox");
		box.setValue1(2);
		box.setTempValue1(3);

		// convert to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("target/box.json"), box); // convert box to
		// JSON
		String boxAsString = objectMapper.writeValueAsString(box); // convert object
		// to string in JSON format

		System.out.println(" [x] Sent '" + boxAsString + "'"); // display box in console in JSON format
		rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz", boxAsString);

		// rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz",
		// "Hello from RabbitMQ!");
	}


	@Override
	public void run(String... args) throws Exception {
		getLatch().await(10, TimeUnit.MILLISECONDS); // latch for waiting before receiver has started
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
