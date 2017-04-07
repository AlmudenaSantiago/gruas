package command.tarifas;

import java.io.IOException;
import java.util.List;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.cargador.tarifas.CargadorListaTarifas;
import process.parser.tarifas.TarifaPorTiempoDeRespuestaParserJson;
import view.tarifas.porTiempoDeRespuesta.JTablaTarifasPorTiempoRespuesta;




public class CargarTarifasPorTiempoDeRespuestaCommand {

    private final CargadorListaTarifas cargadorListaTarifas;
    private final TarifaPorTiempoDeRespuestaParserJson tarifaParserJson;

    
    public CargarTarifasPorTiempoDeRespuestaCommand(CargadorListaTarifas cargadorLista,TarifaPorTiempoDeRespuestaParserJson tarifaParserJson) {
        this.cargadorListaTarifas = cargadorLista;
        this.tarifaParserJson = tarifaParserJson;
    }

    
    public void execute(JTablaTarifasPorTiempoRespuesta tablaTarifas, Integer idCliente) throws IOException{
       tablaTarifas.mostrarTarifas(tarifaParserJson.parsear(cargadorListaTarifas.cargarTarifasPorTiempoDeRespuesta(idCliente)));
    }
    
    
     public List<TarifaPorTiempoDeRespuesta> executeLista(Integer idCliente) throws IOException{
         
         return tarifaParserJson.parsear(cargadorListaTarifas.cargarTarifasPorTiempoDeRespuesta(idCliente));
      }

  
    
    
    

}

