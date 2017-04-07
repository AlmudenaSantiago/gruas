
package process.CRUDAvisos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aviso;
import model.Imagen;
import process.parser.avisos.AvisoParserJson;
import process.parser.imagenes.ImagenParserJson;




public class EliminarImagenAviso {

   Imagen imagen;
   
   
    public EliminarImagenAviso(Imagen imagen) {
       this.imagen = imagen;
    }

    public List<Aviso> eliminar () throws Exception {
        ImagenParserJson parser = new ImagenParserJson();
        String stringjson = parser.devuelveJsonDeImagen(imagen);  
        System.out.println("envio" +  stringjson);
        String stringRespuestaPOST = conexionPOST(stringjson);
       
        if (stringRespuestaPOST == null) JOptionPane.showMessageDialog (null,"No se han podido eliminar las imagenes. Ha ocurrido una excepción inesperada:  " + stringRespuestaPOST);
          return new AvisoParserJson().parsear(stringRespuestaPOST);
      

    }
    
     private String conexionPOST(String datos) {
        String responce = "";     
        BufferedReader rd = null;
        URL url;
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            url = new URL("http://localhost/gruas/gruas/api/eliminarImagenAviso");
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
        
        } catch (IOException e) {
            System.out.println(e);
        } 
        return null;
     
    }
}
    
 