/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.contactos;

import java.util.List;
import model.Contacto;
import process.cargador.contactos.CargadorListaContacto;
import process.parser.contactos.ContactoParserJson;

/**
 *
 * @author gruasjoseantonio
 */
public class CargarContactosCommand {
    
    private final CargadorListaContacto cargadorListaContacto;
    private final ContactoParserJson contactoParserJson;
    
    
    public CargarContactosCommand(CargadorListaContacto cargadorListaContacto,ContactoParserJson contactoParserJson) {
        this.cargadorListaContacto = cargadorListaContacto;
        this.contactoParserJson = contactoParserJson;
    }

   
   
     public List<Contacto> executeLista() throws Exception{
        return contactoParserJson.parsear(cargadorListaContacto.cargar());
      }
    
    
     public List<Contacto> executeListaPorCliente(Integer idCliente) throws Exception{
          System.out.println("lista" + cargadorListaContacto.cargarContactosPorCliente(idCliente));
         return contactoParserJson.parsear(cargadorListaContacto.cargarContactosPorCliente(idCliente));
      }
    
     
     
     
    
}
