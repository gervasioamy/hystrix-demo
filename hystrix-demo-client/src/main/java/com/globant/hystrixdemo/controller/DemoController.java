package com.globant.hystrixdemo.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.globant.hystrixdemo.service.DemoServerClient;
import com.netflix.hystrix.exception.HystrixRuntimeException;

@RestController
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoServerClient client;

    @GetMapping("/users/{userId}")
    public String getUsers(@PathVariable("userId") String userId, @PathParam("fallback") boolean fallback,
            @PathParam("circuitBraker") boolean circuitBraker) {
        logger.info("Looking for user {}.", userId);
        String response;
        if (!circuitBraker) {
            // no hystrix
            response = client.getWithoutHystrix(userId);
        } else {
            // hystrix
            if (fallback) {
                response = client.getWithFallback(userId);
            } else {
                response = client.getWithoutFallback(userId);
            }
        }
        logger.info("Done. Request processed");
        return response;
    }
    
    @ExceptionHandler(HystrixRuntimeException.class)
    public ResponseEntity<Void> handleHystrixRuntimeException(HystrixRuntimeException e) {
        logger.warn("Handling HystrixRuntimeException, returning 503...", e);
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

}
