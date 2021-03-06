
package process.cargador;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import java.util.Date;

public class CargadorListaCategoria3 {

     private static CargadorListaCategoria3 cargadorListaCategoria;
    private Date fechaDesde;
    private Date fechaHasta;

    private Object response;
    private Integer id;

   
    
    public String cargar(Integer id) {
        this.id = id;
        return ejecutarHttpGet();
    }
    
 

    
    public CargadorListaCategoria3() {
    }

    public static CargadorListaCategoria3 getInstance() {
        if (cargadorListaCategoria == null) {
            cargadorListaCategoria = new CargadorListaCategoria3();
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
        HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarCategorias3/" + id.toString());
        request.setHeader("content-type", "application/json");
        return request;
    }
}
    
