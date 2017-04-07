package process.cargador;

import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorListaBasesOperativas {
  private static CargadorListaBasesOperativas cargadorLista;

    private Object response;
    private Integer id;
    private Integer idCliente;
    
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
    
      public String cargarBaseOperativaPorId(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetColorPorId();
    }
    
      
    public String cargarBaseOperativaPorCliente(Integer idCliente) throws IOException {
        this.idCliente = idCliente;
        return ejecutarHttpGetServicioEspeicalPorCliente();
    }
    
      
 
    
    public CargadorListaBasesOperativas() {
    }

    public static CargadorListaBasesOperativas getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaBasesOperativas();
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
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetBaseOperativaPorId()).getEntity());
    }
    
    
    
   
    private HttpGet crearHttpGetColorPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarBaseOperativaPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   

    private HttpGet crearHttpGetBaseOperativaPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarBasesOperativasPorCliente/" + idCliente.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   
    
    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarBasesOperativas");
        request.setHeader("content-type", "application/json");
         return request;
    }    
}
