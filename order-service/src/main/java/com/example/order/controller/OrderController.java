package com.example.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.model.Order;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) throws JsonProcessingException {
		Order createdOrder = orderService.createOrder(order);
		return ResponseEntity.ok(createdOrder);
	}
	
	@GetMapping // Added GET mapping
    public ResponseEntity<String> getOrders() {
        return ResponseEntity.ok("Use POST to create orders.");
    }

}
