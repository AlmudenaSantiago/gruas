/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author loquat
 */
public class RecorridosyKm {
    
    private int id;
    
    private String municipio;
    private Double ida;
    private Double kmtotal;
    private Double turismo;
    private Double todoterreno;
    private Double furgon;
    private Double maquinaria;
    private Double tarifaparamasdetres;
    private Integer idCliente;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    
     
    public Double getIda() {
        return ida;
    }

    public Double getKmtotal() {
        return kmtotal;
    }

    public Double getTurismo() {
        return turismo;
    }

    public Double getTodoterreno() {
        return todoterreno;
    }

    public Double getFurgon() {
        return furgon;
    }

    public Double getMaquinaria() {
        return maquinaria;
    }

    public Double getTarifaparamasdetres() {
        return tarifaparamasdetres;
    }

    public void setIda(Double ida) {
        this.ida = ida;
    }

    public void setKmtotal(Double kmtotal) {
        this.kmtotal = kmtotal;
    }

    public void setTurismo(Double turismo) {
        this.turismo = turismo;
    }

    public void setTodoterreno(Double todoterreno) {
        this.todoterreno = todoterreno;
    }

    public void setFurgon(Double furgon) {
        this.furgon = furgon;
    }

    public void setMaquinaria(Double maquinaria) {
        this.maquinaria = maquinaria;
    }

    public void setTarifaparamasdetres(Double tarifaparamasdetres) {
        this.tarifaparamasdetres = tarifaparamasdetres;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
    
}
