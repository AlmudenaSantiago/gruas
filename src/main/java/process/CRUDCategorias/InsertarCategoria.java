
package process.CRUDCategorias;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Categoria;
import process.parser.categorias.CategoriaParserJson;




public class InsertarCategoria {

   Categoria categoria;
   URL url;
   
    public InsertarCategoria(Categoria categoria) {
       this.categoria= categoria;
    }

    public void insertaCategoria (int nivel) throws Exception {
        CategoriaParserJson categoriaParser = new CategoriaParserJson();
        String stringJsonCategoriaPasarela = categoriaParser.devuelveJsonDeCategoria(categoria);  
        String stringRespuestaPOST = conexionPOST(stringJsonCategoriaPasarela, new URL("http://localhost/gruas/gruas/api/registrarCategoria" + nivel));
        System.out.println(stringRespuestaPOST);
        if (stringRespuestaPOST.equals("ok"))  JOptionPane.showMessageDialog (null,"Categoria insertada correctamente");
        else JOptionPane.showMessageDialog (null,"No se ha podido insertar el categoria");
        

    }
    
  
    
     private String conexionPOST(String datos, URL url) {
        String responce = "";     
        BufferedReader rd = null;
       
        HttpURLConnection connection = null;
        try {
            // creamos la conexion
            this.url = url;
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
    
 