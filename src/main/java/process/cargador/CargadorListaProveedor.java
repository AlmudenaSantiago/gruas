/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador;

import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Aarón
 */
public class CargadorListaProveedor {
    
    private static CargadorListaProveedor cargadorListaProveedor;

    private Object response;
    private Integer id;
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
    
      public String cargarProveedor(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetProveedorPorId();
    }
    
 
    
    public CargadorListaProveedor() {
    }

    public static CargadorListaProveedor getInstance() {
        if (cargadorListaProveedor == null) {
            cargadorListaProveedor = new CargadorListaProveedor();
        }
        return cargadorListaProveedor;
    }

    private String ejecutarHttpGet() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
    }
    
      private String ejecutarHttpGetProveedorPorId() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetProveedorPorId()).getEntity());
    }
    
    
   
    private HttpGet crearHttpGetProveedorPorId() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarProveedorPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }
   

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarProveedores");
        request.setHeader("content-type", "application/json");
         return request;
    }
}


