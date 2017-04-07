/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.diasFestivos;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.DiaFestivo;



public class DiaFestivoDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         DiaFestivo diaFestivo = new DiaFestivo();
         JsonObject jsonObject = (JsonObject)jsonElement;
         diaFestivo.setDiaFestivoString(jsonObject.get("diaFestivo").getAsString());
         diaFestivo.setId(jsonObject.get("id").getAsInt());
         
         return diaFestivo;
    }
}