/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.municipios;

import process.parser.tipoDeServicio.*;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Municipio;




public class MunicipioDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         Municipio municipio = new Municipio();
         JsonObject jsonObject = (JsonObject)jsonElement;
         municipio.setId(jsonObject.get("idMunicipio").getAsInt());
         municipio.setMunicipio(jsonObject.get("municipio").getAsString());
       
         return municipio;
    }
}