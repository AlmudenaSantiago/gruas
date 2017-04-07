
package process.CRUDClientes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import process.parser.clientes.ClienteParserJson;




public class RegistrarCliente {

   Cliente cliente;
    public RegistrarCliente(Cliente cliente) {
       this.cliente = cliente;
    }

    public String insertaCliente () throws Exception {
        ClienteParserJson clienteParser = new ClienteParserJson();
        String stringJsonClientePasarela = clienteParser.devuelveJsonDeCliente(cliente);  
        System.out.println("envio" +  stringJsonClientePasarela);
        String stringRespuestaPOST = conexionPOST(stringJsonClientePasarela);
        System.out.println("MELLEGA " + stringRespuestaPOST);
        if (stringRespuestaPOST == null) {
         JOptionPane.showMessageDialog(null,"Se ha producido un error al registrar el cliente. Es posible que ese identificador ya exista.");
        }
        return stringRespuestaPOST;

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarCliente");
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
    
 