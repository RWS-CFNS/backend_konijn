package nl.cfns.config;

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

import nl.cfns.base.Receiver;

//this configuration creates a connection with an existing rabbitmq server
//only works if a rabbitmq server is running on this PC
@Configuration
@EnableRabbit //for using rabbit annotations in project
public class RabbitConfig {
	//TODO use @value annotation to define variables at the top of configuration here
	public static final String topicExchangeName = "spring-boot-exchange";
	
	// name for queue's and topics
	public static final String queueName = "spring-boot";
	public static final String WEATHER_MEASUREMENT_QUEUE = "cfns.weather";
	public static final String MEASURINGBOX2_QUEUE = "cfns.measuringbox2";
	public static final String MEASUREMENT_QUEUE = "cfns.measurement";
 	
	public static final String WEATHER_MEASUREMENT_KEY = "key.cfns.weather";  
	public static final String routingKey = "foo.bar.abc";
	public static final String MEASURINGBOX2_KEY = "key.cfns.measuringbox2"; 	
	public static final String MEASUREMENT_KEY = "key.cfns.measurement"; 
	
	//name for dead letter, when messages are rejected
    private static final String DEAD_LETTER_QUEUE = "dead.letter.queue";
    private static final String DEAD_LETTER_EXCHANGE = "dead.letter.exchange";
    private static final String DEAD_LETTER_ROUTING_KEY = "dead.letter";
    
	//exchange definitions
	@Bean
	TopicExchange MainExchange() {
		TopicExchange topicExchange = new TopicExchange(topicExchangeName);		
		return topicExchange;
	}

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }
    
    //queue definitions
    @Bean
    Queue testQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return new Queue(queueName, false, false, false, args);
    }
    
    @Bean
    Queue measuringbox2Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return new Queue(MEASURINGBOX2_QUEUE, false, false, false, args);
    }
    
    @Bean
    Queue measurementQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return new Queue(MEASUREMENT_QUEUE, false, false, false, args);
    }
    
    @Bean
    Queue weatherQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return new Queue(WEATHER_MEASUREMENT_QUEUE, false, false, false, args);
    }
    
    
    @Bean
    Queue deadLetterQueue() {
    	Queue queue = new Queue(DEAD_LETTER_QUEUE, false, false, false);
        return queue;
    }

    //binding definitions
	@Bean
	Binding testBinding() {
		return BindingBuilder.bind(testQueue()).to(MainExchange()).with(routingKey);
	}
	
	@Bean
	Binding measuringbox2Binding() {
		return BindingBuilder.bind(measuringbox2Queue()).to(MainExchange()).with(MEASURINGBOX2_KEY);
	}
	
	@Bean
	Binding measurementBinding() {
		return BindingBuilder.bind(measurementQueue()).to(MainExchange()).with(MEASUREMENT_KEY);
	}
	
	@Bean
	Binding weatherBinding() {
		return BindingBuilder.bind(weatherQueue()).to(MainExchange()).with(WEATHER_MEASUREMENT_KEY);
	}
    
    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY);
    }
    
	// adapters to be used by the receiver class
	@Bean
	MessageListenerAdapter exampleListenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
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
	SimpleMessageListenerContainer exampleContainer(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addQueueNames(queueName);
		container.addQueueNames(WEATHER_MEASUREMENT_QUEUE);
		container.addQueueNames(MEASURINGBOX2_QUEUE);
		container.addQueueNames(MEASUREMENT_QUEUE);
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
