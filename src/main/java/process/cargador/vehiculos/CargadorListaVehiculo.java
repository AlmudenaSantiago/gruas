/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador.vehiculos;

import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Aarón
 */
public class CargadorListaVehiculo {
    
    private static CargadorListaVehiculo cargadorListaVehiculo;

    private Object response;
    private Integer id;
    private Integer idMarca;
    private Integer idModelo;
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
    
      public String cargarVehiculosPorId(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetVehiculoPorId();
    }
    
    public String cargarVehiculosPorMarca(Integer idMarca) throws IOException {
        this.idMarca = idMarca;
        return ejecutarHttpGetVehiculoPorMarca();
    }
      public String cargarVehiculosPorMarcaYModelo(Integer idMarca, Integer idModelo) throws IOException {
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        return ejecutarHttpGetVehiculoPorMarcaYModelo();
    } 
    
    public CargadorListaVehiculo() {
    }

    public static CargadorListaVehiculo getInstance() {
        if (cargadorListaVehiculo == null) {
            cargadorListaVehiculo = new CargadorListaVehiculo();
        }
        return cargadorListaVehiculo;
    }

    private String ejecutarHttpGet() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
    }
    
      private String ejecutarHttpGetVehiculoPorId() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetVehiculoPorId()).getEntity());
    }
      
     private String ejecutarHttpGetVehiculoPorMarca() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetVehiculoPorMarca()).getEntity());
    }
       
     private String ejecutarHttpGetVehiculoPorMarcaYModelo() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetVehiculoPorMarcaYModelo()).getEntity());
    }
    
   
    private HttpGet crearHttpGetVehiculoPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarVehiculoPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   
    
    
   private HttpGet crearHttpGetVehiculoPorMarca() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarVehiculosPorMarca/" + idMarca.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   
     private HttpGet crearHttpGetVehiculoPorMarcaYModelo() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarVehiculosPorMarcaYModelo/" + idMarca.toString() +  "/" +  idModelo.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarVehiculos");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


