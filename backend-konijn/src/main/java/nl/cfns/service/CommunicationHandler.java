package nl.cfns.service;

import java.io.IOException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import nl.cfns.config.RabbitConfig;
import nl.cfns.entity.Measuringbox2;
import nl.cfns.entity.Request;
import nl.cfns.repository.Measuringbox2Repository;
import nl.cfns.repository.RequestRepository;


//this class handles requests and data to send to a measuringbox
//examples of request include live data, ask debugging information, check status isalive()
//the class works automatically when measuringboxes are added in the H2 console or separate GUI
//for testing general functionality of the backend, use the nl.cfns.simulate package instead
public class CommunicationHandler {
//	@Autowired 
//	private AmqpTemplate amqpTemplate;
//	
//	@Autowired 
//	private RequestRepository requestRepository;
//	
//    @Autowired
//    private Measuringbox2Repository measuringbox2Repository;
//	
//	//check every 5 seconds if measuringboxes in the database are connected
//	@Scheduled(fixedRate = 5000)
//	@Async
//	void isAliveMeasuringboxes() throws IOException {
//        Iterable<Measuringbox2> measuringboxes = measuringbox2Repository.findAll();
//        Request request = new Request();
//        for (Measuringbox2 box : measuringboxes) {
//            // Create a request object based on the measuring box
//            // You'll need to implement this logic according to your use case
//
//            // Send the request object to RabbitMQ
//            amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.MEASURINGBOX2_KEY, request);
//        }
//    }
	}

