/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador;

import exception.ExceptionHttpGet;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author loquat
 */
public class CargadorListaProvincias {
    
     private static CargadorListaProvincias cargadorListaProvincias;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

       public String cargarProvinciaPorId(String id){
        return ejecutarHttpGetProvincianPorId(id);
    }
    



    public static CargadorListaProvincias getInstance() {
        if (cargadorListaProvincias == null) {
            cargadorListaProvincias = new CargadorListaProvincias();
        }
        return cargadorListaProvincias;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    
      private String ejecutarHttpGetProvincianPorId(String id) {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetProvinciaPorId(id)).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      
      private HttpGet crearHttpGetProvinciaPorId(String id) {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarProvinciaPorId/" + id);
        request.setHeader("content-type", "application/json");
         return request;
    }
      
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarProvincias");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
}
