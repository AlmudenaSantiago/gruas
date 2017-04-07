
package process.CRUDServiciosEspeciales;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.serviciosEspeciales.ServicioEspecial;
import model.basesOperativas.BaseOperativa;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.serviciosEspeciales.ServicioEspecialParserJson;




public class RegistrarServicioEspecial {

   ServicioEspecial ser;
   
   
    public RegistrarServicioEspecial(ServicioEspecial ser) {
       this.ser = ser;
    }

    public void registrar () throws Exception {
        ServicioEspecialParserJson parser = new ServicioEspecialParserJson();
        String stringjson = parser.devuelveJsonDeServicioEspecial(ser);  
        System.out.println("envio" +  stringjson);
        String stringRespuestaPOST = conexionPOST(stringjson);
       
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Servicio especial registrado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido registrar el servicio especial");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarServicioEspecial");
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
    
 