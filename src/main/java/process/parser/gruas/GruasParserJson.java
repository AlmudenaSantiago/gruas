
package process.parser.gruas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.gruas.Grua;


public class GruasParserJson {
    
    
     public List<Grua> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<Grua>>() {}.getType());
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Grua.class, new GruasDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeGrua(Grua grua) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(grua);
         
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
