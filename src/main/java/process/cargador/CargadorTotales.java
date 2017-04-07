
package process.cargador;

import exception.ExceptionHttpGet;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorTotales {
    
    private static CargadorTotales cargadorTotales;
    private Integer id;
  
     public String cargarTotalCantidadPendiente(Integer id) {
           this.id= id;
           return ejecutarHttpGetTotalCantidadPendiente();
    }
     
     
     public String cargarTotalCantidadAbonada(Integer id) {
           this.id= id;
           return ejecutarHttpGetTotalCantidadAbonada();
    }
     
    
    
    public String cargarTotalIngresos() {

           return ejecutarHttpGet();
    }
 
    public String cargarTotalGastos() {

           return ejecutarHttpGetGastos();
    }
     

    public CargadorTotales() {
    }

    public static CargadorTotales getInstance() {
        if (cargadorTotales == null) {
            cargadorTotales = new CargadorTotales();
        }
        return cargadorTotales;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
     private String ejecutarHttpGetTotalCantidadPendiente() {
        try {
           
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetTotalCantidadPendiente()).getEntity());
            
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
     private String ejecutarHttpGetTotalCantidadAbonada() {
        try {
           
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetTotalCantidadAbonada()).getEntity());
            
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
     private String ejecutarHttpGetGastos() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetGastos()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }

     
      private HttpGet crearHttpGetTotalCantidadPendiente() {
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTotalCantidadPendiente/" +  id.toString());
        request.setHeader("content-type", "application/json");
        return request;
    }
      
     private HttpGet crearHttpGetTotalCantidadAbonada() {
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTotalCantidadAbonada/" +  id.toString());
        request.setHeader("content-type", "application/json");
        return request;
    }
     
     
     private HttpGet crearHttpGetGastos() {
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTotalGastos");
        request.setHeader("content-type", "application/json");
         return request;
    }

    private HttpGet crearHttpGet() {
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTotalPedidos");
        request.setHeader("content-type", "application/json");
         return request;
    }
}

    
 