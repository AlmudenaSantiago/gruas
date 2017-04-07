
package process.CRUDAvisos;

import process.CRUDBasesOperativas.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aviso;
import model.basesOperativas.BaseOperativa;
import process.parser.avisos.AvisoParserJson;
import process.parser.basesOperativas.BaseOperativaParserJson;




public class RegistrarAviso {

   Aviso aviso;
   int pdf =0 ;

    public void setPdf(int pdf) {
        this.pdf = pdf;
    }
   
   
   
    public RegistrarAviso(Aviso aviso) {
       this.aviso = aviso;
    }

    public List<Aviso> registrar () throws Exception {
        AvisoParserJson parser = new AvisoParserJson();
        String stringjson = parser.devuelveJsonDeAviso(aviso);  
        System.out.println("envio" +  stringjson);
        String stringRespuestaPOST = conexionPOST(stringjson);
        System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST == null) JOptionPane.showMessageDialog (null,"No se ha podido registrar el aviso. Ha ocurrido una excepción inesperada:  " + stringRespuestaPOST);
          return new AvisoParserJson().parsear(stringRespuestaPOST);
      

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            if (pdf == 1) {
            url = new URL("http://localhost/gruas/gruas/api/registrarAvisoPdf");
            } else {
                url = new URL("http://localhost/gruas/gruas/api/registrarAviso");
            
            }
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
        
        } catch (IOException e) {
            System.out.println(e);
        } 
        return null;
     
    }
}
    
 