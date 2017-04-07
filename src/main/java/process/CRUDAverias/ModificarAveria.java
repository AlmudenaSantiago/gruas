/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDAverias;

import process.CRUD.contactos.*;
import process.CRUDClientes.*;
import command.clientes.CargarClientesCommand;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Cliente;
import model.averiasYSoluciones.Averia;

import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;

import process.limpiador.LimpiarTexto;
import process.parser.averias.AveriasParserJson;

import view.clientes.JTablaCRUDClientes;

/**
 *
 * @author Aaron
 */
public class ModificarAveria extends Modificar {
    
    Averia averia;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlCliente;
    String stringRespuestaPOST;
       
   
    public ModificarAveria(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarAveria () throws IOException {
            prepararAveria();
            enviarModificacion();
         //   actualizarTabla();
    }
    
    public void prepararAveria() throws MalformedURLException {
        averia = new Averia();
        averia.setId(id);
        LimpiarTexto limpiador = new LimpiarTexto();
       
        switch (variableAModificar) {
                              
                case "averia":
                                averia.setAveria(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarAveriaAveria");
                                break;
                case "codigo":
                                averia.setCodigo(Integer.parseInt(valor));
                               urlCliente = new URL(base  + "modificarCodigoAveria");
                                break;
            
              
             }
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio"  + new AveriasParserJson().devuelveJsonDeAveria(averia) );
        stringRespuestaPOST = new ConexionPost(new AveriasParserJson().devuelveJsonDeAveria(averia),urlCliente).conectar();
        System.out.println("lega" + stringRespuestaPOST);
        if (!stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"No se ha podido modificar la averia");
        }
    }
    
    public void actualizarTabla() throws Exception {
          if (stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"Averia actualizada correctamente");
           
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la averia");
        
    
    }



}
