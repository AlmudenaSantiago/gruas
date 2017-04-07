/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDTarifasPorTiempoDeRespuesta;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.parser.tarifas.TarifaPorTiempoDeRespuestaParserJson;


public class EliminarTarifaPorTiempoDeRespuesta {

    TarifaPorTiempoDeRespuesta tarifa;
    public EliminarTarifaPorTiempoDeRespuesta(TarifaPorTiempoDeRespuesta t) {
       this.tarifa=  t;
    }

    public void eliminar () throws Exception {
        String stringJsonPedidoPasarela = new TarifaPorTiempoDeRespuestaParserJson().devuelveJsonDeTipoDeServicio(tarifa);  
        String stringRespuestaPOST = conexionPOST(stringJsonPedidoPasarela);
        if (stringRespuestaPOST == null) JOptionPane.showMessageDialog (null,"No se ha podido eliminar la tarifa. Ha ocurrido un error inesperado:"  +  stringRespuestaPOST);
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/eliminarTarifaPorTiempoDeRespuesta");
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

    
 