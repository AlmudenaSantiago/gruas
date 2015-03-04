package process.cargador;

import exception.ExceptionHttpGet;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CargadorProductosPedido {

    private static CargadorProductosPedido cargadorProductosPedido;
    private Integer idPedido;

    private CargadorProductosPedido() {
    }

    public static CargadorProductosPedido getInstance() {
        if (cargadorProductosPedido == null) {
            cargadorProductosPedido = new CargadorProductosPedido();
        }
        return cargadorProductosPedido;
    }

    public String cargar(Integer idPedido) {
        this.idPedido = idPedido;
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
        HttpGet request =
                new HttpGet("http://doramas.loquatsolutions.com/api/listaProductosPedido/" + idPedido.toString());
        request.setHeader("content-type", "application/json");
        return request;
    }
}
