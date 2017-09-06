package com.globant.hystrixdemo.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.globant.hystrixdemo.controller.DemoController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;

@Service
public class DemoServerClient {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(commandKey = "getUserCommandWithFallback", fallbackMethod = "fallback")
    public String getWithFallback(String userId) {
        logger.info("Hystrix call with fallback configured");
        return callServer(userId);
    }

    public String fallback(String userId) {
        logger.warn("Fallback hit when trying to get user: {}", userId);
        return "Fallback catch!";
    }

    /**
     * More available properties to set:
     * https://github.com/Netflix/Hystrix/wiki/configuration
     * 
     * @param userId
     * @return
     */
    @HystrixCommand(commandKey = "getUserCommandWithoutFallback",raiseHystrixExceptions = {HystrixException.RUNTIME_EXCEPTION})
    public String getWithoutFallback(String userId) {
        logger.info("Hystrix call with NO fallback configured, but with specific command properties (not all defaulted)");
        return callServer(userId);
    }

    public String getWithoutHystrix(String userId) {
        logger.info("Call with NO Hystrix configuration");
        return callServer(userId);
    }

    
    private String callServer(String userId) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:9091/users/").path(userId).build().toUri();
        return this.restTemplate.getForObject(uri, String.class);
    }

}
