package com.patika.logservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.logservice.model.ExceptionLog;
import com.patika.logservice.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerListener {

    private final LogRepository logRepository;

    @KafkaListener(topics = "{spring.kafka.consumer.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        log.info("Received Messasge: {}", message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ExceptionLog exceptionLog = objectMapper.readValue(message, ExceptionLog.class);
            logRepository.insert(exceptionLog);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
