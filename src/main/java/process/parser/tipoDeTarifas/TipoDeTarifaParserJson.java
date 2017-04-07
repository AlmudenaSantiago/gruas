/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.tipoDeTarifas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.Tarifas.TipoDeTarifa;
import model.Tarifas.TipoDeTarifaAuxConClienteID;



public class TipoDeTarifaParserJson {
      public List<TipoDeTarifa> parsear(String json) {
        try {
            System.out.println(json);
            return getGson().fromJson(json, new TypeToken<List<TipoDeTarifa>>() {}.getType());
            
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TipoDeTarifa.class, new TipoDeTarifaDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeTipoDeTarifa(TipoDeTarifa tipo) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(tipo);
         
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
       
       
       
         public String devuelveJsonDeTipoDeTarifaAuxConClienteID(TipoDeTarifaAuxConClienteID tipo) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(tipo);
         
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
       
}
