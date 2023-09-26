package com.example.rabbit;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.entities.Measuringbox;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("receiver")
public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);
	ObjectMapper objectMapper = new ObjectMapper(); // mapper for JSON conversion
	
	//private repository object for interacting with measuringbox section of database
	@Autowired	
	private MeasuringboxRepository measuringboxRepository;

	@Async
	public void receiveMessage(String message) throws JsonMappingException, JsonProcessingException {
		Measuringbox receivedBox = objectMapper.readValue(message, Measuringbox.class); // convert JSON string to class
																						// object
		measuringboxRepository.save(receivedBox);	//insert measuringbox object into database
		
		
		System.out.println("the name of the box is: " + receivedBox.getName());
		System.out.println(" [x] Received '" + message + "'"); // print message as json string

		// System.out.println("Received <" + message + ">");
		latch.countDown(); // why a countdown on receive?
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}

//@Component("receiver")
//public class Receiver {
//	private CountDownLatch latch = new CountDownLatch(1);
//
//	public void receiveMessage(String message) {
//		System.out.println("Received <" + message + ">");
//		latch.countDown(); // why a countdown on receive?
//	}
//
//	public CountDownLatch getLatch() {
//		return latch;
//	}
//
//}
