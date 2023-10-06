package nl.cfns.base;


//for using File objects, print functions
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.cfns.configs.RabbitConfig;
import nl.cfns.entities.Measurement;
import nl.cfns.entities.Measuringbox;
import nl.cfns.entities.Measuringbox2;
import nl.cfns.entities.WeatherMeasurement;


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
	@Scheduled(fixedRate = 10000)
	@Async
	void simulateMessagesExample() throws IOException {
		// create example box with values
		Measuringbox box = new Measuringbox((long) 1, "exampleBox", 2, 3);

		// convert to JSON string
		//objectMapper.writeValue(new File("target/box.json"), box); // convert box to
		// JSON
		//String boxAsString = objectMapper.writeValueAsString(box); // convert object
		// to string in JSON format

		System.out.println(" [x] Sent '" + box.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.routingKey, box);
	}

	// send some test messages every x seconds
	@Scheduled(fixedRate = 5000)
	@Async
	void simulateMessagesMeasuringbox() throws IOException {
		// create example box with values
		Measuringbox2 box = new Measuringbox2((long) 1, "exampleBox", null, null, 2, 3, null);
		System.out.println(" [x] Sent '" + box.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.MEASURINGBOX2_KEY, box);
	}
	
	// send some test messages every x seconds
	@Scheduled(fixedRate = 7000)
	@Async
	void simulateMessagesMeasurement() throws IOException {
		// create example box with values
		Measurement measurement = new Measurement();
		System.out.println(" [x] Sent '" + measurement.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.MEASUREMENT_KEY , measurement);
	}
	
	// send some test messages every x seconds
	@Scheduled(fixedRate = 9000)
	@Async
	void simulateMessagesWeather() throws IOException {
		// create example box with values
		WeatherMeasurement weatherMeasurement = new WeatherMeasurement();
		System.out.println(" [x] Sent '" + weatherMeasurement.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.WEATHER_MEASUREMENT_KEY, weatherMeasurement);
	}
	

	@Override
	public void run(String... args) throws Exception {
		getLatch().await(10, TimeUnit.MILLISECONDS); // latch for waiting before receiver has started
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
