/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDServiciosEspeciales;

import command.serviciosEspeciales.CargarServiciosEspecialesCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.serviciosEspeciales.ServicioEspecial;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.serviciosEspeciales.CargadorListaServiciosEspeciales;
import process.parser.serviciosEspeciales.ServicioEspecialParserJson;
import view.fichaCliente.SingletonCliente;
import view.serviciosEspeciales.JTablaServiciosEspeciales;



public class ModificarServicioEspecial extends Modificar {
    
    ServicioEspecial servicio;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
    
    private JTablaServiciosEspeciales tabla;
       
   
    public ModificarServicioEspecial(Integer id, String var, String valor) {
        super(id,var,valor);
        
    }
    
    public void modificarServicioEspecial () throws IOException {
            preparar();
            enviarModificacion();
            actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
         servicio = new ServicioEspecial();
        servicio.setId(id);
        
        switch (variableAModificar) {
                            case "importeUnidad":   
                               servicio.setImporteUnidad(Double.parseDouble(valor));
                               url = new URL(base + "modificarImporteUnidad");
                               break;
                                
                            case "importeSalida":   
                               servicio.setImporteSalida(Double.parseDouble(valor));
                                url = new URL(base + "modificarImporteSalida");
                                break;
                                
                            case "importeUnidadConSuplemento":
                                servicio.setImporteUnidadSuplemento(Double.parseDouble(valor));
                                url = new URL(base + "modificarImporteUnidadSuplemento");
                                break;
                                
                            case "importeSalidaConSuplemento":
                                servicio.setImporteSalidaSuplemento(Double.parseDouble(valor));
                                 url = new URL(base  + "modificarImporteSalidaSuplemento");
                                break;
                                
                            case "suplemento":
                               servicio.setSuplemento(Double.parseDouble(valor));
                                url= new URL(base  + "modificarSuplemento");
                                break;
                                
                            case "igic":
                                 servicio.setIgic(Integer.parseInt(valor));
                                 url = new URL(base  + "modificarIgic");
                                break;
                            
                             case "tipo":
                                 servicio.setTipo(valor);
                                 url = new URL(base  + "modificarTipo");
                                break;
                            case "servicio":
                                servicio.setServicio(valor);
                                
                                url= new URL(base  + "modificarServicio");
                                break;
                                
                            case "nombreServicio":
                                servicio.setServicioNombre(valor);
                                url= new URL(base  + "modificarNombreServicio");
                                break;
                            case "unidad":
                                servicio.setUnidad(valor);
                                url= new URL(base  + "modificarUnidad");
                                break;
                                
                            case "umbral":
                                servicio.setUmbral(Integer.parseInt(valor));
                                url= new URL(base  + "modificarUmbral");
                                break;
                           
                           
                            case "nombre":   
                               servicio.setNombre(valor);
                               url = new URL(base + "modificarNombreServicioEspecial");
                               break;  
                                
                                
                              
                    }
        
      
        
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio  " + new ServicioEspecialParserJson().devuelveJsonDeServicioEspecial(servicio));
        stringRespuestaPOST = new ConexionPost(new ServicioEspecialParserJson().devuelveJsonDeServicioEspecial(servicio),url).conectar();
        
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
            CargarServiciosEspecialesCommand cargarServiciosEspecialesCommand = new CargarServiciosEspecialesCommand (CargadorListaServiciosEspeciales.getInstance(), new ServicioEspecialParserJson());
            tabla.mostrarServiciosEspeciales(cargarServiciosEspecialesCommand.executeLista(SingletonCliente.getInstance().getCliente().getId()));
           
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la tarifa. Ha ocurrido el siguiente error: \n" + stringRespuestaPOST  );
        
    
    }
    
      public void setTabla(JTablaServiciosEspeciales tabla) {
        this.tabla = tabla;
    }
    



}
