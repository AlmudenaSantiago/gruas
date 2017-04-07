/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDTarifas;

import command.tarifas.CargarTarifasCommand;
import command.tarifas.CargarTarifasNocturnoCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaNocturno;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.tarifas.CargadorListaTarifas;
import process.cargador.tarifas.CargadorListaTarifasNocturno;
import process.parser.tarifas.TarifaNocturnoParserJson;
import process.parser.tarifas.TarifaParserJson;
import view.fichaCliente.SingletonCliente;
import view.recorridosyKm.JTablaCRUDRecorridosyKm;
import view.tarifas.simples.JTablaTarifas;
import view.tarifas.simples.JTablaTarifasNocturno;



public class ModificarTarifaNocturno extends Modificar {
    
    TarifaNocturno tarifa;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlTarifa;
    String stringRespuestaPOST;
    
    private JTablaTarifasNocturno tabla;
       
   
    public ModificarTarifaNocturno(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarTarifa () throws IOException {
            preparar();
            enviarModificacion();
            actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        tarifa = new TarifaNocturno();
        tarifa.setId(id);
        
        switch (variableAModificar) {
                            case "Suplemento":   
                                tarifa.setSuplemento(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarSuplementoNocturno");
                                break;
            
                            case "Urbano":   
                               tarifa.setUrbano(Double.parseDouble(valor));
                               urlTarifa = new URL(base + "modificarUrbanoNocturno");
                                break;
                            case "Salida":   
                               tarifa.setSalida(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarSalidaNocturno");
                                break;
                            case "Km":
                                tarifa.setKm(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarKmNocturno");
                                break;
                            case "KmLR":
                                tarifa.setKmLR(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarKmLRNocturno");
                                break;
                            case "Rescate":
                               tarifa.setRescate(Double.parseDouble(valor));
                                urlTarifa= new URL(base  + "modificarRescateNocturno");
                                break;
                            case "Dsi":
                                 tarifa.setDsi(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarDsiNocturno");
                                break;
                            
                             case "Sar":
                                 tarifa.setSar(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarSarNocturno");
                                break;
                            
                                
                            case "Espera":
                                tarifa.setEspera(Double.parseDouble(valor));
                                urlTarifa= new URL(base  + "modificarEsperaNocturno");
                                break;
                            case "Custodia":
                               tarifa.setCustodia(Double.parseDouble(valor));
                                urlTarifa= new URL(base  + "modificarCustodiaNocturno");
                                break;
                      
                    }
        
      
        
                 
    }
    
    public void enviarModificacion() throws IOException {
       stringRespuestaPOST = new ConexionPost(new TarifaNocturnoParserJson().devuelveJsonDeTarifaNocturno(tarifa),urlTarifa).conectar();
        
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
            CargarTarifasNocturnoCommand cargarTarifasCommandNocturno = new CargarTarifasNocturnoCommand (CargadorListaTarifasNocturno.getInstance(), new TarifaNocturnoParserJson());
          
            tabla.mostrarTarifas(cargarTarifasCommandNocturno.executeListaNocturno(SingletonCliente.getInstance().getCliente().getId()));
           
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la tarifa. Ha ocurrido el siguiente error: \n" + stringRespuestaPOST  );
        
    
    }
    
      public void setTabla(JTablaTarifasNocturno tabla) {
        this.tabla = tabla;
    }
    



}
