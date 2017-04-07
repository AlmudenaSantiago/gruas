package process.parser.tarifas;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Tarifas.Tarifa;


public class TarifaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        Tarifa tarifa = new Tarifa();
        JsonObject jsonObject = (JsonObject)jsonElement;
  
        tarifa.setId(jsonObject.get("id").getAsInt());
        tarifa.setUrbano(jsonObject.get("urbano").getAsDouble());
        tarifa.setSalida(jsonObject.get("salida").getAsDouble());
        tarifa.setKm(jsonObject.get("km").getAsDouble());
        tarifa.setDsi(jsonObject.get("dsi").getAsDouble());
        tarifa.setSar(jsonObject.get("sar").getAsDouble());
        tarifa.setCustodia(jsonObject.get("custodia").getAsDouble());
        tarifa.setEspera(jsonObject.get("rescate").getAsDouble());
        tarifa.setEspera(jsonObject.get("espera").getAsDouble());
        tarifa.setKm(jsonObject.get("km").getAsDouble());
        tarifa.setKmLR(jsonObject.get("kmLR").getAsDouble());
        tarifa.setIdTipoTarifa(jsonObject.get("idTipoTarifa").getAsInt());
       

        return tarifa;
    }
}
