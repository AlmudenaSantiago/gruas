/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;

import command.vehiculos.CargarMarcasCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.vehiculos.Marca;
import process.cargador.vehiculos.CargadorListaMarca;
import process.parser.vehiculos.MarcaParserJson;

 
public class SingletonMarcas {
    
   
    private static SingletonMarcas instance;
    CargarMarcasCommand cargar;
    List<Marca> lista;
    private HashMap<String,Integer> hash;
    
    private SingletonMarcas() throws IOException {
        hash = new HashMap<>();
        actualizarLista();
    }
    
    public void actualizarLista() {
       cargar = new CargarMarcasCommand(CargadorListaMarca.getInstance(), new MarcaParserJson());
      
        try {
            lista = cargar.execute();
                for (int it = 0; it <lista.size(); it++) {
                     hash.put(lista.get(it).getMarca(), lista.get(it).getId());
                }
        } catch (IOException ex) {
            Logger.getLogger(SingletonMarcas.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println(lista.get(0).getMarca());
    }
    

    public List<Marca> getLista() {
        return lista;
    }

    public HashMap<String, Integer> getHash() {
        return hash;
    }
    
    
    public static SingletonMarcas getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonMarcas();
            } catch (IOException ex) {
                Logger.getLogger(SingletonMarcas.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
