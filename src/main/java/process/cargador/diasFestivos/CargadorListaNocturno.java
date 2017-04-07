
package process.cargador.diasFestivos;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class CargadorListaNocturno {

    private static CargadorListaNocturno cargadorLista;
  
    private Object response;
    private Integer idCliente;
    

    
   public String cargarNocturno(Integer idCliente) {
       this.idCliente= idCliente;
        return ejecutarHttpGetNocturno();
    }
 
    
    
    public CargadorListaNocturno() {
    }

    public static CargadorListaNocturno getInstance() {
        if (cargadorLista == null) {
            cargadorLista = new CargadorListaNocturno();
        }
        return cargadorLista;
    }

 
    
    
    
    private String ejecutarHttpGetNocturno() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetNocturno()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    

     private HttpGet crearHttpGetNocturno() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarHorarioNocturnoPorCliente/" + idCliente.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
}


