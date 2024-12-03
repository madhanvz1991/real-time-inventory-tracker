
package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final KafkaProducerService kafkaProducerService;
    private final InventoryRepository inventoryRepository;

    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody Inventory inventory) {
        kafkaProducerService.sendMessage("Updating inventory: " + inventory.getProductName());
        inventoryRepository.save(inventory);
        return ResponseEntity.ok("Inventory updated!");
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getInventory() {
        return ResponseEntity.ok(inventoryRepository.findAll());
    }
}
