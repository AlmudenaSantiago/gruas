
    
    package process.cargador.diasFestivos;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaDiasFestivos {

    private static CargadorListaDiasFestivos cargadorLista;
  
    private Object response;
    private Integer idFestivoSemanal;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
   public String cargarFestivosSemanal(Integer idCliente) {
       this.idFestivoSemanal= idCliente;
        return ejecutarHttpGetFestivosSemanal();
    }
 
    
    
    public CargadorListaDiasFestivos() {
    }

    public static CargadorListaDiasFestivos getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaDiasFestivos();
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
    
    
      private String ejecutarHttpGetFestivosSemanal() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetFestivosSemanal()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarDiasFestivos");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
     private HttpGet crearHttpGetFestivosSemanal() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarFestivoSemanal/" + idFestivoSemanal.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
}


