/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador.vehiculos;

import process.cargador.*;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Aarón
 */
public class CargadorListaModelo {
    
    private static CargadorListaModelo cargadorListaModelo;

    private Object response;
    private Integer id;
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
    
      public String cargarModelosPorIdMarcaPadre(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetModeloPorIdMarcaPadre();
    }
    
 
    
    public CargadorListaModelo() {
    }

    public static CargadorListaModelo getInstance() {
        if (cargadorListaModelo == null) {
            cargadorListaModelo = new CargadorListaModelo();
        }
        return cargadorListaModelo;
    }

    private String ejecutarHttpGet() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
    }
    
      private String ejecutarHttpGetModeloPorIdMarcaPadre() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetModeloPorIdMarcaPadre()).getEntity());
    }
    
    
   
    private HttpGet crearHttpGetModeloPorIdMarcaPadre() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarModelosPorIdMarcaPadre/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarModelos");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


