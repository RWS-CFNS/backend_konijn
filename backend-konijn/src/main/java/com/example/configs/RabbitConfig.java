package com.example.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rabbit.Receiver;

//this configuration creates a connection with an existing rabbitmq server
//only works if a rabbitmq server is running on this PC
@Configuration
@EnableRabbit //for using rabbit annotations in project
public class RabbitConfig {
	//TODO use @value annotation to define variables at the top of configuration here
	
	// name for main queue and topic
	public static final String topicExchangeName = "spring-boot-exchange";
	public static final String queueName = "spring-boot";
	public static final String routingKey = "foo.bar.abc";

	//name for dead letter, when messages are rejected
    private static final String DEAD_LETTER_QUEUE = "dead.letter.queue";
    private static final String DEAD_LETTER_EXCHANGE = "dead.letter.exchange";
    private static final String DEAD_LETTER_ROUTING_KEY = "dead.letter";

    //old main queue configuration
//	@Bean
//	Queue queue() {
//		return new Queue(queueName, false);
//	}

	@Bean
	TopicExchange MainExchange() {
		TopicExchange topicExchange = new TopicExchange(topicExchangeName);		
		return topicExchange;
	}

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

	@Bean
	Binding MainBinding() {
		return BindingBuilder.bind(mainQueue()).to(MainExchange()).with(routingKey);
	}
    
    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY);
    }
    
    @Bean
    Queue mainQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return new Queue(queueName, false, false, false, args);
    }
    
    @Bean
    Queue deadLetterQueue() {
    	Queue queue = new Queue(DEAD_LETTER_QUEUE, false, false, false);
        return queue;
    }
	
	//TODO move connection and user settings in factory to seperate configuration file
	@Bean
	ConnectionFactory connectionFactory(){
	    CachingConnectionFactory connectionFactory =new CachingConnectionFactory() ;
	    connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
	    connectionFactory.setHost("localhost");
	    connectionFactory.setVirtualHost("/");
	    connectionFactory.setPort(5672);
	    connectionFactory.setUsername("backendUser");
	    connectionFactory.setPassword("backendPass");
	    return connectionFactory;
	}

	// Setting the annotation listeners to use the jackson2JsonMessageConverter
	@Bean
	SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}

	@Bean
	SimpleMessageListenerContainer MainContainer(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		container.setConcurrentConsumers(8);
		listenerAdapter.setMessageConverter(jsonMessageConverter());
		return container;
	}
	
	   @Bean
		SimpleMessageListenerContainer DeadContainer(ConnectionFactory connectionFactory,
				DeadLetterMessageListener listener) {
	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	        container.setConnectionFactory(connectionFactory);
	        container.setQueues(deadLetterQueue());
	        container.setMessageListener(listener);
	        container.setDefaultRequeueRejected(false); //this prevents endless exception loop due to dead letters
	        return container;
	    }

	// adapter to be used by the receiver class
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	

	//jackson converter for default conversion from message payload to JSON
    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    //template for setting converter
    @Bean
    AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
