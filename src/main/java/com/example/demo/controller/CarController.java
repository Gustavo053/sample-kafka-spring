package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.kafka.TopicProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/car")
public class CarController {

    @Autowired
    private TopicProducer topicProducer;

    @PostMapping
    public void addQueueCars(@RequestBody Car car) {
        topicProducer.send(car);
        log.info("Car stored in queue kafka");
    }
}
