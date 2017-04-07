/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDMunicipios;

import process.CRUDTiposDeTarifa.*;
import command.basesOperativas.CargarBasesOperativasCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Municipio;
import model.Tarifas.TipoDeTarifa;
import model.basesOperativas.BaseOperativa;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.CargadorListaBasesOperativas;
import process.limpiador.LimpiarTexto;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.municipios.MunicipioParserJson;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;
import view.basesOperativas.JTablaBasesOperativasCRUD;
import view.fichaCliente.SingletonCliente;



public class ModificarMunicipio extends Modificar {
    
    Municipio municipio;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
    
    private JTablaBasesOperativasCRUD tabla;
       
   
    public ModificarMunicipio(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificar () throws IOException {
            preparar();
            enviarModificacion();
     //       actualizarTabla();
    }
    
    public void preparar() throws MalformedURLException {
        municipio = new Municipio();
        municipio.setId(id);
        municipio.setMunicipio(new LimpiarTexto().limpiar(valor));
        url = new URL(base + "modificarMunicipio");
                     
    }
    
    public void enviarModificacion() throws IOException {
        
        stringRespuestaPOST = new ConexionPost(new MunicipioParserJson().devuelveJsonDeMunicipio(municipio),url).conectar();
        System.out.println(stringRespuestaPOST);
    }
    
   
    
      public void setTabla(JTablaBasesOperativasCRUD tabla) {
        this.tabla = tabla;
    }
    



}
