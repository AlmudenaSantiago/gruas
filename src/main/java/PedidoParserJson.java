
import com.google.gson.Gson;

import java.util.List;

public class PedidoParserJson {

    public List<Pedido> parsear(String json){
        PedidoRespuestaJson pedidoRespuestaJson = new Gson().fromJson(json, PedidoRespuestaJson.class);
        return pedidoRespuestaJson.getPedidos();
    }

}
