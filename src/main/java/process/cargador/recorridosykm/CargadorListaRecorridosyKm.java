
package process.cargador.recorridosykm;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaRecorridosyKm {

    private static CargadorListaRecorridosyKm cargadorLista;
  
    private Object response;
    
    public String cargar(Integer idCliente) {
        return ejecutarHttpGet(idCliente);
    }
    

 
    
    
    public CargadorListaRecorridosyKm() {
    }

    public static CargadorListaRecorridosyKm getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaRecorridosyKm();
        }
        return cargadorLista;
    }

    private String ejecutarHttpGet(Integer idCliente) {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet(idCliente)).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private HttpGet crearHttpGet(Integer idCliente) {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifasRecorridosYKm/" + idCliente.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
}


