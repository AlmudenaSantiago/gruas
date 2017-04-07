
    
    package process.cargador.contactos;

import process.cargador.diasFestivos.*;
import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaContacto {

    private static CargadorListaContacto cargadorLista;
  
    private Object response;
    
    
    private Integer idCliente;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
   public String cargarContactosPorCliente(Integer idCliente) {
        this.idCliente= idCliente;
        return ejecutarHttpGetContactosPorCliente();
    }
    
    
    
    public CargadorListaContacto() {
    }

    public static CargadorListaContacto getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaContacto();
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
    
      private String ejecutarHttpGetContactosPorCliente() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetContactosPorCliente()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarContactos");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
     private HttpGet crearHttpGetContactosPorCliente() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/listaContactosCliente/" + idCliente );
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
}


