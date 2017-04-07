/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.vehiculos;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.vehiculos.Vehiculo;


/**
 *
 * @author Aarón
 */
public class VehiculoDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      
         Vehiculo vehiculo = new Vehiculo();
         JsonObject jsonObject = (JsonObject)jsonElement;
         vehiculo.setId(jsonObject.get("id").getAsInt());
         vehiculo.setIdMarca(jsonObject.get("idMarca").getAsInt());
         vehiculo.setAlto(jsonObject.get("alto").getAsDouble());
         vehiculo.setPeso(jsonObject.get("peso").getAsDouble());
         vehiculo.setLargo(jsonObject.get("largo").getAsDouble());
         vehiculo.setTipo(jsonObject.get("tipo").getAsString());
         vehiculo.setMarca(jsonObject.get("marca").getAsString());
         vehiculo.setModelo(jsonObject.get("modelo").getAsString());
         vehiculo.setImagen(jsonObject.get("imagen").getAsString());
        
  
         return vehiculo;
    }
}