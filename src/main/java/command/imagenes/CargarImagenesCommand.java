/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.imagenes;

import command.contactos.*;
import java.util.List;
import model.Contacto;
import model.Imagen;
import process.cargador.contactos.CargadorListaContacto;
import process.cargador.imagenes.CargadorListaImagenes;
import process.parser.contactos.ContactoParserJson;
import process.parser.imagenes.ImagenParserJson;


public class CargarImagenesCommand {
    
    private final CargadorListaImagenes cargadorLista;
    private final ImagenParserJson parserJson;
    
    
    public CargarImagenesCommand(CargadorListaImagenes cargadorListaImagen,ImagenParserJson imagenParserJson) {
        this.cargadorLista = cargadorListaImagen;
        this.parserJson = imagenParserJson;
    }

   
 /*    public List<Contacto> executeLista() throws Exception{
        return parserJson.parsear(cargadorLista.cargar());
      }
    
   */ 
     public List<Imagen> executeListaImagenesAviso(Integer idAviso) throws Exception{
          
         return parserJson.parsear(cargadorLista.cargarImagenesAviso(idAviso));
      }
    
     
     
     
    
}
