package nl.cfns.simulate;


//for using File objects, print functions
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import nl.cfns.basicpojo.Coordinates;
import nl.cfns.config.RabbitConfig;
import nl.cfns.entity.Celltower;
import nl.cfns.entity.Measurement;
import nl.cfns.entity.Measuringbox;
import nl.cfns.entity.Measuringbox2;
import nl.cfns.entity.WeatherMeasurement;
import nl.cfns.repository.CelltowerRepository;

//simulation class for sending messages to backend locally
@Component
public class MessageSimulator{
	@Autowired
    private CelltowerRepository celltowerRepository;
//	private CountDownLatch latch = new CountDownLatch(1);
	//ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired 
	private AmqpTemplate amqpTemplate;

	
	// send some test messages every x seconds
	@Scheduled(fixedRate = 3000)
	@Async
	void simulateMessagesExample() throws IOException {
		// create example box with values
		Measuringbox box = new Measuringbox((long) 1, "exampleBox", 2, 3);

		//System.out.println(" [x] Sent '" + box.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.routingKey, box);
	}

	// send some test messages every x seconds
	@Scheduled(fixedRate = 4000)
	@Async
	void simulateMessagesMeasuringbox() throws IOException {
		// create example box with values
		
		//Measuringbox2 box = new Measuringbox2((long) 1, "exampleBox", "ok", "ok", 2, 3, MeasuringboxStatus.ACTIVE);
		Measuringbox2 box = DataSimulator.generateRandomMeasuringbox();
		//ystem.out.println(" [x] Sent '" + box.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.MEASURINGBOX2_KEY, box);
	}
	
//	// send some test messages every x seconds
//	@Scheduled(fixedRate = 5000)
//	@Async
//	void simulateMessagesSimpleMeasurement() throws IOException {
//		// create example box with values
//		//Measurement measurement = new Measurement((long) 1, new Timestamp(System.currentTimeMillis()), 50, 60.5f, 70.2f, 80, 75, 85, 90, "ExampleOperator", 42, 76);
//		Measurement measurement = DataSimulator.generateRandomMeasurement();
//		//System.out.println(" [x] Sent '" + measurement.toString() + "'"); // display box in console in JSON format
//		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.MEASUREMENT_KEY , measurement);
//	}

	// send some test messages every x seconds
	@Scheduled(fixedRate = 5000)
	@Async
	void simulateMessagesAdjustedMeasurement() throws IOException {
		//take celltowers from database
		Iterable<Celltower> celltowerIterable = celltowerRepository.findAll();
		// create example box with values
		Measurement measurement = DataSimulator.generateRandomMeasurementAdjusted(celltowerIterable);
		
		//System.out.println(" [x] Sent '" + measurement.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.MEASUREMENT_KEY , measurement);
	}
	
	// send some test messages every x seconds
	@Scheduled(fixedRate = 6000)
	@Async
	void simulateMessagesWeather() throws IOException {
		// create example box with values
		//WeatherMeasurement weatherMeasurement = new WeatherMeasurement((long) 1, new Timestamp(System.currentTimeMillis()), 50.5f, 60.5f, 45, 12.3f, 15.7f, 13.2f);
		WeatherMeasurement weatherMeasurement = DataSimulator.generateRandomWeatherMeasurement();
		//System.out.println(" [x] Sent '" + weatherMeasurement.toString() + "'"); // display box in console in JSON format
		amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.WEATHER_MEASUREMENT_KEY, weatherMeasurement);
	}
	


	
//	@Override
//	public void run(String... args) throws Exception {
//		getLatch().await(10, TimeUnit.MILLISECONDS); // latch for waiting before receiver has started
//	}
//
//	public CountDownLatch getLatch() {
//		return latch;
//	}

}
