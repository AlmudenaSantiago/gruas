/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.diasFestivos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.FestivoSemanal;

/**
 *
 * @author loquat
 */
public class FestivoSemanalParserJson {
    
    public List<FestivoSemanal> parsear(String json) {
        try {
            return getGson().fromJson(json, new TypeToken<List<FestivoSemanal>>() {}.getType());
        }
        catch (Exception exception){
            
            System.out.println("Se ha producido una expcecion inesperada: " + exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FestivoSemanal.class, new FestivoSemanalDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeFestivoSemanal(FestivoSemanal festivoSemanal) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(festivoSemanal);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
    
    
}
