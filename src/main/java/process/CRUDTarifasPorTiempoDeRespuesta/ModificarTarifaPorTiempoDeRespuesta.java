/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDTarifasPorTiempoDeRespuesta;

import command.tarifas.CargarTarifasCommand;
import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.tarifas.CargadorListaTarifas;
import process.parser.tarifas.TarifaParserJson;
import process.parser.tarifas.TarifaPorTiempoDeRespuestaParserJson;
import view.fichaCliente.SingletonCliente;
import view.tarifas.porTiempoDeRespuesta.JTablaTarifasPorTiempoDeRespuestaNocturno;
import view.tarifas.porTiempoDeRespuesta.JTablaTarifasPorTiempoRespuesta;
import view.tarifas.simples.JTablaTarifasNocturno;



public class ModificarTarifaPorTiempoDeRespuesta extends Modificar {
    
    TarifaPorTiempoDeRespuesta tarifa;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlTarifa;
    String stringRespuestaPOST;
    
    private JTablaTarifasPorTiempoRespuesta tabla;
       
   
    public ModificarTarifaPorTiempoDeRespuesta(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarTarifa () throws IOException {
            preparar();
            enviarModificacion();
            actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        tarifa = new TarifaPorTiempoDeRespuesta();
        tarifa.setId(id);
        
        switch (variableAModificar) {
                            case "tarifa":   
                               tarifa.setTarifa(valor);
                               urlTarifa = new URL(base + "modificarTarifatr");
                                break;
                            case "hastaKm":   
                               tarifa.setHastaKm(Integer.parseInt(valor));
                               urlTarifa = new URL(base + "modificarHastaKmtr");
                                break;
                            case "Desde":   
                               tarifa.setDesde(Integer.parseInt(valor));
                                urlTarifa = new URL(base + "modificarDesdetr");
                                break;
                             case "Hasta":   
                               tarifa.setHasta(Integer.parseInt(valor));
                               urlTarifa = new URL(base + "modificarHastatr");
                                break;
                            case "Servicio":   
                               tarifa.setServicio(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarServiciotr");
                                break;
                            case "Km":
                                tarifa.setKm(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarKmtr");
                                break;
                            case "Ris":
                                tarifa.setRis(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarRistr");
                                break;
                             case "SegundaSalida":
                                tarifa.setSegundaSalida(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarSegundaSalidatr");
                                break;
                             
                           
                      
                    }
        
      
        
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio" + new TarifaPorTiempoDeRespuestaParserJson().devuelveJsonDeTipoDeServicio(tarifa));
        stringRespuestaPOST = new ConexionPost(new TarifaPorTiempoDeRespuestaParserJson().devuelveJsonDeTipoDeServicio(tarifa),urlTarifa).conectar();
        System.out.println(stringRespuestaPOST);
  
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
            CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand = new CargarTarifasPorTiempoDeRespuestaCommand (CargadorListaTarifas.getInstance(), new TarifaPorTiempoDeRespuestaParserJson());
            tabla.mostrarTarifas(cargarTarifasCommand.executeLista(SingletonCliente.getInstance().getCliente().getId()));
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la tarifa. Ha ocurrido el siguiente error: \n" + stringRespuestaPOST  );
        
    
    }
    
      public void setTabla(JTablaTarifasPorTiempoRespuesta tabla) {
        this.tabla = tabla;
    }
    



}
