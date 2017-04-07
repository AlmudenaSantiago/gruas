package process.cargador.tarifas;

import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorListaTarifasNocturno {
  
    
    private static CargadorListaTarifasNocturno cargadorListaTarifas;
    private Object response;
    private Integer id;
    

    
         
       public String cargarTarifasNocturno(Integer idCliente) throws IOException {
        return ejecutarHttpGetNocturno(idCliente);
    }
      
       public String cargarTarifasNocturnoPorTipo(Integer idCliente, Integer idServicioTipo) throws IOException {
        return ejecutarHttpGetNocturnoPorTipo(idCliente, idServicioTipo);
    }
       
       
    
      public String cargarColor(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetTarifaPorId();
    }
    
 
    
    public CargadorListaTarifasNocturno() {
    }

    public static CargadorListaTarifasNocturno getInstance() {
        if (cargadorListaTarifas == null) {
            cargadorListaTarifas = new CargadorListaTarifasNocturno();
        }
        return cargadorListaTarifas;
    }
    
   
    private String ejecutarHttpGetNocturno(Integer idCliente) throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetNocturno(idCliente)).getEntity());
    }
     private String ejecutarHttpGetNocturnoPorTipo(Integer idCliente, Integer idServicioTipo) throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetNocturnoPorTipo(idCliente, idServicioTipo)).getEntity());
    }
    
      private String ejecutarHttpGetTarifaPorId() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetTarifaNocturnoPorId()).getEntity());
    }
    
    
   
    private HttpGet crearHttpGetTarifaNocturnoPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifaNocturnoPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   
  private HttpGet crearHttpGetNocturnoPorTipo(Integer idCliente, Integer idServicioTipo) {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifasNocturnoPorTipo/" + idCliente.toString() + "/" +  idServicioTipo.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }   
     
      private HttpGet crearHttpGetNocturno(Integer idCliente) {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifasNocturno/" + idCliente.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }   
     

    
}
