/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.serviciosEspeciales;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.serviciosEspeciales.ServicioEspecial;




public class ServicioEspecialParserJson {
      public List<ServicioEspecial> parsear(String json) {
        try {
           
           System.out.println("llega" + json);
            return getGson().fromJson(json, new TypeToken<List<ServicioEspecial>>() {}.getType());
        }
        catch (Exception exception){
           
            System.out.println("Se ha producido una excepcion" + exception);
           
        }
         return null;
    }
     
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ServicioEspecial.class, new ServicioEspecialDeserializer());
        return gsonBuilder.create();
    }
    
      
      
      
   public String devuelveJsonDeServicioEspecial(ServicioEspecial tipo) {    
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
