/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDRecorridosyKm;

import command.tarifas.CargarRecorridosyKmCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.RecorridosyKm;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.recorridosykm.CargadorListaRecorridosyKm;
import process.cargador.tarifas.CargadorListaTarifas;
import process.parser.recorridosykm.RecorridosyKmParserJson;
import view.fichaCliente.SingletonCliente;
import view.recorridosyKm.JTablaCRUDRecorridosyKm;



public class ModificarRecorridosYKilometros extends Modificar {
    
    RecorridosyKm recorridosykm;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
    JTablaCRUDRecorridosyKm tabla;
       
   
    public ModificarRecorridosYKilometros(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarRecorridosyKm () throws IOException {
            preparar();
            enviarModificacion();
            actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        recorridosykm = new RecorridosyKm();
        recorridosykm.setId(id);
        
        switch (variableAModificar) {
                            case "municipio":   
                               recorridosykm.setMunicipio(valor);
                               url = new URL(base + "modificarMunicipioRecorridosYKm");
                               break;
            
                            case "IDA":   
                               recorridosykm.setIda(Double.parseDouble(valor));
                               url = new URL(base + "modificarIDA");
                                break;
                            case "Kmtotal":   
                               recorridosykm.setKmtotal(Double.parseDouble(valor));
                                url = new URL(base + "modificarKmTotal");
                                break;
                            case "Turismo":
                                recorridosykm.setTurismo(Double.parseDouble(valor));
                                url = new URL(base + "modificarTurismo");
                                break;
                            case "Maquinaria":
                                recorridosykm.setMaquinaria(Double.parseDouble(valor));
                                 url = new URL(base  + "modificarMaquinaria");
                                break;
                            case "Todoterreno":
                               recorridosykm.setTodoterreno(Double.parseDouble(valor));
                                url= new URL(base  + "modificarTodoTerreno");
                                break;
                            case "Furgon":
                                recorridosykm.setFurgon(Double.parseDouble(valor));
                                 url = new URL(base  + "modificarFurgon");
                                break;
                            case "Tarifaparamasdetres":
                                recorridosykm.setTarifaparamasdetres(Double.parseDouble(valor));
                                url= new URL(base  + "modificarTarifaParaMasDeTres");
                                break;
                            
                    }
        
      
        
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio " + new RecorridosyKmParserJson().devuelveJsonDeMunicipio(recorridosykm));
        stringRespuestaPOST = new ConexionPost(new RecorridosyKmParserJson().devuelveJsonDeMunicipio(recorridosykm),url).conectar();
        
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
             CargarRecorridosyKmCommand cargador = new CargarRecorridosyKmCommand (CargadorListaRecorridosyKm.getInstance(), new RecorridosyKmParserJson());
             tabla.mostrarRecorridosyKm(cargador.executeLista(SingletonCliente.getInstance().getCliente().getId()));
             
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la tarifa. Ha ocurrido el siguiente error:  \n" + stringRespuestaPOST);
        
    
    }

    public void setTabla(JTablaCRUDRecorridosyKm tabla) {
        this.tabla = tabla;
    }
    
    
    



}
