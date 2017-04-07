/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;

import command.municipios.CargarMunicipiosCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Municipio;
import process.cargador.municipios.CargadorListaMunicipio;
import process.parser.municipios.MunicipioParserJson;

/**
 *
 * @author gruasjoseantonio
 */
public class SingletonMunicipios {
    
   
    private static SingletonMunicipios instance;
    CargarMunicipiosCommand cargar;
    List<Municipio> lista;
    HashMap<String, Integer > hashContadorMunicipios;
    
    private SingletonMunicipios() throws IOException {
        hashContadorMunicipios = new  HashMap<String, Integer >();
        actualizarLista();
    }
    
    public void actualizarLista() {
       cargar = new CargarMunicipiosCommand(CargadorListaMunicipio.getInstance(), new MunicipioParserJson());
       lista = cargar.executeLista();
       
       for (int it = 0; it <lista.size(); it++) {
            hashContadorMunicipios.put(lista.get(it).getMunicipio(), lista.get(it).getId());
        }
    }

    public HashMap<String, Integer> getHashContadorMunicipios() {
        return hashContadorMunicipios;
    }
    
    

    public List<Municipio> getLista() {
        return lista;
    }
    
    public static SingletonMunicipios getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonMunicipios();
            } catch (IOException ex) {
                Logger.getLogger(SingletonMunicipios.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
