package com.example.demo.service.kafka;

import com.example.demo.model.Car;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class TopicProducer {
    @Value("${topic.name.producer}")
    private String topicName;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(Car message) {
        log.info("Sended payload: {}", message);
        kafkaTemplate.send(topicName, message.toString());
    }
}
