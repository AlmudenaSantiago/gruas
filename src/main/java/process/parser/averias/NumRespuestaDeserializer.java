package process.parser.averias;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.averiasYSoluciones.NumRespuesta;

/**
 *
 * @author Aarón
 */
public class NumRespuestaDeserializer implements JsonDeserializer {
 
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         NumRespuesta num = new NumRespuesta();
         JsonObject jsonObject = (JsonObject)jsonElement;
    
         num.setId(jsonObject.get("id").getAsInt());
         num.setNumRespuesta(jsonObject.get("numRespuesta").getAsString());
         num.setCodigo(jsonObject.get("codigo").getAsInt());
         
 
        return num;
    }
}