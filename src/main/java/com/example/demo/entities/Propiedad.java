package com.example.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private Double precio;

    private String tipo; // "Alquiler" o "Venta"
    private String ubicacion;
    
    // Estos campos te servirán para la lógica de actualización que querés
    private Double indiceActualizacion; 
    private LocalDate fechaUltimoAumento;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public Double getIndiceActualizacion() {
        return indiceActualizacion;
    }
    public void setIndiceActualizacion(Double indiceActualizacion) {
        this.indiceActualizacion = indiceActualizacion;
    }
    public LocalDate getFechaUltimoAumento() {
        return fechaUltimoAumento;
    }
    public void setFechaUltimoAumento(LocalDate fechaUltimoAumento) {
        this.fechaUltimoAumento = fechaUltimoAumento;
    }

    
}
