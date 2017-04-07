/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.serviciosEspeciales;

import java.util.Date;

/**
 *
 * @author Aaron
 */
public class ServicioEspecial {
 
    
    private int id;
    private String unidad;
    private Double importeUnidad;
    private Double importeSalida;
    private Double importeUnidadSuplemento;
    private Double importeSalidaSuplemento;
    private Double suplemento;
    private String tipo;
    private Integer umbral;
    private Integer igic;
    private String servicio;
    private String nombreServicio;
    private Integer cliente;
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setImporteUnidadSuplemento(Double importeUnidadSuplemento) {
        this.importeUnidadSuplemento = importeUnidadSuplemento;
    }

    public void setImporteSalidaSuplemento(Double importeSalidaSuplemento) {
        this.importeSalidaSuplemento = importeSalidaSuplemento;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
    
    
    
    
    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
    
    
    
    public int getId() {
        return id;
    }

    
    public String getUnidad() {
        return unidad;
    }

    public Double getImporteUnidad() {
        return importeUnidad;
    }

    public Double getImporteSalida() {
        return importeSalida;
    }

    public Double getImporteUnidadSuplemento() {
        return importeUnidadSuplemento;
    }

    public Double getImporteSalidaSuplemento() {
        return importeSalidaSuplemento;
    }

    public Double getSuplemento() {
        return suplemento;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getUmbral() {
        return umbral;
    }

    public Integer getIgic() {
        return igic;
    }

    public String getServicio() {
        return servicio;
    }

    public String getServicioNombre() {
        return nombreServicio;
    }

 
    public void setId(int id) {
        this.id = id;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setImporteUnidad(Double importeUnidad) {
        this.importeUnidad = importeUnidad;
    }

    public void setImporteSalida(Double importeSalida) {
        this.importeSalida = importeSalida;
    }

  

    public void setSuplemento(Double suplemento) {
        this.suplemento = suplemento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUmbral(Integer umbral) {
        this.umbral = umbral;
    }

    public void setIgic(Integer igic) {
        this.igic = igic;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setServicioNombre(String servicioNombre) {
        this.nombreServicio = servicioNombre;
    }

  
    
    
    
    
    
    
    
    
    
    
    
}

