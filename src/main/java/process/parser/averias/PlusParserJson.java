
package process.parser.averias;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.averiasYSoluciones.Plus;


public class PlusParserJson {
    
    
     public List<Plus> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<Plus>>() {}.getType());
        }
        catch (JsonSyntaxException exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Plus.class, new PlusDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDePlus(Plus s) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(s);
         
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
