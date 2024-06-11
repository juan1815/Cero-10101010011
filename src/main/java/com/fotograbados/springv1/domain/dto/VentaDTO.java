package com.fotograbados.springv1.domain.dto;


import java.util.Date;

public class VentaDTO {
    private Date fechaVenta;
    private String nombreUsuario;
    private String numeroOrden;
    private String descripcionEnvio;
    private Double totalVenta;
    private String metodoPago;

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getDescripcionEnvio() {
        return descripcionEnvio;
    }

    public void setDescripcionEnvio(String descripcionEnvio) {
        this.descripcionEnvio = descripcionEnvio;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}

