package process.CRUDServicios;

import process.CRUD.Modificar;
import process.CRUDProductos.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Servicio;
import process.parser.servicios.ServicioParserJson;
import view.MainFrame;


public class ModificarServicio extends Modificar {
    
  
    Servicio servicio;
    String stringRespuestaPOST;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
   
    
   
    public ModificarServicio(Integer id, String var, String valor) {
      
      super(id,var,valor);
    }
    

    
    public void modificarServicio () throws IOException {
            prepararServicio();
            enviarModificacion();
            //actualizarTabla();
    }
    
    
    
     public void prepararServicio () throws MalformedURLException, IOException {
    
               servicio = new Servicio();
               servicio.setId(id);
              
               switch (variableAModificar) {
                   case "grua":
                          servicio.setGrua(Integer.parseInt(valor));
                          url = new URL(base + "modificarGruaServicio");
                          break;
                   case "gruista":
                         
                          servicio.setGruista(valor);
                          url = new URL(base + "modificarGruistaServicio");
                          break;
                    case "aseguradora":
                         
                          servicio.setAseguradora(valor);
                          url = new URL(base + "modificarAseguradoraServicio");
                        break;
                   case "compania":
                          servicio.setCompania(valor);
                          url = new URL(base + "modificarCompaniaServicio");
                          break;
                   case "negocio":
                          servicio.setNegocio(valor);
                          url = new URL( base + "modificarNegocioServicio");
                          break;
                       
                    case "expediente":
                          servicio.setExpediente(valor);
                          url = new URL( base + "modificarExpedienteServicio");
                          break;
                    case "empresa":
                          servicio.setEmpresa(valor);
                          url = new URL( base + "modificarEmpresaServicio");
                          break;
                          
                    case "horaIntervencion":
                         // servicio.setHoraIntervencion(valor);
                          url = new URL( base + "modificarHoraIntervencionServicio");
                          break;
                    case "marca_modelo":
                          servicio.setMarca_modelo(valor);
                          url = new URL( base + "modificarMarcaModeloServicio");
                          break;
                        
                   case "matricula":
                          servicio.setMatricula(valor);
                          url = new URL( base + "modificarMatriculaServicio");
                          break;
                    case "direccionOrigen":
                          servicio.setDireccionOrigen(valor);
                          url = new URL( base + "modificarDireccionOrigenServicio");
                          break;
                    case "direccionDestino":
                          servicio.setDireccionDestino(valor);
                          url = new URL( base + "modificarDireccionDestinoServicio");
                          break;
                        
                    case "poblacion":
                          servicio.setPoblacion(valor);
                          url = new URL( base + "modificarPoblacionServicio");
                          break;
                     case "averia":
                          servicio.setAveria(valor);
                          url = new URL( base + "modificarAveriaServicio");
                          break;
                     case "estado":
                          servicio.setEstado(valor);
                          url = new URL( base + "modificarEstadoServicio");
                          break;    
                     
               }
                    
            }
     
  
     public void enviarModificacion () throws IOException {
          stringRespuestaPOST = new ConexionPost(new ServicioParserJson().devuelveJsonDeServicio(servicio), url).conectar();
       
     }
     
     public void actualizarTabla () {
     
          if (stringRespuestaPOST.equals("ok"))  {
                        System.out.println(stringRespuestaPOST);
                       JOptionPane.showMessageDialog (null,"Servicio actualizado correctamente");
                       // FALTA ACTUALIZAR LA TABLA
                        MainFrame.getInstance().sincronizacion();
                   }     
                   else JOptionPane.showMessageDialog (null,"No se ha podido modificar el servicio");
     
     }
     
            
            
    
    
}
