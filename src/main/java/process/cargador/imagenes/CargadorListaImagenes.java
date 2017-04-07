
package process.cargador.imagenes;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaImagenes {

    private static CargadorListaImagenes cargadorLista;
  
    private Object response;
    private Integer idAviso;
    
    
    public String cargarImagenesAviso(Integer id) {
        this.idAviso = id;
        return ejecutarHttpGetImagenesAviso();
    }
    

    public static CargadorListaImagenes getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaImagenes();
        }
        return cargadorLista;
    }

    private String ejecutarHttpGetImagenesAviso() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetImagenesAviso()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private HttpGet crearHttpGetImagenesAviso() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarImagenesAviso/" + idAviso);
        request.setHeader("content-type", "application/json");
         return request;
    }
}


