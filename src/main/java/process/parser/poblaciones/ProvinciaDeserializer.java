/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.poblaciones;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Poblacion;
import model.Provincia;

/**
 *
 * @author loquat
 */
public class ProvinciaDeserializer implements JsonDeserializer {
    
        @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
   
        JsonObject jsonObject = (JsonObject)jsonElement;
      
       
        Provincia nuevaProvincia = new Provincia();
        nuevaProvincia.setProvincia(jsonObject.get("provincia").getAsString());
        nuevaProvincia.setCod_prov(jsonObject.get("cod_prov").getAsString());

        return  nuevaProvincia;
    }
    
    }