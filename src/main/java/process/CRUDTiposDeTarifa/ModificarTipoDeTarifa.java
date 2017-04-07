/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDTiposDeTarifa;

import command.basesOperativas.CargarBasesOperativasCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Tarifas.TipoDeTarifa;
import model.basesOperativas.BaseOperativa;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.CargadorListaBasesOperativas;
import process.limpiador.LimpiarTexto;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;
import view.basesOperativas.JTablaBasesOperativasCRUD;
import view.fichaCliente.SingletonCliente;



public class ModificarTipoDeTarifa extends Modificar {
    
    TipoDeTarifa tipo;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
    
    private JTablaBasesOperativasCRUD tabla;
       
   
    public ModificarTipoDeTarifa(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificar () throws IOException {
            preparar();
            enviarModificacion();
     //       actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        tipo = new TipoDeTarifa();
        tipo.setId(id);
        tipo.setTipo(new LimpiarTexto().limpiar(valor));
        url = new URL(base + "modificarTipoDeTarifa");
                     
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio" + new TipoDeTarifaParserJson().devuelveJsonDeTipoDeTarifa(tipo));
        stringRespuestaPOST = new ConexionPost(new TipoDeTarifaParserJson().devuelveJsonDeTipoDeTarifa(tipo),url).conectar();
        
    }
    
   
    
      public void setTabla(JTablaBasesOperativasCRUD tabla) {
        this.tabla = tabla;
    }
    



}
