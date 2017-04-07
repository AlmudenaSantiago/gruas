package process.parser.tarifas;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Tarifas.TarifaPorTiempoDeRespuesta;


public class TarifaPorTiempoDeRespuestaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        TarifaPorTiempoDeRespuesta tarifa = new TarifaPorTiempoDeRespuesta();
        JsonObject jsonObject = (JsonObject)jsonElement;
  
        tarifa.setId(jsonObject.get("id").getAsInt());
        tarifa.setIdCliente(jsonObject.get("idCliente").getAsInt());
        tarifa.setTarifa(jsonObject.get("tarifa").getAsString());
        tarifa.setTipoTarifa(jsonObject.get("tipoTarifa").getAsInt());
        tarifa.setDesde(jsonObject.get("desde").getAsInt());
        tarifa.setHasta(jsonObject.get("hasta").getAsInt());
        tarifa.setHastaKm(jsonObject.get("hastaKm").getAsInt());
        
        tarifa.setServicio(jsonObject.get("servicio").getAsDouble());
        tarifa.setKm(jsonObject.get("km").getAsDouble());
        tarifa.setRis(jsonObject.get("ris").getAsDouble());
        tarifa.setSegundaSalida(jsonObject.get("segundaSalida").getAsDouble());
    
        tarifa.setServicioNocturno(jsonObject.get("servicioNocturno").getAsDouble());
        tarifa.setKmNocturno(jsonObject.get("kmNocturno").getAsDouble());
        tarifa.setRisNocturno(jsonObject.get("risNocturno").getAsDouble());
        tarifa.setSegundaSalidaNocturno(jsonObject.get("segundaSalidaNocturno").getAsDouble());

        return tarifa;
    }
}
