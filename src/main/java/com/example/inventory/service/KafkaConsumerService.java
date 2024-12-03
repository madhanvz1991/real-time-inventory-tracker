
package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final InventoryRepository inventoryRepository;

    @KafkaListener(topics = "inventory-updates", groupId = "inventory_group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // Add logic to parse and update inventory in the database
    }
}
