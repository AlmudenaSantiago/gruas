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

import model.FestivoSemanal;

/**
 *
 * @author loquat
 */
public class FestivoSemanalDeserializer  implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        
         FestivoSemanal festivo = new FestivoSemanal();
         JsonObject jsonObject = (JsonObject)jsonElement;
        
         festivo.setDiaSemana(jsonObject.get("diaSemana").getAsString());
         festivo.setHoraComienzo(jsonObject.get("horaComienzo").getAsString());
         festivo.setHoraFinal(jsonObject.get("horaFinal").getAsString());
        
         return festivo;
    }
    
}
