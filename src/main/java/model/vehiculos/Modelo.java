
package model.vehiculos;

import model.*;


public class Modelo {
    
    
    
    private String modelo;
    private int id;
    private int idMarcaPadre;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    
    

    
                      
    public String getModelo() {
        return modelo;
    }


    public void setModelo(String nombre) {
        this.modelo = nombre;
    }

    public int getIdMarcaPadre() {
        return idMarcaPadre;
    }

    public void setIdMarcaPadre(int idMarcaPadre) {
        this.idMarcaPadre = idMarcaPadre;
    }
    
    
    
    

    
}
