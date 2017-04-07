/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.imagenes;

import process.parser.municipios.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.Imagen;
import model.Municipio;




public class ImagenParserJson {
      public List<Imagen> parsear(String json) {
        try {
     
            return getGson().fromJson(json, new TypeToken<List<Imagen>>() {}.getType());
        }
        catch (Exception exception){
          
            System.out.println(exception);
           
        }
         return null;
    }
     
      private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Imagen.class, new ImagenDeserializer());
        return gsonBuilder.create();
    }
      
      
      
       public String devuelveJsonDeImagen(Imagen imagen) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(imagen);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }
}
