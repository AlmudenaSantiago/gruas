package process.parser.clientes;

import com.google.gson.*;
import java.lang.reflect.Type;
import javax.swing.JOptionPane;
import model.Cliente;


public class ClienteObservacionesGeneralesDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        Cliente cliente = new Cliente();
        JsonObject jsonObject = (JsonObject)jsonElement;
        cliente.setObservacionesGenerales(jsonObject.get("observacionesGenerales").getAsString());
       
       
        return cliente;
    }
}
