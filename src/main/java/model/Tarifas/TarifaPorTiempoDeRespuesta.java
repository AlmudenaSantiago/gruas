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
public class TarifaPorTiempoDeRespuesta {
    
    private int id;
    private int tipoTarifa;
    private String tarifa;
    private int hastaKm;
    private int desde;
    private int hasta;
    private Double servicio;
    private Double km;
    private Double ris;
    private Double segundaSalida;
    private Double servicioNocturno;
    private Double kmNocturno;
    private Double risNocturno;
    private Double segundaSalidaNocturno;
    private Integer idCliente;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
    
  
    
     
    public void setId(int id) {
        this.id = id;
    }

    public void setTipoTarifa(int tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public void setHastaKm(int hastaKm) {
        this.hastaKm = hastaKm;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public void setServicio(Double servicio) {
        this.servicio = servicio;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public void setRis(Double ris) {
        this.ris = ris;
    }

    public void setSegundaSalida(Double segundaSalida) {
        this.segundaSalida = segundaSalida;
    }

    public void setServicioNocturno(Double servicioNocturno) {
        this.servicioNocturno = servicioNocturno;
    }

    public void setKmNocturno(Double kmNocturno) {
        this.kmNocturno = kmNocturno;
    }

    public void setRisNocturno(Double risNocturno) {
        this.risNocturno = risNocturno;
    }

    public void setSegundaSalidaNocturno(Double segundaSalidaNocturno) {
        this.segundaSalidaNocturno = segundaSalidaNocturno;
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public int getTipoTarifa() {
        return tipoTarifa;
    }

    public String getTarifa() {
        return tarifa;
    }

    public int getHastaKm() {
        return hastaKm;
    }

    public int getDesde() {
        return desde;
    }

    public int getHasta() {
        return hasta;
    }

    public Double getServicio() {
        return servicio;
    }

    public Double getKm() {
        return km;
    }

    public Double getRis() {
        return ris;
    }

    public Double getSegundaSalida() {
        return segundaSalida;
    }

    public Double getServicioNocturno() {
        return servicioNocturno;
    }

    public Double getKmNocturno() {
        return kmNocturno;
    }

    public Double getRisNocturno() {
        return risNocturno;
    }

    public Double getSegundaSalidaNocturno() {
        return segundaSalidaNocturno;
    }
    
    
    
    
    
    
}
