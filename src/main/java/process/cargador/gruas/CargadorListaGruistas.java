
    
    package process.cargador.gruas;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaGruistas {

    private static CargadorListaGruistas cargadorListaGruistas;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

 
    
    
    public CargadorListaGruistas() {
    }

    public static CargadorListaGruistas getInstance() {
        if (cargadorListaGruistas == null) {
            cargadorListaGruistas = new CargadorListaGruistas();
        }
        return cargadorListaGruistas;
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarGruistas");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


