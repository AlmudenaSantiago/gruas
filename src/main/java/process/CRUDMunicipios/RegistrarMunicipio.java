
package process.CRUDMunicipios;

import process.CRUDTiposDeTarifa.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Municipio;
import model.Tarifas.TipoDeTarifa;
import process.parser.municipios.MunicipioParserJson;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;




public class RegistrarMunicipio {

   Municipio municipio;
   
   
    public RegistrarMunicipio(Municipio tipo) {
       this.municipio= tipo;
    }

    public void registrar () throws Exception {
        MunicipioParserJson parser = new MunicipioParserJson();
        String stringjson = parser.devuelveJsonDeMunicipio(municipio);  
        System.out.println("envio" +  stringjson);
        String stringRespuestaPOST = conexionPOST(stringjson);
       
        if (!stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"No se ha podido registrar la base operativa");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarMunicipio");
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
    
 