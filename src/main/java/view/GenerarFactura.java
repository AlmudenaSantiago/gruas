/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import model.Pedido;
import model.PedidoPasarelaActualizadorEstado;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.client.methods.HttpPost;
import process.parser.PedidoParserJson;

public class GenerarFactura {

    Integer id;
    String estado = "Finalizado";
    
    public GenerarFactura(Integer id) {
        this.id = id; 
    }

    public void actualizarEstado () throws Exception {
        Pedido pedido = new Pedido();
        pedido.id=id;
        pedido.estado="finalizado";
        //PedidoPasarelaActualizadorEstado pedidoPasarela = new PedidoPasarelaActualizadorEstado(id,estado);      
        PedidoParserJson pedidoParser = new PedidoParserJson();
        String stringJsonPedidoPasarela = pedidoParser.devuelveJsonDePedido(pedido);
        //PedidoParserJson pedidoParser = new PedidoParserJson();
        //String stringJsonPedidoPasarela = pedidoParser.devuelveJsonDePedidoPasarela(pedidoPasarela);
        System.out.println("la cadena json enviada es " + stringJsonPedidoPasarela);
        conexionPOST(stringJsonPedidoPasarela);
    }
    
     private static void conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://doramas.loquatsolutions.com/api/cambiarEstado");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");		
            connection.setRequestProperty("Content-Length", "" + Integer.toString(datos.getBytes().length));			
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            System.out.println("La conexion creada es " + connection);
            
            
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
                System.out.println(responce);
        
        } catch (Exception e) {
            System.out.println("excepcion en la conexion");
        } 

        }

    }
    
    

