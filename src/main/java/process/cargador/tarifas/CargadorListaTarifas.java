package process.cargador.tarifas;

import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorListaTarifas {
  
    
    private static CargadorListaTarifas cargadorListaTarifas;
    private Object response;
    private Integer id;
    
    public String cargarTarifasPorTiempoDeRespuesta(Integer idCliente) throws IOException {
        return ejecutarHttpGetPorTiempoDeRespuesta(idCliente);
    }
    
    
       public String cargarTarifas(Integer idCliente) throws IOException {
        return ejecutarHttpGet(idCliente);
    }
      public String cargarTarifasPorTipo(Integer idCliente, Integer idServicioTipo) throws IOException {
        return ejecutarHttpGetPorTipo(idCliente, idServicioTipo);
    }
    
         
       public String cargarTarifasNocturno(Integer idCliente) throws IOException {
        return ejecutarHttpGetNocturno(idCliente);
    }
    
       
       
    
      public String cargarColor(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetTarifaPorId();
    }
    
 
    
    public CargadorListaTarifas() {
    }

    public static CargadorListaTarifas getInstance() {
        if (cargadorListaTarifas == null) {
            cargadorListaTarifas = new CargadorListaTarifas();
        }
        return cargadorListaTarifas;
    }
    
    
    private String ejecutarHttpGet(Integer idCliente) throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet(idCliente)).getEntity());
    }
    
     private String ejecutarHttpGetPorTipo(Integer idCliente, Integer idServicioTipo) throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetPorTipo(idCliente, idServicioTipo)).getEntity());
    }
    
    private String ejecutarHttpGetNocturno(Integer idCliente) throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetNocturno(idCliente)).getEntity());
    }
    
    
    
    
    private String ejecutarHttpGetPorTiempoDeRespuesta(Integer idCliente) throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetPorTiempoDeRespuesta(idCliente)).getEntity());
    }
    
    
 
      private String ejecutarHttpGetTarifaPorId() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetTarifaPorId()).getEntity());
    }
    
    
      
      
      
   
    private HttpGet crearHttpGetTarifaPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifaPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   
     private HttpGet crearHttpGet(Integer idCliente) {
       System.out.println("cargador tarifas");
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifas/" + idCliente.toString());
        request.setHeader("content-type", "application/json");
        System.out.println("respuesta request" + request);
         return request;
    }   
    private HttpGet crearHttpGetPorTipo(Integer idCliente, Integer idServicioTipo) {
       System.out.println("cargador tarifas" + idCliente + idServicioTipo);
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifasPorTipo/" + idCliente.toString() + "/" + idServicioTipo.toString());
        request.setHeader("content-type", "application/json");
        System.out.println("respuesta request" + request);
         return request;
    }  
     
      private HttpGet crearHttpGetNocturno(Integer idCliente) {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifasNocturno/" + idCliente.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }   
     

    private HttpGet crearHttpGetPorTiempoDeRespuesta(Integer idCliente) {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarTarifasPorTiempoDeRespuesta/" +  idCliente.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }    
}
