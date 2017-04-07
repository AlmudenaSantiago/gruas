/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.tipoDeTarifas;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Tarifas.TipoDeTarifa;


/**
 *
 * @author Aarón
 */
public class TipoDeTarifaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         TipoDeTarifa tipoDeServicio = new TipoDeTarifa();
         JsonObject jsonObject = (JsonObject)jsonElement;
         tipoDeServicio.setId(jsonObject.get("id").getAsInt());
         tipoDeServicio.setTipo(jsonObject.get("tipo").getAsString());
         return tipoDeServicio;
    }
}