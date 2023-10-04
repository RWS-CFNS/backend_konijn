package nl.cfns.base;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.cfns.entities.Measuringbox;
import nl.cfns.repositories.MeasuringboxRepository;

@Component("receiver")
//@RabbitListener(queues = "spring-boot") annotation does not work on class level for some reason?
public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);
	ObjectMapper objectMapper = new ObjectMapper(); // mapper for JSON conversion
	
	//private repository object for interacting with measuringbox section of database
	@Autowired	
	private MeasuringboxRepository measuringboxRepository;

	@Async
	//@RabbitHandler
	@RabbitListener(queues = "spring-boot")
	public void receiveMessage(Measuringbox receivedBox) {
		//Measuringbox receivedBox = objectMapper.readValue(message, Measuringbox.class); // convert JSON string to class object
		measuringboxRepository.save(receivedBox);	//insert measuringbox object into database
	
		System.out.println("the name of the box is: " + receivedBox.getName());
		System.out.println(" [x] Received '" + receivedBox + "'"); // print message as string

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
