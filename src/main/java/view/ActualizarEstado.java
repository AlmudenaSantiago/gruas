
package view;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import static jdk.nashorn.internal.objects.NativeJava.type;
import model.Pedido;

import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.client.methods.HttpPost;
import process.parser.PedidoDeserializer;
import process.parser.PedidoParserJson;

public class ActualizarEstado {

    Integer id;
    String estado = "Finalizado";
    
    public ActualizarEstado(Integer id) {
        this.id = id; 
    }

    public Pedido actualizarEstado () throws Exception {
        Pedido pedido = new Pedido();
        pedido.id=id;
        pedido.estado="finalizado";
        PedidoParserJson pedidoParser = new PedidoParserJson();
        String stringJsonPedidoPasarela = pedidoParser.devuelveJsonDePedido(pedido);  
        String pedidoStringDevuelta = conexionPOST(stringJsonPedidoPasarela);
        Pedido pedidoDevuelto = new PedidoParserJson().parsearPedido(pedidoStringDevuelta);
        return pedidoDevuelto;
    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://doramas.loquatsolutions.com/api/cambiarEstado");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");		
            connection.setRequestProperty("Content-Length", "" + Integer.toString(datos.getBytes().length));			
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            
            //Send request
             DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
             wr.writeBytes (datos);
             wr.flush ();
             wr.close ();
            
             //Recibir respuesta
                rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));            
                String line;
                while ((line = rd.readLine()) != null) {
                     responce += line;
                }    
               return responce;
        
        } catch (Exception e) {
            System.out.println("excepcion en la conexion");
        } 
        return null;
     
    }
}
    
 