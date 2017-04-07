package process.parser.productos;

import com.google.gson.*;
import java.lang.reflect.Type;

import model.Producto;

public class ProductoDeserializer implements JsonDeserializer {
    
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Producto producto = new Producto();
        JsonObject jsonObject = (JsonObject)jsonElement;
        
        producto.setId(jsonObject.get("id").getAsInt());
        producto.setNombre(jsonObject.get("nombre").getAsString());
        producto.setCategoria1_nombre(jsonObject.get("categoria1").getAsString());
        producto.setCategoria2_nombre(jsonObject.get("categoria2").getAsString());
        producto.setCategoria3_nombre(jsonObject.get("categoria3").getAsString());
        producto.setCategoria4_nombre(jsonObject.get("categoria4").getAsString());
        producto.setCategoria1_id(jsonObject.get("categoria1_id").getAsInt());
        producto.setCategoria2_id(jsonObject.get("categoria2_id").getAsInt());
        producto.setCategoria3_id(jsonObject.get("categoria3_id").getAsInt());
        producto.setCategoria4_id(jsonObject.get("categoria4_id").getAsInt());
        producto.setColor(jsonObject.get("color").getAsString());
        producto.setDescripcion(jsonObject.get("descripcion").getAsString());
        producto.setStock(jsonObject.get("stock").getAsDouble());
        producto.setPrecio(jsonObject.get("precio").getAsDouble()); 
        producto.setPrecioCompra(jsonObject.get("precioCompra").getAsDouble());
        producto.setProveedor(jsonObject.get("proveedor").getAsString());
        producto.setIdProveedor(jsonObject.get("idProveedor").getAsInt());
        producto.setImagen(jsonObject.get("imagen").getAsString());
        return producto;
    }
}
