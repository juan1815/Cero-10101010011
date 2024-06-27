package com.fotograbados.springv1.domain.dto;

import lombok.Data;

public class ChartData {
    private String month;
    private int salesCount;

    // Getters y Setters
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }
}

