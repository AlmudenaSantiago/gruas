package process.parser.averias;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.averiasYSoluciones.Averia;

/**
 *
 * @author Aarón
 */
public class AveriasDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Averia averia = new Averia();
         JsonObject jsonObject = (JsonObject)jsonElement;
    
         averia.setId(jsonObject.get("id").getAsInt());
         averia.setAveria(jsonObject.get("averia").getAsString());
         averia.setCodigo(jsonObject.get("codigo").getAsInt());
         
 
        return averia;
    }
}