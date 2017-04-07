/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.avisos;

import command.avisos.CargarAvisosCommand;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aviso;
import process.cargador.avisos.CargadorListaAvisos;
import process.parser.avisos.AvisoParserJson;


/**
 *
 * @author gruasjoseantonio
 */
public class SingletonAviso {
 
    
     
    private Aviso aviso;

    private static SingletonAviso instance;
    
    
    
    
  
    public static SingletonAviso getInstance() {
        if (instance == null) {
            instance = new SingletonAviso();
        }
        return instance;
    }

    public Aviso getAviso() {
        return aviso;
    }

    public void setAviso(Aviso aviso) {
        this.aviso = aviso;
    }
    
   
    
    public void actualizarAviso () {
         List<Aviso> avisos  =  null;
         
        try {
            avisos  = new CargarAvisosCommand(new CargadorListaAvisos(), new AvisoParserJson()).executeListaPorId(aviso.getId());
        } catch (IOException ex) {
            Logger.getLogger(SingletonAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                 
                 this.aviso = avisos.get(0);
                
    }
 
    
    
    
}
