
    
package process.cargador.distancias;


import process.cargador.avisos.*;
import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaDistancias {

    private static CargadorListaDistancias cargadorLista;
  
    private Object response;
    
    private Integer id;
    
  
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
   public String cargarPorId(Integer id) {
       this.id = id;
        return ejecutarHttpGetDistanciasPorId();
    }
   
    
    public CargadorListaDistancias() {
    }
    
    

    public static CargadorListaDistancias getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaDistancias();
        }
        return cargadorLista;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    
      private String ejecutarHttpGetDistanciasPorId() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetAvisosPorId()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
  
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarDistancias");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
     
     private HttpGet crearHttpGetAvisosPorId() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarDistanciasPorId/"  + id );
        request.setHeader("content-type", "application/json");
         return request;
    }
     
    
}


