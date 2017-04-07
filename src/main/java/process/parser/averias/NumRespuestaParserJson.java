
package process.parser.averias;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.averiasYSoluciones.NumRespuesta;


public class NumRespuestaParserJson {
    
    
     public List<NumRespuesta> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<NumRespuesta>>() {}.getType());
        }
        catch (JsonSyntaxException exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(NumRespuesta.class, new NumRespuestaDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeAveria(NumRespuesta s) {    
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
