package com.fotograbados.springv1.domain.dto;

import java.util.Date;

public class ReporteVentasDiariasDTO {
    private Date fechaVenta;
    private double totalVentas;

    // Constructor
    public ReporteVentasDiariasDTO(Date fechaVenta, double totalVentas) {
        this.fechaVenta = fechaVenta;
        this.totalVentas = totalVentas;
    }

    // Getters y Setters
}

