/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.contactos;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Contacto;




public class ContactoDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Contacto contacto = new Contacto();
            JsonObject jsonObject = (JsonObject)jsonElement;
            contacto.setNombre(jsonObject.get("nombre").getAsString());
            contacto.setTelefono(jsonObject.get("telefono").getAsString());
            contacto.setEmail(jsonObject.get("email").getAsString());
            contacto.setId(jsonObject.get("id").getAsInt());

         
         return contacto;
    }
}