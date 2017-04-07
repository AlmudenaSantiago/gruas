/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDBasesOperativas;

import command.basesOperativas.CargarBasesOperativasCommand;
import process.CRUDTarifas.*;
import command.tarifas.CargarTarifasCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.basesOperativas.BaseOperativa;
import model.Tarifas.Tarifa;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.CargadorListaBasesOperativas;
import process.cargador.tarifas.CargadorListaTarifas;
import process.limpiador.LimpiarTexto;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.tarifas.TarifaParserJson;
import view.basesOperativas.JTablaBasesOperativasCRUD;
import view.fichaCliente.SingletonCliente;
import view.recorridosyKm.JTablaCRUDRecorridosyKm;
import view.tarifas.simples.JTablaTarifas;



public class ModificarBaseOperativa extends Modificar {
    
    BaseOperativa baseOperativa;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
    
    private JTablaBasesOperativasCRUD tabla;
       
   
    public ModificarBaseOperativa(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarBaseOperativa () throws IOException {
            preparar();
            enviarModificacion();
          //  actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        baseOperativa = new BaseOperativa();
        baseOperativa.setId(id);
        if (variableAModificar.equals("baseOperativa")) {
            
            baseOperativa.setBaseOperativa(new LimpiarTexto().limpiar(valor));
            url = new URL(base + "modificarBaseOperativa");
            } else {
            baseOperativa.setIdMunicipio(Integer.parseInt(valor));
            url = new URL(base + "modificarIdMunicipioBaseOperativa");

        }
        valor=null;
        
    }
    
    public void enviarModificacion() throws IOException {
        stringRespuestaPOST = new ConexionPost(new BaseOperativaParserJson().devuelveJsonDeBaseOperativa(baseOperativa),url).conectar();
        
    }
    
    public void actualizarTabla() throws IOException {
          if (stringRespuestaPOST.equals("ok"))  {
            CargarBasesOperativasCommand cargarCommand = new CargarBasesOperativasCommand (CargadorListaBasesOperativas.getInstance(), new BaseOperativaParserJson());
            tabla.mostrar(cargarCommand.executeLista(SingletonCliente.getInstance().getCliente().getId()));
            
          }     
          else JOptionPane.showMessageDialog (null,"No se ha podido modificar la base operativa. Ha ocurrido el siguiente error: \n" + stringRespuestaPOST  );
        
    
    }
    
      public void setTabla(JTablaBasesOperativasCRUD tabla) {
        this.tabla = tabla;
    }
    



}
