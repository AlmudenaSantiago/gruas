package process.parser.averias;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.averiasYSoluciones.Averia;
import model.averiasYSoluciones.Plus;

/**
 *
 * @author Aarón
 */
public class PlusDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Plus plus = new Plus();
         JsonObject jsonObject = (JsonObject)jsonElement;
    
         plus.setId(jsonObject.get("id").getAsInt());
         plus.setPlus(jsonObject.get("plus").getAsString());
         
 
        return plus;
    }
}