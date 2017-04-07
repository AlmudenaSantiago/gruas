
package process.cargador;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import java.util.Date;

public class CargadorListaCategoria {

    private static CargadorListaCategoria cargadorListaCategoria;
    private Date fechaDesde;
    private Date fechaHasta;

    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
 

    
    public CargadorListaCategoria() {
    }

    public static CargadorListaCategoria getInstance() {
        if (cargadorListaCategoria == null) {
            cargadorListaCategoria = new CargadorListaCategoria();
        }
        return cargadorListaCategoria;
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarCategorias");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


