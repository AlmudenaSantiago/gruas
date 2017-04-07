
package command.servicios;

import java.io.IOException;
import java.util.List;
import model.TipoDeServicio;
import process.cargador.tiposDeServicio.CargadorListaTiposDeServicio;
import process.parser.tipoDeServicio.TipoDeServicioParserJson;


public class CargarTiposDeServicioCommand {
 
    private final CargadorListaTiposDeServicio cargadorListaTiposDeServicio;
    private final TipoDeServicioParserJson tipoDeServicioParserJson;

    
    public CargarTiposDeServicioCommand(CargadorListaTiposDeServicio cargadorLista,TipoDeServicioParserJson servicioParserJson) {
        this.cargadorListaTiposDeServicio = cargadorLista;
        this.tipoDeServicioParserJson = servicioParserJson;
    }

 
     
     public List<TipoDeServicio> executeLista() throws IOException{
       
         return tipoDeServicioParserJson.parsear(cargadorListaTiposDeServicio.cargar());
      }

   
    
    
}
