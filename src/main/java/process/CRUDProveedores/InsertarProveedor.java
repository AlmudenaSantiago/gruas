/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDProveedores;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;

import model.Proveedor;
import process.parser.clientes.ClienteParserJson;
import process.parser.proveedor.ProveedorParserJson;

/**
 *
 * @author Aarón
 */
public class InsertarProveedor {
    
   Proveedor proveedor;
    public InsertarProveedor(Proveedor proveedor) {
       this.proveedor = proveedor;
    }

    public void insertaProveedor () throws Exception {
        String stringJsonClientePasarela = new ProveedorParserJson().devuelveJsonDeProveedor(proveedor);  
        String stringRespuestaPOST = conexionPOST(stringJsonClientePasarela);
      
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Proveedor insertado correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido insertar el producto");
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/registrarProveedor");
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
    
 
