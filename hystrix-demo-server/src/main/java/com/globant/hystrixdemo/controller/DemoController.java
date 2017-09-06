package com.globant.hystrixdemo.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @GetMapping("/users/{userId}")
    public String getUsers(@PathVariable("userId") String userId) {
        // random delay between 0 and 2500 millesec.
        int delayMS = new Random().nextInt(2500);
        //int delayMS = 1000 * 1;
        logger.info("Looking for user {}. Will sleep for {} milliseconds", userId, delayMS);
        try {
            Thread.sleep(delayMS);
        } catch (InterruptedException e) {
            // nothing to do
        }
        logger.info("Done. Request processed");
        return "OK";
    }

}
