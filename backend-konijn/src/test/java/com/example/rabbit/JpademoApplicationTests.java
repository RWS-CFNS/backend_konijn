package com.example.rabbit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpademoApplicationTests {

   @Autowired
   MeasuringboxRepository boxRepository;
   
   @Autowired
   MeasuringboxService boxService;

   @Test
   public void contextLoads() {

	   	Measuringbox boxMeasuringbox = new Measuringbox("testje", 5, 2); //create box example
	   	boxRepository.save(boxMeasuringbox); //put box in database
        Optional<Measuringbox> thisBoxIsSus = boxRepository.findById(boxMeasuringbox.getId()); //find box in database using ID
        Measuringbox definatelyABox = thisBoxIsSus.get(); //check if box is not null, convert it from "optional"  to normal object
        
        assertNotNull(definatelyABox); //check if box is not null
        assertEquals(boxMeasuringbox.getId(),definatelyABox.getId()); //compare ID box example, with ID in the box from the database

   }

}