package process.parser;

import com.google.gson.Gson;
import exception.ExceptionPedidoParserJson;
import model.Pedido;

import java.util.List;

public class PedidoParserJson {

    public List<Pedido> parsear(String json){
        try{
            PedidoRespuestaJson pedidoRespuestaJson = new Gson().fromJson(json, PedidoRespuestaJson.class);
            return pedidoRespuestaJson.getPedidos();
        }
        catch(Exception exception){
            throw new ExceptionPedidoParserJson();
        }
    }

}
