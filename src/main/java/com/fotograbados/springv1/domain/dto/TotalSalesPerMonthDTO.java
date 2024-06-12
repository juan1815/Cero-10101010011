package com.fotograbados.springv1.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TotalSalesPerMonthDTO {
    private int year;
    private int month;
    private double totalSales;

}

