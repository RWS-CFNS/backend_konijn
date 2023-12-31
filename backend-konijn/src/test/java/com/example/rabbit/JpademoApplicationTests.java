package com.example.rabbit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nl.cfns.base.BackendKonijnApplication;
import nl.cfns.entity.Measuringbox;
import nl.cfns.repository.MeasuringboxRepository;
import nl.cfns.service.MeasuringboxService;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendKonijnApplication.class)
public class JpademoApplicationTests {

   @Autowired
   MeasuringboxRepository boxRepository;
   
   @Autowired
   MeasuringboxService boxService;

   @Test
   public void contextLoads() {

	   	Measuringbox boxMeasuringbox = new Measuringbox(null, "testje", 5, 2); //create box example
	   	boxRepository.save(boxMeasuringbox); //put box in database
        Optional<Measuringbox> thisBoxIsSus = boxRepository.findById(boxMeasuringbox.getId()); //find box in database using ID
        Measuringbox definatelyABox = thisBoxIsSus.get(); //check if box is not null, convert it from "optional"  to normal object
        
        assertNotNull(definatelyABox); //check if box is not null
        assertEquals(boxMeasuringbox.getId(),definatelyABox.getId()); //compare ID box example, with ID in the box from the database

   }

}