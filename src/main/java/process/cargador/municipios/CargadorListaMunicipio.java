
package process.cargador.municipios;

import process.cargador.recorridosykm.*;
import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaMunicipio {

    private static CargadorListaMunicipio cargadorLista;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

    public static CargadorListaMunicipio getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaMunicipio();
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarMunicipios");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


