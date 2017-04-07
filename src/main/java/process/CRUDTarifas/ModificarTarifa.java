/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDTarifas;

import command.tarifas.CargarTarifasCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Tarifas.Tarifa;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.tarifas.CargadorListaTarifas;
import process.parser.tarifas.TarifaParserJson;
import view.fichaCliente.SingletonCliente;
import view.recorridosyKm.JTablaCRUDRecorridosyKm;
import view.tarifas.simples.JTablaTarifas;



public class ModificarTarifa extends Modificar {
    
    Tarifa tarifa;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlTarifa;
    String stringRespuestaPOST;
    
    private JTablaTarifas tabla;
       
   
    public ModificarTarifa(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarTarifa () throws IOException {
            preparar();
            enviarModificacion();
            actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        tarifa = new Tarifa();
        tarifa.setId(id);
        
        switch (variableAModificar) {
                            case "Urbano":   
                               tarifa.setUrbano(Double.parseDouble(valor));
                               urlTarifa = new URL(base + "modificarUrbano");
                                break;
                            case "Salida":   
                               tarifa.setSalida(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarSalida");
                                break;
                            case "Km":
                                tarifa.setKm(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarKm");
                                break;
                            case "KmLR":
                                tarifa.setKmLR(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarKmLR");
                                break;
                            case "Rescate":
                               tarifa.setRescate(Double.parseDouble(valor));
                                urlTarifa= new URL(base  + "modificarRescate");
                                break;
                            case "Dsi":
                                 tarifa.setDsi(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarDsi");
                                break;
                            
                             case "Sar":
                                 tarifa.setSar(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarSar");
                                break;
                            
                                
                            case "Espera":
                                tarifa.setEspera(Double.parseDouble(valor));
                                urlTarifa= new URL(base  + "modificarEspera");
                                break;
                            case "Custodia":
                               tarifa.setCustodia(Double.parseDouble(valor));
                                urlTarifa= new URL(base  + "modificarCustodia");
                                break;
                      
                    }
        
      
        
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio" + new TarifaParserJson().devuelveJsonDeTipoDeServicio(tarifa));
        stringRespuestaPOST = new ConexionPost(new TarifaParserJson().devuelveJsonDeTipoDeServicio(tarifa),urlTarifa).conectar();
        
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
            CargarTarifasCommand cargarTarifasCommand = new CargarTarifasCommand (CargadorListaTarifas.getInstance(), new TarifaParserJson());
            tabla.mostrarTarifas(cargarTarifasCommand.executeLista(SingletonCliente.getInstance().getCliente().getId()));
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la tarifa. Ha ocurrido el siguiente error: \n" + stringRespuestaPOST  );
        
    
    }
    
      public void setTabla(JTablaTarifas tabla) {
        this.tabla = tabla;
    }
    



}
