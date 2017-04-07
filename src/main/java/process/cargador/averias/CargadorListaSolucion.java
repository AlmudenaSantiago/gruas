
    
    package process.cargador.averias;


import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaSolucion {

    private static CargadorListaSolucion cargadorListaAverias;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

 
    
    
    public CargadorListaSolucion() {
    }

    public static CargadorListaSolucion getInstance() {
        if (cargadorListaAverias == null) {
            cargadorListaAverias = new CargadorListaSolucion();
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarSoluciones");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


