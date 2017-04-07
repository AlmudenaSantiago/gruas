/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gruasjoseantonio
 */
public class Distancia {
    
    private Integer idOrigen;
    private Integer idDestino;
    private Double kms;
    private Integer id;
    private String municipioOrigen;
    private String municipioDestino;

    public String getMunicipioOrigen() {
        return municipioOrigen;
    }

    public String getMunicipioDestino() {
        return municipioDestino;
    }

    public void setMunicipioOrigen(String municipioOrigen) {
        this.municipioOrigen = municipioOrigen;
    }

    public void setMunicipioDestino(String municipioDestino) {
        this.municipioDestino = municipioDestino;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public Double getKms() {
        return kms;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public void setKms(Double kms) {
        this.kms = kms;
    }
    
    
    
    
    
    
    
}
