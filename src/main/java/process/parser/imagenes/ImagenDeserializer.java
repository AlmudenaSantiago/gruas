/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.imagenes;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Imagen;




public class ImagenDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         Imagen imagen = new Imagen();
         JsonObject jsonObject = (JsonObject)jsonElement;
         imagen.setImagen(jsonObject.get("imagen").getAsString());
         imagen.setId(jsonObject.get("id").getAsInt());
         return imagen;
    }
}