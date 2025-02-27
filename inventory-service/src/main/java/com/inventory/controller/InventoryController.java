package com.inventory.controller;

import com.inventory.service.*;
import com.microservice.common.models.InventoryEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveInventory(@RequestBody InventoryEvent event) {
        inventoryService.reserveInventory(event);
        return new ResponseEntity<>("Inventory reserved", HttpStatus.OK);
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<String> getInventoryStatus(@PathVariable Long orderId) {
        String status = inventoryService.getInventoryStatus(orderId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
