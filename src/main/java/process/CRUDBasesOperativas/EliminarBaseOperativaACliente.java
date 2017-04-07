/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDBasesOperativas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.basesOperativas.BaseOperativa;
import model.basesOperativas.BaseOperativaAuxConClienteId;
import process.parser.basesOperativas.BaseOperativaParserJson;
import view.fichaCliente.SingletonCliente;

/**
 *
 * @author loquat
 */
public class EliminarBaseOperativaACliente {
    
    
    BaseOperativaAuxConClienteId baseOperativa;
    
    public EliminarBaseOperativaACliente(BaseOperativa base) {
       baseOperativa = new BaseOperativaAuxConClienteId();
       baseOperativa.setBaseOperativa(base.getBaseOperativa());
       baseOperativa.setId(base.getId());
       baseOperativa.setIdCliente(SingletonCliente.getInstance().getCliente().getId());
       
       
    }

    public void eliminar () throws Exception {
        BaseOperativaParserJson parser = new BaseOperativaParserJson();
        String stringJsonPasarela = parser.devuelveJsonDeBaseOperativaAuxConClienteId(baseOperativa);
        System.out.println("envio ELIMINAR"  + stringJsonPasarela);
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
            url = new URL("http://localhost/gruas/gruas/api/eliminarBaseOperativaACliente");
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
