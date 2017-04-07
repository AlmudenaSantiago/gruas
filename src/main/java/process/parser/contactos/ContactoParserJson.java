/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.contactos;

import process.parser.diasFestivos.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.Contacto;
import model.DiaFestivo;




public class ContactoParserJson {
      public List<Contacto> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<Contacto>>() {}.getType());
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Contacto.class, new ContactoDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeContacto(Contacto conta) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(conta);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
}
