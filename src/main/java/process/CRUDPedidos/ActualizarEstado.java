
package process.CRUDPedidos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import model.Pedido;

import process.parser.pedidos.PedidoParserJson;

public class ActualizarEstado {

    Pedido pedidoPasarela;
    String estado = "finalizado";
    
    public ActualizarEstado(Pedido pedidoPasarela) {
      this.pedidoPasarela = pedidoPasarela;
      pedidoPasarela.estado="finalizado";
    }

    public Pedido actualizarEstado () throws Exception {
        PedidoParserJson pedidoParser = new PedidoParserJson();
        
        String stringJsonPedidoPasarela = pedidoParser.devuelveJsonDePedido(pedidoPasarela);  
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
            url = new URL("http://localhost/gruas/gruas/api/cambiarEstado");
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
    
 