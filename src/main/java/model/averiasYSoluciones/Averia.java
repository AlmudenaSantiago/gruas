/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.averiasYSoluciones;

/**
 *
 * @author loquat
 */
public class Averia {
    
    
    private Integer id;
    private String averia;
    private Integer codigo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    

    public void setAveria(String averia) {
        this.averia = averia;
    }

    public String getAveria() {
        return averia;
    }
    
    

    public Integer getId() {
        return id;
    }

   

    public void setId(Integer id) {
        this.id = id;
    }


    
}
