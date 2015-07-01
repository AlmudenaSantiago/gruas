package process.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionPedidoParserJson;
import exception.ExceptionProductoParserJson;
import java.net.URLEncoder;
import model.Pedido;

import java.util.List;
import model.Producto;
import model.ProductoPedido;


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
    
       public Pedido parsearPedido(String json) {
        try {
            return getGsonPedido().fromJson(json, new TypeToken<Pedido>() {}.getType());
        }
        catch (Exception exception){
            throw new ExceptionProductoParserJson();
        }
    }

    
     private Gson getGsonPedido() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Pedido.class, new PedidoDeserializer());
        return gsonBuilder.create();
    }
    
       public String devuelveJsonDePedido(Pedido pedido) {    
       try {  
          
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            
            String json = gson.toJson(pedido);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionPedidoParserJson(); 
        }
    }
    
    

}
