package process.parser.categorias;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import exception.ExceptionProductoParserJson;
import java.util.List;
import model.Categoria;


public class CategoriaParserJson {

    public List<Categoria> parsear(String json) {
        try {
            return getGson().fromJson(json, new TypeToken<List<Categoria>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }
    
     public List<Categoria> parsearCategorias2(String json) {
        try {
            return getGson2().fromJson(json, new TypeToken<List<Categoria>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }
     
     
     public List<Categoria> parsearCategorias3(String json) {
        try {
            return getGson3().fromJson(json, new TypeToken<List<Categoria>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }
      public List<Categoria> parsearCategorias4(String json) {
        try {
            return getGson4().fromJson(json, new TypeToken<List<Categoria>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new ExceptionProductoParserJson();
        }
    }
     
     
     
      private Gson getGson3() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Categoria.class, new CategoriaDeserializer3());
    
        return gsonBuilder.create();
    }
    
         
      private Gson getGson4() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Categoria.class, new CategoriaDeserializer4());
       
        return gsonBuilder.create();
    }
    
      
     
    
    private Gson getGson2() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Categoria.class, new CategoriaDeserializer2());
       
        return gsonBuilder.create();
    }
    
    


    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Categoria.class, new CategoriaDeserializer());
        System.out.println(gsonBuilder.create());
        return gsonBuilder.create();
    }
    
       public String devuelveJsonDeCategoria(Categoria categoria) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(categoria);
            System.out.println(json);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }

}






