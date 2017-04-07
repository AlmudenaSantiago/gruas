
package process.parser.vehiculos;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.lang.reflect.Type;
import java.util.List;
import model.Producto;
import model.vehiculos.Vehiculo;
import process.parser.productos.ProductoDeserializer;


public class VehiculoParserJson {
    
    
     public List<Vehiculo> parsear(String json) {
        try {
            System.out.println(json);
            Type collectionType = new TypeToken<List<Vehiculo>>(){}.getType();
            return (List<Vehiculo>) new Gson().fromJson( json , collectionType);
            //   return getGson().fromJson(json, new TypeToken<List<Vehiculo>>() {}.getType());
             
        }
        catch (JsonSyntaxException exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Vehiculo.class, new VehiculoDeserializer());
        return gsonBuilder.create();
    }
      
       private Gson getGsonVehiculo() {
           GsonBuilder gsonBuilder = new GsonBuilder();
           gsonBuilder.registerTypeAdapter(Vehiculo.class, new VehiculoDeserializer());
           return gsonBuilder.create();
       }
       
       
      
       public Vehiculo parsearProducto(String json) {
        try {
            return getGsonVehiculo().fromJson(json, new TypeToken<Vehiculo>() {}.getType());
        }
        catch (Exception exception){
              System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }
      
       public String devuelveJsonDeVehiculo(Vehiculo v) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(v);
            System.out.println(json);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
     
     
}
