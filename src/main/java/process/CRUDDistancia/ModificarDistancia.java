/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDDistancia;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Distancia;

import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.parser.distancias.DistanciaParserJson;




public class ModificarDistancia extends Modificar {
    
    Distancia distancia;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
       
   
    public ModificarDistancia(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificar() throws IOException {
            preparar();
            enviarModificacion();
          
    }
    
    
    
    public void preparar() throws MalformedURLException {
        distancia = new Distancia();
        distancia.setId(id);
        
        switch (variableAModificar) {
            
                          case "municipioOrigen":   
                                distancia.setIdOrigen(Integer.parseInt(valor));
                                url = new URL(base + "modificarMunicipioOrigenDistancia");
                                break;
            
                           case "municipioDestino":   
                                distancia.setIdDestino(Integer.parseInt(valor));
                                url = new URL(base + "modificarMunicipioDestinoDistancia");
                                break;
            
                            case "kms":   
                               distancia.setKms(Double.parseDouble(valor));
                               url  = new URL(base + "modificarKmsDistancia");
                                break;
                                
                          
                      
                    }
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("ENVIO" +new DistanciaParserJson().devuelveJsonDeDistancia(distancia) );
        stringRespuestaPOST = new ConexionPost(new DistanciaParserJson().devuelveJsonDeDistancia(distancia),url).conectar();
        System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST == null) JOptionPane.showMessageDialog(null,"No se ha podido modificar la distancia.");
       
    }
    
   

}
