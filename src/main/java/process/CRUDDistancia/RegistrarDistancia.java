
package process.CRUDDistancia;

import process.CRUDGruas.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Distancia;
import model.gruas.Grua;
import process.parser.distancias.DistanciaParserJson;
import process.parser.gruas.GruasParserJson;




public class RegistrarDistancia {

   Distancia distancia;
   
   
    public RegistrarDistancia(Distancia g) {
       this.distancia= g;
    }

    public void insertar () throws Exception {
        DistanciaParserJson parser = new DistanciaParserJson();
        String stringjson = parser.devuelveJsonDeDistancia(distancia);  
        String stringRespuestaPOST = conexionPOST(stringjson);
       System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST == null)  JOptionPane.showMessageDialog (null,"No se ha podido registrar la distancia. Ha ocurrido un error inesperado: " + stringRespuestaPOST);
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarDistancia");
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
    
 