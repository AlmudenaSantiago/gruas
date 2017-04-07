/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;


import command.averias.CargarAveriasCommand;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.averiasYSoluciones.Averia;
import process.cargador.averias.CargadorListaAverias;
import process.parser.averias.AveriasParserJson;




 
public class SingletonAverias {
    
   
    private static SingletonAverias instance;
    CargarAveriasCommand cargar;
    List<Averia> lista;
    
    private SingletonAverias() throws IOException {
        
        actualizarLista();
    }
    
    public void actualizarLista() {
       cargar = new CargarAveriasCommand(CargadorListaAverias.getInstance(), new AveriasParserJson());
      lista = cargar.executeLista();
       
      
    }
    

    public List<Averia> getLista() {
        return lista;
    }
    
    public static SingletonAverias getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonAverias();
            } catch (IOException ex) {
                Logger.getLogger(SingletonAverias.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
