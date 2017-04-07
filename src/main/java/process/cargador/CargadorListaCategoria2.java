
package process.cargador;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import java.util.Date;

public class CargadorListaCategoria2 {

    private static CargadorListaCategoria2 cargadorListaCategoria;
    private Date fechaDesde;
    private Date fechaHasta;

    private Object response;
    private Integer id;
    
    public String cargar(Integer id) {
        this.id = id;
        return ejecutarHttpGet();
    }
    
 

    
    public CargadorListaCategoria2() {
    }

    public static CargadorListaCategoria2 getInstance() {
        if (cargadorListaCategoria == null) {
            cargadorListaCategoria = new CargadorListaCategoria2();
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
        HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarCategorias2/" + id.toString());
        request.setHeader("content-type", "application/json");
        return request;
    }
    
  


  
}


