/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.basesOperativas;

/**
 *
 * @author loquat
 */
public class BaseOperativaAuxConClienteId {
    
    
    private Integer id;
    private String baseOperativa;
    private Integer idCliente;

    
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    

    public String getBaseOperativa() {
        return baseOperativa;
    }

    public void setBaseOperativa(String base) {
        this.baseOperativa = base;
    }


    

    public int getId() {
        return id;
    }

   

    public void setId(int id) {
        this.id = id;
    }


    
}
