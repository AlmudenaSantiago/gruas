/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDTarifasPorTiempoDeRespuesta;

import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.tarifas.CargadorListaTarifas;
import process.parser.tarifas.TarifaPorTiempoDeRespuestaParserJson;
import view.fichaCliente.SingletonCliente;
import view.tarifas.porTiempoDeRespuesta.JTablaTarifasPorTiempoDeRespuestaNocturno;



public class ModificarTarifaPorTiempoDeRespuestaNocturno extends Modificar {
    
    TarifaPorTiempoDeRespuesta tarifa;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlTarifa;
    String stringRespuestaPOST;
    
    private JTablaTarifasPorTiempoDeRespuestaNocturno tabla;
       
   
    public ModificarTarifaPorTiempoDeRespuestaNocturno(Integer id, String var, String valor) {
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
                            case "Servicio":   
                               tarifa.setServicioNocturno(Double.parseDouble(valor));
                               urlTarifa = new URL(base + "modificarServicioNocturnotr");
                                break;
                            case "Km":   
                               tarifa.setKmNocturno(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarkmNocturnotr");
                                break;
                            case "Ris":
                                tarifa.setRisNocturno(Double.parseDouble(valor));
                                urlTarifa = new URL(base + "modificarRisNocturnotr");
                                break;
                            case "SegundaSalida":
                                tarifa.setSegundaSalidaNocturno(Double.parseDouble(valor));
                                 urlTarifa = new URL(base  + "modificarSegundaSalidaNocturnotr");
                                break;
                           
                      
                    }
        
      
        
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio" + new TarifaPorTiempoDeRespuestaParserJson().devuelveJsonDeTipoDeServicio(tarifa));
        stringRespuestaPOST = new ConexionPost(new TarifaPorTiempoDeRespuestaParserJson().devuelveJsonDeTipoDeServicio(tarifa),urlTarifa).conectar();
        
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
            CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand = new CargarTarifasPorTiempoDeRespuestaCommand (CargadorListaTarifas.getInstance(), new TarifaPorTiempoDeRespuestaParserJson());
            tabla.mostrarTarifas(cargarTarifasCommand.executeLista(SingletonCliente.getInstance().getCliente().getId()));
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la tarifa. Ha ocurrido el siguiente error: \n" + stringRespuestaPOST  );
        
    
    }
    
      public void setTabla(JTablaTarifasPorTiempoDeRespuestaNocturno tabla) {
        this.tabla = tabla;
    }
    



}
