/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;



public class DiaFestivo {
 
  
    private Date diaFestivo;
    private String diaFestivoString;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
    
    public String getDiaFestivoString() {
        return diaFestivoString;
    }

    public void setDiaFestivoString(String diaFestivoString) {
        this.diaFestivoString = diaFestivoString;
    }
    
    
    public Date getDiaFestivo() {
        return diaFestivo;
    }

    public void setDiaFestivo(Date date) {
        this.diaFestivo = date;
    }
    
    
    
    
    
}
