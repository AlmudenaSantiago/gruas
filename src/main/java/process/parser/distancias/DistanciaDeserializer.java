/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.distancias;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Distancia;




public class DistanciaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         Distancia distancia = new Distancia();
         JsonObject jsonObject = (JsonObject)jsonElement;
         
         distancia.setId(jsonObject.get("id").getAsInt());
         
         if (jsonObject.get("idOrigen").isJsonNull()) {
                distancia.setIdOrigen(0);
                distancia.setMunicipioOrigen("");         
         } else {
                distancia.setIdOrigen(jsonObject.get("idOrigen").getAsInt());
                distancia.setMunicipioOrigen(jsonObject.get("municipioOrigen").getAsString());
         }
         
         if (jsonObject.get("idDestino").isJsonNull()) {
       
               distancia.setIdDestino(0);
               distancia.setMunicipioDestino("");
         } else {
              distancia.setIdDestino(jsonObject.get("idDestino").getAsInt());
              distancia.setMunicipioDestino(jsonObject.get("municipioDestino").getAsString());
         }
         
         System.out.println("paso por dese");
         
         
         
         distancia.setKms(jsonObject.get("kms").getAsDouble());
         
         return distancia;
    }
}