package com.fotograbados.springv1.domain.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Data
public class TotalSalesPerDayDTO  {
    private int year;
    private int month;
    private int day;
    private double totalSales;


}

