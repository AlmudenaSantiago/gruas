/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;

import command.vehiculos.CargarColoresCommand;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.vehiculos.ColorVehiculo;

import process.cargador.vehiculos.CargadorListaColor;
import process.parser.vehiculos.ColorParserJson;

 
public class SingletonColores {
    
   
    private static SingletonColores instance;
    CargarColoresCommand cargar;
    List<ColorVehiculo> lista;
    HashMap<String, Integer> hash;
    
    private SingletonColores() throws IOException {
        hash = new HashMap<>();
        actualizarLista();
        
    }
    
    public void actualizarLista() {
       cargar = new CargarColoresCommand(CargadorListaColor.getInstance(), new ColorParserJson());
        try {
            lista = cargar.execute();
            for (int it = 0; it <lista.size(); it++) {
                     hash.put(lista.get(it).getNombre(), lista.get(it).getId());
            }
        } catch (IOException ex) {
            Logger.getLogger(SingletonColores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public List<ColorVehiculo> getLista() {
        return lista;
    }
    
    public static SingletonColores getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonColores();
            } catch (IOException ex) {
                Logger.getLogger(SingletonColores.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
