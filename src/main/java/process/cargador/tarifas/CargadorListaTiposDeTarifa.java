/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador.tarifas;

import exception.ExceptionHttpGet;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorListaTiposDeTarifa {

    private static CargadorListaTiposDeTarifa cargadorLista;
  
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

 
    
    
    public CargadorListaTiposDeTarifa() {
    }

    public static CargadorListaTiposDeTarifa getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaTiposDeTarifa();
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTiposDeTarifas");
        request.setHeader("content-type", "application/json");
         return request;
    }

    
}
