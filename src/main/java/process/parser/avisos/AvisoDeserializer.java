/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.avisos;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Aviso;

/**
 *
 * @author Aarón
 */
public class AvisoDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        Aviso aviso = new Aviso();
        JsonObject jsonObject = (JsonObject) jsonElement;
      
        aviso.setId(jsonObject.get("id").getAsInt());

        aviso.setLocalizador(jsonObject.get("localizador").getAsInt());
        aviso.setMatricula(jsonObject.get("matricula").getAsString());
     
     
        if (jsonObject.get("idColor").isJsonNull()) {
            aviso.setColor("");
            aviso.setIdColor(0);

        } else {
             aviso.setColor(jsonObject.get("color").getAsString());
             aviso.setIdColor(jsonObject.get("idColor").getAsInt());
            
  
        }
       
         aviso.setObservaciones(jsonObject.get("observaciones").getAsString());
      
   

        if (jsonObject.get("idModelo").isJsonNull()) {
            aviso.setIdModelo(0);
            aviso.setModelo("");

        } else {
            aviso.setIdModelo(jsonObject.get("idModelo").getAsInt());
            aviso.setModelo(jsonObject.get("modelo").getAsString());
        }

        if (jsonObject.get("idMarca").isJsonNull()) {

            aviso.setMarca("");
            aviso.setIdMarca(0);

        } else {
            aviso.setMarca(jsonObject.get("marca").getAsString());
            aviso.setIdMarca(jsonObject.get("idMarca").getAsInt());

        }

        if (jsonObject.get("idCliente").isJsonNull()) {
            aviso.setCliente("");
            aviso.setIdCliente(0);

        } else {
            aviso.setCliente(jsonObject.get("cliente").getAsString());
            aviso.setIdCliente(jsonObject.get("idCliente").getAsInt());

        }

        aviso.setBastidor(jsonObject.get("bastidor").getAsInt());
        aviso.setKms(jsonObject.get("kms").getAsInt());

        aviso.setExpediente(jsonObject.get("expediente").getAsString());
        aviso.setPoliza(jsonObject.get("poliza").getAsLong());
       
        
        if (jsonObject.get("idBase").isJsonNull()) {
            aviso.setBase("");
        } else {
           aviso.setBase(jsonObject.get("baseoperativa").getAsString());
        }
        
        aviso.setNombre(jsonObject.get("nombre").getAsString());
        aviso.setTelefono(jsonObject.get("telefono").getAsString());

        aviso.setDireccionLocalizacion(jsonObject.get("direccionLocalizacion").getAsString());
        aviso.setDireccionDestino(jsonObject.get("direccionDestino").getAsString());
        
        if (jsonObject.get("municipioLocalizacion").isJsonNull()) {
           aviso.setMunicipioLocalizacion("");
        } else {
            aviso.setMunicipioLocalizacion(jsonObject.get("municipioLocalizacion").getAsString());
        }
        
        if (jsonObject.get("municipioDestino").isJsonNull()) {
           aviso.setMunicipioDestino("");
        } else {
           aviso.setMunicipioDestino(jsonObject.get("municipioDestino").getAsString());
        }
        
        
        if (jsonObject.get("desdeBase").isJsonNull()) {
            aviso.setIdDesdeBase(0);
            aviso.setDesdeBaseNombre("");
        } else {
            aviso.setIdDesdeBase(jsonObject.get("desdeBase").getAsInt());
            aviso.setDesdeBaseNombre(jsonObject.get("desdeBaseNombre").getAsString());

        }
      
        aviso.setFechaAsignacion(jsonObject.get("fechaAsignacion").getAsString());
        aviso.setFechaRealizar(jsonObject.get("fechaRealizacion").getAsString());
        aviso.setHoraSalida(jsonObject.get("horaSalida").getAsString());
        aviso.setHoraLocalizado(jsonObject.get("horaLocalizado").getAsString());
        aviso.setHoraFinalizado(jsonObject.get("horaFinalizado").getAsString());
        aviso.setTiempoLlegada(jsonObject.get("tiempoLlegada").getAsDouble());

        aviso.setTalon(jsonObject.get("talon").getAsInt());
        
        
        if (jsonObject.get("idNumRespuesta").isJsonNull()) {
            aviso.setIdNumRespuesta(0);
            aviso.setNumRespuesta("");
            aviso.setCodigoNumRespuesta(0);
        } else {
            
            aviso.setNumRespuesta(jsonObject.get("numRespuesta").getAsString());
            aviso.setIdNumRespuesta(jsonObject.get("idNumRespuesta").getAsInt());
            aviso.setCodigoAveria(jsonObject.get("codigoRespuesta").getAsInt());

        }
 
        
        if (jsonObject.get("idAveria").isJsonNull()) {
            aviso.setIdAveria(0);
            aviso.setAveria("");
            aviso.setCodigoAveria(0);
        } else {
            aviso.setIdAveria(jsonObject.get("idAveria").getAsInt());
            aviso.setAveria(jsonObject.get("averia").getAsString());
            aviso.setCodigoAveria(jsonObject.get("codigoAveria").getAsInt());

        }
 
        if (jsonObject.get("idSolucion").isJsonNull() ) {
             aviso.setSolucion("");
             aviso.setIdSolucion(0);
        } else {
            aviso.setIdSolucion(jsonObject.get("idSolucion").getAsInt());
            aviso.setSolucion(jsonObject.get("solucion").getAsString());
           
        }

           if (jsonObject.get("idGrua").isJsonNull()) {
            aviso.setIdGrua(0);
            aviso.setGrua(0);
        } else {
            aviso.setIdGrua(jsonObject.get("idGrua").getAsInt());
            aviso.setGrua(jsonObject.get("numGrua").getAsInt());
        }
 
        if (jsonObject.get("idConductor").isJsonNull()) {
            aviso.setConductorGrua("");
            aviso.setIdGruista(0);
        } else {
            aviso.setConductorGrua(jsonObject.get("nombreGruista").getAsString());
            aviso.setIdGruista(jsonObject.get("idConductor").getAsInt());
        }

        
        
        aviso.setKmsGrua(jsonObject.get("kmsGrua").getAsInt());
       
        if (jsonObject.get("tipo").isJsonNull()) {
           aviso.setTipotarifa("");
           aviso.setIdServicioTipo(0);
      
        } else {
            aviso.setIdServicioTipo(jsonObject.get("idServicioTipo").getAsInt());
           aviso.setTipotarifa(jsonObject.get("tipo").getAsString());
        }
        
        
        aviso.setEspera(jsonObject.get("espera").getAsInt());
        aviso.setRescate(jsonObject.get("rescate").getAsInt());

        aviso.setDiasCustodia(jsonObject.get("diasCustodia").getAsInt());
        aviso.setNf(jsonObject.get("nf").getAsString());
            if (jsonObject.get("plus").isJsonNull()) {
               aviso.setPlus("");
            }else {
                aviso.setPlus(jsonObject.get("plus").getAsString());
            }

       
      
        //**************** importes **********************//
        aviso.setSuplemento(jsonObject.get("suplemento").getAsDouble());
        aviso.setImporteServicioEspecial(jsonObject.get("importeServicioEspecial").getAsDouble());
        aviso.setImporteServicio(jsonObject.get("importeServicio").getAsDouble());
        aviso.setIgic(jsonObject.get("igic").getAsDouble());
        aviso.setServicioEspecial(jsonObject.get("servicioEspecial").getAsDouble());
        aviso.setIgicServicioEspecial(jsonObject.get("igicServicioEspecial").getAsDouble());
        aviso.setTotal(jsonObject.get("total").getAsDouble());
        aviso.setBaseImponible(jsonObject.get("baseImponible").getAsDouble());

        return aviso;
    }
}
