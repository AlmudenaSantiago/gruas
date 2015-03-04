package process.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Producto;
import model.ProductoPedido;

import java.lang.reflect.Type;
import java.util.List;

public class ProductoParserJson {

    public List<Producto> parsear(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ProductoPedido.class, new ProductoPedidoDeserializer());
        Gson gson = gsonBuilder.create();
        final Type tipoProducto = new TypeToken<List<Producto>>(){}.getType();
        return gson.fromJson(json, tipoProducto);
    }

}
