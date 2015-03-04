package process.cargador;

import exception.ExceptionHttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CargadorProductosPedido {

    private Integer idPedido;

    public String cargar(Integer idPedido) {
        this.idPedido = idPedido;
        return ejecutarHttpGet();
    }

    private String ejecutarHttpGet() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = crearHttpGet();

        try {
            HttpResponse response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
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
