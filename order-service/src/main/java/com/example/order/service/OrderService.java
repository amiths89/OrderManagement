package com.example.order.service;

import com.example.order.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.common.models.OrderEvent;
@Service
public class OrderService {

    @Autowired
    private KafkaTemplate<Object, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Order createOrder(Order order) throws JsonProcessingException {
        // Simulate saving to a database
        order.setId(System.currentTimeMillis());
        OrderEvent orderEvent = new OrderEvent(order.getId(), order.getCustomerName(), order.getProduct(), order.getQuantity(), "CREATED");
        String orderEventJson = objectMapper.writeValueAsString(orderEvent);
        kafkaTemplate.send("order-events", orderEventJson);
        return order;
    }
}