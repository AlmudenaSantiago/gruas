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
public class AvisoDeserializer1 implements JsonDeserializer {

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
             JsonObject jsonObjectClolor = (JsonObject) jsonElement;
             jsonObjectClolor = jsonObject.get("color").getAsJsonObject();
             aviso.setColor(jsonObjectClolor.get("color").getAsString());
             aviso.setIdColor(jsonObject.get("idColor").getAsInt());
                
  
        }
      
       
       
         aviso.setObservaciones(jsonObject.get("observaciones").getAsString());
      
   
     
        if (jsonObject.get("idModelo").isJsonNull()) {
          
            aviso.setIdModelo(0);
            aviso.setModelo("");

        } else {
           
            aviso.setIdModelo(jsonObject.get("idModelo").getAsInt());
            JsonObject jsonObjectModelo= (JsonObject) jsonElement;
            jsonObjectModelo = jsonObject.get("modelo").getAsJsonObject();
            aviso.setModelo(jsonObjectModelo.get("modelo").getAsString());
       
        }
       
    
        if (jsonObject.get("idMarca").isJsonNull()) {

            aviso.setMarca("");
            aviso.setIdMarca(0);

        } else {
             JsonObject jsonObjectMarca = (JsonObject) jsonElement;
             jsonObjectMarca = jsonObject.get("marca").getAsJsonObject();
             aviso.setMarca(jsonObjectMarca.get("marca").getAsString());
             aviso.setIdMarca(jsonObject.get("idMarca").getAsInt());
         }
      
 
        if (jsonObject.get("idCliente").isJsonNull()) {
            aviso.setCliente("");
            aviso.setIdCliente(0);

        } else {
             JsonObject jsonObjectCliente = (JsonObject) jsonElement;
             jsonObjectCliente = jsonObject.get("cliente").getAsJsonObject();
             aviso.setCliente(jsonObjectCliente.get("nombre").getAsString());
             aviso.setIdCliente(jsonObject.get("idCliente").getAsInt());

        }

     
     
        aviso.setBastidor(jsonObject.get("bastidor").getAsInt());
        aviso.setKms(jsonObject.get("kms").getAsInt());

        aviso.setExpediente(jsonObject.get("expediente").getAsString());
        aviso.setPoliza(jsonObject.get("poliza").getAsLong());
       
       
        if (jsonObject.get("desdebase").isJsonNull()) {
            aviso.setBase("");
        } else {
            JsonObject jsonObjectDesde = (JsonObject) jsonElement;
            jsonObjectDesde = jsonObject.get("desdebase").getAsJsonObject();
            
            aviso.setBase(jsonObjectDesde.get("baseoperativa").getAsString());
        }
        
    System.out.println("llego 5");
           
        
        aviso.setNombre(jsonObject.get("nombre").getAsString());
        aviso.setTelefono(jsonObject.get("telefono").getAsString());

        aviso.setDireccionLocalizacion(jsonObject.get("direccionLocalizacion").getAsString());
        aviso.setDireccionDestino(jsonObject.get("direccionDestino").getAsString());
        
 
        if (jsonObject.get("municipio_localizacion").isJsonNull()) {
            aviso.setMunicipioLocalizacion("");
        } else {
            JsonObject jsonObjectMunicipioLocalizacion = (JsonObject) jsonElement;
            jsonObjectMunicipioLocalizacion = jsonObject.get("municipio_localizacion").getAsJsonObject();
            aviso.setMunicipioLocalizacion(jsonObjectMunicipioLocalizacion.get("municipio").getAsString());
           
        }
      
         
        if (jsonObject.get("municipio_destino").isJsonNull()) {
           aviso.setMunicipioDestino("");
        } else {
            JsonObject jsonObjectMunicipioDestino = (JsonObject) jsonElement;
            jsonObjectMunicipioDestino = jsonObject.get("municipio_destino").getAsJsonObject();
            aviso.setMunicipioDestino(jsonObjectMunicipioDestino.get("municipio").getAsString());
        }
      
        if (jsonObject.get("desdeBase").isJsonNull()) {
            aviso.setIdDesdeBase(0);
            aviso.setDesdeBaseNombre("");
        } else {
            aviso.setIdDesdeBase(jsonObject.get("desdeBase").getAsInt());
            JsonObject jsonObjectDesdeBase = (JsonObject) jsonElement;
            jsonObjectDesdeBase = jsonObject.get("desdebase").getAsJsonObject();
            aviso.setDesdeBaseNombre(jsonObjectDesdeBase.get("baseoperativa").getAsString());

        }
      
        aviso.setFechaAsignacion(jsonObject.get("fechaAsignacion").getAsString());
        aviso.setFechaRealizar(jsonObject.get("fechaRealizacion").getAsString());
        aviso.setTiempoLlegada(jsonObject.get("tiempoLlegada").getAsDouble());
        aviso.setHoraSalida(jsonObject.get("horaSalida").getAsString());
        aviso.setHoraLocalizado(jsonObject.get("horaLocalizado").getAsString());
        aviso.setHoraFinalizado(jsonObject.get("horaFinalizado").getAsString());
  
        aviso.setTalon(jsonObject.get("talon").getAsInt());
       
      
        if (jsonObject.get("idNumRespuesta").isJsonNull()) {
            aviso.setIdNumRespuesta(0);
            aviso.setNumRespuesta("");
            aviso.setCodigoNumRespuesta(0);
        } else {
            aviso.setIdNumRespuesta(jsonObject.get("idNumRespuesta").getAsInt());
            JsonObject jsonObjectNumRespuesta = (JsonObject) jsonElement;
            jsonObjectNumRespuesta = jsonObject.get("numrespuesta").getAsJsonObject();
            aviso.setNumRespuesta(jsonObjectNumRespuesta.get("numRespuesta").getAsString());
            aviso.setCodigoNumRespuesta(jsonObjectNumRespuesta.get("codigo").getAsInt());

        }
        
        if (jsonObject.get("idAveria").isJsonNull()) {
            aviso.setIdAveria(0);
            aviso.setAveria("");
            aviso.setCodigoAveria(0);
        } else {
            aviso.setIdAveria(jsonObject.get("idAveria").getAsInt());
            JsonObject jsonObjectAveria = (JsonObject) jsonElement;
            jsonObjectAveria = jsonObject.get("averia").getAsJsonObject();
            aviso.setAveria(jsonObjectAveria.get("averia").getAsString());
            aviso.setCodigoAveria(jsonObjectAveria.get("codigo").getAsInt());

        }
        
    
 
        if (jsonObject.get("idSolucion").isJsonNull() ) {
             aviso.setSolucion("");
             aviso.setIdSolucion(0);
        } else {
            aviso.setIdSolucion(jsonObject.get("idSolucion").getAsInt());
            JsonObject jsonObjectSolucion = (JsonObject) jsonElement;
            jsonObjectSolucion = jsonObject.get("solucion").getAsJsonObject();
            aviso.setSolucion(jsonObjectSolucion.get("solucion").getAsString());
         }
      
          System.out.println("llega 6");
     
        if (jsonObject.get("idGrua").isJsonNull()) {
        
            aviso.setIdGrua(0);
            aviso.setGrua(0);
            aviso.setIdGruista(0);
            aviso.setConductorGrua("");
        } else {
            
            aviso.setIdGrua(jsonObject.get("idGrua").getAsInt());
            JsonObject jsonObjectGrua = (JsonObject) jsonElement;
            jsonObjectGrua = jsonObject.get("grua").getAsJsonObject();
          
            aviso.setGrua(jsonObjectGrua.get("numGrua").getAsInt());
              
            aviso.setIdGruista(jsonObjectGrua.get("idConductor").getAsInt());
           
            
        }
 
        System.out.println("llega 7");
     
        
        aviso.setKmsGrua(jsonObject.get("kmsGrua").getAsInt());
       
        if (jsonObject.get("tipotarifa").isJsonNull()) {
           aviso.setTipotarifa("");
           aviso.setIdServicioTipo(0);
      
        } else {
            JsonObject jsonObjectTipoTarifa = (JsonObject) jsonElement;
            jsonObjectTipoTarifa = jsonObject.get("tipotarifa").getAsJsonObject();
          
           aviso.setTipotarifa(jsonObjectTipoTarifa.get("tipo").getAsString());
           aviso.setIdServicioTipo(jsonObject.get("idServicioTipo").getAsInt());
        }
          System.out.println("llega 8");
     
        
        aviso.setEspera(jsonObject.get("espera").getAsInt());
        aviso.setRescate(jsonObject.get("rescate").getAsInt());

        aviso.setDiasCustodia(jsonObject.get("diasCustodia").getAsInt());
        aviso.setNf(jsonObject.get("nf").getAsString());
            // falta plus
        aviso.setPlus("");
       
       System.out.println("llega 9");
       
        //**************** importes **********************/
        aviso.setSuplemento(jsonObject.get("suplemento").getAsDouble());
        aviso.setImporteServicioEspecial(jsonObject.get("importeServicioEspecial").getAsDouble());
        aviso.setImporteServicio(jsonObject.get("importeServicio").getAsDouble());
        aviso.setIgic(jsonObject.get("igic").getAsDouble());
        aviso.setServicioEspecial(jsonObject.get("servicioEspecial").getAsDouble());
        aviso.setIgicServicioEspecial(jsonObject.get("igicServicioEspecial").getAsDouble());
        aviso.setTotal(jsonObject.get("total").getAsDouble());
        aviso.setBaseImponible(jsonObject.get("baseImponible").getAsDouble());

       System.out.println("llega 10");
        return aviso;
    }
}
