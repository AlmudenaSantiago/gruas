
    
    package process.cargador.gruas;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaGruas {

    private static CargadorListaGruas cargadorListaGruas;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

 
    
    
    public CargadorListaGruas() {
    }

    public static CargadorListaGruas getInstance() {
        if (cargadorListaGruas == null) {
            cargadorListaGruas = new CargadorListaGruas();
        }
        return cargadorListaGruas;
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarGruas");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


