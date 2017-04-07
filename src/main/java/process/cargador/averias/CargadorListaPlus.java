
    
    package process.cargador.averias;

import process.cargador.gruas.*;
import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaPlus {

    private static CargadorListaPlus cargadorLista;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

 
    
    
    public CargadorListaPlus() {
    }

    public static CargadorListaPlus getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaPlus();
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
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarPlus");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


