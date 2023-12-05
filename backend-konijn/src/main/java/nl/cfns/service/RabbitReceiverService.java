package nl.cfns.service;

import java.util.concurrent.CountDownLatch;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import nl.cfns.config.RabbitConfig;
import nl.cfns.dto.MeasurementDto;
import nl.cfns.dto.MeasuringboxDto;
import nl.cfns.dto.WeatherMeasurementDto;
import nl.cfns.entity.Measurement;
import nl.cfns.entity.Measuringbox;
import nl.cfns.entity.Testbox;
import nl.cfns.entity.WeatherMeasurement;
import nl.cfns.repository.MeasurementRepository;
import nl.cfns.repository.MeasuringboxRepository;
import nl.cfns.repository.TestboxRepository;
import nl.cfns.repository.WeatherMeasurementRepository;


//this class is the most important one when it comes to defining how data from rabbitMQ messages are handled
//each function handles a different object
//data is converted from a JSON datastructure to an entity (model) or a DTO derived from the models
//for more information, see the designs in 23H2 Onedrive
@Component("receiver")
public class RabbitReceiverService {
	//define object for delays, which may be needed in some instances
	private CountDownLatch latch = new CountDownLatch(1);
	
	@Autowired
	private ModelMapper modelMapper;

	//private repository objects for interacting with measuringbox section of database
	@Autowired	
	private TestboxRepository testboxRepository;

	@Autowired	
	private MeasurementRepository measurementsRepository;
	
	@Autowired	
	private MeasuringboxRepository measuringboxRepository;
	
	@Autowired	
	private WeatherMeasurementRepository weatherMeasurementRepository;
	
	@Async
	@RabbitListener(queues = RabbitConfig.queueName)
	public void receiveMessage(Testbox receivedBox) {
		//Testbox receivedBox = objectMapper.readValue(message, Testbox.class); // convert JSON string to class object
		//receivedBox.generateNewId();
		testboxRepository.save(receivedBox);	//insert measuringbox object into database
		
		//System.out.println("the name of the box is: " + receivedBox.getName());
		//System.out.println(" [x] Received testbox '" + receivedBox + "'"); // print message as string

		// System.out.println("Received <" + message + ">");
		latch.countDown(); // why a countdown on receive?
		
	}

	@Async
	@RabbitListener(queues = RabbitConfig.WEATHER_MEASUREMENT_QUEUE)
	public void receiveMessage(WeatherMeasurementDto weatherMeasurementDto) {
		//System.out.println(" [x] Received weather" + weatherMeasurement.toString());
		WeatherMeasurement weatherMeasurement = modelMapper.map(weatherMeasurementDto, WeatherMeasurement.class);
		//weatherMeasurement.generateNewId();
		//WeatherMeasurement validweatherMeasurement = new WeatherMeasurement(weatherMeasurement);
		weatherMeasurementRepository.save(weatherMeasurement);
		latch.countDown(); // why a countdown on receive?
		
	}

	@Async
	@RabbitListener(queues = RabbitConfig.MEASUREMENT_QUEUE)
	public void receiveMessage(MeasurementDto measurementDto) {

		Measurement measurement = modelMapper.map(measurementDto, Measurement.class);
		//measurement.generateNewId();
		System.out.println(" [x] Received " + measurementDto.toString());
		System.out.println("converted to " + measurement.toString());
		measurementsRepository.save(measurement);
		latch.countDown(); // why a countdown on receive?
		


	}
	
	@Async
	@RabbitListener(queues = RabbitConfig.MEASURINGBOX2_QUEUE)
	public void receiveMessage(MeasuringboxDto measuringboxDto) {
		//System.out.println(" [x] Received box2" + measuringbox2.toString());
		Measuringbox measuringbox = modelMapper.map(measuringboxDto, Measuringbox.class);
		//measuringbox.generateNewId();
		measuringboxRepository.save(measuringbox);
		latch.countDown(); // why a countdown on receive?
		
	}
	
	//small delay may be needed in some instances
	public CountDownLatch getLatch() {
		return latch;
	}

}

