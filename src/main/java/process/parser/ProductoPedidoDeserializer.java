package process.parser;

import com.google.gson.*;
import model.ProductoPedido;

import java.lang.reflect.Type;

public class ProductoPedidoDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ProductoPedido productoPedido = new ProductoPedido();

        JsonObject jsonObject = (JsonObject)jsonElement;
        productoPedido.setProducto_id(jsonObject.get("producto_id").getAsInt());
        productoPedido.setPedido_id(jsonObject.get("pedido_id").getAsInt());
        productoPedido.setCantidad(jsonObject.get("cantidad").getAsDouble());

        return productoPedido;
    }
}
