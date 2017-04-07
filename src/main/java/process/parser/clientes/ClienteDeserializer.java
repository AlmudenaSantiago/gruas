package process.parser.clientes;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.Cliente;


public class ClienteDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

       
        Cliente cliente = new Cliente();
        JsonObject jsonObject = (JsonObject)jsonElement;
        cliente.setId(jsonObject.get("id").getAsInt());
        cliente.setGrupo(jsonObject.get("grupo").getAsString());
        cliente.setTipoCliente(jsonObject.get("tipoCliente").getAsString());
       
        cliente.setImpuesto(jsonObject.get("impuesto").getAsInt());
      
        
        cliente.setNombre(jsonObject.get("nombre").getAsString());

        cliente.setNif(jsonObject.get("nif").getAsString());
        cliente.setDomicilio(jsonObject.get("domicilio").getAsString());
        cliente.setPoblacion(jsonObject.get("poblacion").getAsString());
        cliente.setProvincia(jsonObject.get("provincia").getAsString());
        cliente.setCp(jsonObject.get("cp").getAsString());
        cliente.setPais(jsonObject.get("pais").getAsString());
      
        cliente.setTelefono1(jsonObject.get("telefono1").getAsString());
        cliente.setTelefono2(jsonObject.get("telefono2").getAsString());
        cliente.setTelefonoAdmon(jsonObject.get("telefonoAdmon").getAsString());
        cliente.setTelefonoConsulta(jsonObject.get("telefonoConsultas").getAsString());
        cliente.setTelefonoExtra(jsonObject.get("telefonoExtra").getAsString());
        cliente.setTelefonoAsistencia(jsonObject.get("telefonoAsistencia").getAsString());
        
        cliente.setFax(jsonObject.get("fax").getAsString());
        cliente.setTelefonoExtra(jsonObject.get("telefonoExtra").getAsString());
        cliente.setCorreo(jsonObject.get("correo").getAsString());
        System.out.println("deserializo el co==>>>" + jsonObject.get("correo").getAsString() );
        cliente.setCorreoAvisos(jsonObject.get("correoAvisos").getAsString());
       //  cliente.setCorreoAdmon(jsonObject.get("correoAdmon").getAsString());
        
        cliente.setEmailAdmon(jsonObject.get("emailAdmon").getAsString());
        cliente.setEmailAsistencia(jsonObject.get("emailAsistencia").getAsString());
        cliente.setEmailConsultas(jsonObject.get("emailConsultas").getAsString());
        cliente.setEmailExtra(jsonObject.get("emailExtra").getAsString());
     
        
        
        
        
        cliente.setFechaBaja(jsonObject.get("fechaBaja").getAsString());
        cliente.setFechaAlta(jsonObject.get("fechaAlta").getAsString());
        cliente.setFechaEntregaFra(jsonObject.get("fechaEntregaFra").getAsString());
        cliente.setFechaCobro(jsonObject.get("fechaCobro").getAsString());
       
        cliente.setFormaCobro(jsonObject.get("formaCobro").getAsString());
        cliente.setTipoFacturacion(jsonObject.get("tipoFacturacion").getAsInt());
        cliente.setClienteSinIGIC(jsonObject.get("clienteSinIGIC").getAsString());
         cliente.setNumProveedor(jsonObject.get("numProveedor").getAsString());
      
        cliente.setEstado(jsonObject.get("estado").getAsInt());
        cliente.setMinimoRetraso(jsonObject.get("minimoRetraso").getAsInt());
        cliente.setTelefonoExtra(jsonObject.get("telefonoExtra").getAsString());
        cliente.setImprimeKm(jsonObject.get("imprimeKm").getAsString());
        cliente.setContacto(jsonObject.get("contacto").getAsString());
        cliente.setPuestoContacto(jsonObject.get("puestoContacto").getAsString());
        cliente.setNumInfFinalizado(jsonObject.get("numInfFinalizado").getAsInt());
        cliente.setObservaciones(jsonObject.get("observaciones").getAsString());
        
        cliente.setImagen(jsonObject.get("imagen").getAsString());
        
        
        cliente.setRadioUrbano(jsonObject.get("radioUrbano").getAsInt());
        cliente.setIdNocturno(jsonObject.get("idNocturno").getAsInt());
        cliente.setNumDiasCoberturaEnBase(jsonObject.get("numDiasCoberturaEnBase").getAsInt());
        cliente.setMuestraSuplemento(jsonObject.get("muestraSuplemento").getAsInt());
        
        cliente.setPorcentajeSuplementoNocturno(jsonObject.get("porcentajeSuplementoNocturno").getAsDouble());
        
        
        
        cliente.setIdFestivo1(jsonObject.get("idFestivo1").getAsInt());
        cliente.setIdFestivo2(jsonObject.get("idFestivo2").getAsInt());
        
        
        
        
      // cliente.setCantidadPendiente(jsonObject.get("cantidadTotalPendiente").getAsDouble());
      //  cliente.setCantidadAbonada(jsonObject.get("cantidadTotalAbonada").getAsDouble());
         
      
        
        return cliente;
    }
}
