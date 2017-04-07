package process.parser.serviciosEspeciales;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.serviciosEspeciales.ServicioEspecial;


public class ServicioEspecialDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        ServicioEspecial servicioEspecial = new ServicioEspecial();
        JsonObject jsonObject = (JsonObject)jsonElement;
  
        servicioEspecial.setId(jsonObject.get("id").getAsInt());
        servicioEspecial.setUnidad(jsonObject.get("unidad").getAsString());
        servicioEspecial.setCliente(jsonObject.get("cliente").getAsInt());
        servicioEspecial.setImporteUnidad(jsonObject.get("importeUnidad").getAsDouble());
        servicioEspecial.setImporteSalida(jsonObject.get("importeSalida").getAsDouble());
        servicioEspecial.setImporteUnidadSuplemento(jsonObject.get("importeUnidadSuplemento").getAsDouble());
        servicioEspecial.setImporteSalidaSuplemento(jsonObject.get("importeSalidaSuplemento").getAsDouble());
        servicioEspecial.setSuplemento(jsonObject.get("suplemento").getAsDouble());
        servicioEspecial.setTipo(jsonObject.get("tipo").getAsString());
        servicioEspecial.setUmbral(jsonObject.get("umbral").getAsInt());
        servicioEspecial.setIgic(jsonObject.get("igic").getAsInt());
        servicioEspecial.setServicio(jsonObject.get("servicio").getAsString());
        servicioEspecial.setServicioNombre(jsonObject.get("nombreServicio").getAsString());
        servicioEspecial.setNombre(jsonObject.get("nombre").getAsString());
        
  
        
        return servicioEspecial;
    }
}
