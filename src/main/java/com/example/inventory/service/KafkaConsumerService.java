
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

        String[] parts = message.split(",");
        if (parts.length == 2) {
            String productName = parts[0].trim();
            int quantity;

            try {
                quantity = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                System.err.println("Invalid quantity format in message: " + message);
                return;
            }

            // Check if the product already exists in the database
            Inventory inventory = inventoryRepository.findAll().stream()
                    .filter(item -> item.getProductName().equalsIgnoreCase(productName))
                    .findFirst()
                    .orElse(new Inventory(null, productName, 0));

            // Update the quantity
            inventory.setQuantity(inventory.getQuantity() + quantity);

            // Save the updated inventory
            inventoryRepository.save(inventory);
            System.out.println("Updated inventory: " + inventory);
        } else {
            System.err.println("Invalid message format: " + message);
        }
    }
}
