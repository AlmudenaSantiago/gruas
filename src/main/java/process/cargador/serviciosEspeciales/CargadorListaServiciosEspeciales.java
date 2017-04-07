package process.cargador.serviciosEspeciales;

import process.cargador.vehiculos.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorListaServiciosEspeciales {
  private static CargadorListaServiciosEspeciales cargadorLista;

    private Object response;
    private Integer id;
    private Integer idCliente;
    
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
    
      public String cargarServicioEspecialPorId(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetColorPorId();
    }
    
      
    public String cargarServicioEspecialPorCliente(Integer idCliente) throws IOException {
        this.idCliente = idCliente;
        return ejecutarHttpGetServicioEspeicalPorCliente();
    }
    
      
 
    
    public CargadorListaServiciosEspeciales() {
    }

    public static CargadorListaServiciosEspeciales getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaServiciosEspeciales();
        }
        return cargadorLista;
    }

    private String ejecutarHttpGet() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
    }
    
      private String ejecutarHttpGetColorPorId() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetColorPorId()).getEntity());
    }
    
      
   
    private String ejecutarHttpGetServicioEspeicalPorCliente() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetServicioEspecialPorIdCliente()).getEntity());
    }
    
    
    
   
    private HttpGet crearHttpGetColorPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarServicioEspecialPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   

    private HttpGet crearHttpGetServicioEspecialPorIdCliente() {
        
        
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarServiciosEspecialesPorIdCliente/" + idCliente.toString());
        request.setHeader("content-type", "application/json");
        
         return request;
    }
   
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarServiciosEspeciales");
        request.setHeader("content-type", "application/json");
         return request;
    }    
}
