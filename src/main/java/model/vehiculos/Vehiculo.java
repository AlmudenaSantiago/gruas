/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vehiculos;

/**
 *
 * @author gruasjoseantonio
 */
public class Vehiculo {
 
    private Integer id;
    private String marca;
    private String modelo;
    private String tipo;
    private Double largo;
    private Double alto;
    private Double peso;
    private Integer idMarca;
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }
    
    
            
            

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getLargo() {
        return largo;
    }

    public Double getAlto() {
        return alto;
    }

    public Double getPeso() {
        return peso;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
     
        
    
    
}
