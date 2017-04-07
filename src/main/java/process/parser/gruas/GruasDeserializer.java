/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.gruas;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.gruas.Grua;

/**
 *
 * @author Aarón
 */
public class GruasDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Grua nuevaGrua = new Grua();
         JsonObject jsonObject = (JsonObject)jsonElement;
    
         nuevaGrua.setId(jsonObject.get("id").getAsInt());
         nuevaGrua.setNumGrua(jsonObject.get("numGrua").getAsInt());
         nuevaGrua.setTipo(jsonObject.get("tipo").getAsString());
         nuevaGrua.setConductor(jsonObject.get("nombreGruista").getAsString());
         nuevaGrua.setBase(jsonObject.get("base").getAsInt());
         nuevaGrua.setMensaje(jsonObject.get("mensaje").getAsString());
         nuevaGrua.setRotulado(jsonObject.get("rotulado").getAsString());
         nuevaGrua.setHorario(jsonObject.get("horario").getAsString());
         nuevaGrua.setDemora(jsonObject.get("demora").getAsString());
         nuevaGrua.setSrc(jsonObject.get("src").getAsString());
         nuevaGrua.setKm(jsonObject.get("km").getAsDouble());
         nuevaGrua.setEstado(jsonObject.get("estado").getAsString());
         nuevaGrua.setImagen(jsonObject.get("imagen").getAsString());
  
          nuevaGrua.setLargo(jsonObject.get("largo").getAsDouble());
          nuevaGrua.setAlto(jsonObject.get("alto").getAsDouble());
          nuevaGrua.setPeso(jsonObject.get("peso").getAsDouble());
   
         
       
        return nuevaGrua;
    }
}