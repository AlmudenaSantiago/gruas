/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.proveedor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.Proveedor;

/**
 *
 * @author Aarón
 */
public class ProveedorDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         Proveedor proveedor = new Proveedor();
         JsonObject jsonObject = (JsonObject)jsonElement;
     
         proveedor.setId(jsonObject.get("id").getAsInt());
         proveedor.setNombre(jsonObject.get("nombre").getAsString());
         proveedor.setNif(jsonObject.get("nif").getAsString());
         proveedor.setDomicilio(jsonObject.get("domicilio").getAsString());
         proveedor.setPoblacion(jsonObject.get("poblacion").getAsString());
         proveedor.setProvincia(jsonObject.get("provincia").getAsString());
         proveedor.setPais(jsonObject.get("pais").getAsString());
         proveedor.setCp(jsonObject.get("cp").getAsString());
         proveedor.setTelefono1(jsonObject.get("telefono1").getAsString());
         proveedor.setTelefono2(jsonObject.get("telefono2").getAsString());
         proveedor.setCorreo(jsonObject.get("correo").getAsString());
         return proveedor;
    }
}