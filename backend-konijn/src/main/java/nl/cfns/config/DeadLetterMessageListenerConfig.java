package nl.cfns.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

//import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;
@Component
@Log
public class DeadLetterMessageListenerConfig implements MessageListener {	
    @Override
    public void onMessage(Message message) {
        try {
            // Deserialize message body
            Object body = SerializationUtils.deserialize(message.getBody());
            log.info("Received message from DLQ: {}" + body);
            
            // handle the message
            // ...
            // if message is handled successfully , acknowledge the message
            // otherwise throw an exception
            }catch(Exception e){
              // log the error
            	log.severe("Error while processing message from DLQ" + e);
              //reject the message
              throw e;
            }
    }
}
