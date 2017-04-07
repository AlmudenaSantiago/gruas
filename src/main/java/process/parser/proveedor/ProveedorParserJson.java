
package process.parser.proveedor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;

import model.Proveedor;

public class ProveedorParserJson {
    
    
     public List<Proveedor> parsear(String json) {
        try {
            System.out.println("cadena json"  + json);
            return getGson().fromJson(json, new TypeToken<List<Proveedor>>() {}.getType());
             
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Proveedor.class, new ProveedorDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeProveedor(Proveedor proveedor) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(proveedor);
            System.out.println(json);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
