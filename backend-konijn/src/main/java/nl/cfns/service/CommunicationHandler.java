package nl.cfns.service;

import java.io.IOException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import nl.cfns.config.RabbitConfig;
import nl.cfns.entity.Measuringbox2;
import nl.cfns.entity.Request;
import nl.cfns.entity.Request.MeasuringboxStatus;
import nl.cfns.entity.Request.RequestType;
import nl.cfns.repository.Measuringbox2Repository;
import nl.cfns.repository.RequestRepository;


//this class handles requests and data to send to a measuringbox
//examples of request include live data, ask debugging information, check status isalive()
//the class works automatically when measuringboxes are added in the H2 console or separate GUI
//for testing general functionality of the backend, use the nl.cfns.simulate package instead
@Service
public class CommunicationHandler {
	@Autowired 
	private AmqpTemplate amqpTemplate;
	
	@Autowired 
	private RequestRepository requestRepository;
	
    @Autowired
    private Measuringbox2Repository measuringbox2Repository;
	
	//check every 5 seconds if measuringboxes in the database are connected
	@Scheduled(fixedRate = 30000)
	@Async
	void isAliveMeasuringboxes() throws IOException {
		System.out.println("try to send request");
        Iterable<Measuringbox2> measuringboxes = measuringbox2Repository.findAll();
        
        //create request object for sending ISALIVE messages
        Request request = new Request();
        request.setMeasuringboxStatus(MeasuringboxStatus.CONNECTING);
        request.setRequestType(RequestType.ISALIVE);
        
        //iterate all measuringboxes in database
        for (Measuringbox2 box : measuringboxes) {

            // Send the request object to RabbitMQ. Save request for later processing
        	request.setMeasuringboxID(box.getId());
        	System.out.println(" [x] Sent '" + request.toString() + "'");
            amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.REQUEST_KEY, request);
            requestRepository.save(request);
        }
    }
}

