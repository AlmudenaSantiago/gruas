
package command.tarifas;

import java.io.IOException;
import java.util.List;
import model.Tarifas.TipoDeTarifa;
import model.TipoDeServicio;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;


public class CargarTiposDeTarifaCommand {
 
    private final CargadorListaTiposDeTarifa cargadorListaTiposDeTarifa;
    private final TipoDeTarifaParserJson tipoDeServicioParserJson;

    
    public CargarTiposDeTarifaCommand(CargadorListaTiposDeTarifa cargadorLista,TipoDeTarifaParserJson tarifaParserJson) {
        this.cargadorListaTiposDeTarifa = cargadorLista;
        this.tipoDeServicioParserJson = tarifaParserJson;
    }

 
     
     public List<TipoDeTarifa> executeLista() throws IOException{
       
         return tipoDeServicioParserJson.parsear(cargadorListaTiposDeTarifa.cargar());
      }

   
    
    
}
