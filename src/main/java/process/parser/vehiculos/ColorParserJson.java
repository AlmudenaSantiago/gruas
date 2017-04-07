
package process.parser.vehiculos;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.awt.Color;
import java.util.List;
import model.vehiculos.ColorVehiculo;



public class ColorParserJson {
    
    
     public List<ColorVehiculo> parsear(String json) {
        try {
            System.out.println("cadena json"  + json);
            return getGson().fromJson(json, new TypeToken<List<Color>>() {}.getType());
             
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Color.class, new ColorDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeColor(Color color) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(color);
            System.out.println(json);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
