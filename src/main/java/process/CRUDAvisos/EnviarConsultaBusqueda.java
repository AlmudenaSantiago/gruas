/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDAvisos;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import model.Aviso;
import process.CRUDProductos.ConexionPost;
import process.parser.avisos.AvisoParserJson;

/**
 *
 * @author gruasjoseantonio
 */
public class EnviarConsultaBusqueda {
    
    URL url;
     Aviso aviso;
    public EnviarConsultaBusqueda(Aviso aviso) {
         
        this.aviso = aviso;
    }
     
     
     
     
      public List<Aviso> enviar () throws IOException {
         url = new URL("http://localhost/gruas/gruas/api/busquedaAvisos");
         return enviarModificacion();
            
       
    }
    
  
    
     public List<Aviso> enviarModificacion() throws IOException {
        System.out.println("envio" + new AvisoParserJson().devuelveJsonDeAviso(aviso));
        
        String stringRespuestaPOST = new ConexionPost(new AvisoParserJson().devuelveJsonDeAviso(aviso),url).conectar();
        System.out.println("string respuesta post" + stringRespuestaPOST);
        return new AvisoParserJson().parsear1(stringRespuestaPOST);
        
       
  
    }
    
    
}
