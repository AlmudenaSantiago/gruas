/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;


import command.gruas.CargarGruistasCommand;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gruas.Gruista;
import process.cargador.gruas.CargadorListaGruistas;
import process.parser.gruistas.GruistasParserJson;



 
public class SingletonGruistas {
    
   
    private static SingletonGruistas instance;
    CargarGruistasCommand cargar;
    List<Gruista> lista;
    
    private SingletonGruistas() throws IOException {
        
        actualizarLista();
    }
    
    public void actualizarLista() {
       cargar = new CargarGruistasCommand(CargadorListaGruistas.getInstance(), new GruistasParserJson());
      lista = cargar.executeLista();
       
      
    }
    

    public List<Gruista> getLista() {
        return lista;
    }
    
    public static SingletonGruistas getInstance() {
       
            if (instance == null)
                try {
                    instance = new SingletonGruistas();
            } catch (IOException ex) {
                Logger.getLogger(SingletonGruistas.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      
         return instance;
    }
            
    
    
    
}
