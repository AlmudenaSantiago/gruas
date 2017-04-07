/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.basesOperativas;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.basesOperativas.BaseOperativa;
import model.basesOperativas.BaseOperativaAuxConClienteId;




public class BaseOperativaParserJson {
      public List<BaseOperativa> parsear(String json) {
        try {
           
           System.out.println("llega" + json);
            return getGson().fromJson(json, new TypeToken<List<BaseOperativa>>() {}.getType());
        }
        catch (Exception exception){
           
            System.out.println("Se ha producido una excepcion" + exception);
           
        }
         return null;
    }
     
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BaseOperativa.class, new BaseOperativaDeserializer());
        return gsonBuilder.create();
    }
    
      
      
      
       public String devuelveJsonDeBaseOperativa(BaseOperativa baseoperativa) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(baseoperativa);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
       
       
       
 
       
       public String devuelveJsonDeBaseOperativaAuxConClienteId(BaseOperativaAuxConClienteId base) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(base);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
       
       
       
}
