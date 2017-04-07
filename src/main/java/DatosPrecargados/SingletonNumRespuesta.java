/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;


import command.averias.CargarNumRespuestaCommand;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.averiasYSoluciones.NumRespuesta;
import process.cargador.averias.CargadorListaNumRespuesta;
import process.parser.averias.NumRespuestaParserJson;




 
public class SingletonNumRespuesta {
    
   
    private static SingletonNumRespuesta instance;
    CargarNumRespuestaCommand cargar;
    List<NumRespuesta> lista;
    
    private SingletonNumRespuesta() throws IOException {
        
        actualizarLista();
    }
    
    public void actualizarLista() {
       cargar = new CargarNumRespuestaCommand(CargadorListaNumRespuesta.getInstance(), new NumRespuestaParserJson());
       lista = cargar.executeLista();
       
      
    }
    

    public List<NumRespuesta> getLista() {
        return lista;
    }
    
    public static SingletonNumRespuesta getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonNumRespuesta();
            } catch (IOException ex) {
                Logger.getLogger(SingletonNumRespuesta.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
