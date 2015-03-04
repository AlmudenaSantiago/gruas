package process.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import model.Producto;
import model.ProductoPedido;

import java.lang.reflect.Type;
import java.util.List;

public class ProductoParserJson {

    public List<Producto> parsear(String json) {
        try {
            return getGson().fromJson(json, new TypeToken<List<Producto>>() {}.getType());
        }
        catch (Exception exception){
            throw new ExceptionProductoParserJson();
        }
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ProductoPedido.class, new ProductoPedidoDeserializer());
        return gsonBuilder.create();
    }

}
