
package process.CRUD.contactos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Contacto;
import process.parser.contactos.ContactoParserJson;



public class InsertarContacto {

    Contacto  c;
    public InsertarContacto(Contacto c) {
       this.c = c;
    }

    public void inserta () throws Exception {
        ContactoParserJson contactoParser = new ContactoParserJson();
        String stringJsonPasarela = contactoParser.devuelveJsonDeContacto(c);
        System.out.println(stringJsonPasarela);
        String stringRespuestaPOST = conexionPOST(stringJsonPasarela);
        System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST == null)  JOptionPane.showMessageDialog (null,"Ha ocurrido un error al registrar el contacto");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarContacto");
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
    
 