/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gruasjoseantonio
 */
public class HorarioNocturno {
    String horaSalida;
    String horaEntrada;
    Integer idCliente;
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
    
    

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

 
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

 
    
}
