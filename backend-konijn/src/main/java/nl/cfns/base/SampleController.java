package nl.cfns.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//a controller like this can be used to display a front-end
//this example can be accessed through http://localhost:8090/greetings
@RestController
public class SampleController {

    @RequestMapping("/greeting")
    String hello() {
        return "HelloWorld!";
    }
}
