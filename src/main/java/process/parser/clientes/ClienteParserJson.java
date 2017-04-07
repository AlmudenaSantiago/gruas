package process.parser.clientes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionProductoParserJson;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;


public class ClienteParserJson {

    public List<Cliente> parsear(String json) throws Exception {
        try {
            return getGson().fromJson(json, new TypeToken<List<Cliente>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new Exception();
        }
    }

    
  
 public List<Cliente> parsearObservaciones(String json) throws Exception {
        try {
            return getGsonClienteObservaciones().fromJson(json, new TypeToken<List<Cliente>>() {}.getType());
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new Exception();
        }
    }

      private Gson getGsonClienteObservaciones() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Cliente.class, new ClienteObservacionesGeneralesDeserializer());
        return gsonBuilder.create();
    }
 
     
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Cliente.class, new ClienteDeserializer());
        return gsonBuilder.create();
    }
    
       public String devuelveJsonDeCliente(Cliente cliente) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(cliente);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionProductoParserJson(); 
        }
    }

}






