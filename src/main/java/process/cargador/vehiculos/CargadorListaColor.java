package process.cargador.vehiculos;

import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CargadorListaColor {
  private static CargadorListaColor cargadorListaColor;

    private Object response;
    private Integer id;
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
    
      public String cargarColor(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetColorPorId();
    }
    
 
    
    public CargadorListaColor() {
    }

    public static CargadorListaColor getInstance() {
        if (cargadorListaColor == null) {
            cargadorListaColor = new CargadorListaColor();
        }
        return cargadorListaColor;
    }

    private String ejecutarHttpGet() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
    }
    
      private String ejecutarHttpGetColorPorId() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetColorPorId()).getEntity());
    }
    
    
   
    private HttpGet crearHttpGetColorPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarColorPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarColores");
        request.setHeader("content-type", "application/json");
         return request;
    }    
}
