/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Vendedor {
   
    private Integer id;
    private String nombre;
    private String nif;
    private String apellidos;
    private Double comision1;
    private Double comision2;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    
    
    
    
    
    public String getNif() {
        return nif;
    }

    public Double getComision1() {
        return comision1;
    }

    public Double getComision2() {
        return comision2;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setComision1(Double comision1) {
        this.comision1 = comision1;
    }

    public void setComision2(Double comision2) {
        this.comision2 = comision2;
    }
   
    
  


    
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

   
  
 

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
    
    
    
    
    
    
    
    
    
}
