package command.tarifas;

import java.io.IOException;
import java.util.List;
import model.Tarifas.Tarifa;
import process.cargador.tarifas.CargadorListaTarifas;
import process.parser.tarifas.TarifaParserJson;




public class CargarTarifasCommand {

    private final CargadorListaTarifas cargadorListaTarifas;
    private final TarifaParserJson tarifaParserJson;

    
    public CargarTarifasCommand(CargadorListaTarifas cargadorLista,TarifaParserJson tarifaParserJson) {
        this.cargadorListaTarifas = cargadorLista;
        this.tarifaParserJson = tarifaParserJson;
    }

      public List<Tarifa> executeListaPorTipo(Integer idCliente, Integer idServicioTipo) throws IOException{
         
         return tarifaParserJson.parsear(cargadorListaTarifas.cargarTarifasPorTipo(idCliente, idServicioTipo));
      }

  
    
     public List<Tarifa> executeLista(Integer idCliente) throws IOException{
         
         return tarifaParserJson.parsear(cargadorListaTarifas.cargarTarifas(idCliente));
      }

    
  


}

