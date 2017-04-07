
package process.CRUDRecorridosyKm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.RecorridosyKm;
import process.parser.recorridosykm.RecorridosyKmParserJson;
import process.parser.serviciosEspeciales.ServicioEspecialParserJson;




public class RegistrarRecorridosyKm {

   RecorridosyKm recorridos;
   
   
    public RegistrarRecorridosyKm(RecorridosyKm r) {
       this.recorridos = r;
    }

    public void registrar () throws Exception {
        RecorridosyKmParserJson parser = new RecorridosyKmParserJson();
        String stringjson = parser.devuelveJsonDeMunicipio(recorridos);  
        System.out.println("envio" +  stringjson);
        String stringRespuestaPOST = conexionPOST(stringjson);
       
        if (!stringRespuestaPOST.equals("ok"))   JOptionPane.showMessageDialog (null,"No se ha podido registrar el servicio especial");
        
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarRecorridosYKm");
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
    
 