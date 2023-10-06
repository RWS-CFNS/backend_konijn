package nl.cfns.base;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.cfns.configs.RabbitConfig;
import nl.cfns.entities.Measurement;
import nl.cfns.entities.Measuringbox;
import nl.cfns.entities.Measuringbox2;
import nl.cfns.entities.WeatherMeasurement;
import nl.cfns.repositories.MeasurementsRepository;
import nl.cfns.repositories.Measuringbox2Repository;
import nl.cfns.repositories.MeasuringboxRepository;
import nl.cfns.repositories.WeatherMeasurementRepository;

@Component("receiver")
//@RabbitListener(queues = "spring-boot") annotation does not work on class level for some reason?
public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);
	ObjectMapper objectMapper = new ObjectMapper(); // mapper for JSON conversion
	
	//private repository object for interacting with measuringbox section of database
	@Autowired	
	private MeasuringboxRepository measuringboxRepository;

	@Autowired	
	private MeasurementsRepository measurementsRepository;
	
	@Autowired	
	private Measuringbox2Repository measuringbox2Repository;
	
	@Autowired	
	private WeatherMeasurementRepository weatherMeasurementRepository;
	
	@Async
	//@RabbitHandler
	@RabbitListener(queues = RabbitConfig.queueName)
	public void receiveMessage(Measuringbox receivedBox) {
		//Measuringbox receivedBox = objectMapper.readValue(message, Measuringbox.class); // convert JSON string to class object
		measuringboxRepository.save(receivedBox);	//insert measuringbox object into database
	
		System.out.println("the name of the box is: " + receivedBox.getName());
		System.out.println(" [x] Received '" + receivedBox + "'"); // print message as string

		// System.out.println("Received <" + message + ">");
		latch.countDown(); // why a countdown on receive?
		
	}

	@Async
	@RabbitListener(queues = RabbitConfig.WEATHER_MEASUREMENT_QUEUE)
	public void receiveMessage(WeatherMeasurement weatherMeasurement) {
		System.out.println(weatherMeasurement.toString());
		latch.countDown(); // why a countdown on receive?
		
	}

	@Async
	@RabbitListener(queues = RabbitConfig.MEASUREMENT_QUEUE)
	public void receiveMessage(Measurement measurement) {
		System.out.println(measurement.toString());
		latch.countDown(); // why a countdown on receive?
		
	}
	
	@Async
	@RabbitListener(queues = RabbitConfig.MEASURINGBOX2_QUEUE)
	public void receiveMessage(Measuringbox2 measuringbox2) {
		System.out.println(measuringbox2.toString());
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
