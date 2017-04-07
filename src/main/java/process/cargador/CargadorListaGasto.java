
    
    package process.cargador;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CargadorListaGasto {

    private static CargadorListaGasto cargadorListaGasto;
    private Date fechaDesde;
    private Date fechaHasta;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    

    public String cargarGastosFechas (Date fechaDesde, Date fechaHasta) {
        this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
         
        return ejecutarHttpGetGastosFechas();
    }
    
    
    
    public CargadorListaGasto() {
    }

    public static CargadorListaGasto getInstance() {
        if (cargadorListaGasto == null) {
            cargadorListaGasto = new CargadorListaGasto();
        }
        return cargadorListaGasto;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
  
        
    private String ejecutarHttpGetGastosFechas() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetFechas()).getEntity());
        } catch (IOException ex) {
            System.out.println(ex);
            throw new ExceptionHttpGet();
        }
    }
 

     private HttpGet crearHttpGetFechas() {
        String fechDesde = new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde);
        String fechHasta = new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta);
        System.out.println("llega" + fechDesde + fechHasta );
        String URL = "http://localhost/gruas/gruas/api/mostrarGastosPorFecha/" + fechDesde + "/" + fechHasta ;
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
       
        return request;
    }
    

  

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarGastos");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


