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
import model.HorarioNocturno;

/**
 *
 * @author loquat
 */
public class NocturnoDeserializer  implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        
         HorarioNocturno festivo = new HorarioNocturno();
         JsonObject jsonObject = (JsonObject)jsonElement;
        
         festivo.setHoraEntrada(jsonObject.get("horaEntrada").getAsString());
         festivo.setHoraSalida(jsonObject.get("horaSalida").getAsString());
         festivo.setIdCliente(jsonObject.get("idCliente").getAsInt());
         festivo.setId(jsonObject.get("idNocturno").getAsInt());
        
         return festivo;
    }
    
}
