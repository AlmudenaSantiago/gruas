/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.avisos;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.ArrayList;
import java.util.List;
import model.Aviso;





public class AvisoParserJson {
   
    
    public List<Aviso> parsear1(String json) {
        try {
            
            if (json.equals("no encontrado")) return null;
            return getGson1().fromJson(json, new TypeToken<List<Aviso>>() {}.getType());
            
        }
        catch (JsonSyntaxException exception){
           
            System.out.println("Se ha producido una excepcion" + exception);
           
        }
         return null;
    }
    
    
    public List<Aviso> parsear(String json) {
        try {
            getGson().fromJson(json, new TypeToken<List<Aviso>>() {}.getType());
            System.out.println("hasta aqui hemos llegado");
            return getGson().fromJson(json, new TypeToken<List<Aviso>>() {}.getType());
        }
        catch (JsonSyntaxException exception){
           
            System.out.println("Se ha producido una excepcion" + exception);
           
        }
         return null;
    }
     
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Aviso.class, new AvisoDeserializer());
        return gsonBuilder.create();
    }
    
      private Gson getGson1() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Aviso.class, new AvisoDeserializer1());
        
        return gsonBuilder.create();
    }
      
      
       public String devuelveJsonDeAviso(Aviso aviso) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(aviso);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
       
   
       
}
