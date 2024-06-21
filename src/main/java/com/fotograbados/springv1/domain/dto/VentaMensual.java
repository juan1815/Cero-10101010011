package com.fotograbados.springv1.domain.dto;

public class VentaMensual {
    private String mes;
    private double total;

    // Constructor, getters y setters
    public VentaMensual(String mes, double total) {
        this.mes = mes;
        this.total = total;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}