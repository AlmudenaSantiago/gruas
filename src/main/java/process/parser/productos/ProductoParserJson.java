package process.parser.productos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import exception.ExceptionProductoParserJson;
import model.Producto;


import java.util.List;


public class ProductoParserJson {

    public List<Producto> parsear(String json) {
        System.out.println(json);
        try {
            return getGson().fromJson(json, new TypeToken<List<Producto>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }


    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Producto.class, new ProductoDeserializer());
        return gsonBuilder.create();
    }

  
 
   
    
    
       public Producto parsearProducto(String json) {
        try {
            return getGsonProducto().fromJson(json, new TypeToken<Producto>() {}.getType());
        }
        catch (Exception exception){
              System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }

    
        private Gson getGsonProducto() {
           GsonBuilder gsonBuilder = new GsonBuilder();
           gsonBuilder.registerTypeAdapter(Producto.class, new ProductoDeserializer());
           return gsonBuilder.create();
       }
    
        
        public List<Producto> parsearStock(String json) {
        try {
            
            ProductoRespuestaJson productoRespuestaJson = new Gson().fromJson(json, ProductoRespuestaJson.class);
            return productoRespuestaJson.getProductos();
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }

        private Gson getGsonStock() {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Producto.class, new ProductoDeserializer());
            return gsonBuilder.create();
        }


          public String devuelveJsonDeProducto(Producto producto) {    
       try {  
                    
           GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(producto);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }


}






