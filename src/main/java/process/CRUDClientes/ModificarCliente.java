/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDClientes;

import command.clientes.CargarClientesCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Cliente;
import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.limpiador.LimpiarTexto;
import view.clientes.JTablaCRUDClientes;

/**
 *
 * @author Aaron
 */
public class ModificarCliente extends Modificar {
    
    Cliente cliente;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlCliente;
    String stringRespuestaPOST;
       
   
    public ModificarCliente(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarCliente () throws IOException {
            prepararCliente();
            enviarModificacion();
         //   actualizarTabla();
    }
    
    public void prepararCliente() throws MalformedURLException {
        cliente = new Cliente();
        cliente.setId(id);
        LimpiarTexto limpiador = new LimpiarTexto();
       
        switch (variableAModificar) {
                           
                     
                            //__________________FECHAS________________________//
                            case "fechaAlta":
                                cliente.setFechaAlta(valor);
                                urlCliente = new URL(base  + "modificarFechaAlta");
                                break;
                            case "fechaBaja":
                                cliente.setFechaBaja(valor);
                                urlCliente = new URL(base  + "modificarFechaBaja");
                                break;
            
                           
                           
                             case "impuesto":   
                               cliente.setImpuesto(Integer.parseInt(valor));
                               urlCliente = new URL(base + "modificarImpuesto");
                                break;
                            //____________ DATOS DEL CLIENTE__________________//
                            
                   
                            case "nombre":   
                               cliente.setNombre(limpiador.limpiar(valor));
                               urlCliente = new URL(base + "modificarNombre");
                                break;
                            
                            case "nif":
                                cliente.setNif(valor);
                                urlCliente = new URL(base + "modificarNif");
                                break;
                            case "domicilio":
                                cliente.setDomicilio(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarDomicilio");
                                break;
                            case "poblacion":
                                cliente.setPoblacion(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarPoblacion");
                                break;
                            case "provincia":
                               cliente.setProvincia(limpiador.limpiar(valor));
                               urlCliente = new URL(base  + "modificarProvincia");
                                break;
                                
                            case "pais":
                               cliente.setPais(limpiador.limpiar(valor));
                               urlCliente = new URL(base  + "modificarPais");
                                break;
                            case "cp":
                                cliente.setCp(valor);
                                 urlCliente = new URL(base  + "modificarCp");
                                break;
                            
                         
                           
                            //____________ TELEFONOS Y CORREOS__________________//
                            
                            
                             case "telefono1":
                                cliente.setTelefono1(valor);
                                 urlCliente = new URL(base  + "modificarTelefono1");
                                break;
                            case "correo":
                                cliente.setCorreo(valor);
                                urlCliente = new URL(base  + "modificarCorreo");
                                break;
                            case "correoAvisos":
                                cliente.setCorreoAvisos(valor);
                                urlCliente = new URL(base  + "modificarCorreoAvisos");
                                break;
                            //_______________________________________//     
                            case "telefonoConsultas":
                                cliente.setTelefonoConsulta(valor);
                                urlCliente= new URL(base  + "modificarTelefonoConsultas");
                                break;
                                 
                            case "emailConsultas":
                                cliente.setEmailConsultas(valor);
                                urlCliente= new URL(base  + "modificarEmailConsultas");
                                break;
                            //________________________________________//        
                             
                              case "telefonoAdmon":
                                 cliente.setTelefonoAdmon(valor);
                                 urlCliente = new URL(base  + "modificarTelefonoAdmon");
                                break;
                             case "emailAdmon":
                                  cliente.setEmailAdmon(valor);
                                  urlCliente = new URL(base  + "modificarEmailAdmon");
                                break;
                            //__________________________________________//
                                
                              case "telefonoAsistencia":
                                cliente.setTelefonoAsistencia(valor);
                                urlCliente= new URL(base  + "modificarTelefonoAsistencia");
                                break; 
                             case "emailAsistencia":
                                cliente.setEmailAsistencia(valor);
                                urlCliente= new URL(base  + "modificarEmailAsistencia");
                                break; 
                             //__________________________________________//
                         
                               case "telefonoExtra":
                                cliente.setTelefonoExtra(valor);
                                 urlCliente = new URL(base  + "modificarTelefonoExtra");
                                break;
                                
                               case "emailExtra":
                                cliente.setEmailExtra(valor);
                                 urlCliente = new URL(base  + "modificarEmailExtra");
                                break;
                            //____________________________________________//  
                                
                             
                             case "fax":
                                cliente.setFax(valor);
                                 urlCliente = new URL(base  + "modificarFax");
                                break;    
                           
                        
                            //___________________ PERSONA DE CONTACTO _________________________//  
                                
                            
                            case "contacto":
                                cliente.setContacto(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarContacto");
                                break;
                            
                            case "puestoContacto":
                                cliente.setPuestoContacto(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarPuestoContacto");
                                break;
                            
                             //___________________ OTROS DATOS  _________________________//  
                              
                                
                            case "tipoFacturacion":
                                cliente.setTipoFacturacion(Integer.parseInt(valor));
                                urlCliente = new URL(base  + "modificarTipoFacturacion");
                                break;
                            case "tipoCliente":
                                cliente.setTipoCliente(valor);
                                urlCliente = new URL(base  + "modificarTipoCliente");
                                break;
                                          
                            case "observacionesAImprimir":
                                cliente.setObservaciones(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarObservacionesAImprimir");
                                break;
                            
                            case "observacionesgenerales":
                                cliente.setObservaciones(limpiador.limpiar(valor));
                                urlCliente = new URL(base  + "modificarObservacionesGenerales");
                                break;
                                
                                 
                            case "clienteSinIGIC":
                                cliente.setClienteSinIGIC(valor);
                                urlCliente = new URL(base  + "modificarClienteSinIGIC");
                                break;
                            
                            case "imprimeKm":
                                cliente.setImprimeKm(valor);
                                urlCliente = new URL(base  + "modificarImprimeKm");
                                break;
                                
                            case "fechaCobro":
                                cliente.setFechaCobro(valor);
                                urlCliente = new URL(base  + "modificarFechaCobro");
                                break;
                            case "formaCobro":
                                cliente.setFormaCobro(valor);
                                urlCliente = new URL(base  + "modificarFormaCobro");
                                break;
                            case "fechaEntregaFra":
                                cliente.setFechaEntregaFra(valor);
                                urlCliente = new URL(base  + "modificarFechaEntregaFra");
                                break;
                            case "minimoRetraso":
                                cliente.setMinimoRetraso(Integer.parseInt(valor));
                                urlCliente = new URL(base  + "modificarMinimoRetraso");
                                break;
                             case "numInfFinalizado":
                                cliente.setNumInfFinalizado(Integer.parseInt(valor));
                                urlCliente = new URL(base  + "modificarNumInfFinalizado");
                                break;
                                
                             case "numProveedor":
                                cliente.setNumProveedor(valor);
                                urlCliente = new URL(base  + "modificarNumProveedor");
                                break;    
                                
                                
                            case "grupo":
                                cliente.setGrupo(valor);
                                urlCliente = new URL(base  + "modificarGrupo");
                                break;
                                
                             case "radioUrbano":
                                cliente.setRadioUrbano(Integer.parseInt(valor));
                                urlCliente = new URL(base  + "modificarRadioUrbano");
                                break;
                            
                            case "RIS":
                                cliente.setRIS(Double.parseDouble(valor));
                                urlCliente = new URL(base  + "modificarRis");
                                break;
                                
                            case "umbral":
                                cliente.setUmbral(Double.parseDouble(valor));
                                urlCliente = new URL(base  + "modificarUmbralCliente");
                                break;   
                                
                            case "numDiasCoberturaEnBase":
                                cliente.setNumDiasCoberturaEnBase(Integer.parseInt(valor));
                                urlCliente = new URL(base  + "modificarNumDiasCoberturaEnBase");
                                break;
                             
                            case "muestraSuplemento":
                                cliente.setMuestraSuplemento(Integer.parseInt(valor));
                                urlCliente = new URL(base  + "modificarMuestraSuplemento");
                                break;
                            case "porcentajeSuplementoNocturno":
                                cliente.setPorcentajeSuplementoNocturno(Double.parseDouble(valor));
                                urlCliente = new URL(base  + "modificarPorcentajeSuplementoNocturno");
                                break; 
                            case "imagen":
                                cliente.setImagen(valor);
                                urlCliente = new URL(base  + "modificarImagen");
                                break; 
                        }
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio"  + new ClienteParserJson().devuelveJsonDeCliente(cliente) );
        stringRespuestaPOST = new ConexionPost(new ClienteParserJson().devuelveJsonDeCliente(cliente),urlCliente).conectar();
        System.out.println("lega" + stringRespuestaPOST);
        if (!stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"No se ha podido modificar el cliente");
        }
    }
    
   


}
