package process.parser.averias;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.averiasYSoluciones.Solucion;

/**
 *
 * @author Aarón
 */
public class SolucionDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Solucion solucion = new Solucion();
         JsonObject jsonObject = (JsonObject)jsonElement;
    
         solucion.setId(jsonObject.get("id").getAsInt());
         solucion.setSolucion(jsonObject.get("solucion").getAsString());
         solucion.setCodigo(jsonObject.get("codigo").getAsInt());
         
 
        return solucion;
    }
}