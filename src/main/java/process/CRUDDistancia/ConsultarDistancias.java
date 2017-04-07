
package process.CRUDDistancia;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Distancia;
import process.parser.distancias.DistanciaParserJson;




public class ConsultarDistancias {

   Distancia distancia;

    public ConsultarDistancias(Distancia distancia) {
        this.distancia = distancia;
    }
   
 
   
   
   
    public Distancia consultar () throws Exception {
        DistanciaParserJson parser = new DistanciaParserJson();
        String stringjson = parser.devuelveJsonDeDistancia(distancia);  
        System.out.println("envio" +  stringjson);
        String stringRespuestaPOST = conexionPOST(stringjson);
        Distancia distanciaCero = new Distancia();
        distanciaCero.setKms(0.0);
        if (stringRespuestaPOST.equals("0")) return distanciaCero;
        if (stringRespuestaPOST == null)   JOptionPane.showMessageDialog (null,"No se ha podido realizar la consulta de distancias. Ha ocurrido un error inesperado: " + stringRespuestaPOST );
         
        return parser.parsear(stringRespuestaPOST).get(0);
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/consultarDistancia");
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
    
 