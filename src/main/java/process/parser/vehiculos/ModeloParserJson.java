
package process.parser.vehiculos;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.vehiculos.Modelo;

public class ModeloParserJson {
    
    
     public List<Modelo> parsear(String json) {
        try {
            System.out.println("cadena json"  + json);
            return getGson().fromJson(json, new TypeToken<List<Modelo>>() {}.getType());
             
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Modelo.class, new ModeloDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeProveedor(Modelo modelo) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(modelo);
            System.out.println(json);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
