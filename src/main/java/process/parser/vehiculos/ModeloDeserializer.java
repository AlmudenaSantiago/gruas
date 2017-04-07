/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.vehiculos;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.vehiculos.Modelo;

/**
 *
 * @author Aarón
 */
public class ModeloDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Modelo modelo = new Modelo();
         JsonObject jsonObject = (JsonObject)jsonElement;
     
         modelo.setId(jsonObject.get("id").getAsInt());
         modelo.setModelo(jsonObject.get("modelo").getAsString());
         modelo.setIdMarcaPadre(jsonObject.get("idMarcaPadre").getAsInt());
  
         return modelo;
    }
}