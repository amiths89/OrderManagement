package com.inventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.common.models.InventoryEvent;
import com.microservice.common.models.OrderEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    @Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<Long, String> inventoryStatusMap = new HashMap<>();

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void handleOrderEvent(String orderEventJson) {
        OrderEvent orderEvent;
		try {
			orderEvent = objectMapper.readValue(orderEventJson, OrderEvent.class);
			String inventoryStatus = "RESERVED"; // Or your actual reservation logic
	        inventoryStatusMap.put(orderEvent.getOrderId(), inventoryStatus);

	        // Create InventoryEvent
	        InventoryEvent inventoryEvent = new InventoryEvent(orderEvent.getOrderId(), orderEvent.getCustomerName(),orderEvent.getProduct(),orderEvent.getQuantity(), inventoryStatus);

	        // Publish InventoryEvent
	        kafkaTemplate.send("inventory-events", inventoryEvent);
	        System.out.println("InventoryEvent sent: " + inventoryEvent);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Manually deserialize
        // Implement inventory reservation logic here
    }

    public void reserveInventory(InventoryEvent event) {
        // Implement inventory reservation logic
        inventoryStatusMap.put(event.getOrderId(), event.getInventoryStatus());
        System.out.println("Inventory reserved for order: " + event.getOrderId());
    }

    public String getInventoryStatus(Long orderId) {
        // Implement logic to retrieve inventory status
        return inventoryStatusMap.getOrDefault(orderId, "Not Found");
    }
}
