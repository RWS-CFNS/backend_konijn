package com.example.rabbit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class JpademoApplicationTests {
//
//   @Autowired
//   MeasuringboxRepository userRepository;
//
//   @Test
//   public void contextLoads() {
//
//	   	Measuringbox boxMeasuringbox = new Measuringbox("testje", 5, 2);
//        Measuringbox user = MeasuringboxRepository.save(boxMeasuringbox);
//        Measuringbox searchUser= MeasuringboxRepository.findById(user.getId());
//
//        assertNotNull(searchUser);
//        assertEquals(user.getId(),searchUser.getId());
//
//   }
//
//}