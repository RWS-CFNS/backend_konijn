package com.example.rabbit;


//for using File objects, print functions
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.configs.RabbitConfig;
import com.example.entities.Measuringbox;
import com.fasterxml.jackson.databind.ObjectMapper;


//simulation class for sending messages to backend locally
@Component("runner")
@Configuration
public class Runner implements CommandLineRunner {
	private CountDownLatch latch = new CountDownLatch(1);
	ObjectMapper objectMapper = new ObjectMapper();

//	// rabbit template for publishing messages
//	private final RabbitTemplate rabbitTemplate;
//	public Runner(RabbitTemplate rabbitTemplate) {
//		this.rabbitTemplate = rabbitTemplate;
//	}
	
	@Autowired 
	private AmqpTemplate amqpTemplate;
	
	// send some test messages every x seconds
	@Scheduled(fixedRate = 3000)
	@Async
	void simulateMessages() throws IOException {
		// create example box with values
		Measuringbox box = new Measuringbox((long) 1, "exampleBox", 2, 3);

		// convert to JSON string
		objectMapper.writeValue(new File("target/box.json"), box); // convert box to
		// JSON
		//String boxAsString = objectMapper.writeValueAsString(box); // convert object
		// to string in JSON format

		System.out.println(" [x] Sent '" + box.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.abc", box);
	}


	@Override
	public void run(String... args) throws Exception {
		getLatch().await(10, TimeUnit.MILLISECONDS); // latch for waiting before receiver has started
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
