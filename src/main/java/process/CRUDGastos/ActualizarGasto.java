
package process.CRUDGastos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Gasto;

import process.parser.gastos.GastoParserJson;



public class ActualizarGasto {

    Integer id;
     String forma; 
    
    public ActualizarGasto(Integer id, String forma) {
        this.id = id; 
        this.forma = forma; 
    }

    public void actualizarEstado () throws Exception {
        Gasto gasto = new Gasto();
        gasto.id=id;
        gasto.setForma(forma);
        GastoParserJson gastoParser = new GastoParserJson();
        String stringJsonGastoPasarela = gastoParser.devuelveJsonDeGasto(gasto);  
        String stringRespuestaPOST = conexionPOST(stringJsonGastoPasarela);
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Gasto actualizado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido actualizar el gasto");
    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/doramas%282%29/doramas/api/actualizarGasto");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");		
            connection.setRequestProperty("Content-Length", "" + Integer.toString(datos.getBytes().length));
       
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            
        
            // enviamos la consulta
             DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
             wr.writeBytes (datos);
             wr.flush ();
             wr.close ();
            
             //Recibimos respuesta
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
    
 