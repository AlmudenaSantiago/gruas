/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador;

import exception.ExceptionHttpGet;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


 
public class CargadorListaPoblaciones {

      private static CargadorListaPoblaciones cargadorListaPoblaciones;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

       public String cargarPoblacionPorCp(String cp) {
        return ejecutarHttpGetPoblacionPorCp(cp);
    }
    



    public static CargadorListaPoblaciones getInstance() {
        if (cargadorListaPoblaciones == null) {
            cargadorListaPoblaciones = new CargadorListaPoblaciones();
        }
        return cargadorListaPoblaciones;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    
      private String ejecutarHttpGetPoblacionPorCp(String cp) {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetPoblacionPorCp(cp)).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      
      private HttpGet crearHttpGetPoblacionPorCp(String cp) {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarPoblacionPorCp/" + cp);
        request.setHeader("content-type", "application/json");
         return request;
    }
      
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarPoblaciones");
        request.setHeader("content-type", "application/json");
         return request;
    }

    
}
