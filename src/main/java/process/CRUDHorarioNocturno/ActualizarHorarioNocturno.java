
package process.CRUDHorarioNocturno;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.HorarioNocturno;
import process.parser.diasFestivos.NocturnoParserJson;



public class ActualizarHorarioNocturno {
    


    HorarioNocturno nocturno;
    
    
    public ActualizarHorarioNocturno(HorarioNocturno nocturno) {
       this.nocturno = nocturno;
    }

    public void actualizar () throws Exception {
        NocturnoParserJson nocturnop = new NocturnoParserJson();
        String stringPasarela = nocturnop.devuelveJsonNocutrno(nocturno);
        String stringRespuestaPOST = conexionPOST(stringPasarela);
        
        if (stringRespuestaPOST == null) JOptionPane.showMessageDialog (null,"Ha ocurrido algun error al actualizar el horario");    
    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            
            // NO ESTA HECHO EN EL SERVIDOR
            url = new URL("http://localhost/gruas/gruas/api/actualizarNocturno");
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
    
 

