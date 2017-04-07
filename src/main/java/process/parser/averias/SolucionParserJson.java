
package process.parser.averias;

import process.parser.gruas.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.averiasYSoluciones.Averia;
import model.averiasYSoluciones.Solucion;
import model.gruas.Grua;


public class SolucionParserJson {
    
    
     public List<Solucion> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<Solucion>>() {}.getType());
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Solucion.class, new SolucionDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeSolucion(Solucion solucion) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(solucion);
         
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
