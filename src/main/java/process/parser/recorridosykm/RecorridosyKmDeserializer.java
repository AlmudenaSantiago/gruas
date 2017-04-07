/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.recorridosykm;

import process.parser.tipoDeServicio.*;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.RecorridosyKm;



public class RecorridosyKmDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         RecorridosyKm municipio = new RecorridosyKm();
         JsonObject jsonObject = (JsonObject)jsonElement;
         municipio.setId(jsonObject.get("id").getAsInt());
         municipio.setIdCliente(jsonObject.get("idCliente").getAsInt());
         municipio.setMunicipio(jsonObject.get("municipio").getAsString());
         municipio.setIda(jsonObject.get("ida").getAsDouble());
         municipio.setKmtotal(jsonObject.get("kmtotal").getAsDouble());
         municipio.setTurismo(jsonObject.get("turismo").getAsDouble());
         municipio.setTodoterreno(jsonObject.get("todoterreno").getAsDouble());
         municipio.setFurgon(jsonObject.get("furgon").getAsDouble());
         municipio.setMaquinaria(jsonObject.get("maquinaria").getAsDouble());
         municipio.setTarifaparamasdetres(jsonObject.get("tarifaparamasdetres").getAsDouble());
         
         return municipio;
    }
}