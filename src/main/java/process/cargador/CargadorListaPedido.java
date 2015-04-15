package process.cargador;

import exception.ExceptionHttpGet;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CargadorListaPedido {

    private static CargadorListaPedido cargadorListaPedido;

    public String cargar() {
        return ejecutarHttpGet();
    }

    private CargadorListaPedido() {
    }

    public static CargadorListaPedido getInstance() {
        if (cargadorListaPedido == null) {
            cargadorListaPedido = new CargadorListaPedido();
        }
        return cargadorListaPedido;
    }

    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }

    private HttpGet crearHttpGet() {
        HttpGet request =
                new HttpGet("http://doramas.loquatsolutions.com/api/mostrarPedidos");
        request.setHeader("content-type", "application/json");
        return request;
    }
}
