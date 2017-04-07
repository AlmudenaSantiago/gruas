/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;

import command.clientes.CargarClientesCommand;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;

/**
 *
 * @author gruasjoseantonio
 */
public final class SingletonClientes {

    
    private static SingletonClientes instance;
    CargarClientesCommand cargar;
    List<Cliente> lista;
    
    private SingletonClientes() throws IOException {
       actualizarLista();
    }
    
    public void actualizarLista() {
        try {
            lista = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeLista();
        } catch (Exception ex) {
            Logger.getLogger(SingletonClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    

    public List<Cliente> getLista() {
        return lista;
    }
    
    public static SingletonClientes getInstance() {
        try {
            if (instance == null)
                instance = new SingletonClientes();
           
        } catch (IOException ex) {
            Logger.getLogger(SingletonClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
         return instance;
    }
            
    
    
             
    
    
}
