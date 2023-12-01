/**
* Domain classes used to config certain aspacts of the Spring Boot program at the startup
* <p>
* These classes contain many Spring Beans and settings. The classes are:
* CustomAsyncExceptionHandler -> class for throwing exceptions relevant to async
* DeadLetterMessageListener -> receives messages that cannot be consumed by the normal messanger class. For debugging purposes
* RabbitConfig -> contains configuration for setting up connections with the rabbitMQ server. Defines Exchanges, queues and containers
* SchedulerConfig -> Defines executor for multi-threading
* SecurityConfig -> At the moment only defines HTTP security. opening consoles does not work without this configuration
* 
* </p>
*
* @since 1.0
* @author Niels de Bruin
* @version 1.0
*/

package nl.cfns.config;