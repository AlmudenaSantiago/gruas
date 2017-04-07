/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Tarifas;

/**
 *
 * @author loquat
 */
public class TarifaNocturno {
    
    private Double urbano;
    private Double salida;
    private Double rescate;
    private Double espera;
    private Double custodia;
    private Double km;
    private Double kmLR;
    private Double dsi;
    private Double sar;
    private int id;
    private Integer idTipoTarifa;
    private Double suplemento;
    
    

    public Integer getIdTipoTarifa() {
        return idTipoTarifa;
    }

    public Double getSuplemento() {
        return suplemento;
    }

    public void setSuplemento(Double suplemento) {
        this.suplemento = suplemento;
    }
    

    public Integer getTipoDeTarifa() {
        return idTipoTarifa;
    }

    public void setIdTipoTarifa(Integer tipoDeTarifa) {
        this.idTipoTarifa = tipoDeTarifa;
    }
    
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
        
    public void setUrbano(Double urbano) {
        this.urbano = urbano;
    }

    public void setSalida(Double salida) {
        this.salida = salida;
    }

    public void setRescate(Double rescate) {
        this.rescate = rescate;
    }

    public void setEspera(Double espera) {
        this.espera = espera;
    }

    public void setCustodia(Double custodia) {
        this.custodia = custodia;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public void setKmLR(Double kmLR) {
        this.kmLR = kmLR;
    }

    public void setDsi(Double dsi) {
        this.dsi = dsi;
    }

    public void setSar(Double sar) {
        this.sar = sar;
    }

    
    
    public Double getUrbano() {
        return urbano;
    }

    public Double getSalida() {
        return salida;
    }

    public Double getRescate() {
        return rescate;
    }

    public Double getEspera() {
        return espera;
    }

    public Double getCustodia() {
        return custodia;
    }

    public Double getKm() {
        return km;
    }

    public Double getKmLR() {
        return kmLR;
    }

    public Double getDsi() {
        return dsi;
    }

    public Double getSar() {
        return sar;
    }
    
    
    
    
    
    
    
    
}
