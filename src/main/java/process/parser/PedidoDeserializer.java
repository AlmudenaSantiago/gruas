package process.parser;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Pedido;

public class PedidoDeserializer implements JsonDeserializer {
    
        @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Pedido nuevoPedido = new Pedido();
        JsonObject jsonObject = (JsonObject)jsonElement;
        jsonObject = jsonObject.get("pedido").getAsJsonObject();
        nuevoPedido.setId(jsonObject.get("id").getAsInt());
        nuevoPedido.setUsuario(jsonObject.get("usuario").getAsString());
        nuevoPedido.setCliente(jsonObject.get("cliente").getAsString());
        nuevoPedido.setEstado(jsonObject.get("estado").getAsString());
        nuevoPedido.setImporte(jsonObject.get("importe").getAsDouble());

        return  nuevoPedido;
    }
    
    }
