package command.tarifas;

import java.io.IOException;
import java.util.List;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaNocturno;
import process.cargador.tarifas.CargadorListaTarifas;
import process.cargador.tarifas.CargadorListaTarifasNocturno;
import process.parser.tarifas.TarifaNocturnoParserJson;
import process.parser.tarifas.TarifaParserJson;




public class CargarTarifasNocturnoCommand {

    private final CargadorListaTarifasNocturno cargadorListaTarifas;
    private final TarifaNocturnoParserJson tarifaParserJson;

    
    public CargarTarifasNocturnoCommand(CargadorListaTarifasNocturno cargadorLista,TarifaNocturnoParserJson tarifaParserJson) {
        this.cargadorListaTarifas = cargadorLista;
        this.tarifaParserJson = tarifaParserJson;
    }

    
     public List<TarifaNocturno> executeListaNocturno(Integer idCliente) throws IOException{
         
         return tarifaParserJson.parsear(cargadorListaTarifas.cargarTarifasNocturno(idCliente));
      }

  public List<TarifaNocturno> executeListaNocturnoPorTipo(Integer idCliente, Integer idServicioTipo) throws IOException{
         
         return tarifaParserJson.parsear(cargadorListaTarifas.cargarTarifasNocturnoPorTipo(idCliente, idServicioTipo));
      }



}

