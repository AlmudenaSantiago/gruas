package process.parser.gastos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.ExceptionPedidoParserJson;
import exception.ExceptionProductoParserJson;

import java.util.List;
import javax.swing.JOptionPane;
import model.Gasto;
import model.Pedido;



public class GastoParserJson {

   
    public List<Gasto> parsear(String json){
        try{
            GastoRespuestaJson gastoRespuestaJson = new Gson().fromJson(json, GastoRespuestaJson.class);
            return gastoRespuestaJson.getGastos();
        }
        catch(Exception exception){
            JOptionPane.showMessageDialog(null, "No hay gastos en esas fechas");
            throw new ExceptionPedidoParserJson();
        }
    }
    public String devuelveJsonDeGasto(Gasto gasto) {    
       try {  
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create(); 
            String json = gson.toJson(gasto);
            return json;
       }  catch(Exception exception) {
           System.out.println("Excepcion al transformar a json"); 
           throw new ExceptionPedidoParserJson(); 
        }
    }
    
    public Gasto parsearGasto(String json) {
        try {
            return getGsonGasto().fromJson(json, new TypeToken<Pedido>() {}.getType());
        }
        catch (Exception exception){
            throw new ExceptionProductoParserJson();
        }
    }

       private Gson getGsonGasto() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Pedido.class, new GastoDeserializer());
        return gsonBuilder.create();
    }

}
