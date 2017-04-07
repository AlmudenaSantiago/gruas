/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gruas;


public class Grua {

    
    private Integer id;
    private String tipo;
    private String conductor;
    private int base;
    private String rotulado;
    private Double km;
    private String estado;
    private String src;
    private String demora;
    private String horario;
    private String mensaje;
    private Integer numGrua;
    private String imagen;
    private Double alto;
    private Double largo;
    private Double peso;
    
    
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
    public Integer getNumGrua() {
        return numGrua;
    }

    public void setNumGrua(Integer numGrua) {
        this.numGrua = numGrua;
    }
    
    

    public String getSrc() {
        return src;
    }

    public String getDemora() {
        return demora;
    }

    public String getHorario() {
        return horario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDemora(String demora) {
        this.demora = demora;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
           

    public int getBase() {
        return base;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getConductor() {
        return conductor;
    }

    public String getRotulado() {
        return rotulado;
    }

    public Double getKm() {
        return km;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setRotulado(String rotulado) {
        this.rotulado = rotulado;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getAlto() {
        return alto;
    }

    public Double getLargo() {
        return largo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    
    
    
    
    
    
}
