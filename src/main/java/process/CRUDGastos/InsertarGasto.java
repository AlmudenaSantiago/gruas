
package process.CRUDGastos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Gasto;

import process.parser.gastos.GastoParserJson;


public class InsertarGasto {

    Gasto gasto;
    public InsertarGasto(Gasto gasto) {
       this.gasto = gasto;
    }

    public void insertaGasto () throws Exception {
        GastoParserJson gastoParser = new GastoParserJson();
        String stringJsonGastoPasarela = gastoParser.devuelveJsonDeGasto(gasto);
        System.out.println(stringJsonGastoPasarela);
        String stringRespuestaPOST = conexionPOST(stringJsonGastoPasarela);
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Gasto insertado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido insertar el producto");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarGasto");
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
    
 