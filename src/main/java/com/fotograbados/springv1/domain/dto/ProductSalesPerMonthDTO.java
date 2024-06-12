package com.fotograbados.springv1.domain.dto;

import lombok.Data;

@Data
public class ProductSalesPerMonthDTO {
    private int year;
    private int month;
    private Long productId;
    private int quantitySold;
}
