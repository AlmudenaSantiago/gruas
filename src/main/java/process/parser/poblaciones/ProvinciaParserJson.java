/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.poblaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.List;

import model.Provincia;

public class ProvinciaParserJson {
      
    public List<Provincia> parsear(String json) throws Exception{
      
        try{
            System.out.println("2adadena a parsear"  + json);
            return getGson().fromJson(json, new TypeToken<List<Provincia>>() {}.getType());
           
        }
        catch(JsonSyntaxException exception){
            if (json.equals("[]")) { 
               System.out.println("");
            }
            else        
                throw new Exception();
        }
     return null;  
    }
    
   
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Provincia.class, new ProvinciaDeserializer());
        return gsonBuilder.create();
    }
}
