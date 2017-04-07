package process.parser.gastos;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Gasto;
        
        
public class GastoDeserializer implements JsonDeserializer {
    
        @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gasto nuevoGasto = new Gasto();
        JsonObject jsonObject = (JsonObject)jsonElement;
        nuevoGasto.setId(jsonObject.get("id").getAsInt());
        nuevoGasto.setNombre(jsonObject.get("nombre").getAsString());
        nuevoGasto.setCantidad(jsonObject.get("cantidad").getAsString());
        nuevoGasto.setPrecio(jsonObject.get("precio").getAsString());
        nuevoGasto.setImporte(jsonObject.get("importe").getAsDouble());
        nuevoGasto.setForma(jsonObject.get("forma").getAsString());

     

        return  nuevoGasto;
    }
    
    }
