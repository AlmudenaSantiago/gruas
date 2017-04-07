/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Aaron
 */
public class Servicio {
 
    
    private int id;
    private int grua;
    private String gruista;
    private String aseguradora;
    private String compania;
    private String negocio;
    private String expediente;
    private String empresa;
    private Date horaIntervencion;
    private String marca_modelo;
    private String matricula;
    private String direccionOrigen;
    private String poblacionOrigen;
    private String direccionDestino;
    private String poblacionDestino;
    private String averia;
    private String estado;
    private String operador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public String getPoblacionOrigen() {
        return poblacionOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public String getPoblacionDestino() {
        return poblacionDestino;
    }

    public void setPoblacionOrigen(String poblacionOrigen) {
        this.poblacionOrigen = poblacionOrigen;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public void setPoblacionDestino(String poblacionDestino) {
        this.poblacionDestino = poblacionDestino;
    }

    
    
    public void setGrua(int grua) {
        this.grua = grua;
    }

    public void setGruista(String gruista) {
        this.gruista = gruista;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setHoraIntervencion(Date horaIntervencion) {
        this.horaIntervencion = horaIntervencion;
    }

    public void setMarca_modelo(String marca_modelo) {
        this.marca_modelo = marca_modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public void setPoblacion(String poblacion) {
        this.poblacionOrigen = poblacion;
    }

    public void setAveria(String averia) {
        this.averia = averia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    
    
    public int getGrua() {
        return grua;
    }

    public String getGruista() {
        return gruista;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public String getCompania() {
        return compania;
    }

    public String getNegocio() {
        return negocio;
    }

    public String getExpediente() {
        return expediente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public Date getHoraIntervencion() {
        return horaIntervencion;
    }

    public String getMarca_modelo() {
        return marca_modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public String getPoblacion() {
        return poblacionOrigen;
    }

    public String getAveria() {
        return averia;
    }

    public String getEstado() {
        return estado;
    }

    public String getOperador() {
        return operador;
    }
    
    

    
    
}

