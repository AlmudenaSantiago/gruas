/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDAverias;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.averiasYSoluciones.Solucion;

import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;

import process.limpiador.LimpiarTexto;
import process.parser.averias.SolucionParserJson;


/**
 *
 * @author Aaron
 */
public class ModificarSolucion extends Modificar {
    
    Solucion solucion;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlCliente;
    String stringRespuestaPOST;
       
   
    public ModificarSolucion(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarSolucion () throws IOException {
            prepararSolucion();
            enviarModificacion();
         //   actualizarTabla();
    }
    
    public void prepararSolucion() throws MalformedURLException {
        solucion = new Solucion();
        solucion.setId(id);
        LimpiarTexto limpiador = new LimpiarTexto();
       
        switch (variableAModificar) {
                              
                case "solucion":
                                solucion.setSolucion(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarSolucionSolucion");
                                break;
                case "codigo":
                                solucion.setCodigo(Integer.parseInt(valor));
                               urlCliente = new URL(base  + "modificarCodigoSolucion");
                                break;
            
              
             }
                 
    }
    
    public void enviarModificacion() throws IOException {
        stringRespuestaPOST = new ConexionPost(new SolucionParserJson().devuelveJsonDeSolucion(solucion),urlCliente).conectar();
        if (!stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"No se ha podido modificar la solución");
        }
    }
    
    public void actualizarTabla() throws Exception {
          if (stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"Solucion actualizada correctamente");
           
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la solución");
        
    
    }



}
