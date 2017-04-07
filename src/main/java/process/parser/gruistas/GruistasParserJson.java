
package process.parser.gruistas;

import process.parser.gruas.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.gruas.Grua;
import model.gruas.Gruista;


public class GruistasParserJson {
    
    
     public List<Gruista> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<Gruista>>() {}.getType());
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Gruista.class, new GruistasDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeGrua(Gruista gruista) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(gruista);
         
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
