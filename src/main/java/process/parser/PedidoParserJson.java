package process.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.ExceptionPedidoParserJson;
import java.net.URLEncoder;
import model.Pedido;

import java.util.List;
import model.PedidoPasarelaActualizadorEstado;

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

    public String devuelveJsonDePedidoPasarela(PedidoPasarelaActualizadorEstado pedidoPasarela) {    
       try { 
        Gson gson = new Gson();
        return gson.toJson(pedidoPasarela);
        
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionPedidoParserJson(); 
        }
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
