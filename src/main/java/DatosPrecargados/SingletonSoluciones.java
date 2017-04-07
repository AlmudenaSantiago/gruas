/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;


import command.averias.CargarSolucionesCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.averiasYSoluciones.Solucion;
import process.cargador.averias.CargadorListaSolucion;
import process.parser.averias.SolucionParserJson;




 
public class SingletonSoluciones {
    
   
    private static SingletonSoluciones instance;
    CargarSolucionesCommand cargar;
    List<Solucion> lista;
    private HashMap<String, Integer> hash;
    
    private SingletonSoluciones() throws IOException {
        hash = new HashMap<>();
        actualizarLista();
        
    }
    
    public void actualizarLista() {
       cargar = new CargarSolucionesCommand(CargadorListaSolucion.getInstance(), new SolucionParserJson());
      lista = cargar.executeLista();
      for (int it = 0; it <lista.size(); it++) {
                     hash.put(lista.get(it).getSolucion(), lista.get(it).getId());
                }
       
      
    }
    

    public List<Solucion> getLista() {
        return lista;
    }
    
    public static SingletonSoluciones getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonSoluciones();
            } catch (IOException ex) {
                Logger.getLogger(SingletonSoluciones.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
