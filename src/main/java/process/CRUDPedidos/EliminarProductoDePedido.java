
package process.CRUDPedidos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.ProductoPedido;

import process.parser.productos.ProductoPedidoParserJson;


public class EliminarProductoDePedido{

    Integer idPedido;
    Integer idProducto;
   
    
    
    public EliminarProductoDePedido(Integer idPedido, Integer idProducto) {
       this.idPedido = idPedido;
       this.idProducto = idProducto;
   
       
    }

    public void eliminar () throws Exception {
        ProductoPedido pp = new ProductoPedido();
        pp.setProducto_id(idProducto);
        pp.setPedido_id(idPedido);
        ProductoPedidoParserJson productoPedidoParser = new ProductoPedidoParserJson();
        String stringJsonProductoPedidoPasarela = productoPedidoParser.devuelveJsonDeProductoPedido(pp);  
        String stringRespuestaPOST = conexionPOST(stringJsonProductoPedidoPasarela);
        System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Producto eliminado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido eliminar el producto");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/eliminarProductoPedido");
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
    
 