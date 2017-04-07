
package process.CRUDPedidos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Pedido;

import process.parser.pedidos.PedidoParserJson;


public class EliminarPedido {

    Integer id;
    public EliminarPedido(Integer id) {
       this.id = id;
    }

    public void eliminar () throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        PedidoParserJson pedidoParser = new PedidoParserJson();
        String stringJsonPedidoPasarela = pedidoParser.devuelveJsonDePedido(pedido);  
        String stringRespuestaPOST = conexionPOST(stringJsonPedidoPasarela);
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Pedido eliminado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido eliminar el pedido");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/eliminarPedido");
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
            System.out.println(e);
        } 
        return null;
     
    }
}
    
 