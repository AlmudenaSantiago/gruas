
package DatosPrecargados;

import java.util.ArrayList;
import java.util.List;
import model.Poblacion;
import process.cargador.CargadorListaPoblaciones;
import process.parser.poblaciones.PoblacionParserJson;


public class Poblaciones {
    
    List<Poblacion> listaPoblaciones = new ArrayList<>();
    
    public Poblaciones ()  {
    
    } 
    
    
    public List<Poblacion> getPoblacionPorCp(String cp) throws Exception {
         return  new PoblacionParserJson().parsear(new CargadorListaPoblaciones().cargarPoblacionPorCp(cp));
    }
    
    public void inicializarTodasPoblaciones() throws Exception {
      listaPoblaciones = new PoblacionParserJson().parsear(new CargadorListaPoblaciones().cargar());
    
    }
    public List<Poblacion> getListaPoblaciones() {
        return listaPoblaciones;
    }
    
    
   
    
    
    
    
}
