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
public class CargadorListaMarca {
    
    private static CargadorListaMarca cargadorListaMarca;

    private Object response;
    private Integer id;
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
    
      public String cargarMarca(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetMarcaPorId();
    }
    
 
    
    public CargadorListaMarca() {
    }

    public static CargadorListaMarca getInstance() {
        if (cargadorListaMarca == null) {
            cargadorListaMarca = new CargadorListaMarca();
        }
        return cargadorListaMarca;
    }

    private String ejecutarHttpGet() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
    }
    
      private String ejecutarHttpGetMarcaPorId() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetMarcaPorId()).getEntity());
    }
    
    
   
    private HttpGet crearHttpGetMarcaPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarMarcaPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarMarcas");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


