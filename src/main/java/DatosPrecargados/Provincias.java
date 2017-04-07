/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;

import java.util.ArrayList;
import java.util.List;
import model.Provincia;
import process.cargador.CargadorListaProvincias;
import process.parser.poblaciones.ProvinciaParserJson;

/**
 *
 * @author loquat
 */
public class Provincias {
      List<Provincia> listaProvincias = new ArrayList<>();
  
      
       public List<Provincia> getProvinciaPorId(String id) throws Exception {
         return  new ProvinciaParserJson().parsear(new CargadorListaProvincias().cargarProvinciaPorId(id));
    }
    
}
