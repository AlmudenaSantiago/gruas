/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUD.contactos;

import process.CRUDClientes.*;
import command.clientes.CargarClientesCommand;
import command.contactos.CargarContactosCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Contacto;
import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.contactos.CargadorListaContacto;
import process.limpiador.LimpiarTexto;
import process.parser.contactos.ContactoParserJson;
import view.clientes.JTablaCRUDClientes;

/**
 *
 * @author Aaron
 */
public class ModificarContacto extends Modificar {
    
    Contacto contacto;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlCliente;
    String stringRespuestaPOST;
       
   
    public ModificarContacto(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarContacto () throws IOException {
            prepararContacto();
            enviarModificacion();
         //   actualizarTabla();
    }
    
    public void prepararContacto() throws MalformedURLException {
        contacto = new Contacto();
        contacto.setId(id);
        LimpiarTexto limpiador = new LimpiarTexto();
       
        switch (variableAModificar) {
                              
                case "nombre":
                                contacto.setNombre(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarNombreContacto");
                                break;
                case "telefono":
                                contacto.setTelefono(limpiador.limpiar(valor));
                               urlCliente = new URL(base  + "modificarTelefonoContacto");
                                break;
            
                case "email":
                                contacto.setEmail(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarEmailContacto");
                                break;
             }
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio"  + new ContactoParserJson().devuelveJsonDeContacto(contacto) );
        stringRespuestaPOST = new ConexionPost(new ContactoParserJson().devuelveJsonDeContacto(contacto),urlCliente).conectar();
        System.out.println("lega" + stringRespuestaPOST);
        if (!stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"No se ha podido modificar el contacto");
        }
    }
    
    public void actualizarTabla() throws Exception {
          if (stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"Contacto actualizado correctamente");
           
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar el contacto");
        
    
    }



}
