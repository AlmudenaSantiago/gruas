/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.gruistas;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.gruas.Gruista;

/**
 *
 * @author Aarón
 */
public class GruistasDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Gruista nuevaGruista = new Gruista();
         JsonObject jsonObject = (JsonObject)jsonElement;
    
         nuevaGruista.setId(jsonObject.get("id").getAsInt());
         nuevaGruista.setNombre(jsonObject.get("nombreGruista").getAsString());
         nuevaGruista.setApellidos(jsonObject.get("apellidos").getAsString());
         nuevaGruista.setTipo(jsonObject.get("tipo").getAsString());
    
  
       
        return nuevaGruista;
    }
}