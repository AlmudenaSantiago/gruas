/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fichaCliente;

import command.clientes.CargarClientesCommand;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;
import view.clientes.FichaClienteFrame;

/**
 *
 * @author loquat
 */
public class SingletonCliente {
    
    
    
    private Cliente cliente;

    private static SingletonCliente instance;
    
    
    
    
  
    public static SingletonCliente getInstance() {
        if (instance == null) {
            instance = new SingletonCliente();
        }
        return instance;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
   
    
    public void actualizarCliente () {
         List<Cliente> clientes  =  null;
          try {
                  clientes  = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(cliente.getId());
                 
                 } catch (Exception ex) {
                     Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
                 }
                this.cliente = clientes.get(0);
                System.out.println("en el singleton el cliente" + clientes.get(0).getCorreoAvisos() + clientes.get(0).getTelefono1());
    }
 
    
    
    
    
    
    
    
}
