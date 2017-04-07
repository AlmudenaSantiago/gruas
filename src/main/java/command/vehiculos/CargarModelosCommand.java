package command.vehiculos;

import java.io.IOException;
import java.util.List;
import model.vehiculos.Modelo;
import process.cargador.vehiculos.CargadorListaModelo;
import process.parser.vehiculos.ModeloParserJson;


public class CargarModelosCommand {

  
    private final CargadorListaModelo cargadorListaModelo;
    private final ModeloParserJson modeloParseJson;

    
    
    public CargarModelosCommand(CargadorListaModelo cargadorListaModelo,ModeloParserJson modeloParserJson) {
        this.cargadorListaModelo = cargadorListaModelo;
        this.modeloParseJson = modeloParserJson;
    }

    public List<Modelo> execute() throws IOException{
       return  modeloParseJson.parsear(cargadorListaModelo.cargar());
       
   }

   public List<Modelo> executePorIdMarcaPadre(int idMarcaPadre) throws IOException{
       System.out.println("me traigo los modelos" +  cargadorListaModelo.cargarModelosPorIdMarcaPadre(idMarcaPadre));
       return  modeloParseJson.parsear(cargadorListaModelo.cargarModelosPorIdMarcaPadre(idMarcaPadre));
       
      }
    


}