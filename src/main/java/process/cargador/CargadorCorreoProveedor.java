/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.cargador;


import exception.ExceptionHttpGet;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CargadorCorreoProveedor {

    private static CargadorCorreoProveedor cargador;
    private Integer idProveedor;

     

    public static CargadorCorreoProveedor getInstance() {
        if (cargador == null) {
            cargador = new CargadorCorreoProveedor();
          }
         return cargador;
    }

    public String cargar(Integer idProveedor) {
        this.idProveedor = idProveedor;
        return ejecutarHttpGet();
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }

    private HttpGet crearHttpGet() {
        HttpGet request = new HttpGet("http://localhost/gruas/gruas/api/mostrarCorreoProveedor/" + idProveedor.toString());
        request.setHeader("content-type", "application/json");
        return request;
    }
}

