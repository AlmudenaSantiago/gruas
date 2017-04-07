/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDMarcas;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import model.vehiculos.Marca;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.limpiador.LimpiarTexto;
import process.parser.vehiculos.MarcaParserJson;



public class ModificarMarca extends Modificar {
    
    Marca marca;
    String base = "http://localhost/gruas/gruas/api/";
    URL url;
    String stringRespuestaPOST;
       
   
    public ModificarMarca(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarMarca () throws IOException {
            preparar();
            enviarModificacion();
         
    }
    
    public void preparar() throws MalformedURLException {
        marca = new Marca();
        marca.setId(id);
        marca.setMarca(new LimpiarTexto().limpiar(valor));
        url = new URL(base + "modificarMarca");
                     
    }
    
    public void enviarModificacion() throws IOException {
        stringRespuestaPOST = new ConexionPost(new MarcaParserJson().devuelveJsonDeProveedor(marca),url).conectar();
    }
    
   
    



}
