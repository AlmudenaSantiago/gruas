package process.parser.pedidos;

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
        nuevoPedido.setEstado(jsonObject.get("estado").getAsString());
        nuevoPedido.setImporte(jsonObject.get("importe").getAsDouble());
        nuevoPedido.setClienteId(jsonObject.get("clienteId").getAsInt());
        nuevoPedido.setCliente(jsonObject.get("cliente").getAsString());
        nuevoPedido.setVendedor(jsonObject.get("vendedor").getAsString());
        nuevoPedido.setDescuento(jsonObject.get("descuento").getAsString());
        nuevoPedido.setCantidadPendiente(jsonObject.get("cantidadPendiente").getAsDouble());
   
        return  nuevoPedido;
    }
    
    }
