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
public class BaseOperativa {
    
    
    private Integer id;
    private String baseOperativa;
    private Integer idMunicipio;
    private String municipio;

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }
    
    

    public String getBaseOperativa() {
        return baseOperativa;
    }

    public void setBaseOperativa(String base) {
        this.baseOperativa = base;
    }


    

    public Integer getId() {
        return id;
    }

   

    public void setId(Integer id) {
        this.id = id;
    }


    
}
