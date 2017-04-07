package process.parser.poblaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import model.Poblacion;

import java.util.List;



public class PoblacionParserJson {

    
    public List<Poblacion> parsear(String json) throws Exception{
      
        try{
            return getGson().fromJson(json, new TypeToken<List<Poblacion>>() {}.getType());
           
        }
        catch(JsonSyntaxException exception){
            if (json.equals("[]")) { 
               System.out.println("");
            }
            else        
                throw new Exception();
        }
     return null;  
    }
    
   
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Poblacion.class, new PoblacionDeserializer());
        return gsonBuilder.create();
    }
    
     
    
    
    

}
