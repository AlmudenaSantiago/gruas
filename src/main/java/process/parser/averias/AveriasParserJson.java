
package process.parser.averias;

import process.parser.gruas.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.averiasYSoluciones.Averia;
import model.gruas.Grua;


public class AveriasParserJson {
    
    
     public List<Averia> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<Averia>>() {}.getType());
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Averia.class, new AveriasDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeAveria(Averia averia) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(averia);
         
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
