
package process.CRUDVehiculos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import model.vehiculos.Vehiculo;
import process.parser.vehiculos.VehiculoParserJson;



public class InsertarVehiculo {

    Vehiculo  v;
    public InsertarVehiculo(Vehiculo v) {
       this.v= v;
    }

    public List<Vehiculo> inserta () throws Exception {
        VehiculoParserJson parser = new VehiculoParserJson();
        String stringJsonPasarela = parser.devuelveJsonDeVehiculo(v);
        System.out.println(stringJsonPasarela);
        String stringRespuestaPOST = conexionPOST(stringJsonPasarela);
        System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST == null)  JOptionPane.showMessageDialog (null,"Ha ocurrido un error al registrar el vehiculo");
        return parser.parsear(stringRespuestaPOST);

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarVehiculo");
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
    
 