package process.parser.productos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import exception.ExceptionProductoParserJson;
import java.util.List;
import model.Producto;
import model.ProductoPedido;

public class ProductoPedidoParserJson {


  public List<Producto> parsearProductoPedido(String json) {
        try {
            return getGsonProductoPedido().fromJson(json, new TypeToken<List<Producto>>() {}.getType());
             
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }


    private Gson getGsonProductoPedido() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ProductoPedido.class, new ProductoPedidoDeserializer());
        return gsonBuilder.create();
    }
    
    
    
    public String devuelveJsonDeProductoPedido(ProductoPedido productoPedido) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(productoPedido);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }


}






