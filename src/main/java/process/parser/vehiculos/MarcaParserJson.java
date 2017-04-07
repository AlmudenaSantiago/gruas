
package process.parser.vehiculos;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.vehiculos.Marca;

public class MarcaParserJson {
    
    
     public List<Marca> parsear(String json) {
        try {
            System.out.println("cadena json"  + json);
            return getGson().fromJson(json, new TypeToken<List<Marca>>() {}.getType());
             
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Marca.class, new MarcaDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeProveedor(Marca marca) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(marca);
            System.out.println(json);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
