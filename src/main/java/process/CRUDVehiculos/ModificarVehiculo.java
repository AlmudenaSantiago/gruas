/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDVehiculos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.vehiculos.Vehiculo;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.parser.vehiculos.VehiculoParserJson;




public class ModificarVehiculo extends Modificar {
    
    Vehiculo vehiculo;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlGrua;
    String stringRespuestaPOST;
       
   
    public ModificarVehiculo(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificar () throws IOException {
            preparar();
            enviarModificacion();
          
    }
    
    public void preparar() throws MalformedURLException {
        vehiculo = new Vehiculo();
        vehiculo.setId(id);
        
        switch (variableAModificar) {
            
                          case "peso":   
                                vehiculo.setPeso(Double.parseDouble(valor));
                                urlGrua = new URL(base + "modificarPesoVehiculo");
                                break;
            
                           case "alto":   
                                vehiculo.setAlto(Double.parseDouble(valor));
                                urlGrua = new URL(base + "modificaAltoVehiculo");
                                break;
            
                            case "largo":   
                               vehiculo.setLargo(Double.parseDouble(valor));
                               urlGrua = new URL(base + "modificarLargoVehiculo");
                                break;
                                
                            case "modelo":  
                                vehiculo.setModelo(valor);
                                urlGrua = new URL(base + "modificarModeloVehiculo");
                                break;
                                
                            case "marca":
                                 vehiculo.setMarca(valor);
                                urlGrua = new URL(base + "modificarMarcaVehiculo");
                                break;
                            case "tipo":
                                 vehiculo.setTipo(valor);
                                urlGrua = new URL(base + "modificarTipoVehiculo");
                                break; 
                            case "imagen":
                                 vehiculo.setImagen(valor);
                                urlGrua = new URL(base + "modificarImagenVehiculo");
                                break;   
                          
                    }
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio"  + new VehiculoParserJson().devuelveJsonDeVehiculo(vehiculo));
        stringRespuestaPOST = new ConexionPost(new VehiculoParserJson().devuelveJsonDeVehiculo(vehiculo),urlGrua).conectar();
        if (stringRespuestaPOST == null) JOptionPane.showMessageDialog(null,"No se ha podido modificar la grua.");
    
    }
    
   

}
