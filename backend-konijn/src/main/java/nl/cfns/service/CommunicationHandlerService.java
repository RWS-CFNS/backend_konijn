package nl.cfns.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import nl.cfns.config.RabbitConfig;
import nl.cfns.entity.Measuringbox;
import nl.cfns.entity.Request;
import nl.cfns.entity.Request.MeasuringboxStatus;
import nl.cfns.entity.Request.RequestType;
import nl.cfns.repository.MeasuringboxRepository;
import nl.cfns.repository.RequestRepository;


//this class handles requests and data to send to a measuringbox
//examples of request include live data, ask debugging information, check status isalive()
//the class works automatically when measuringboxes are added in the H2 console or separate GUI
//for testing general functionality of the backend, use the nl.cfns.simulate package instead
@Service
public class CommunicationHandlerService {
	@Autowired 
	private AmqpTemplate amqpTemplate;
	
	@Autowired 
	private RequestRepository requestRepository;
	
    @Autowired
    private MeasuringboxRepository measuringbox2Repository;
	
	//check every 30 seconds if measuringboxes in the database are connected
    //it checks activity by sending a message to each measuringbox. 
	@Scheduled(fixedRate = 30000)
	@Async
	void isAliveMeasuringboxes() throws IOException {
		//System.out.println("try to send request");
        Iterable<Measuringbox> measuringboxes = measuringbox2Repository.findAll();
        
        //create request object for sending ISALIVE messages
        Request request = new Request();
        request.setMeasuringboxStatus(MeasuringboxStatus.CONNECTING);
        request.setRequestType(RequestType.ISALIVE);
        
        //iterate all measuringboxes in database
        for (Measuringbox box : measuringboxes) {

            // Send the request object to RabbitMQ. Save request for later processing
        	request.setMeasuringboxID(box.getId());
        	sendandSaveRequest(request);
        }
    }

	
	//ask the measuringbox to send data that is not being send automatically. This forces the box to check for leftover data
	//Testbox will send a debug JSON file. 
	@Async
	void requestStatusMeasuringbox(UUID id) throws IOException{     
        //create request object for sending ISALIVE messages
        Request request = new Request();
        request.setMeasuringboxStatus(MeasuringboxStatus.ACTIVE);
        request.setRequestType(RequestType.STATUS);	
        sendandSaveRequest(request);
	}
	
	//send request to measuringbox for resetting the program
	@Async
	void requestResetMeasuringbox(UUID id) throws IOException{     
        //create request object for sending ISALIVE messages
        Request request = new Request();
        request.setMeasuringboxStatus(MeasuringboxStatus.RESETTING);
        request.setRequestType(RequestType.RESET);	
        sendandSaveRequest(request);
	}
	
	//function for sending as well as saving a request
	@Async
	void sendandSaveRequest(Request request) {
    	System.out.println(" [x] Sent '" + request.toString() + "'");
        amqpTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.REQUEST_KEY, request);
        requestRepository.save(request);		
	}
}

