package com.example.demo.service.kafka;

import com.example.demo.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class TopicConsumer {
    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public ConsumerRecord<String, String> consume(ConsumerRecord<String, String> payload) throws Exception {
        log.info("Topic: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order {}", payload.value());

        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(payload.value(), Car.class);

        System.out.println("----------------------------");
        System.out.println(car.getBrand());
        System.out.println(car.getModel());
        System.out.println(car.getYear());

        return payload;
    }
}
