
package process.CRUDPedidos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Pedido;

import process.parser.pedidos.PedidoParserJson;


public class EnviarPedido {

    Pedido pedido;
    String stringRespuestaPOST = "";
    
    public EnviarPedido(Pedido pedido) {
      this.pedido = pedido;
    }

    public void enviar() throws Exception {
        
        PedidoParserJson pedidoParser = new PedidoParserJson();
        String stringJsonPedidoPasarela = pedidoParser.devuelveJsonDePedido(pedido);  
   System.out.println(stringJsonPedidoPasarela);
        stringRespuestaPOST = conexionPOST(stringJsonPedidoPasarela);
        if (stringRespuestaPOST == null) {
            JOptionPane.showMessageDialog (null,"Algo ha ido mal. Revisa vendedor, cliente y productos antes de enviar");
        }
      
       System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Pedido enviado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido enviar el pedido");
    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarPedido");
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
    
 