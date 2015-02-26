package process.parser;

import com.google.gson.Gson;
import model.Pedido;

import java.util.List;

public class PedidoParserJson {

    public List<Pedido> parsear(String json){
        PedidoRespuestaJson pedidoRespuestaJson = new Gson().fromJson(json, PedidoRespuestaJson.class);
        return pedidoRespuestaJson.getPedidos();
    }

}
