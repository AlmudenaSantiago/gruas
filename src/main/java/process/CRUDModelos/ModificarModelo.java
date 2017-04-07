/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDModelos;

import process.CRUDModelos.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import model.vehiculos.Modelo;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.limpiador.LimpiarTexto;
import process.parser.vehiculos.ModeloParserJson;
import view.basesOperativas.JTablaBasesOperativasCRUD;



public class ModificarModelo extends Modificar {
    
    Modelo modelo;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
       
   
    public ModificarModelo(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarModelo () throws IOException {
            preparar();
            enviarModificacion();
         
    }
    
    public void preparar() throws MalformedURLException {
        modelo = new Modelo();
        modelo.setId(id);
        modelo.setModelo(new LimpiarTexto().limpiar(valor));
        url = new URL(base + "modificarModelo");
                     
    }
    
    public void enviarModificacion() throws IOException {
        stringRespuestaPOST = new ConexionPost(new ModeloParserJson().devuelveJsonDeProveedor(modelo),url).conectar();
    }
    
   
    



}
