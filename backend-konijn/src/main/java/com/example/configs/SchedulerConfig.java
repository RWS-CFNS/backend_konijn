package com.example.configs;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

//seperate class for configurations to make scheduling modular. it can be turned on or off by including the class
//configuration for asynchronus implementation with multiple threads
@Configuration
@EnableScheduling // for repeating tasks
@EnableAsync // for scheduling classes in parallel
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true) // able to shut down scheduler in tests
public class SchedulerConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	// destroymethod parameter for properly deleting executor when program is shut
	// down
	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(8);
	}

}