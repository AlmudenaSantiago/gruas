
    
package process.cargador.averias;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaNumRespuesta {

    private static CargadorListaNumRespuesta cargador;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
    public CargadorListaNumRespuesta() {
    }

    public static CargadorListaNumRespuesta getInstance() {
        if (cargador == null) {
            cargador = new CargadorListaNumRespuesta();
        }
        return cargador;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarNumRespuesta");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


