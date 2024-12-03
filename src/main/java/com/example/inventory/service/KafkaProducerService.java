
package com.example.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "inventory-updates";

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
