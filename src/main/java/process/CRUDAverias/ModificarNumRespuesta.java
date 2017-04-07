/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDNumRespuestas;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.averiasYSoluciones.NumRespuesta;

import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;

import process.limpiador.LimpiarTexto;
import process.parser.averias.NumRespuestaParserJson;



/**
 *
 * @author Aaron
 */
public class ModificarNumRespuesta extends Modificar {
    
    NumRespuesta num;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlCliente;
    String stringRespuestaPOST;
       
   
    public ModificarNumRespuesta(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarNumRespuesta () throws IOException {
            prepararNumRespuesta();
            enviarModificacion();
         //   actualizarTabla();
    }
    
    public void prepararNumRespuesta() throws MalformedURLException {
        num = new NumRespuesta();
        num.setId(id);
       
        switch (variableAModificar) {
                              
                case "numRespuesta":
                                num.setNumRespuesta(valor);
                                urlCliente = new URL(base  + "modificarNumRespuestaNumRespuesta");
                                break;
                case "codigo":
                                num.setCodigo(Integer.parseInt(valor));
                               urlCliente = new URL(base  + "modificarCodigoNumRespuesta");
                                break;
            
              
             }
                 
    }
    
    public void enviarModificacion() throws IOException {
        stringRespuestaPOST = new ConexionPost(new NumRespuestaParserJson().devuelveJsonDeAveria(num),urlCliente).conectar();
        if (!stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"No se ha podido modificar el numero de respuesta");
        }
    }
    
    public void actualizarTabla() throws Exception {
          if (stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"NumRespuesta actualizada correctamente");
           
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar el numero de respuesta");
        
    
    }



}
