/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador;

import exception.ExceptionHttpGet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorListaPlatosServidos {
  
    
    private static CargadorListaPlatosServidos cargadorListaPlatosServidos;
    private Date fechaDesde;
    private Date fechaHasta;

    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
    public String cargarListaPlatosServidosPorFecha (Date fechaDesde, Date fechaHasta) {
         this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
    
        return ejecutarHttpGetFechas();
    }
    

    
    public CargadorListaPlatosServidos() {
    }

    public static CargadorListaPlatosServidos getInstance() {
        if (cargadorListaPlatosServidos == null) {
            cargadorListaPlatosServidos = new CargadorListaPlatosServidos();
        }
        return cargadorListaPlatosServidos;
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
                new HttpGet("http://localhost/gruas/gruas/api/mostrarPlatosServidos");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
    
      private String ejecutarHttpGetFechas() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetFechas()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }

   private HttpGet crearHttpGetFechas() {
        String fechDesde = new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde);
        String fechHasta = new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta);
       
        String URL = "http://localhost/gruas/gruas/api/mostrarPlatosServidosPorFecha/" + fechDesde + "/" + fechHasta ;
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");

        return request;
    }
    





}


    
    
    

