package com.example.rabbit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//        Measuringbox user = MeasuringboxRepository.save(new Measuringbox("testje", 5, 2));
//        Measuringbox searchUser= MeasuringboxRepository.findById(user.getId());
//
//        assertNotNull(searchUser);
//        assertEquals(user.getId(),searchUser.getId());
//
//   }
//
//}