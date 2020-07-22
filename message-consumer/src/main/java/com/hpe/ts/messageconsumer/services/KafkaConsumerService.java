package com.hpe.ts.messageconsumer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${message.topic.name}", groupId = "retry", containerFactory = "retryKafkaListenerContainerFactory")
    public void onMessage(String message) {
        logger.info(String.format("#### -> Consumed message -> %s", message));


        if (message.contains("Test")) {
            System.out.println("Incompatible message " + message);
            throw new RuntimeException("Incompatible message " + message);
        }
    }
}
