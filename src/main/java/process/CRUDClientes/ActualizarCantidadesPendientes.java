/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDClientes;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Cliente;
import process.cargador.CargadorTotales;
import process.parser.clientes.ClienteParserJson;



public class ActualizarCantidadesPendientes {

    Integer id;
    public ActualizarCantidadesPendientes(Integer id) {
       this.id = id;
    }

    public void actualizar() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setCantidadPendiente(Double.parseDouble(CargadorTotales.getInstance().cargarTotalCantidadPendiente(cliente.getId())));
        cliente.setCantidadAbonada(Double.parseDouble(CargadorTotales.getInstance().cargarTotalCantidadAbonada(cliente.getId())));
      
        String stringJsonClientePasarela = new ClienteParserJson().devuelveJsonDeCliente(cliente);  
        System.out.println("la cadena a enviar es: " + stringJsonClientePasarela);
      
        String stringRespuestaPOST = conexionPOST(stringJsonClientePasarela);
        System.out.println("la cadena RECIBIBA es: " + stringRespuestaPOST); 
      
        

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/actualizarCantidadesCliente");
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

    
 