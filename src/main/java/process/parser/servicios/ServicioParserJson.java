package process.parser.servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionPedidoParserJson;
import exception.ExceptionProductoParserJson;
import model.Servicio;

import java.util.List;



public class ServicioParserJson {

 
    public List<Servicio> parsear(String json) {
        try {
          
            return getGson().fromJson(json, new TypeToken<List<Servicio>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }


    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Servicio.class, new ServicioDeserializer());
        return gsonBuilder.create();
    }
    
  
       public Servicio parsearServicio(String json) {
        try {
            return getGsonServicio().fromJson(json, new TypeToken<Servicio>() {}.getType());
        }
        catch (Exception exception){
            throw new ExceptionProductoParserJson();
        }
    }

    
     private Gson getGsonServicio() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Servicio.class, new ServicioDeserializer());
        return gsonBuilder.create();
    }
    
       public String devuelveJsonDeServicio(Servicio servicio) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(servicio);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionPedidoParserJson(); 
        }
    }
    
    

}
