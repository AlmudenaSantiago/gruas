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
import model.vehiculos.Marca;

/**
 *
 * @author Aarón
 */
public class MarcaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Marca marca = new Marca();
         JsonObject jsonObject = (JsonObject)jsonElement;
     
         marca.setId(jsonObject.get("id").getAsInt());
         marca.setMarca(jsonObject.get("marca").getAsString());
  
         return marca;
    }
}