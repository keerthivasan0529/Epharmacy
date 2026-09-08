package com.epharmacy.medicine_service.dto;

import lombok.Data;

// This DTO is used by Order Service to reduce stock
// when a customer places an order
@Data
public class UpdateStockDto {
    private Integer orderedQuantity;
}