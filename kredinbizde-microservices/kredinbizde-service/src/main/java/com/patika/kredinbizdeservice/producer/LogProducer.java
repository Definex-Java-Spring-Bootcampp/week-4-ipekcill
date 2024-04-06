package com.patika.kredinbizdeservice.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topicName;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }
}
