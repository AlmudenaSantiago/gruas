
package process.CRUDModelos;

import process.CRUDModelos.*;
import process.CRUDBasesOperativas.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.vehiculos.Modelo;
import process.parser.vehiculos.ModeloParserJson;




public class EliminarModelo {

   Modelo m;
   
   
    public EliminarModelo(Modelo m) {
       this.m = m;
    }

    public void eliminar () throws Exception {
        ModeloParserJson parser = new ModeloParserJson();
        String stringjson = parser.devuelveJsonDeProveedor(m);  
        String stringRespuestaPOST = conexionPOST(stringjson);
        if (stringRespuestaPOST == null)  JOptionPane.showMessageDialog (null,"No se ha podido registrar la base operativa. Ha ocurrido una excepción inesperada:  " + stringRespuestaPOST);
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/eliminarModelo");
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
    
 