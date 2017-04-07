package process.parser.tarifas;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaNocturno;


public class TarifaNocturnoDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        TarifaNocturno tarifa = new TarifaNocturno();
        JsonObject jsonObject = (JsonObject)jsonElement;
  
        tarifa.setId(jsonObject.get("id").getAsInt());
        tarifa.setUrbano(jsonObject.get("urbano").getAsDouble());
        tarifa.setSalida(jsonObject.get("salida").getAsDouble());
        tarifa.setKm(jsonObject.get("km").getAsDouble());
        tarifa.setDsi(jsonObject.get("dsi").getAsDouble());
        tarifa.setSar(jsonObject.get("sar").getAsDouble());
        tarifa.setCustodia(jsonObject.get("custodia").getAsDouble());
        tarifa.setEspera(jsonObject.get("espera").getAsDouble());
        tarifa.setRescate(jsonObject.get("rescate").getAsDouble());
        tarifa.setKm(jsonObject.get("km").getAsDouble());
        tarifa.setKmLR(jsonObject.get("kmLR").getAsDouble());
        tarifa.setIdTipoTarifa(jsonObject.get("idTipoTarifa").getAsInt());
        tarifa.setSuplemento(jsonObject.get("suplemento").getAsDouble());
       

        return tarifa;
    }
}
