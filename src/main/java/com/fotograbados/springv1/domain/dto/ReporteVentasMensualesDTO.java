package com.fotograbados.springv1.domain.dto;

public class ReporteVentasMensualesDTO {
    private int year;
    private int month;
    private double totalVentas;

    // Constructor
    public ReporteVentasMensualesDTO(int year, int month, double totalVentas) {
        this.year = year;
        this.month = month;
        this.totalVentas = totalVentas;
    }

    // Getters y Setters
}

