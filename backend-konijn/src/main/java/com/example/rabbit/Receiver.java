package com.example.rabbit;

import org.springframework.stereotype.Component;

@Component("receiver")
public class Receiver {



	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
		// latch.countDown(); //why a countdown on receive?
	}


}
