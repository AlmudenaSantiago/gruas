
package process.CRUDTarifasPorTiempoDeRespuesta;

import process.CRUDServiciosEspeciales.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import model.serviciosEspeciales.ServicioEspecial;
import model.basesOperativas.BaseOperativa;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.serviciosEspeciales.ServicioEspecialParserJson;
import process.parser.tarifas.TarifaPorTiempoDeRespuestaParserJson;




public class RegistrarTarifaPorTiempoDeRespuesta {

   TarifaPorTiempoDeRespuesta tarifatr;
   
   
    public RegistrarTarifaPorTiempoDeRespuesta(TarifaPorTiempoDeRespuesta tar) {
       this.tarifatr = tar;
    }

    public void registrar () throws Exception {
        TarifaPorTiempoDeRespuestaParserJson parser = new TarifaPorTiempoDeRespuestaParserJson();
        String stringjson = parser.devuelveJsonDeTipoDeServicio(tarifatr);  
        System.out.println("envio" +  stringjson);
        String stringRespuestaPOST = conexionPOST(stringjson);
       
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Tarifa por tiempo registrada correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido registrar la nueva tarifa");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarTarifaPorTiempoDeRespuesta");
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
    
 