
    
package process.cargador.avisos;


import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaAvisos {

    private static CargadorListaAvisos cargadorLista;
  
    private Object response;
    
    
    private Integer idCliente;
    private Integer poliza;
    private String expediente;
    private String matricula;
    private Integer id;
    
  
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
  
    public String cargarAvisosPorCliente(Integer idCliente) {
        this.idCliente = idCliente;
        return ejecutarHttpGetAvisosPorCliente();
    }
    
   
  
    public String cargarAvisosPorPoliza(Integer poliza) {
        this.poliza= poliza;
        return ejecutarHttpGetAvisosPorPoliza();
    }
     
     
     
     
     public String cargarAvisosPorExpediente(String expediente) {
        this.expediente = expediente;
        return ejecutarHttpGetAvisosPorExpediente();
    }
     
     
     public String cargarAvisosPorId(Integer id) {
        this.id= id;
        return ejecutarHttpGetAvisosPorId();
    }
    
      public String cargarAvisosPorMatricula(String matricula) {
        this.matricula = matricula;
        return ejecutarHttpGetAvisosPorMatricula();
    }
    
    
    public CargadorListaAvisos() {
    }

    public static CargadorListaAvisos getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaAvisos();
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
    
      private String ejecutarHttpGetAvisosPorCliente() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetAvisosPorCliente()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
          private String ejecutarHttpGetAvisosPorPoliza() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetAvisosPorPoliza()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
        private String ejecutarHttpGetAvisosPorExpediente() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetAvisosPorExpediente()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      
      private String ejecutarHttpGetAvisosPorId() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetAvisosPorId()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private String ejecutarHttpGetAvisosPorMatricula() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetAvisosPorMatricula()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarAvisos");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
     private HttpGet crearHttpGetAvisosPorCliente() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarAvisosPorCliente/" + idCliente.toString() );
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    private HttpGet crearHttpGetAvisosPorPoliza() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarAvisosPorPoliza/"  + poliza.toString() );
        request.setHeader("content-type", "application/json");
         return request;
    }
        
    private HttpGet crearHttpGetAvisosPorMatricula() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarAvisosPorMatricula/"  + matricula );
        request.setHeader("content-type", "application/json");
         return request;
    }
     
    private HttpGet crearHttpGetAvisosPorExpediente() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarAvisosPorExpediente/"  + expediente );
        request.setHeader("content-type", "application/json");
         return request;
    }
     
     private HttpGet crearHttpGetAvisosPorId() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarAvisosPorId/"  + id );
        request.setHeader("content-type", "application/json");
         return request;
    }
     
    
}


