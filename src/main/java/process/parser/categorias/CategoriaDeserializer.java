package process.parser.categorias;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Categoria;


public class CategoriaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        Categoria categoria = new Categoria();
        JsonObject jsonObject = (JsonObject)jsonElement;
  
        categoria.setId(jsonObject.get("id").getAsInt());
        categoria.setNombre(jsonObject.get("nombre").getAsString());
       
        return categoria;
    }
}
