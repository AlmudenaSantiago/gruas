/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.recorridosykm;

import process.parser.tipoDeServicio.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.RecorridosyKm;




public class RecorridosyKmParserJson {
      public List<RecorridosyKm> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<RecorridosyKm>>() {}.getType());
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RecorridosyKm.class, new RecorridosyKmDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeMunicipio(RecorridosyKm municipio) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(municipio);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
}
