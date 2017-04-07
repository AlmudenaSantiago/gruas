/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDTarifas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Producto;
import model.Tarifas.TipoDeTarifa;
import model.Tarifas.TipoDeTarifaAuxConClienteID;
import process.parser.productos.ProductoParserJson;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;

/**
 *
 * @author loquat
 */
public class AsociarTipoDeTarifaACliente {
    
    
    TipoDeTarifaAuxConClienteID tipoDeTarifa;
    
    public AsociarTipoDeTarifaACliente(TipoDeTarifa tipoDeTarifa1, Integer idCliente) {
       tipoDeTarifa = new TipoDeTarifaAuxConClienteID();
       tipoDeTarifa.setId(tipoDeTarifa1.getId());
       tipoDeTarifa.setTipo(tipoDeTarifa1.getTipo());
       tipoDeTarifa.setIdCliente(idCliente);
       
       
       
    }

    public void asociar () throws Exception {
        TipoDeTarifaParserJson parser = new TipoDeTarifaParserJson();
        String stringJsonPasarela = parser.devuelveJsonDeTipoDeTarifaAuxConClienteID(tipoDeTarifa);
        System.out.println(stringJsonPasarela);
        String stringRespuestaPOST = conexionPOST(stringJsonPasarela);
      
        if (!stringRespuestaPOST.equals("ok"))   {
               JOptionPane.showMessageDialog (null,"No se ha podido asociar");
        
        }
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/asociarTipoDeTarifaACliente");
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
