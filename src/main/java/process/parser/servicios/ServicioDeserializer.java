package process.parser.servicios;

import com.google.gson.*;
import model.Servicio;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioDeserializer implements JsonDeserializer {
    
        @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Servicio nuevoServicio = new Servicio();
             JsonObject jsonObject = (JsonObject)jsonElement;
           
            nuevoServicio.setId(jsonObject.get("id").getAsInt());
            nuevoServicio.setGrua(jsonObject.get("grua").getAsInt());
            nuevoServicio.setGruista(jsonObject.get("gruista").getAsString());
            nuevoServicio.setAseguradora(jsonObject.get("aseguradora").getAsString());
         
            nuevoServicio.setCompania(jsonObject.get("compania").getAsString());
            nuevoServicio.setNegocio(jsonObject.get("negocio").getAsString());
            nuevoServicio.setExpediente(jsonObject.get("expediente").getAsString());
            nuevoServicio.setEmpresa(jsonObject.get("empresa").getAsString());
      
            
            nuevoServicio.setMarca_modelo(jsonObject.get("marca_modelo").getAsString());
            nuevoServicio.setDireccionOrigen(jsonObject.get("direccionOrigen").getAsString());
            nuevoServicio.setPoblacionOrigen(jsonObject.get("poblacionOrigen").getAsString());
            nuevoServicio.setDireccionDestino(jsonObject.get("direccionDestino").getAsString());
            nuevoServicio.setPoblacionDestino(jsonObject.get("poblacionDestino").getAsString());
      
            
            String horaString = jsonObject.get("horaIntervencion").getAsString();
 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date horaIntervencion = null;
            try {
                horaIntervencion = sdf.parse(horaString);
            } catch (ParseException ex) {
                Logger.getLogger(ServicioDeserializer.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            nuevoServicio.setHoraIntervencion(horaIntervencion);
            nuevoServicio.setAveria(jsonObject.get("averia").getAsString());
            nuevoServicio.setAveria(jsonObject.get("matricula").getAsString());
            nuevoServicio.setEstado(jsonObject.get("estado").getAsString());
            nuevoServicio.setOperador(jsonObject.get("operador").getAsString());
        
        return  nuevoServicio;
    }
    
    }
