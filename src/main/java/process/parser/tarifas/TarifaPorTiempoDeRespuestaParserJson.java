/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.tarifas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import model.Servicio;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.parser.servicios.ServicioDeserializer;



public class TarifaPorTiempoDeRespuestaParserJson {
      public List<TarifaPorTiempoDeRespuesta> parsear(String json) {
        try {
          
      
            return getGson().fromJson(json, new TypeToken<List<TarifaPorTiempoDeRespuesta>>() {}.getType());
        }
        catch (Exception exception){
           
            System.out.println("Se ha producido una excepcion" + exception);
           
        }
         return null;
    }
     
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TarifaPorTiempoDeRespuesta.class, new TarifaPorTiempoDeRespuestaDeserializer());
        return gsonBuilder.create();
    }
    
      
      
      
       public String devuelveJsonDeTipoDeServicio(TarifaPorTiempoDeRespuesta tipo) {    
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
