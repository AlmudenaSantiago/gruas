/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDAvisos;


import command.avisos.CargarAvisosCommand;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aviso;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.avisos.CargadorListaAvisos;
import process.parser.avisos.AvisoParserJson;





public class ModificarAviso extends Modificar {
    
    Aviso aviso;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
    
//    private JTablaAvisos tabla;
       
   
    public ModificarAviso(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public List<Aviso> modificar () throws IOException {
            preparar();
           return enviarModificacion();
            
         //   actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        aviso = new Aviso();
        aviso.setId(id);
        
        switch (variableAModificar) {
            
            
                      case "talon":
                               aviso.setTalon(Integer.parseInt(valor));
                               url = new URL(base  + "modificarTalonAviso");
                               break;
                      case "localizador":
                                 aviso.setLocalizador(Integer.parseInt(valor));
                                 url = new URL(base  + "modificarLocalizadorAviso");
                                break;    
                  
                                
                       ///********************cliente ************************///
                            case "nombre":
                                 aviso.setNombre(valor);
                                 url = new URL(base  + "modificarNombreAviso");
                                break;    
                                
                            case "telefono":
                                 aviso.setTelefono(valor);
                                 url = new URL(base  + "modificarTelefonoAviso");
                                break;     
                      
                            // ******************* coche ***************// 
                            case "matricula":
                                aviso.setMatricula(valor);
                                 url = new URL(base  + "modificarMatriculaAviso");
                                break;
                         
                            
                            case "marca":   
                               aviso.setIdMarca(Integer.parseInt(valor));
                               url = new URL(base + "modificarMarcaAviso");
                                break;
                            case "modelo":   
                               aviso.setIdModelo(Integer.parseInt(valor));
                               url = new URL(base + "modificarModeloAviso");
                                break;
                                
                            case "color":   
                               aviso.setIdColor(Integer.parseInt(valor));
                               url = new URL(base + "modificarColorAviso");
                                break;
                                
                                     
                            case "bastidor":
                                aviso.setBastidor(Integer.parseInt(valor));
                                url = new URL(base + "modificarBastidorAviso");
                                break;
                             
                            case "kms":
                                aviso.setKms(Integer.parseInt(valor));
                                url = new URL(base + "modificarKmsAviso");
                                break;
                                
                                
                             // ******************* aseguradora ********************//
                            case "idCliente":   
                                aviso.setIdCliente(Integer.parseInt(valor));
                                url = new URL(base + "modificarClienteAviso");
                                break;
                         
                            case "poliza":   
                                aviso.setPoliza(Long.parseLong(valor));
                                url = new URL(base + "modificarPolizaAviso");
                                break;
                                
                             
                            case "expediente":   
                                aviso.setExpediente(valor);
                                url = new URL(base + "modificarExpedienteAviso");
                                break;
                                
                                
                           
                          //************************** donde ****************//
                            case "municipioLocalizacion":
                                aviso.setMunicipioLocalizacion(valor);
                                 url = new URL(base  + "modificarMunicipioLocalizacionAviso");
                                break;
                                    
                             case "direccionLocalizacion":
                                 aviso.setDireccionLocalizacion(valor);
                                 url = new URL(base  + "modificarDireccionLocalizacionAviso");
                                 break;   
                             
                             case "municipioDestino":
                                 aviso.setMunicipioDestino(valor);
                                 url = new URL(base  + "modificarMunicipioDestinoAviso");
                                 break;
                               
                             case "direccionDestino":
                                 aviso.setDireccionDestino(valor);
                                 url = new URL(base  + "modificarDireccionDestinoAviso");
                                 break;
                         
                             case "desdeBase":
                                 if (valor.equals("")) {
                                     aviso.setIdDesdeBase(0);
                                 } else {
                                     aviso.setIdDesdeBase(Integer.parseInt(valor));
                                 }
                                 url = new URL(base  + "modificarDesdeBaseAviso");
                                break;
                                
                              case "base":
                                 aviso.setBase(valor);
                                 url = new URL(base  + "modificarBaseAviso");
                                 break;
                                
                              
                            //*********************** cuando **********************//
                                 
                             case "fechaAsignacion":
                                 aviso.setFechaAsignacion(valor);
                                 url = new URL(base  + "modificarFechaAsignacionAviso");
                                 break;
                             case "fechaRealizacion":
                                 aviso.setFechaRealizar(valor);
                                 url = new URL(base  + "modificarFechaRealizacionAviso");
                                 break;       
                                 
                             case "horaSalida":
                                 aviso.setHoraSalida(valor);
                                 url = new URL(base  + "modificarHoraSalidaAviso");
                                 break; 
                                 
                             case "horaLocalizado":
                                 aviso.setHoraLocalizado(valor);
                                 System.out.println("paso por hora localizado" +  valor);
                                 url = new URL(base  + "modificarHoraLocalizadoAviso");
                                 break;     
                                 
                              
                             case "tiempoLlegada":
                                 aviso.setTiempoLlegada(Double.parseDouble(valor));
                                 url = new URL(base  + "modificarTiempoLlegadaAviso");
                                 break;    
                             
                             case "horaFinalizado":
                                 aviso.setHoraFinalizado(valor);
                                 url = new URL(base  + "modificarHoraFinalizadoAviso");
                                 break; 
                                 
                             //***************** que ocurre *********************//
                                 
                             case "averia":
                                 aviso.setIdAveria(Integer.parseInt(valor));
                                 url = new URL(base  + "modificarAveriaAviso");
                                 break;   
                                 
                            case "solucion":   
                               aviso.setIdSolucion(Integer.parseInt(valor));
                                url = new URL(base + "modificarSolucionAviso");
                                break;
                           
                            case "numRespuesta":   
                               aviso.setIdNumRespuesta(Integer.parseInt(valor));
                               url = new URL(base + "modificarNumRespuestaAviso");
                                break;
                                
                            //*******************************************************//  
                            case "kmsGrua":   
                               aviso.setKmsGrua(Integer.parseInt(valor));
                               url = new URL(base + "modificarKmsGruaAviso");
                                break;
                            case "gruista":
                                 aviso.setIdGruista(Integer.parseInt(valor));
                                 url = new URL(base + "modificarGruistaAviso");
                                 break;
                             case "grua":
                                 aviso.setIdGrua(Integer.parseInt(valor));
                                 url = new URL(base + "modificarGruaAviso");
                                 break;    
                              
                                
                             //*************** caluclos para tarifa *******************//
                            case "servicioTipo":   
                               aviso.setIdServicioTipo(Integer.parseInt(valor));
                               url = new URL(base + "modificarServicioTipoAviso");
                                break;
                            case "plus":   
                               aviso.setPlus(valor);
                               url = new URL(base + "modificarPlusAviso");
                                break;
                            case "nocturnoOFestivo":   
                               aviso.setNf(valor);
                               url = new URL(base + "modificarNocturnoOFestivoAviso");
                                break;
                                
                            case "espera":   
                               aviso.setEspera(Integer.parseInt(valor));
                               url = new URL(base + "modificarEsperaAviso");
                                break;
                                 
                            case "rescate":   
                               aviso.setRescate(Integer.parseInt(valor.trim()));
                               url = new URL(base + "modificarRescateAviso");
                                break;
                           
                            case "custodia":   
                               aviso.setDiasCustodia(Integer.parseInt(valor));
                               url = new URL(base + "modificarDiasCustodiaAviso");
                                break;
                                   
                            case "observaciones":
                                aviso.setObservaciones(valor);
                                url = new URL(base + "modificarObservacionesAviso");
                                
                                
                      
                    }
        
      
        
                 
    }
    
    public List<Aviso> enviarModificacion() throws IOException {
        System.out.println("envio" + new AvisoParserJson().devuelveJsonDeAviso(aviso));
        
        stringRespuestaPOST = new ConexionPost(new AvisoParserJson().devuelveJsonDeAviso(aviso),url).conectar();
        System.out.println(stringRespuestaPOST);
        return new AvisoParserJson().parsear(stringRespuestaPOST);
        
       
  
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
            CargarAvisosCommand cargador = new CargarAvisosCommand (CargadorListaAvisos.getInstance(), new AvisoParserJson());
          //  tabla.mostrarAvisos();
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la tarifa. Ha ocurrido el siguiente error: \n" + stringRespuestaPOST  );
        
    
    }
    
    /*  public void setTabla(JTablaAvisos tabla) {
        this.tabla = tabla;
    }*/
    



}
