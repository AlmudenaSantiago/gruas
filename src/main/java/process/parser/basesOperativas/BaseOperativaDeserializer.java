package process.parser.basesOperativas;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.basesOperativas.BaseOperativa;


public class BaseOperativaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        BaseOperativa base = new BaseOperativa();
        JsonObject jsonObject = (JsonObject)jsonElement;
  
        base.setId(jsonObject.get("id").getAsInt());
        base.setBaseOperativa(jsonObject.get("baseoperativa").getAsString());
        base.setIdMunicipio(jsonObject.get("idMunicipio").getAsInt());
        base.setMunicipio(jsonObject.get("municipio").getAsString());
        return base;
    }
}
