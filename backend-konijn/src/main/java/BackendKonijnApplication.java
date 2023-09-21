

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TODO devide classes into different packages

@SpringBootApplication // main app
@Profile("dev") // profile for configurations. TODO: create diffent profile for production environment
@ComponentScan(basePackages = "com.example.rabbit") // add other classes to main app. autoconfiguration is automatically enabled here
@EntityScan("com.example.rabbit") // add database object structures to main app
@EnableJpaRepositories("com.example.rabbit") //for detecting repository extentions that help interacting with database

@Configuration //configuration annotation after scanning, this may prevent scanning problems
public class BackendKonijnApplication {
//	@Async
//	@Scheduled(fixedRate = 3000)
//	void asyncRepeater() {
//		System.out.println("asyncRepeater");
//
//	}
//
//	@Bean
//	void Repeater() {
//		System.out.println("Repeater2");
//	}
//
//	@Async
//	@Scheduled(fixedRate = 3000)
//	public void doSomething() { // show info about current threads
//		System.out.println("Scheduled job is running with thread: " + Thread.currentThread().getName() + " at time: "
//				+ LocalDateTime.now());
//		try {
//			Thread.sleep(5000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}


	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BackendKonijnApplication.class, args);
	}
}

