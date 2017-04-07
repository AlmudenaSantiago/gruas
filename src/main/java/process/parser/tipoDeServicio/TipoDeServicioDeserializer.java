/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.tipoDeServicio;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.TipoDeServicio;


/**
 *
 * @author Aarón
 */
public class TipoDeServicioDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         TipoDeServicio tipoDeServicio = new TipoDeServicio();
         JsonObject jsonObject = (JsonObject)jsonElement;
         tipoDeServicio.setId(jsonObject.get("id").getAsInt());
         tipoDeServicio.setTipo(jsonObject.get("tipo").getAsInt());
         return tipoDeServicio;
    }
}