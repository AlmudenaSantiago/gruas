
package process.cargador;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class CargadorListaCliente {

    private static CargadorListaCliente cargadorListaCliente;
    private Integer id;

    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
    
    public String cargarClientesBaja() {
        return ejecutarHttpGetClientesBaja();
    }
    
   public String cargar(Integer id) {
       this.id = id; 
       return ejecutarHttpGetClientePorId();
    }
    
  public String cargarObservaciones(Integer id) {
       this.id = id; 
       return ejecutarHttpGetObservaciones();
    }

    public String cargarIdUltimoCliente() {
       return ejecutarHttpGetIdUltimoCliente();
    
    
    }
  
    
    
     public String cargarIdPrimerCliente() {
       return ejecutarHttpGetIdPrimerCliente();
    
    
    }
    
    
    public String cargarSiguienteId(Integer id) {
      return ejecutarHttpGetSiguienteId(id);
    
    }
    
    
     public String cargarAnteriorId(Integer id) {
      return ejecutarHttpGetAnteriorId(id);
    
    }
    
    
    
     
     
  public String cargarTotalClientes() {
     return ejecutarHttpGetTotalClientes();
  }
    public CargadorListaCliente() {
    }
    
    public static CargadorListaCliente getInstance() {
        if (cargadorListaCliente == null) {
            cargadorListaCliente = new CargadorListaCliente();
        }
        return cargadorListaCliente;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      private String ejecutarHttpGetClientesBaja() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetClientesBaja()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
      private String ejecutarHttpGetIdUltimoCliente() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetIdUltimoCliente()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      
      
      
         
      private String ejecutarHttpGetIdPrimerCliente() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetIdPrimerCliente()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
     private String ejecutarHttpGetTotalClientes() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetTotalClientes()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
     private String ejecutarHttpGetClientePorId() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetClientePorId()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
     
    private String ejecutarHttpGetObservaciones() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetObservaciones()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
     private String ejecutarHttpGetAnteriorId(Integer id) {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetAnteriorId(id)).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
       private String ejecutarHttpGetSiguienteId(Integer id) {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetSiguienteId(id)).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
 
    private HttpGet crearHttpGet() {
       
        HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarClientes");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    private HttpGet crearHttpGetClientesBaja() {
       
        HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarClientesBaja");
        request.setHeader("content-type", "application/json");
        System.out.println("RESPUESTA DEL SERVIDOR"  + request);
         return request;
    }
    
   
    
     private HttpGet crearHttpGetIdUltimoCliente() {
     
        HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarIdUltimoCliente");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
    private HttpGet crearHttpGetClientePorId() {
         HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarClientePorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
    
       private HttpGet crearHttpGetIdPrimerCliente() {
         HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarIdPrimerCliente");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
    private HttpGet crearHttpGetAnteriorId(Integer id) {
         HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarAnteriorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    private HttpGet crearHttpGetSiguienteId(Integer id) {
         HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarSiguienteId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
    
    private HttpGet crearHttpGetObservaciones() {
         HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarObservacionesCliente/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    private HttpGet crearHttpGetTotalClientes() {
         HttpGet request =  new HttpGet("http://localhost/gruas/gruas/api/mostrarTotalClientes");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
}

