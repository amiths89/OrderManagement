package com.microservice.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent implements Serializable {
    private Long orderId;
    private String customerName;
    private String product;
    private int quantity;
    private String inventoryStatus;
}