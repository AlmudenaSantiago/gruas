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
public class Solucion {
    
    
    private Integer id;
    private String solucion;
    private Integer codigo;

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
    

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }


    

    public Integer getId() {
        return id;
    }

   

    public void setId(Integer id) {
        this.id = id;
    }


    
}
