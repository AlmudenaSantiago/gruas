/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;


import command.gruas.CargarGruasCommand;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gruas.Grua;
import process.cargador.gruas.CargadorListaGruas;
import process.parser.gruas.GruasParserJson;



 
public class SingletonGruas {
    
   
    private static SingletonGruas instance;
    CargarGruasCommand cargar;
    List<Grua> lista;
    
    private SingletonGruas() throws IOException {
        
        actualizarLista();
    }
    
    public void actualizarLista() {
       cargar = new CargarGruasCommand(CargadorListaGruas.getInstance(), new GruasParserJson());
       lista = cargar.executeLista();
       
      
    }
    

    public List<Grua> getLista() {
        return lista;
    }
    
    public static SingletonGruas getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonGruas();
            } catch (IOException ex) {
                Logger.getLogger(SingletonGruas.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
