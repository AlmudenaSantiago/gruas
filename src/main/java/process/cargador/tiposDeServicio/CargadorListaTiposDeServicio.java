/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador.tiposDeServicio;

import exception.ExceptionHttpGet;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import process.cargador.averias.CargadorListaSolucion;


public class CargadorListaTiposDeServicio {

    private static CargadorListaTiposDeServicio cargadorLista;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

 
    
    
    public CargadorListaTiposDeServicio() {
    }

    public static CargadorListaTiposDeServicio getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaTiposDeServicio();
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTiposDeServicio");
        request.setHeader("content-type", "application/json");
         return request;
    }

    
}
