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
public class NumRespuesta {
    
    
    private Integer id;
    private String numRespuesta;
    private Integer codigo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    

    public void setNumRespuesta(String s) {
        this.numRespuesta = s;
    }

    public String getNumRespuesta() {
        return numRespuesta;
    }
    
    

    public Integer getId() {
        return id;
    }

   

    public void setId(Integer id) {
        this.id = id;
    }


    
}
