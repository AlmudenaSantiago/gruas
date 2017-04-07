
package process.CRUDClientes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Cliente;
import process.parser.clientes.ClienteParserJson;




public class ModificarClienteBack {

   Cliente cliente;
    public ModificarClienteBack(Cliente cliente) {
       this.cliente = cliente;
    }

    public void insertaCliente () throws Exception {
        ClienteParserJson clienteParser = new ClienteParserJson();
        String stringJsonClientePasarela = clienteParser.devuelveJsonDeCliente(cliente);  
        String stringRespuestaPOST = conexionPOST(stringJsonClientePasarela);
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Cliente modificado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido modificar el cliente");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/modificarCliente");
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
    
 