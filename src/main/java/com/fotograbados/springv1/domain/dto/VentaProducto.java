package com.fotograbados.springv1.domain.dto;

public class VentaProducto {
    private String producto;
    private double total;

    // Constructor, getters y setters
    public VentaProducto(String producto, double total) {
        this.producto = producto;
        this.total = total;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
