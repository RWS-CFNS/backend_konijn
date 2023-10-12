package nl.cfns.controller;

//a controller like this can be used to display a front-end
//this example can be accessed through http://localhost:8090/greetings
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//access http://localhost:8090/greeting to see the contents of this controller and send a request
//access http://localhost:8080/greeting?name=User to see the contents where "user"  is greeted instead

@RestController
public class SampleController {
	public record Greeting(long id, String content) {
	};

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
