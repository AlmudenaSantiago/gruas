package process.parser.poblaciones;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Poblacion;


public class PoblacionDeserializer implements JsonDeserializer {
    
        @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
   
        JsonObject jsonObject = (JsonObject)jsonElement;
      
       
        Poblacion nuevaPoblacion = new Poblacion();
        nuevaPoblacion.setCodigoPostal(jsonObject.get("cod_postal").getAsString());
        nuevaPoblacion.setProvincia(jsonObject.get("cod_prov").getAsString());
        nuevaPoblacion.setNombrePoblacion(jsonObject.get("poblacion").getAsString());
        

        return  nuevaPoblacion;
    }
    
    }
