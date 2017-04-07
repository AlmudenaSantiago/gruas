
    
    package process.cargador.averias;

import process.cargador.gruas.*;
import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaAverias {

    private static CargadorListaAverias cargadorListaAverias;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

 
    
    
    public CargadorListaAverias() {
    }

    public static CargadorListaAverias getInstance() {
        if (cargadorListaAverias == null) {
            cargadorListaAverias = new CargadorListaAverias();
        }
        return cargadorListaAverias;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarAverias");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


