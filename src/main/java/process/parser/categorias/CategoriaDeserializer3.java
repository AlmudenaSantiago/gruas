/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.categorias;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Categoria;



public class CategoriaDeserializer3 implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
 
        Categoria categoria = new Categoria();
        JsonObject jsonObject = (JsonObject)jsonElement;
      
        categoria.setId(jsonObject.get("categoria1_id").getAsInt());
        categoria.setId2(jsonObject.get("categoria2_id").getAsInt());
         categoria.setId3(jsonObject.get("categoria3_id").getAsInt());
        categoria.setNombre(jsonObject.get("nombre").getAsString());
       
        return categoria;
    }
}