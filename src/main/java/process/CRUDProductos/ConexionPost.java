/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDProductos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Aaron
 */
public class ConexionPost {
 
    URL url;
    String datos;
    
    public ConexionPost(String datos, URL url){

            this.url = url;
            this.datos = datos;
    }
    
    public String conectar() throws IOException {
        
        String responce = "";     
        BufferedReader rd = null;
       
        HttpURLConnection connection = null;
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
      //  connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(datos.getBytes().length));			
        connection.setUseCaches (false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
            
           
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
       
    }
}
    
    
    

